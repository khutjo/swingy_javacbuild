package swingy_home;

// package swingy;

import java.util.Scanner;



//************************************************************************************************
//************************************************************************************************
//                                      console input getter
//************************************************************************************************
//************************************************************************************************


class ReadConsole {

    public String that() {

        Scanner scanner = new Scanner(System.in);


            String input = scanner.nextLine();
        // scanner.close();
        return input;
    }

}
