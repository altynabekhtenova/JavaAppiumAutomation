package org.example;

public class MainClass {
    private int class_number = 20;
    private String class_string = "Hello, world";

    public String getClassString() {
        return class_string;
    }

    public int getClassNumber() {
        return class_number;
    }

    public static int getLocalNumber() {
        return 14;
    }
}