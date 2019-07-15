// package swingy;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;



//************************************************************************************************
//************************************************************************************************
//                                      fight ligic     
//************************************************************************************************
//************************************************************************************************

public class Fight {
    private BasicHero basicHero;

    public Fight (BasicHero basichero){
        basicHero = basichero;
    }
    
    ///input 
    private String getmoves(){
        System.out.println("Fight monster (yes/no) : ");
		return new ReadConsole().that();
    }

    private boolean UserChoose(){
        String userinput = getmoves();
        if (userinput.equals("yes"))
            return true;
        else if (userinput.equals("no"))
            return false;
        else
            return UserChoose();
    }

    private boolean GetYourLuck(){
        Random addspice = new Random();
        Random chooseop = new Random(addspice.nextInt(1000));

        if (basicHero.EnumToStringHeroClass().equals("Defense") && chooseop.nextInt(10000) < 8000)
            return true;
        if (chooseop.nextInt(10000) < 4000)
            return true;
        else return false;
    }

    private boolean DoILetThemWin(){

        if (basicHero.getEnemyClass() == 'A')
            return true;
        else if (basicHero.getEnemyClass() == 'C')
            return false;
        else if (basicHero.getEnemyClass() == 'B' && GetYourLuck())
            return true;
        return false;
    }

    private void TheyWon(){
        int XP = basicHero.getXP();
        if (basicHero.EnumToStringHeroClass().equals("Attack"))
            basicHero.setXP(XP + 250);
        else if (basicHero.EnumToStringHeroClass().equals("Defense"))
            basicHero.setXP(XP + 100);
        basicHero.setMap(basicHero.getNewMap());
    }

    public void EngageFight(){
        if (!basicHero.getFight())
            return ;

        boolean choice = UserChoose();

        if (choice && DoILetThemWin()){
            TheyWon();
            basicHero.setFight(false);
            return ;
        }else if (!choice){
            if (GetYourLuck()){
                basicHero.setFight(false);
                return ;
                }
            else{
                //output
                System.out.println("The monster does not let you leave");
                EngageFight();
                return ;
            }
        }
        else {
            ///output
            System.out.println("you were kill brutaly but the gods of valhala acept your secrifice");
            System.exit(0);
        }
        return ;
    }

}