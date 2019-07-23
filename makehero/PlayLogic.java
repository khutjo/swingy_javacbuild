// package swingy;

// import java.util.Scanner;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Random;

//************************************************************************************************
//************************************************************************************************
//                                      play Logic
//************************************************************************************************
//************************************************************************************************

public class PlayLogic {
    private BasicHero basicHero;
    private WriteAction Printer;
	// private int Level;
	// private int MapSize;
    // private boolean Fight;
    // private boolean AtWall;
    // private char EnemyClass;

    // public int getXP(){return XP;}
    // public int getLevel(){return Level;}
    // public void setXP(int xpadd){XP += xpadd;}
    // public boolean getFight(){return Fight;}
    // public boolean getAtWall(){return AtWall;}
    // public char  getEnemyClass(){return EnemyClass;}

	public PlayLogic(BasicHero basichero, WriteAction printer){
        basicHero = basichero;
        Printer = printer;
	// 	MapSize = ((((level - 1) * 5) + 10) - (level % 2));
	// 	Level = level;
	// 	XP = xp;
	}

	private void ToWall(){
        int x = basicHero.getMapSize();
        char [][] map = basicHero.getNewMap();
		for (int i = 0; i < x; i++)
			for (int j = 0; j < x; j++){
				if (j == 0 || j == -1 + x || i == -1 + x || i == 0)
					if (map[i][j] == 'M'){
                        basicHero.setAtWall(true);
                        return ;
                    }
			}		
            basicHero.setAtWall(false);
	}

    private int LevelUpXp(){
       return (basicHero.getLeval() * 1000) + (((basicHero.getLeval() - 1) * (basicHero.getLeval() - 1)) * 450);
    }

    public void UnSetFight(){
        basicHero.setFight(false);
    }

    public void UnSetAtWalL(){
        basicHero.setAtWall(false);
    }

    private void UpdateMapize(){
        basicHero.setMapSize(((((basicHero.getLeval() - 1) * 5) + 10) - (basicHero.getLeval() % 2)));
    }

    public void LevelUp(){
        int levelup = LevelUpXp();
            //System.out.println("A" + levelup);
        if (basicHero.getXP() > levelup){
            //System.out.println("B");
            basicHero.setLevel(1 + basicHero.getLeval());
        }
        if (basicHero.getAtWall()){
            //System.out.println("C");
            UpdateMapize();
            UnSetAtWalL();
            basicHero.setMap(new GenMap().makemap(basicHero.getLeval()));
            basicHero.setNewMap(basicHero.getMap());
        }
    }

	private void copymap(){
        char [][] newmap = new char[basicHero.getMapSize()][basicHero.getMapSize()];
        char [][] map = basicHero.getMap();
        int mapsize = basicHero.getMapSize();
		for (int i = 0; i < mapsize; i++)
			for (int j = 0; j < mapsize; j++)
				newmap[i][j] = map[i][j];
		basicHero.setNewMap(newmap);	
	}
    /////input
	private String hardcodemove(){
        Printer.OutputplayTextln("Enter direction : ");
		return new ReadConsole().that();
	}
	
	private void FindMyCoords(){
		int [] me = new int[2];
        char [][] map = basicHero.getMap();
        int mapsize = basicHero.getMapSize();
		for(int i = 0; i < mapsize; i++)
			for (int j = 0; j < mapsize; j++)
				if (map[i][j] == 'M'){
					me[0] = i;
                    me[1] = j;
                    //System.out.println(j +" "+ i);
                    basicHero.setMyCoords(me);
                }
	}

    private void MoveAdder(){
        String move = hardcodemove();
        int [] NewCoords = {basicHero.getMyCoords()[0], basicHero.getMyCoords()[1]};
		if (move.equals("east"))
            NewCoords[1]--;
		else if (move.equals("west"))
            NewCoords[1]++;
		else if (move.equals("north"))
            NewCoords[0]--;
		else if (move.equals("south"))
            NewCoords[0]++;
        else { 
               MoveAdder();
               return ;
        }
		basicHero.setMyNewCoords(NewCoords);
    }

    private void IsFight(){
        char [][] map = basicHero.getMap();
        int [] MyCoords = {basicHero.getMyNewCoords()[0], basicHero.getMyNewCoords()[1]};
        if (map[MyCoords[0]][MyCoords[1]] != '.'){
            basicHero.setFight(true);
            basicHero.setEnemyClass(map[MyCoords[0]][MyCoords[1]]);
        }
        else {
            basicHero.setFight(false);
            basicHero.setEnemyClass('.');
        }
            

    }

    private void PutMoveToMap(){
        // System.out.println(map[MyCoords[0]][MyCoords[1]] + " " + map[NewCoords[0]][NewCoords[1]] );
        char [][] map = basicHero.getNewMap();
        int [] MyCoords = basicHero.getMyCoords();
        int [] NewCoords = basicHero.getMyNewCoords();
        map[MyCoords[0]][MyCoords[1]] = '.';
        map[NewCoords[0]][NewCoords[1]] = 'M';
        basicHero.setNewMap(map);
    }

    private boolean DoNotTouch(){
        int len = basicHero.getMapSize();
        char [][] map1 = basicHero.getMap();
        char [][] map2 = basicHero.getNewMap();
        for (int k = 0; k < len; k++)
            for ( int g = 0; g < len; g++){
                // System.out.println(map1[k][g] + " : "+ map2[k][g]);
                if (map1[k][g] != map2[k][g])
                    return true;
            }
         return false;
    }

	public void MakeMove(){
        if (DoNotTouch())
            return ;
		copymap();
		FindMyCoords(); 
        MoveAdder();
        PutMoveToMap();
        ToWall();
        IsFight();
	}
}