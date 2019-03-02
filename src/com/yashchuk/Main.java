package com.yashchuk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your expression");

        String line = scanner.nextLine();

        String[] operations = line.replaceAll("\\s?", "") // удаляем пробелы
                .split("((?<=[+\\-*/])|(?=[+\\-*/]))"); // создаем массив из операндов и операций [5.5, *, 23]

        double result = 0;
        NumeralsHolder nh = null;
        for (int i = 0; i < operations.length - 2; i += 2) {
            String firstOperand;
            if (i > 0) {
                firstOperand = String.valueOf(result);
            } else {
                firstOperand = operations[0];
            }

            String operation = operations[i + 1];
            String secondOperand = operations[i + 2];

            if (nh == null) {
                nh = NumeralsHolder.determineDigitType(firstOperand);
            }

            result = doMath(nh.getAbsolute(firstOperand), nh.getAbsolute(secondOperand), operation);
        }

        System.out.println(result);
    }

    private static double doMath(double f, double s, String op) {
        switch (op) {
            case "+":
                return f + s;
            case "-":
                return f - s;
            case "*":
                return f * s;
            case "/":
                return f / s;
            default:
                return 0;
        }
    }
}
