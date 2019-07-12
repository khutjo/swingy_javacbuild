// package rwfile;

//import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class ReadHeroFileData {
	
	private int CountStack(Stack<String> src){
		int  count = 0;
		Iterator<String> Next = src.iterator();
		while (Next.hasNext()){
			Next.next();
			count++;
		}
		return --count;
	}

	private boolean FileNameFormat(String src){
		int NameLen = src.substring(0,src.length() - 4).length();
		boolean CheckA = src.substring(src.length() - 4).equals(".sgy");
		boolean CheckB = src.substring(0,src.length() - 4).matches("[a-zA-Z0-9]+");
		
		if (!CheckA || !CheckB || NameLen < 3 || NameLen > 9)
			return true;
		return false;
	}

	private boolean ValidateFile(Stack<String> name, int SkipNum, int MaxStack){
		int CountRun = 0;
		String HoldStr = "";
		// boolean PassOne = false;
		String StackSTR = name.get(SkipNum);
		Iterator<String> StackITR = name.iterator();
		
		if (FileNameFormat(StackSTR))
			return true;
		// for (char i : name.toCharArray()){
		// 	System.out.print(i);

		// }
			//return true;
			while (StackITR.hasNext()){
				HoldStr = StackITR.next();
				if (CountRun != SkipNum){
					if (HoldStr.equals(StackSTR)){
						// System.out.println("--->"+CountRun+" : "+ StackSTR+" : "+SkipNum+" : "+HoldStr);
						return true;
				}
			}
			CountRun++;
		}
		if (SkipNum < MaxStack){
				// System.out.println("\n--->"+SkipNum);
			return ValidateFile(name, ++SkipNum, MaxStack);
		}
		return false;
	}

	public Stack<String> getHeroData() {
		Stack<String> HeroData = new Stack<String>();
		// Iterator<String> tempIterator;
		String HoldString;

		System.out.println("--->B");
		File InFile = new File("HeroDataFile/SavedHero.bin");
		//System.out.println("hello");
		try{Scanner FileReader = new Scanner(InFile);
				while (FileReader.hasNextLine()){
						HoldString = FileReader.nextLine();
						HoldString = HoldString.trim();
						HeroData.push(HoldString);
						System.out.println("--->"+HoldString);
				}
				FileReader.close();
		}catch (FileNotFoundException e){
				return (null);
		}
		if (ValidateFile(HeroData, 0, CountStack(HeroData))){
			throw new RuntimeException();
		}
		return (HeroData);
	}

	public static void main (String[] args){

		// ReadHeroFileData hold = new ReadHeroFileData();
		// hold.getHeroData();
	}
}