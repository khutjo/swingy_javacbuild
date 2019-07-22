/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khutjo_laptop
 */
public class GuiToConsoleBridge {
   private static GuiToConsoleBridge HoldBridge;
   private String content;
   private String infoscreen;
   private String Choose;
   private String Direction;
   private String TextField;
   private boolean direction;
   private boolean choose;
   private boolean textfield;
   
   private GuiToConsoleBridge(){}
   
   public static GuiToConsoleBridge getBridgeIntance(){
       if (HoldBridge == null)
           HoldBridge = new GuiToConsoleBridge();
       return HoldBridge;
   }
   
   public void ClearContent(){
       content = "";
   }
    
   public String getContent(){return content;}
   public String getInfoScreen(){return infoscreen;}
   public String getChoose (){return Choose;}
   public String getDirection (){return Direction;}
   public String getTextField (){return TextField;}
   public boolean getdirection (){return direction;}
   public boolean getchoose (){return choose;}
   public boolean gettextfeild (){return textfield;}
   
   public GuiToConsoleBridge SetContent (String input){content = input; return this;}
   public GuiToConsoleBridge setInfoscreen (String input){infoscreen = input; return this;}
   public GuiToConsoleBridge setChoose (String input){Choose = input; return this;}
   public GuiToConsoleBridge setDirection (String input){Direction = input; return this;}
   public GuiToConsoleBridge setTextField (String input){TextField = input; return this;}
   public GuiToConsoleBridge setdirection (boolean state){direction = state; return this;}
   public GuiToConsoleBridge setchoose (boolean state){choose = state; return this;}
   public GuiToConsoleBridge settextField (boolean state){textfield = state; return this;}
}
