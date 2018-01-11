package com.classes.login;

public class LoginCommand {

    private String emailId;
    private String password;

    public LoginCommand(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public LoginCommand() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}