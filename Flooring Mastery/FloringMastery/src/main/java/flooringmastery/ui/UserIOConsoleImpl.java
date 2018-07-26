/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Y
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public void printDouble(double prompt) {
        System.out.println(prompt);
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt + "  " + min + "  " + max);
        Scanner sc = new Scanner(System.in);
        double input = sc.nextDouble();
        return input;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        double input = 0;
        try {
            String stringInput = sc.nextLine().trim();
            input = Double.parseDouble(stringInput);
        } catch (Exception e) {
            System.out.println("Enter a valid number: ");
            readDoubleEdit(prompt);
        }
        return input;
    }

    @Override
    public double readDoubleEdit(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        double input = 0;
        try {
            String stringInput = sc.nextLine().trim();
            if (stringInput.equalsIgnoreCase("")) {
                return 0;
            }
            input = Double.parseDouble(stringInput);
        } catch (Exception e) {
            System.out.println("Enter a valid number: ");
            readDoubleEdit(prompt);
        }
        return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        float input = sc.nextFloat();
        return input;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt + "  " + min + "  " + max);
        Scanner sc = new Scanner(System.in);
        float input = sc.nextFloat();
        return input;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        System.out.println(prompt + "  " + min + "  " + max);
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        return input;
    }

    @Override
    public long readLong(String prompt) {

        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        long input = sc.nextLong();
        return input;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt + "  " + min + "  " + max);
        Scanner sc = new Scanner(System.in);
        long input = sc.nextLong();
        return input;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String Id = sc.nextLine();
        return Id.trim();
    }

    @Override
    public String readStringDate(String prompt) {
        System.out.println(prompt);
        String Id = sc.nextLine();
        return Id;
    }

    @Override
    public String readCustomerName(String prompt) {
        System.out.println(prompt);
        String Id = sc.nextLine();

        if (Id.contains(",")) {
            Id = Id.replace(",", "/");
        }
        return Id.trim();
    }

    @Override
    public void printCustomerName(String prompt) {
        if (prompt.contains("/")) {
            prompt = prompt.replace("/", ",");
        }
        System.out.println(prompt);
    }

    @Override
    public String readDateReturnString(String prompt) {
        System.out.println(prompt);
        String date = sc.nextLine();

        if (!date.equalsIgnoreCase("")) {
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
                date = localDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
            } catch (Exception e) {
                System.out.println("Enter Date in MMddyyyy fomate");
                readDateReturnString(prompt);
            }
            return date;
        }
        return date;
    }

}
