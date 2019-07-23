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
    private WriteAction Printer;
    private boolean FightRequest;
    private String prize;
    private GuiToConsoleController Bridge;

    public Fight (BasicHero basichero, WriteAction printer, GuiToConsoleController bridge){
        basicHero = basichero;
        Printer = printer;
        FightRequest = false;
        Bridge = bridge;
    }
    
    ///input 
    private String getmoves(){
        if (!FightRequest)
            Printer.OutputplayTextln("You stumbled accross a monster do you want to fight it (yes/no) : ");
        if (FightRequest)
            Printer.OutputplayTextln(" do you fight (yes/no)");
		return new ReadConsole().that();
    }

    public boolean UserChoose(){
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

        if (basicHero.EnumToStringHeroEfacsEnum().equals("Attack")){
            if (basicHero.EnumToStringHeroClass().equals("Defense") && chooseop.nextInt(10000) < 4500)
                return true;
            if (chooseop.nextInt(10000) < 2000)
                return true;
        }
        if (basicHero.EnumToStringHeroClass().equals("Defense") && chooseop.nextInt(10000) < 4000)
            return true;
        if (chooseop.nextInt(10000) < 1500)
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

    private void EfacsYouHave(String NewEfacs){
        String OldEfacs = basicHero.EnumToStringHeroEfacsEnum();
        if (OldEfacs.equals(NewEfacs)){
            Printer.OutputplayTextln("you already have this so what do you want to do (yes/no) : ");
        }
        else if (OldEfacs.equals("HitPoints")){
            Printer.OutputplayTextln("you have an XP booster do you want to take this? (yes/no) : ");
        }
        else if (OldEfacs.equals("Attack")){
            Printer.OutputplayTextln("you have a Sword do you want to take this? (yes/no) : ");
        }
        else if (OldEfacs.equals("Defense")){
            Printer.OutputplayTextln("you have an Shield do you want to take this? (yes/no) : ");
        }
        else if (OldEfacs.equals("none")){
            Printer.OutputplayTextln("you have nothing just take it (yes/no) : ");
        }
    }

    public int GiveThemanefacs(String state){
            //String HoldString = new ReadConsole().that();
            if (state.equals("yes")){
                System.out.println("given");
                basicHero.stringsetHeroEfacs(prize);}
            else if (state.equals("no"))
                return 1;
        return 1;
    }

    public int DoTheyGetAnArtefacs(){
        Random addspice = new Random();
        int chance = new Random(addspice.nextInt(1000)).nextInt(10000);

        if (chance < 3000){
            if (chance < 1000){
                Printer.OutputplayTextln("The monster droped a sheild");
                prize = "Defense";
            }
            else if (chance > 2000){
                Printer.OutputplayTextln("The monster droped a sword you will win more fights with this");
                prize = "Attack";
            }
            else {
                ///output
                Printer.OutputplayTextln("The monster drop a thing that give you more hit points dont worry what it is");
                prize = "HitPoints";
            }
            EfacsYouHave(prize);
            Bridge.setTX(false).setchoose(true);
            return 1;
        }
        return 2;
    }
    private void TheyWon(){
        int XP = basicHero.getXP();
        if (basicHero.EnumToStringHeroEfacsEnum().equals("HitPoints"))
        XP += 50;
        if (basicHero.EnumToStringHeroClass().equals("Attack"))
        basicHero.setXP(XP + 250);
        else if (basicHero.EnumToStringHeroClass().equals("Defense"))
        basicHero.setXP(XP + 100);
        basicHero.setMap(basicHero.getNewMap());

    }

    private void UpdateMap(){
	basicHero.setMap(basicHero.getNewMap());
	basicHero.setMyCoords(basicHero.getMyNewCoords());
    }

    private void RestMap(){
        basicHero.setNewMap(basicHero.getMap());
        basicHero.setMyNewCoords(basicHero.getMyCoords());
    }

    private boolean getDefenceLuck(){
        Boolean state = basicHero.EnumToStringHeroEfacsEnum().equals("Defense");
        Random addspice = new Random();
        int chance = new Random(addspice.nextInt(1000)).nextInt(10000);
        if (state && chance < 5000)
            return true;
        return false;
    }

    private boolean getrunLuck(){
        Random addspice = new Random();
        int chance = new Random(addspice.nextInt(1000)).nextInt(10000);
        if (chance < 5000)
            return true;
        return false;
    }

    public int SetUpFight(){
                if (!basicHero.getFight()){
         	UpdateMap();
                System.out.println("\nskip ");
		return 4;
	}
                Bridge.setTX(false).setchoose(true);
                System.out.println("\nnext ");
                Printer.OutputplayTextln("You stumbled accross a monster do you want to fight it (yes/no) : ");
         return 1;
    }
    
    public int EngageFight(boolean choice){

    //if (basicHero.EnumToStringHeroEfacsEnum().equals("Defense"))
//        boolean choice = UserChoose();
        if (choice && DoILetThemWin()){
            TheyWon();
            basicHero.setFight(false);
            Printer.OutputplayTextln("you bitch slapped it to death");
            return 1;
        }else if (!choice){
            if (getrunLuck()){
                RestMap();
                basicHero.setFight(false);
                basicHero.setAtWall(false);
                FightRequest = false;
                return 3;
            }
            else{
                //output
                Bridge.setTX(false).setchoose(true);
                Printer.OutputplayTextln("The monster does not take no for an answer");
                FightRequest = true;
                return 0;
            }
        }
        else {
            if (getDefenceLuck()){
                RestMap();
                Printer.OutputplayText("just as the monster rasies hes axe to kill you, you put up your sheild and block the fatal bow and get away ");
                basicHero.setFight(false);
                basicHero.setAtWall(false);
                FightRequest = false;
                return 3;
            }
            ///output
                Printer.OutputplayTextln("you were kill brutaly but the gods of valhala accept your secrifice");
                System.exit(0);
        }
        return 0;
    }

}
