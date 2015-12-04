package project4;

import java.util.Scanner;

public interface IUnMix {
	/** Sends File, and mixed message to UnMix Class*/
	String UnMixUsingFile (String filename, String mixedMessage);

	public static void main(String[] Args){
		
		UnMix myUnMix = new UnMix();
		
		String file = "", mixed = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter name of commands file: ");
		file = scan.nextLine();
		
		System.out.print("Enter mixed message: ");
		mixed = scan.nextLine();
		
		scan.close();
		
		System.out.println(myUnMix.UnMixUsingFile(file, mixed));
		
	}
}
