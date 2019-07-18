// package rwfile;
//
import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

//************************************************************************************************
//************************************************************************************************
//                                      Create Hero
//************************************************************************************************
//************************************************************************************************

public class CreateHero{

	private boolean Save (String save){
		System.out.println("SAVE : " + save + " (yes/no)");
		String HoldString = new ReadConsole().that();
		if (HoldString.equals("yes"))
			return true;
		else if (HoldString.equals("no"))
			return false;
		else
			return Save(save);
	}

	private void getnewHeroName (Stack<String> namesinuse, BasicHero basichero){
		String HoldString;
		///output
		System.out.println("Enter Hero Name");
		HoldString = new ReadConsole().that();
//		regax
		if (HoldString.equals("")){
			System.out.println("You cannot enter nothing as a name.");
			getnewHeroName(namesinuse, basichero);
			return ;
		}

		if (!HoldString.matches("[a-zA-Z0-9]+")){// && Pattern.matches("[^A-Z]+", HoldString) && Pattern.matches("[^0-9]+", HoldString)){
			System.out.println("You name contains invalid charectors (A-z/0-9)");
			getnewHeroName(namesinuse, basichero);
			return ;
		}
		if (NameInUse(HoldString, namesinuse)){
			System.out.println("Tha name "+HoldString+" is in use choose another one");
			getnewHeroName(namesinuse, basichero);
			return ;
		}
		if (Save(HoldString))
			basichero.setHeroName(HoldString);
		else{
			getnewHeroName(namesinuse, basichero);
			return ;
		}
		return ;
	}

	private boolean NameInUse(String HeroName, Stack<String> ListOfNames){
		Iterator<String> listofnames = ListOfNames.iterator();
		
		while (listofnames.hasNext())
			if (listofnames.next().equals(HeroName)){
				return true;
			}

		return false;
	}

	private Stack<String> getListOfSavedHeroNames(Stack<BasicHero> basicherolist){

		
		Iterator<BasicHero> basichero = basicherolist.iterator();
		Stack<String> savedheronames = new Stack<String>();
		String HoldString;

		while (basichero.hasNext()){
			HoldString = basichero.next().getHeroName();
			System.out.println(HoldString);
			savedheronames.push(HoldString);
		}
		
		return savedheronames;
	}

	private void getnewHeroClass(BasicHero basichero){
//		int fuckyou = 0;
		String HoldString;
		///output
		System.out.println("Chooce Hero class");
		System.out.println("Attack\nIf you choose attack you gain more XP when you fight a monster,"+
			       		"\nbut you are mor likely to lose a fight");
		System.out.println("Defense\nIf you choose defanse you are more likely to win fight againt a monster"
						+"\nbut you gain less XP ");
		HoldString = new ReadConsole().that();
//		regax
		if (HoldString.equals("") || (!HoldString.equals("Attack") && !HoldString.equals("Defense"))){
			System.out.println("you think this is a game, This is your life,");// if you do that again i will choose for you");
			getnewHeroClass(basichero);
			return ;
		}
		if (Save(HoldString))
			basichero.stringsetHeroClass(HoldString);
		else{
			getnewHeroClass(basichero);
			return ;
		}
		return ;


	}

	public BasicHero MakeNewHero(Stack<BasicHero> savedheros){
		char [][] newmap = new GenMap().makemap(1);
	//	CreateHero hold = new CreateHero();
	//
		if (savedheros == null)
			System.out.println("shit");
		Stack<String> NamesInUse = getListOfSavedHeroNames(savedheros);
		BasicHero basicHero = new BasicHero();
		basicHero.setLevel(1)
			.setXP(0)
			.stringsetHeroEfacs("none")
			.setMap(newmap)
			.setNewMap(newmap)
			.setMapSize(9)
			.stringsetMyCoords("4", "4", "4", "4")
			.setEnemyClass('.')
			.setAtWall(false)
			.setFight(false);		
		getnewHeroName(NamesInUse, basicHero);
		getnewHeroClass(basicHero);
		PrintOutBasicHero hold = new PrintOutBasicHero();
		hold.PrintHeroData(basicHero);

		if (Save(basicHero.getHeroName()))
			return basicHero;
		else return MakeNewHero(savedheros);
	}
}
