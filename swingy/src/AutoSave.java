
import java.io.FileWriter;

public class AutoSave {
    private String FileName;
    private BasicHero Hero;

    private boolean booleanToString (boolean bool){
        if (bool)
            return true;
        return false;
    }

    private void WrtieTheHash(FileWriter fw) throws Exception{
        int len = Hero.getMapSize();
        fw.write("\n");
        for (int k = 0 ; k < len; k++)
            fw.write("#");
    }

    private void WriteTheMap(FileWriter fw) throws Exception{
        WrtieTheHash(fw);
        int len = Hero.getMapSize();
        char [][] map = Hero.getMap();
        for (int k = 0; k < len; k++){
            fw.write("\n");
            for (int g = 0; g < len; g++)
                fw.write(map[k][g]);
        }
        WrtieTheHash(fw);
        map = Hero.getNewMap();
        for (int k = 0; k < len; k++){
            fw.write("\n");
            for (int g = 0; g < len; g++)
                fw.write(map[k][g]);
        }
        WrtieTheHash(fw);
    }

    public void SaveStatus(){
        try{    
            FileWriter fw=new FileWriter(FileName);   
            fw.write(Hero.getHeroName());
            fw.write("\n"+Hero.EnumToStringHeroClass());
            fw.write("\n"+Integer.toString(Hero.getLeval()));
            fw.write("\n"+Integer.toString(Hero.getXP()));
            fw.write("\n"+Hero.EnumToStringHeroEfacsEnum());
            fw.write("\n"+Hero.getEnemyClass());
            fw.write("\n"+booleanToString(Hero.getFight()));
            fw.write("\n"+booleanToString(Hero.getAtWall()));
            fw.write("\n"+Integer.toString(Hero.getMyCoords()[0]));
            fw.write("\n"+Integer.toString(Hero.getMyCoords()[1]));
            fw.write("\n"+Integer.toString(Hero.getMyNewCoords()[0]));
            fw.write("\n"+Integer.toString(Hero.getMyNewCoords()[1]));
            fw.write("\n"+Integer.toString(Hero.getMapSize()));
            WriteTheMap(fw);
            fw.close();    
           }catch(Exception e){
                System.out.println(e);
                System.out.println("AutoSave failed but you can keep playing");
           }
    }

    public AutoSave (BasicHero hero){
        FileName = "HeroDataFile/"+hero.getHeroName()+".sgy";
        Hero = hero;
        // SaveStatus();
    }    
    
}

