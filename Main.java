// package swingy;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


//************************************************************************************************
//************************************************************************************************
//                                      play logic
//************************************************************************************************
//************************************************************************************************

class play {
    private int XP;
	private int Level;
	private int MapSize;
    private boolean Fight;
    private boolean AtWall;
    private char EnemyClass;

    public int getXP(){return XP;}
    public int getLevel(){return Level;}
    public void setXP(int xpadd){XP += xpadd;}
    public boolean getFight(){return Fight;}
    public boolean getAtWall(){return AtWall;}
    public char  getEnemyClass(){return EnemyClass;}

	public play(int level, int xp){
		MapSize = ((((level - 1) * 5) + 10) - (level % 2));
		Level = level;
		XP = xp;
	}

	private void ToWall(char [][]map){
		for (int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++){
				if (j == 0 || j == -1 + MapSize || i == -1 + MapSize || i == 0)
					if (map[i][j] == 'M'){
                        AtWall = true;
                        return ;
                    }
			}		
		AtWall = false;
	}

    private int LevelUpXp(){
       return (Level * 1000) + (((Level - 1) * (Level - 1)) * 450);
    }

    public void UnSetFight(){
        Fight = false;
    }

    public void UnSetAtWalL(){
        Fight = false;
    }

    public char [][] LevelUp(GenMap genmap, char [][] map){
        int levelup = LevelUpXp();
        System.out.println("A");
        if (XP > levelup){
        System.out.println("B");
            Level++;
        }
        if (getAtWall()){
        System.out.println("C");
            UnSetAtWalL();
            return genmap.makemap(Level);
        }

        return map;
    }

	private char[][] copymap(char [][]map){
		char [][] newmap = new char[MapSize][MapSize];
		for (int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++)
				newmap[i][j] = map[i][j];
		return 	newmap;	
	}
    /////input
	private String hardcodemove(){
        System.out.println("Enter direction : ");
		return new ReadConsole().that();
	}
	
	private int [] FindMyCoords(char [][]map){
		int [] me = new int[2];

		for(int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++)
				if (map[i][j] == 'M'){
					me[0] = i;
                    me[1] = j;
                    System.out.println(j +" "+ i);
                    return me;
                }
		return me;
	}

    private int [] MoveAdder(int []MyCoords){
        String move = hardcodemove();
        int [] NewCoords = {MyCoords[0], MyCoords[1]};
		if (move.equals("east"))
            NewCoords[1]--;
		else if (move.equals("west"))
            NewCoords[1]++;
		else if (move.equals("north"))
            NewCoords[0]--;
		else if (move.equals("south"))
            NewCoords[0]++;
        
		return NewCoords;
    }

    private void IsFight(char [][] map, int [] MyCoords){
        if (map[MyCoords[0]][MyCoords[1]] != '.'){
            Fight = true;
            EnemyClass = map[MyCoords[0]][MyCoords[1]];
        }
        else {
            Fight = false;
            EnemyClass = '.';
        }
            

    }

    private char [][] PutMoveToMap(char [][] map, int [] MyCoords, int [] NewCoords){
        // System.out.println(map[MyCoords[0]][MyCoords[1]] + " " + map[NewCoords[0]][NewCoords[1]] );
        map[MyCoords[0]][MyCoords[1]] = '.';
        map[NewCoords[0]][NewCoords[1]] = 'M';

        return map;
    }

	public char [][] MakeMove(char [][]map){
		char [][]newmap = copymap(map);
		int [] mycoord = FindMyCoords(map); 
        int [] mymove = MoveAdder(mycoord);
        newmap = PutMoveToMap(newmap, mycoord, mymove);
        ToWall(newmap);
        IsFight(map, mymove);
		return newmap;
	}
}

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


// class WeatherProvider {
// 	private static WeatherProvider weatherprovider = null;
// 	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

// 	private WeatherProvider(){}

// 	public static WeatherProvider getProvider(){
// 		if (weatherprovider == null)
// 			weatherprovider = new WeatherProvider();
// 		return weatherprovider;
// 	}

// 	public String getCurrentWeather(Coordinates coordinates){
// 		Random SpicySeed = new Random();
// 		int longitude = coordinates.getLongitude();
// 		int latitude = coordinates.getLatitude();
// 		int height = coordinates.getHeight();
// 		long weatherseed = longitude * latitude * height *
// 		SpicySeed.nextInt(500);

// 		Random rand = new Random(weatherseed);
// 		int n = rand.nextInt(4);
// 		return (weather[n]);
// 	}

//************************************************************************************************
//************************************************************************************************
//                                      fight ligic     
//************************************************************************************************
//************************************************************************************************

class Fight {
    ///input
    private String getmoves(){
        System.out.println("Fight monster (yes/no) : ");
		return new ReadConsole().that();
    }

    private boolean UserChoose(){
        String userinput = getmoves();
        if (userinput.equals("yes"))
            return true;
        if (userinput.equals("no"))
            return false;
        return false;
    }

    private boolean GetYourLuck(){
        Random addspice = new Random();
        Random chooseop = new Random(addspice.nextInt(1000));

        if (chooseop.nextInt(10000) < 5000)
            return true;
        else return false;
    }

    private boolean DoILetThemWin(play playlogic){

        if (playlogic.getEnemyClass() == 'A')
            return true;
        else if (playlogic.getEnemyClass() == 'C')
            return false;
        else if (playlogic.getEnemyClass() == 'B' && GetYourLuck())
            return true;
        return false;
    }

    public char [][] EngageFight(char [][] map, char [][] newmap, play playlogic){
        if (!playlogic.getFight())
            return newmap;

        boolean choice = UserChoose();

        if (choice && DoILetThemWin(playlogic)){
            playlogic.setXP(100);
            playlogic.UnSetFight();
            return newmap;
        }else if (!choice){
            if (GetYourLuck()){
                playlogic.UnSetFight();
                return map;
                }
            else{
                //output
                System.out.println("The monster does not let you leave");
                return (EngageFight(map, newmap, playlogic));
            }
        }
        else {
            ///output
            System.out.println("you were kill brutaly but the gods of valhala acept your secrifice");
            System.exit(0);
        }
        return newmap;
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
        char [][]maps2;// = map.makemap(1);
        for (char [] j : maps){
            for (char i : j)
                System.out.print(i);
            System.out.println("");
        }
        //ReadConsole hold = new ReadConsole();
        //hold.that();
        play hold = new play(1, 999);
        Fight hold2 = new Fight();
        int runtime = 10;
        while(runtime-- > 0){
        maps2 = hold.MakeMove(maps);
        maps = hold2.EngageFight(maps, maps2, hold);
        maps = hold.LevelUp(map, maps);
        System.out.println("at wall " + hold.getAtWall() + " is fight " + hold.getFight() + " enemy class "+ hold.getEnemyClass());
        for (char [] j : maps){
            for (char i : j)
                System.out.print(i);
            System.out.println("");
        }
    }

    }

}
