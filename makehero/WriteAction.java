import javax.lang.model.util.ElementScanner6;

public class WriteAction {
    private boolean outChanal;

    public WriteAction(String [] outpath){
        if (outpath.length > 0){
            if (outpath[0].equals("gui"))
                outChanal = true;
            else if (outpath[0].equals("console"))
                outChanal = false;
            else
                System.out.println("unrecognized  out channal");
        }else{
            System.out.println("No out channal selected (gui/console)");
            System.exit(0);
            }
        }

        private void printtoconsole(String text){
            System.out.print(text);
        }

        private void printtogui(String text){
            printtoconsole(text);
        }

        public void OutputplayText(String text){
            if (outChanal)
                printtogui(text);
            if (!outChanal)
                printtoconsole(text);
        }

        public void OutputplayTextln(String text){
            if (outChanal)
                printtogui("\n"+text);
            if (!outChanal)
                printtoconsole("\n"+text);
        }

}