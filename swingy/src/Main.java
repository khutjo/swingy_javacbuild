import java.util.Iterator;

//package swingy

import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    public static void run(BasicHero basichero, WriteAction printer){

        PlayLogic playlogic = new PlayLogic(basichero, printer);
        Fight fightlogic = new Fight(basichero, printer);
        AutoSave autosave = new AutoSave(basichero);
int holddd = 0;
		while (true){
        if (holddd == 0)
            holddd += playlogic.SetUpMove();
        if (holddd == 1){
            holddd += playlogic.MakeMove(playlogic.hardcodemove());
            autosave.SaveStatus();
        }
        if (holddd == 2)
            holddd += fightlogic.SetUpFight();
        if (holddd == 3)
            holddd += fightlogic.EngageFight(fightlogic.UserChoose());
        if (holddd == 4)
            holddd += fightlogic.DoTheyGetAnArtefacs();
        if (holddd == 5)
            holddd += fightlogic.GiveThemanefacs("true");
        if (holddd > 5)
            holddd = 0;
        playlogic.LevelUp();
        autosave.SaveStatus();
        new PrintOutBasicHero().PrintHeroDatafull(basichero);
        }
    }


	public static void main (String [] args){
            WriteAction printer = new WriteAction("gui");
            Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
            GuiToConsoleController bridge = GuiToConsoleController.getBridgeIntance();
            bridge.run(HoldSavedHeros, printer);
            //BasicHero HoldNewHero = new CreateHero().MakeNewHero(HoldSavedHeros);
	//	Iterator<BasicHero> HoldHero = HoldSavedHeros.iterator();
	//	BasicHero Hero = HoldHero.next();        
		// new PrintOutBasicHero().PrintHeroDatafull(Hero);
	//	run(Hero, printer);
		
	}

}
