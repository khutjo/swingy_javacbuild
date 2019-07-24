package swingy_home;

import java.util.*;


public class Main {



	public static void main (String [] args){
            WriteAction printer = new WriteAction("console");
            Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
            GuiToConsoleController bridge = GuiToConsoleController.getBridgeIntance();
            if (bridge.getView())
                bridge.rungui(HoldSavedHeros, printer);
            else
                bridge.runconsole(HoldSavedHeros, printer);
                
            //BasicHero HoldNewHero = new CreateHero().MakeNewHero(HoldSavedHeros);
	//	Iterator<BasicHero> HoldHero = HoldSavedHeros.iterator();
	//	BasicHero Hero = HoldHero.next();        
		// new PrintOutBasicHero().PrintHeroDatafull(Hero);
	//	run(Hero, printer);
		
	}

}
