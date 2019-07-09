// package swingy;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//************************************************************************************************
//************************************************************************************************
//                                      main thing
//************************************************************************************************
//************************************************************************************************


class ReadConsole2 {

    public void that() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Enter something : ");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }

            System.out.println("input : " + input);
            System.out.println("-----------\n");
        }

        scanner.close();

    }

}




//************************************************************************************************
//************************************************************************************************
//                                      main thing
//************************************************************************************************
//************************************************************************************************


public class Main {

    public static void main(String[] args){
        GenMap map = new GenMap();
        char [][]maps = map.makemap(1);
        for (char [] j : maps){
            System.out.println("");
            for (char i : j)
                System.out.print(i);
        }
        ReadConsole2 hold = new ReadConsole2();
        hold.that();

    }

}
