//package swingy

import java.util.*;
import java.io.*;

public class Main {


	public static void main (String [] args){
		Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
		BasicHero HoldNewHero = new CreateHero().MakeNewHero(HoldSavedHeros);
	}

}
