
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kmaputla
 */
public class ChooseHero {
    private Stack<BasicHero> SavedHeros;
    private GuiToConsoleController Bridge;
    private WriteAction Printer;
   
   public ChooseHero(Stack<BasicHero> savedheros, WriteAction printer, GuiToConsoleController bridge){
       SavedHeros = savedheros;
       Printer = printer;
       Bridge = bridge;
   }
   
   public int availableheros(){
       
   }
    
}
