package project4;

import java.util.Scanner;

public interface IMix {

	void setInitialMessage(String message);

	String processCommand(String command);

	public static void main(String[] args){
		//		MyLinkedList<Character> list = new 
		//				MyLinkedList<Character>();
		Mix myMix = new Mix();

		System.out.println("List Of Commands:" + "\n" +
				"Q\t> Quit" + "\n" +
				"b c #\t> insert character 'c' before position #." + "\n" +
				"r #\t> remove character at position #." + "\n" +
				"w & #\t> switch characters at position & with #."  + "\n" +
				"x & #\t> cut to clipboard, starting at & to # (inclusive)" + "\n" +
				"p #\t> paste from clipboard, start after #" + "\n" +
				"c & #\t> copy to clipboard, starting at & to # (inclusive)" + "\n" +
				"v\t> view current clip board" + "\n" +
				"e\t> erase current clip board" + "\n" +
				"s &\t> save encryprion commands to text file \"&\"."  + "\n");

		Scanner scan = new Scanner(System.in);
		String myMessage = scan.nextLine();
		//scan.close();
		myMix.setInitialMessage(myMessage);
		System.out.println(myMix);
		
		String cmd = scan.nextLine();
		while (cmd.charAt(0) != 'Q'){
			myMix.processCommand(cmd);
			System.out.println(myMix);
			cmd = scan.nextLine();
		}
		scan.close();
	}
}
