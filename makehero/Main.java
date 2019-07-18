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

    private static void printmap(char [][] map){
        for (char [] j : map){
            for (char i : j)
                System.out.print(i);
                System.out.println("");
        }
        System.out.println("");
    }

    public static void run(BasicHero basichero, WriteAction printer){

        PlayLogic playlogic = new PlayLogic(basichero, printer);
        Fight fightlogic = new Fight(basichero, printer);
        AutoSave autosave = new AutoSave(basichero);

		while (true){
            playlogic.MakeMove();
        autosave.SaveStatus();
		fightlogic.EngageFight();
        playlogic.LevelUp();
        autosave.SaveStatus();
        new PrintOutBasicHero().PrintHeroDatafull(basichero);
        }
    }


	public static void main (String [] args){

        
        WriteAction printer = new WriteAction(args);
		Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
		//BasicHero HoldNewHero = new CreateHero().MakeNewHero(HoldSavedHeros);
		Iterator<BasicHero> HoldHero = HoldSavedHeros.iterator();
		BasicHero Hero = HoldHero.next();        
		// new PrintOutBasicHero().PrintHeroDatafull(Hero);
		run(Hero, printer);
		
	}

}
