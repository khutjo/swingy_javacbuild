class play {
	int Level;
	int MapSize;
	int XP;

	public play(int level){
		MapSize = ((((level - 1) * 5) + 10) - (level % 2));
		level = level;
		xp = 0;
	}

	private boolean ToWall(char [][]map){
		for (int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++){
				if (j == 0 || j == -1 + MapSize || i == -1 + MapSize || i == 0;)
					if (map[i][j] == 'M')
						return false;
			}		
		return true;
	}
o
	private char copymap(char [][]map){
		char [][] newmap = new char[MapSize][MapSize];
		for (int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++)
				newmap[i][j] = map[i][j];
		return 	newmap;	
	}

	private String hardcodemove(){
		return "west";
	}
	
	private int [] FindMyCoords(char [][]map){
		int [] me = new int[2];

		for(int i = 0; i < MapSize; i++)
			for (int j = 0; j < MapSize; j++)
				if (map[i][j] == 'M'){
					me[0] = i;
					me[1] = j;
				}
		return me;
	}

        private int [] MoveAdder(int []MyCoords){
		String move = hardcodemove();
		if (move.equals("east")
			MyCoords[1]--;
		else if (move.equals("west")
			MyCoords[1]++;
		else if (move.equals("north")
			MyCoords[0]--;
		else if (move.equals("south")
			MyCoords[0]++;
		return MyCoords;
	)

	private char [][] MakeMove(char [][]map){
		char [][]newmap = copymap(map);
		int [] mycoord = FindMyCoords(map);
		
		return map
	}

	public move(char [][] map){
		char []tempmap
		//getmoves

		while (ToWall(map){
			tempmap = Makemove(map)
		}
	}

} 

class genmap {

	public char [][]makemap(int level){
		char [][] map = {{'A','.','C','.','A'}
				,{'.','B','.','B','.'}
				,{'C','.','M','.','C'}
				,{'.','B','.','B','.'}
				,{'A','.','C','.','A'}};
		
		return map;
	}
}


public class test {

	public static void main (String[] argc){


		genmap mapp = new genmap();

		char [][]map = mapp.makemap(2);

		for (char []i : map){
		
		
			for (char j : i)
				System.out.print(j);
		
			System.out.println("");
		}
	}
}
