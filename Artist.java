package com.example.masstechfour.crazytasty;

import java.util.Collection;
import java.util.Collections;

public class Artist {
    String id;
    String fullname;
    String username;
    String password;
    String password1;
    String emailid;
    String phoneno;


    public Artist(String id, String username, String password) {

    }

    public Artist(String id, String fullname, String username, String password, String password1, String emailid, String phoneno) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.password1 = password1;
        this.emailid = emailid;
        this.phoneno = phoneno;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword1() {
        return password1;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhoneno() {
        return phoneno;
    }


}
