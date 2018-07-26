/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.ui;

/**
 *
 * @author Y
 */
public interface UserIO {

    void print(String prompt);

    String readCustomerName(String prompt);

    void printDouble(double prompt);

    public double readDouble(String prompt);

    double readDouble(String promp, double min, double max);

    public double readDoubleEdit(String prompt);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    String readStringDate(String prompt);

    public void printCustomerName(String prompt);

    public String readDateReturnString(String prompt);

}
