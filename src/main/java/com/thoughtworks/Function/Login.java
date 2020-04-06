package com.thoughtworks.Function;

import com.thoughtworks.Repository.User;
import com.thoughtworks.Repository.UserRepository;

//功能类，核心功能是当账号存在时，提取账号对应的所有信息
public class Login {

    private String message = "";


    public User loginInfo(String id) {
        if (checkExist(id) && !checkLooked(id)) {
            return UserRepository.getInfo(id);
        } else return null;
    }

    private boolean checkExist(String id) {
        if (UserRepository.isExist(id)) {
            this.message = "该用户名不存在，请重新输入：";
            return true;
        } else return false;
    }

    private boolean checkLooked(String id) {
        if (UserRepository.isLocked(id)) {
            this.message = "该账户已被锁定，请重新输入：";
            return true;
        } else return false;
    }

    public String getMessage() {
        return message;
    }

}
