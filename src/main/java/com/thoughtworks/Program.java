package com.thoughtworks;

import java.util.Objects;
import java.util.Scanner;

public class Program {

    public Program() {
    }

    public void start() {
        System.out.println("1.注册\n" + "2.登陆\n" + "3.退出\n" + "请输入你的选择（1-3）");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (Objects.equals(input, "1")) {
            System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
            Scanner sc1 = new Scanner(System.in);
            String registerInfo = sc1.nextLine();
            try {
                register(registerInfo);
            } catch (InputException e) {
                System.out.println("格式错误\n" +
                        "请按正确格式输入注册信息：");
            }
            start();
        } else if (Objects.equals(input, "2")) {
            System.out.println("请输入用户名和密码(格式：用户名,密码)：");
            Scanner sc2 = new Scanner(System.in);
            String loginInfo = sc2.nextLine();
            try {
                login(loginInfo);
            } catch (InputException e) {
                System.out.println("格式错误\n" +
                        "请按正确格式输入用户名和密码：");
            }
            start();
        } else if (Objects.equals(input, "3")) {
            System.out.println("退出成功");
        } else {
            System.out.println("格式错误\n" +
                    "请按正确格式输入注册信息：");
            start();
        }

    }

    public void register(String info) {
        if (!info.contains(",")) {
            throw new InputException();
        }

        String[] dataSplit = info.split(",");
        if (dataSplit.length != 4) {
            throw new InputException();
        }

        UserInfo user = new UserInfo();

        try {
            user.setAccount(dataSplit[0]);
        } catch (InputException e) {
            System.out.println("用户名不合法\n" +
                    "请输入合法的注册信息：");
        }

        try {
            user.setMobile(dataSplit[1]);
        } catch (InputException e) {
            System.out.println("手机号不合法\n" +
                    "请输入合法的注册信息：");
        }

        try {
            user.setEmail(dataSplit[2]);
        } catch (InputException e) {
            System.out.println("邮箱不合法\n" +
                    "请输入合法的注册信息：");
        }

        try {
            user.setPassWord(dataSplit[3]);
        } catch (InputException e) {
            System.out.println("密码不合法\n" +
                    "请输入合法的注册信息：");
        }

        UserRepository repository = new UserRepository();
        repository.save(user);
    }

    public void login(String info) {
        if (!info.contains(",")) {
            throw new InputException();
        }

        String[] dataSplit = info.split(",");
        if (dataSplit.length != 2) {
            throw new InputException();
        }

        String id = dataSplit[0];
        UserRepository repository = new UserRepository();
        String rightPassword = repository.getPassword(id);

        if (Objects.equals(rightPassword, dataSplit[1])) {
            UserInfo user = repository.getInfo(id);
            System.out.printf("%s，欢迎回来！\n" +
                    "您的手机号是%s，邮箱是%s", id, user.getMobile(), user.getEmail());
        } else {
            System.out.println("密码或用户名错误\n" +
                    "请重新输入用户名和密码：");
            Scanner sc2 = new Scanner(System.in);
            String loginInfo = sc2.nextLine();
            login(loginInfo);
        }

    }

}


