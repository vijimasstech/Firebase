package com.example.masstechfour.crazytasty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

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
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
    //private Toolbar toolbar;
    private EditText username1, password2;
    private Button buttonlogin;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        username1 = (EditText) findViewById ( R.id.username1 );
        password2 = (EditText) findViewById ( R.id.password2 );
        buttonlogin = (Button) findViewById ( R.id.buttonlogin );
        databaseReference = FirebaseDatabase.getInstance ().getReference ( "Usres" );
        firebaseAuth = FirebaseAuth.getInstance ();

        buttonlogin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                login ();
            }
        } );
    }

    private void login() {
        final String username = username1.getText ().toString ().trim ();
        final String password = password2.getText ().toString ().trim ();
        final String id = databaseReference.push ().getKey ();
        Artist artist=new Artist ( id, username,password );
        databaseReference.child ( id ).setValue ( artist );

         if (username.equals ( "Crazy" ) && password.equals ( "Tasty" )) {
            Intent i2 = new Intent ( LoginActivity.this, Admin.class );
            startActivity ( i2);
        }else if (username.equals ( artist.getUsername () ) && password.equals ( artist.getPassword () )){
             Toast.makeText ( getApplicationContext (), "Login successfully...",Toast.LENGTH_LONG ).show ();
         }
        else {
            Toast.makeText ( getApplicationContext (), "Username and Password is incorrect", Toast.LENGTH_LONG ).show ();
            }

    }
}
//        Artist artist = new Artist ( username,password  );
//        if (username.equals ( "Crazy" ) && password.equals ( "Tasty" )){
//            Intent intent1 = new Intent ( LoginActivity.this, Admin.class );
//            startActivity ( intent1 );
//            }
//            else{
//            Toast.makeText ( getApplicationContext (), "Username and Password is incorrect", Toast.LENGTH_LONG ).show ();
//            }





//                        else if (artist.getUsername ().equals ( username1) && artist.getPassword ().equals ( password2 )){
//                            Intent intent2 = new Intent ( LoginActivity.this, UserLogin.class );
//                            startActivity ( intent2 );
//                        }





//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        } );





//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // dataSnapshot is the "issue" node with all children with id 0
//
//                    for (DataSnapshot user : dataSnapshot.getChildren()) {
//                        // do something with the individual "issues"
//                        UsersBean usersBean = user.getValue(UsersBean.class);
//
//                        if (usersBean.password.equals(txvPassword.getText().toString().trim())) {
//                            Intent intent = new Intent(context, MainActivity.class);
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(context, "Password is wrong", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        final String username=username1.getText ().toString ().trim ();
//        final String password=password2.getText ().toString ().trim ();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference("Users"); //users is a node in your Firebase Database.
//        Artist artist = new Artist (username, password); //ObjectClass for Users
//        databaseReference.push().setValue(artist);
//

//final String Username = username1.getText ().toString ().trim ();
//                final String Password = password2.getText ().toString ().trim ();
//                final Artist artist = new Artist ( Username, Password );
//                if (Username.equals ( "Crazy" ) && Password.equals ( "Tasty" )) {
//                    Intent intent = new Intent ( getApplicationContext (), Admin.class );
//                    startActivity ( intent );
//                }else {
//
//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//                    if (Username.equals ( "Crazy" ) && Password.equals ( "Tasty" )) {
//                        Intent intent = new Intent ( getApplicationContext (), Admin.class );
//                        startActivity ( intent );
//                    ref.child("Users").child("username").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if(dataSnapshot.exists()){
//
//                                // use "username" already exists
//                            } else {
//                                // "username" does not exist yet.
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
