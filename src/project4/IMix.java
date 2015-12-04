package project4;

import java.util.Scanner;

/*********************************************************
 * 
 * @author David Bizzocchi
 * @version November, 2015
 *
 * Interface for Mix class
 ********************************************************/

public interface IMix {

	/** sets message for mix class */
	void setInitialMessage(String message);

	/** processes command for mix class */
	String processCommand(String command);

	/*************************************************************
	 * @param args
	 * 
	 * Main interface for mix class, runs until prompted to stop
	 ************************************************************/
	public static void main(String[] args){
		Mix myMix = new Mix();

		System.out.println("List Of Commands:" + "\n" +
				"Q or q\t> Quits program" + "\n" +
				"b c #\t> insert character 'c' before position #."+"\n"+
				"r #\t> remove character at position #." + "\n" +
				"w & #\t> switch characters at position & with #."  
				+ "\n" +
				"x & #\t> cut to clipboard, starting at & to # "
				+ "(inclusive)" + "\n" +
				"p #\t> paste from clipboard, start after #" + "\n" +
				"c & #\t> copy to clipboard, starting at & to # "
				+ "(inclusive)" + "\n" +
				"v\t> view current clip board" + "\n" +
				"e\t> erase current clip board" + "\n" +
				"a c\t> insert character 'c' at end of message" + "\n" +
				"s &\t> save encryprion commands to text file \"&\"."  
				+ "\n");

		System.out.println("Enter Initial Message:");
		
		//scans and sets initial message
		Scanner scan = new Scanner(System.in);
		String myMessage = scan.nextLine();
		myMix.setInitialMessage(myMessage);
		System.out.println(myMix);
		
		//reads first command, repeats until Q/q is entered
		String cmd = scan.nextLine();
		while (cmd.charAt(0) != 'Q'){
			myMix.processCommand(cmd);
			
			//prints current message
			System.out.println(myMix);
			
			//scans new command
			cmd = scan.nextLine();	
		}
		
		scan.close();
		System.out.println("EXITING...");
	}
}
