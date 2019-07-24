import javax.lang.model.util.ElementScanner6;

public class WriteAction {
    private boolean outChanal;
    private GuiToConsoleController bridge;

    public WriteAction(String outpath){
//        String outpath = "gui";
        if (outpath.length() > 0){
            if (outpath.equals("gui")){
                outChanal = true;
                NewJFrame hold = new NewJFrame();
                hold.screen();
                setUpBridge(true);
            }
            else if (outpath.equals("console")){
                outChanal = false;
                setUpBridge(false);
            }else{
                System.out.println("unrecognized  out channal");
                System.exit(0);
            }
        }else{
            System.out.println("No out channal selected (gui/console)");
            System.exit(0);
            }
        }

    private void setUpBridge(boolean state){
        bridge = GuiToConsoleController.getBridgeIntance();
        bridge.setdirection(true)
               .setchoose(true)
               .settextField(false)
               .setstartgame(false)
               .setView(state)
               .setTX(true)
               .setRX(true)
               .SetContent("")
               .setInfoscreen("")
               .setChoose("")
               .setDirection("")
               .setTextField("");
    }
    
        private void printtoconsole(String text){
            System.out.print(text);
        }

        private void printtogui(String text){
            bridge.SetContent(text).setRX(true);
            System.out.print(text);
        }

        public void OutputplayText(String text){
            if (outChanal)
                bridge.SetContent(text).setRX(true);
            if (!outChanal)
                printtoconsole(text);
        }

        public void OutputplayTextln(String text){
            if (outChanal)
                printtogui("\n"+text);
            if (!outChanal)
                printtoconsole("\n"+text);
        }
        
        //private void updateinfoscreen(BasicHero hero){
          //  bridge.setInfoscreen(her
        //}
        
        public void PrintOutStates(BasicHero hero, boolean call){
            if (outChanal){
                
            }else if (call){
                
            }
        }

}