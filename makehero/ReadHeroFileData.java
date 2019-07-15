// package rwfile;

//import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class ReadHeroFileData {
	
	public Stack<String> getFileData(String openfile) {
		Stack<String> fileData = new Stack<String>();
		// Iterator<String> tempIterator;
		String HoldString;

//		System.out.println("--->B");
		File InFile = new File(openfile);
		//System.out.println("hello");
		try{Scanner FileReader = new Scanner(InFile);
				while (FileReader.hasNextLine()){
					HoldString = FileReader.nextLine();
					HoldString = HoldString.trim();
					fileData.push(HoldString);
				}
				FileReader.close();
		}catch (FileNotFoundException e){
				return (null);
		}
		return (fileData);
	}

	

	public Stack<Stack<String>> getHeroData(Stack<String> filenamedata){
		Iterator<String> filename = filenamedata.iterator();
		Stack<Stack<String>> Heros = new Stack<Stack<String>>();
		String HoldString;

		while (filename.hasNext()){
			HoldString = filename.next();
			//System.out.println(HoldString);
			Heros.push(getFileData("HeroDataFile/"+HoldString));
		}	
		return Heros;
	}

	private BasicHero MakeHero(Stack<String> filedata){
		
		BasicHero basichero = new BasicHero();
		Iterator<String> filelines = filedata.iterator();

		basichero.setHeroName(filelines.next());
		basichero.stringsetHeroClass(filelines.next());
		basichero.setLevel(Integer.parseInt(filelines.next()));
		basichero.setXP(Integer.parseInt(filelines.next()));
		basichero.stringsetHeroEfacs(filelines.next());
		basichero.stringsetEnamyClass(filelines.next());
		basichero.stringsetFight(filelines.next());
		basichero.stringsetAtwall(filelines.next());
		basichero.stringsetMyCoords(filelines.next(), filelines.next());
		basichero.setMapSize(Integer.parseInt(filelines.next()));
		filelines.next();
		int holdmapsize = basichero.getMapSize();
		char [][] map = new char[basichero.getMapSize()][basichero.getMapSize()];
		for (int i = 0; i < holdmapsize; i++){
			map[i] = filelines.next().toCharArray();
		}
		basichero.setMap(map);
		return basichero;
	}

	public Stack<BasicHero> SortHeroData(Stack<Stack<String>> HeroData){
		Stack<BasicHero> SortedHero = new Stack<BasicHero>();
		Iterator<Stack<String>> Hero = HeroData.iterator();

		while (Hero.hasNext()){
			SortedHero.push(MakeHero(Hero.next()));
		}
		return SortedHero;
	}

	private void printdata (BasicHero holdbasic){
		System.out.println("\nname : "+holdbasic.getHeroName());
		//System,out.println(getHeroClass());
		System.out.println("level : "+holdbasic.getLeval());
		System.out.println("XP : "+holdbasic.getXP());
		//System.out.println(getHeroEfacs());
		int x = holdbasic.getMapSize();
		System.out.println("mapsize : "+x);
		System.out.println("coords : "+holdbasic.getMyCoords()[0] +" "+ holdbasic.getMyCoords()[1]);
		System.out.println("enemyclass : "+holdbasic.getEnemyClass());
		System.out.println("atwall : "+holdbasic.getAtWall());
		System.out.println("fight : "+holdbasic.getFight());
		char [][] mpa = holdbasic.getMap();
		for (int i = 0; i < x; i++){
			System.out.print("\t");
			for (int j = 0; j < x; j++)
				System.out.print(mpa[i][j]);
			System.out.print("\n");
		}
}

	private void readoutdata (Stack<BasicHero> src){
		Iterator<BasicHero> holdherodata = src.iterator();
	
		while (holdherodata.hasNext()){
			printdata(holdherodata.next());
		}
	}

	public Stack<BasicHero> GetSavedHeros (){

		Stack<String> HeroFileNameData = getFileData("HeroDataFile/SavedHero.bin");
		if (HeroFileNameData == null)
			return null;
		Stack<Stack<String>> Heros = getHeroData(HeroFileNameData);
		Stack<BasicHero> Basichero = SortHeroData(Heros);
	//	readoutdata(Basichero);
		return Basichero;
	}
}
