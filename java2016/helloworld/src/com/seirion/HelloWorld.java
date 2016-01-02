package com.seirion;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.print("Hello World");

        outerLoop:
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                if (row == 1 && col == 3) {
                    continue outerLoop;
                }
                System.out.println("(" + row + ", " + col + ")");
            }
        }
    }
}
