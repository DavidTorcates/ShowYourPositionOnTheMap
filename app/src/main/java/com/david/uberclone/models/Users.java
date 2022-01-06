package com.david.uberclone.models;

public class Users {

    String id;
    String mail;
    String password;

    public void User(){

    }

    public void User(String id, String password, String mail){
        this.id = id;
        this.password = password;
        this.mail = mail;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }
}
