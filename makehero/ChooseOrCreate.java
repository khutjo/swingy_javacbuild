// package swingy;


import java.util.*;


public class ChooseOrCreate {
    private Stack<BasicHero> HoldSavedHeros;
    private WriteAction Printer;

    public ChooseOrCreate(WriteAction printer){
        HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
        Printer = printer;
    }

    public BasicHero Choose(){
        Printer.OutputplayTextln("Do you want to make a new hero a ");

    }
}
