package com.example.fooder;

import java.io.Serializable;

public class User implements Serializable
{
    // LOGIN
    private String LoginName;
    private String LoginPassword;

    // REGISTER
    private String RegisterName;
    private String RegisterEmail;
    private String RegisterUserName;
    private String RegisterPassword;
    private String RegisterPhone;

    User() // DEFAULT CONSTRUCTOR
    {
        // LOGIN
        this.LoginName = "invalid";
        this.LoginPassword = "invalid";

        // REGISTER
        this.RegisterName = "invalid";
        this.RegisterEmail = "invalid";
        this.RegisterUserName = "invalid";
        this.RegisterPassword = "invalid";
        this.RegisterPhone = "invalid";
    }

    // SETTERS
    public void setLoginName(String LoginName)
    {
        this.LoginName = LoginName;
    }

    public void setLoginPassword(String LoginPassword)
    {
        this.LoginPassword = LoginPassword;
    }

    public void setRegisterName(String registerName) {
        this.RegisterName = registerName;
    }

    public void setRegisterEmail(String registerEmail) {
        this.RegisterEmail = registerEmail;
    }

    public void setRegisterUserName(String registerUserName) {
        this.RegisterUserName = registerUserName;
    }

    public void setRegisterPassword(String registerPassword) {
        this.RegisterPassword = registerPassword;
    }

    public void setRegisterPhone(String registerPhone) {
        this.RegisterPhone = registerPhone;
    }


    // GETTERS
    public String getRegisterName() {
        return RegisterName;
    }

    public String getRegisterEmail() {
        return RegisterEmail;
    }

    public String getRegisterUserName() {
        return RegisterUserName;
    }

    public String getRegisterPassword() {
        return RegisterPassword;
    }

    public String getRegisterPhone() {
        return RegisterPhone;
    }

    public String getLoginName()
    {
        return LoginName;
    }

    public String getLoginPassword()
    {
        return LoginPassword;
    }

}

