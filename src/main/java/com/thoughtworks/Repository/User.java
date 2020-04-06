package com.thoughtworks.Repository;


import com.thoughtworks.InputException.InputException;

public class User {
    private String account;
    private String mobile;
    private String email;
    private String passWord;


    public User() {

    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        if (!account.matches("[a-zA-Z0-9_]{2,10}")) {
            throw new InputException("用户名");
        }
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        if (!mobile.matches("^1\\d{10}$")) {
            throw new InputException("手机号");
        }
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (!email.contains("@")) {
            throw new InputException("邮箱");
        }
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        if (!passWord.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            throw new InputException("密码");
        }
        this.passWord = passWord;
    }


}

