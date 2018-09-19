package com.example.masstechfour.crazytasty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText name, usern, pass, pass1, email, phone;
    private Button reg;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );

        databaseReference = FirebaseDatabase.getInstance ().getReference ("Users" );
        firebaseAuth = FirebaseAuth.getInstance ();


        name = (EditText) findViewById ( R.id.name );
        usern = (EditText) findViewById ( R.id.usern );
        pass = (EditText) findViewById ( R.id.pass );
        pass1 = (EditText) findViewById ( R.id.pass1 );
        email = (EditText) findViewById ( R.id.email );
        phone = (EditText) findViewById ( R.id.phone );
        reg = (Button) findViewById ( R.id.reg );

        reg.setOnClickListener ( this );
    }

    private void registerUser() {

        final String fullname = name.getText ().toString ().trim ();
        final String username = usern.getText ().toString ().trim ();
        final String password = pass.getText ().toString ().trim ();
        final String password1 = pass1.getText ().toString ().trim ();
        final String emailid = email.getText ().toString ().trim ();
        final String phoneno = phone.getText ().toString ().trim ();

        if (TextUtils.isEmpty ( fullname )) {

            name.setError ( "Name is required" );
            return;
        }
        if (TextUtils.isEmpty ( username )) {
            usern.setError ( "UserName is required" );
            return;
        }
        if (username.length () < 6) {
            usern.setError ( "UserName should be 6 Chars" );
            return;
        }
        if (TextUtils.isEmpty ( password )) {
            pass.setError ( "Password is required" );
            return;
        }
        if (TextUtils.isEmpty ( password1 )) {
            pass1.setError ( "Comfirm Password is required" );
            return;
        }
        if (!password.equals ( password1 )) {
            Toast.makeText ( getApplicationContext (), "Password not match", Toast.LENGTH_LONG ).show ();
            return;
        }
        if (TextUtils.isEmpty ( emailid )) {
            email.setError ( "Email id is required" );
            return;
        }
        if (!isValidEmail ( emailid )) {
            //emailEditText.setError("Invalid Email");
            Toast.makeText ( getApplicationContext (), "Invalid Email", Toast.LENGTH_LONG ).show ();
            return;
        }
        if (TextUtils.isEmpty ( phoneno )) {
            phone.setError ( "Phone no is required" );
            return;
        }
        if (phoneno.length () <= 9 || phoneno.length () >= 11) {

            Toast.makeText ( getApplicationContext (), "Phone No 10 Char long", Toast.LENGTH_LONG ).show ();
            return;
        }


        Toast.makeText ( getApplicationContext (), "Account Created Succesfully", Toast.LENGTH_SHORT ).show ();
        firebaseAuth.signInAnonymously ().addOnCompleteListener ( new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful ()) {
                    String id = databaseReference.push ().getKey ();
                    Artist artist = new Artist ( id, fullname, username, password, password1, emailid, phoneno );
                    databaseReference.child ( id ).setValue ( artist );
                } else {
                    Log.d("firebaseLog", "failure");
                    task.getException ().printStackTrace ();
                }
            }
        } );
    }

    @Override
    public void onClick(View view) {
        if (view == reg) {
            registerUser ();
        }
    }

    private boolean isValidEmail(String EmailID) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile ( EMAIL_PATTERN );
        Matcher matcher = pattern.matcher ( (CharSequence) EmailID );
        return matcher.matches ();
    }
}