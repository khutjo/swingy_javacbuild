// package swingy;

// import java.util.Scanner;


// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
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

    public Fight (BasicHero basichero, WriteAction printer){
        basicHero = basichero;
        Printer = printer;
        FightRequest = false;
    }
    
    ///input 
    private String getmoves(){
        if (!FightRequest)
            Printer.OutputplayTextln("You stumbled accross a monster do you want to fight it (yes/no) : ");
        if (FightRequest)
            Printer.OutputplayTextln(" do you fight (yes/no)");
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

    private void GiveThemanefacs(String NewEfacs){
            EfacsYouHave(NewEfacs);
            String HoldString = new ReadConsole().that();
            if (HoldString.equals("yes"))
                basicHero.stringsetHeroEfacs(NewEfacs);
            else if (HoldString.equals("no"))
                return ;
            else
                GiveThemanefacs(NewEfacs);
        
    }

    private void DoesHeGetAnArtefacs(){
        Random addspice = new Random();
        int chance = new Random(addspice.nextInt(1000)).nextInt(10000);

        if (chance < 3000){
            if (chance < 1000){
                Printer.OutputplayTextln("The monster droped a sheild");
                GiveThemanefacs("Defense");
            }
            else if (chance > 2000){
                Printer.OutputplayTextln("The monster droped a sword you will win more fights with this");
                GiveThemanefacs("Attack");
            }
            else {
                ///output
                Printer.OutputplayTextln("The monster drop a thing that give you more hit points dont worry what it is");
                GiveThemanefacs("HitPoints");
            }
        }


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
        DoesHeGetAnArtefacs();
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


    public void EngageFight(){
        if (!basicHero.getFight()){
            Printer.OutputplayTextln("coast clear");
         	UpdateMap();
		return ;
	}
    //if (basicHero.EnumToStringHeroEfacsEnum().equals("Defense"))
        boolean choice = UserChoose();

        if (choice && DoILetThemWin()){
            TheyWon();
            basicHero.setFight(false);
            return ;
        }else if (!choice){
            if (getrunLuck()){
                RestMap();
                basicHero.setFight(false);
                basicHero.setAtWall(false);
                FightRequest = false;
                return ;
            }
            else{
                //output
                Printer.OutputplayTextln("The monster does not take no for an answer");
                FightRequest = true;
                EngageFight();
                return ;
            }
        }
        else {
            if (getDefenceLuck()){
                RestMap();
                Printer.OutputplayText("just as the monster rasies hes axe to kill you, you put up your sheild and block the fatal bow and get away ");
                basicHero.setFight(false);
                basicHero.setAtWall(false);
                FightRequest = false;
                return ;
            }
            ///output
                Printer.OutputplayTextln("you were kill brutaly but the gods of valhala accept your secrifice");
                System.exit(0);
        }
        return ;
    }

}
