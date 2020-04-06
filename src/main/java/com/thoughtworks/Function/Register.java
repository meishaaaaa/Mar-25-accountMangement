package com.thoughtworks.Function;

import com.thoughtworks.InputException.InputException;
import com.thoughtworks.Repository.User;
import com.thoughtworks.Repository.UserRepository;

//功能类，核验输入的信息并注册
public class Register {
    private String message = "";

    public boolean register(String[] input) {
        User user = new User();
        try {
            user.setAccount(input[0]);
        } catch (InputException e) {
            e.getMessage();
        }

        try {
            user.setMobile(input[1]);
        } catch (InputException e) {
            e.getMessage();
        }

        try {
            user.setEmail(input[2]);
        } catch (InputException e) {
            e.getMessage();
        }

        try {
            user.setPassWord(input[3]);
        } catch (InputException e) {
            e.getMessage();
        }

        if (!UserRepository.isExist(user.getAccount())) {
            UserRepository.save(user);
            return true;
        } else {
            this.message = "该用户已存在请重新输入";
            return false;
        }
    }

    public String getMessage() {
        return message;
    }
}

