/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khutjo_laptop
 */
import java.util.*;


public class GuiToConsoleController {
   private PlayLogic playlogic;
   private Fight fightlogic ;
   private AutoSave autosave;
   private static GuiToConsoleController HoldBridge;
   private Stack<BasicHero> SavedHeros;
   private BasicHero Hero;
   private String content;
   private String infoscreen;
   private String Choose;
   private String Direction;
   private String TextField;
   private int callorder;
   private boolean direction;
   private boolean choose;
   private boolean textfield;
   private boolean startgame;
   private boolean view;
   private boolean RX;
   private boolean TX;
   
   private GuiToConsoleController(){

   }
   
   public static GuiToConsoleController getBridgeIntance(){
       if (HoldBridge == null)
           HoldBridge = new GuiToConsoleController();
       return HoldBridge;
   }
   
 
   public String getContent(){return content;}
   public String getInfoScreen(){return infoscreen;}
   public String getChoose (){return Choose;}
   public String getDirection (){return Direction;}
   public String getTextField (){return TextField;}
   public boolean getdirection (){return direction;}
   public boolean getchoose (){return choose;}
   public boolean gettextfield (){return textfield;}
   public boolean getstartgame (){return startgame;}
   public boolean getView(){return view;}
   public boolean getTX(){return TX;}
   public boolean getRX(){return RX;}

   
   public GuiToConsoleController SetContent (String input){content = input; return this;}
   public GuiToConsoleController setInfoscreen (String input){infoscreen = input; return this;}
   public GuiToConsoleController setChoose (String input){Choose = input; return this;}
   public GuiToConsoleController setDirection (String input){Direction = input; return this;}
   public GuiToConsoleController setTextField (String input){TextField = input; return this;}
   public GuiToConsoleController setdirection (boolean state){direction = state; return this;}
   public GuiToConsoleController setchoose (boolean state){choose = state; return this;}
   public GuiToConsoleController settextField (boolean state){textfield = state; return this;}
   public GuiToConsoleController setstartgame (boolean state){startgame = state; return this;}
   public GuiToConsoleController setView (boolean state){view = state; return this;}
   public GuiToConsoleController setTX (boolean state){TX = state; return this;}
   public GuiToConsoleController setRX (boolean state){RX = state; return this;}
   
   private void RunLogic(String input){
                       System.out.println("\norder "+callorder);
        if (callorder == 0){
            playlogic.LevelUp();
            callorder += playlogic.SetUpMove();
        }else if (callorder == 1){
            callorder += playlogic.MakeMove(input);
            autosave.SaveStatus();
        }
        if (callorder == 2)
            callorder += fightlogic.SetUpFight();
        else if (callorder == 3){
            if (input.equals("yes"))
            callorder += fightlogic.EngageFight(true);
            else if (input.equals("no"))
            callorder += fightlogic.EngageFight(false);
        }
        if (callorder == 4)
            callorder += fightlogic.DoTheyGetAnArtefacs();
        else if (callorder == 5)
            callorder += fightlogic.GiveThemanefacs(input);
        if (callorder > 5){
            callorder = 0;
            RunLogic(input);
        }

        autosave.SaveStatus();
        new PrintOutBasicHero().PrintHeroDatafull(Hero);
   }
   
   private boolean IsGameOn(){
       if (!startgame){
       System.out.println("\nletmeee ");
           if (Choose.equals("yes")){
                startgame = true;
                Choose = "";
                RunLogic("start");
           }
           else if (Choose.equals("no"))
                   System.exit(0);
           return true;
       }
       return false;
   }
   
   public void Getrequet(){
       
       if (IsGameOn())
           return ;
       
       switch (Choose) {
           case "yes":
               RunLogic(Choose);
               System.out.println("yes");
               break;
           case "no":
               RunLogic(Choose);
               System.out.println("no");
               break;
           default:
               break;
       }switch (Direction) {
           case "north":
               RunLogic(Direction);
               System.out.println("north");
               break;
           case "west":
               RunLogic(Direction);
               System.out.println("west");
               break;
           case "east":
               RunLogic(Direction);
               System.out.println("east");
               break;
           case "south":
               RunLogic(Direction);
               System.out.println("south");
               break;
           default:
               break;
       }
        Direction = "";
        Choose = "";
   }
   
   public void run(Stack<BasicHero> savedheros, WriteAction printer){
        SavedHeros = savedheros;
        Iterator<BasicHero> HoldHero = SavedHeros.iterator();
	Hero = HoldHero.next();
        playlogic = new PlayLogic(Hero, printer, this);
        fightlogic = new Fight(Hero, printer, this);
        autosave = new AutoSave(Hero);
        callorder = 0;

       //while (true){
          // if (Choose.equals("yes")){
           //    System.out.println("hello");
         //      System.exit(0);
       //    }
       //}
   }
}
