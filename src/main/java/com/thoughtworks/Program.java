package com.thoughtworks;

import com.thoughtworks.Function.Login;
import com.thoughtworks.Function.Register;
import com.thoughtworks.Function.Tools;
import com.thoughtworks.InputException.InputException;
import com.thoughtworks.Repository.User;
import com.thoughtworks.Repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class Program {


    public void start() {
        //一开始就存储被取出的用户
        Map<String, Integer> accountList = new HashMap<>();


        while (true) {
            String menu = "1.注册\n" + "2.登陆\n" + "3.退出\n" + "请输入你的选择（1-3）";
            System.out.println(menu);
            String input = Tools.getScanner();

            switch (input) {
                case "1":
                    try {
                        register();
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        login(accountList);
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    System.exit(0);

                default:
                    System.out.println("输入错误，请重新输入：");
                    break;
            }
        }
    }


    public void register() {
        while (true) {
            String register = "请输入注册信息(格式：用户名,手机号,邮箱,密码)：";
            System.out.println(register);
            String registerInfo = Tools.getScanner();

            int registerInputLimit = 4;
            String[] input = Tools.parseInput(registerInfo, registerInputLimit);
            Register registerAccount = new Register();
            if (registerAccount.register(input)) {
                break;
            } else {
                System.out.println(registerAccount.getMessage());
            }

        }
    }

    public void login(Map<String, Integer> accountList) {
        while (true) {
            String login = "请输入用户名和密码(格式：用户名,密码)：";
            System.out.println(login);
            String loginInfo = Tools.getScanner();

            Login loginAccount = new Login();

            int loginInputLimit = 2;
            String id = Tools.parseInput(loginInfo, loginInputLimit)[0];
            String password = Tools.parseInput(loginInfo, loginInputLimit)[1];

            User correctInfo = loginAccount.loginInfo(id);

            if (correctInfo == null) {
                System.out.println(loginAccount.getMessage());
                break;
            } else {
                if (!accountList.containsKey(id)) {
                    accountList.put(correctInfo.getAccount(), 0);
                }

                String correctPassword = correctInfo.getPassWord();

                if (accountList.get(id) < 3) {
                    if (password.equals(correctPassword)) {
                        correctInfo(accountList, id, correctInfo);
                        break;
                    } else {
                        wrongInputInfo(accountList, id);
                    }
                    break;
                }
                if (accountList.get(id) == 3) {
                    lockedInfo(id);
                    break;
                }
            }
        }
    }

    private void lockedInfo(String id) {
        UserRepository.lockAccount(id);
        System.out.println("您已3次输错密码，账号被锁定");
    }

    private void wrongInputInfo(Map<String, Integer> accountList, String id) {
        System.out.println("输入错误，请输入正确的账号密码：");
        accountList.put(id, accountList.get(id) + 1);
    }

    private void correctInfo(Map<String, Integer> accountList, String id, User correctInfo) {
        System.out.printf("%s，欢迎回来！您的手机号是%s，邮箱是%s\n", id, correctInfo.getMobile(), correctInfo.getEmail());
        accountList.replace(id, 0);
    }
}


