package com.thoughtworks.Function;



import com.thoughtworks.InputException.InputException;

import java.util.Scanner;

public class Tools {
    public static String[] parseInput(String input, int parseCount) {
        String[] dataSplit = input.split(",");
        if (dataSplit.length != parseCount) {
            throw new InputException("格式");
        }
        return dataSplit;
    }

    public static String getScanner(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }
}
