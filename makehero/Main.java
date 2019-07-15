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

    public static void run(BasicHero basichero){
        // GenMap map = new GenMap();
        // char [][]maps = map.makemap(1);
        // char [][]maps2;// = map.makemap(1);
        // for (char [] j : maps){
        //     for (char i : j)
        //         System.out.print(i);
        //     System.out.println("");
        // }
        //ReadConsole hold = new ReadConsole();
        //hold.that();
        PlayLogic playlogic = new PlayLogic(basichero);
        Fight fightlogic = new Fight(basichero);
        // int runtime = 10;
        // while(true){
		playlogic.MakeMove();
        //     printmap(maps);
		//     printmap(maps2);
		fightlogic.EngageFight();
        //     printmap(maps);
        playlogic.LevelUp();
        //     printmap(maps);
        //     System.out.println("at wall " + hold.getAtWall() + " is fight " + hold.getFight() + " enemy class "+ hold.getEnemyClass()+ " XP " + hold.getXP() + " level " + hold.getLevel());
        //     printmap(maps);
        // }

    }


	public static void main (String [] args){
		Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
		//BasicHero HoldNewHero = new CreateHero().MakeNewHero(HoldSavedHeros);
		Iterator<BasicHero> HoldHero = HoldSavedHeros.iterator();
		BasicHero Hero = HoldHero.next();
		new PrintOutBasicHero().PrintHeroDatafull(Hero);
		while (true){
			run(Hero);
			new PrintOutBasicHero().PrintHeroDatafull(Hero);
			System.out.println("new coords : "+Hero.getMyNewCoords()[0] +" "+ Hero.getMyNewCoords()[1]);
			// int x = Hero.getMapSize();
			// char [][] mpa = Hero.getNewMap();
			// for (int i = 0; i < x; i++){
			// 	System.out.print("\t");
			// 	for (int j = 0; j < x; j++)
			// 		System.out.print(mpa[i][j]);
			// 	System.out.print("\n");
			// }
		}
	}

}
