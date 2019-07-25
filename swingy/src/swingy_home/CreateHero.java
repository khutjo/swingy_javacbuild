package swingy_home;


import java.util.Iterator;
import java.util.Stack;




//************************************************************************************************
//************************************************************************************************
//                                      Create Hero
//************************************************************************************************
//************************************************************************************************

public class CreateHero{
        private Stack<BasicHero> SavedHeros;
        private GuiToConsoleController Bridge;
        private WriteAction Printer;
        private Stack<String> NamesInUse;
        private BasicHero basicHero;
    
        public CreateHero(Stack<BasicHero> savedheros, WriteAction printer, GuiToConsoleController bridge){
            SavedHeros = savedheros;
            Printer = printer;
            Bridge = bridge;
        }
        
	public int Save (String input){
		if (input.equals("no"))
			return -1;
		else if (input.equals("yes")){
                    Bridge.SetHero(basicHero);
			return 7;
                }else{
                    Printer.RePrint();
                    return 5;
                }
	}

	public int getnewHeroName (String input){
		///output
//		regax
		if (input.equals("")){
			Printer.OutputplayTextln("You cannot enter nothing as a name.\n Enter new name");
                        Bridge.setTX(false).settextField(true);
                        return 0;
		}

		if (!input.matches("[a-zA-Z0-9]+")){
			Printer.OutputplayTextln("You name contains invalid charectors (A-z/0-9)\n Enter new name");
			Bridge.setTX(false).settextField(true);
                        return 0;
		}
		if (NameInUse(input)){
			Printer.OutputplayTextln("Tha name "+input+" is in use choose another one\n Enter new name");
			Bridge.setTX(false).settextField(true);
                        return 0;
		}

		String HoldString = "Chooce Hero class\n";
		HoldString += "Attack\nIf you choose attack you gain more XP when you fight a monster,"+
			       		"\nbut you are mor likely to lose a fight\n";
		HoldString +=  "Defense\nIf you choose defanse you are more likely to win fight againt a monster"
						+"\nbut you gain less XP \n";
                Printer.OutputplayTextln(HoldString);
                
                Bridge.setTX(false).settextField(true);
                basicHero.setHeroName(input);
		return 1;
	}

	private boolean NameInUse(String HeroName){
		Iterator<String> listofnames = NamesInUse.iterator();
		
		while (listofnames.hasNext())
			if (listofnames.next().equals(HeroName)){
				return true;
			}

		return false;
	}

	private void getListOfSavedHeroNames(Stack<BasicHero> basicherolist){

		
		Iterator<BasicHero> basichero = basicherolist.iterator();
		NamesInUse = new Stack<String>();
		String HoldString;

		while (basichero.hasNext()){
			HoldString = basichero.next().getHeroName();
			NamesInUse.push(HoldString);
		}
	}

	public int getnewHeroClass(String input){
            
		if (input.equals("") || (!input.equals("Attack") && !input.equals("Defense"))){
                    Printer.RePrint();
                    Bridge.setTX(false).settextField(true);
                    return 0;
		}
		basicHero.stringsetHeroClass(input);
                Printer.PrintOutStates(basicHero, !Bridge.getView());
                Printer.OutputplayTextln("Do you want to save (yes/no)\n");
                Bridge.setTX(false).setchoose(true);
                return 1;


	}
        public int SetUpCreateHero(){
            char [][] newmap = new GenMap().makemap(1);
            getListOfSavedHeroNames(SavedHeros);
            basicHero = new BasicHero();
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
            Bridge.setTX(false).settextField(true);
            Printer.OutputplayTextln("Enter new hero name\n");
            return 1;
        }
        

}
