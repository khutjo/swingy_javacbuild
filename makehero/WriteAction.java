// import javax.lang.model.util.ElementScanner6;

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

        

        private void printtoconsolestats(BasicHero basichero){
            String efacs = basichero.EnumToStringHeroEfacsEnum();

            OutputplayTextln("Name      : "+basichero.getHeroName());
            OutputplayTextln("Hero Type : "+basichero.EnumToStringHeroClass());
            if (efacs.equals("Attack"))
                OutputplayTextln("Artefacs  : Sword");
            if (efacs.equals("Defense"))
                OutputplayTextln("Artefacs  : Shield");
            if (efacs.equals("HitPoints"))
                OutputplayTextln("Artefacs  : Magic somthing");
            OutputplayTextln("Level     : "+Integer.toString(basichero.getLeval()));
            OutputplayTextln("XP Point  : "+Integer.toString(basichero.getXP()));
        }
        private void printtoguistats(BasicHero basichero){
            printtoconsolestats(basichero);
        }

        public void OutputPlayerstates(BasicHero basichero){

            if (outChanal)
                printtoconsolestats(basichero);
            else if (!outChanal)
                printtoguistats(basichero);
        }

}