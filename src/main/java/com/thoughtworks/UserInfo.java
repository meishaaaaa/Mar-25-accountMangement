package com.thoughtworks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo {
    private String account;
    private String mobile;
    private String email;
    private String passWord;

    public UserInfo() {

    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]{2,10}");
        Matcher m = p.matcher(account);

        if (!m.matches()) {
            throw new InputException();
        }
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobile);

        if (!m.matches()) {
            throw new InputException();
        }
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (!email.contains("@")) {
            throw new InputException();
        }
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
        Matcher m = p.matcher(passWord);

        if (!m.matches()) {
            throw new InputException();
        }
        this.passWord = passWord;
    }


}
