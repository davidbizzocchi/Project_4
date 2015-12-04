package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/****************************************************
 * @author David Bizzocchi
 * @version November 2015 
 * ***************************************************/

public class UnMix implements IUnMix{

	private String[] commandList;

	private MyLinkedList<Character> message;
	
	private String unMixed;

	//private ArrayList<String> commands;
	//local main method for testing purposes
	public static void main(String[] args) {
		UnMix test = new UnMix();
		System.out.println(test.UnMixUsingFile("Test", "s is a secret messageThi"));
	}

	/**
	 * ************************************************************
	 * @param String fileName name of file
	 * @param mixedMessage the message being decrypted
	 *
	 * decrypts the code
	 * 
	 * @returns message.toString()
	 * ***********************************************************
	 */

	public String UnMixUsingFile(String fileName, String mixedMessage) {

		this.commandList = loadCommands(fileName);
		//instantiate MyLinkedList
		this.message = new MyLinkedList<>();
		//Add items from the mixedMessage param to linkedList
		for (int i = mixedMessage.length() - 1; i >= 0; i--) {
			message.addFirst(mixedMessage.charAt(i));
		}

		System.out.println(decrypt(commandList));
		
		return(unMixed);
	}

	/**
	 * *********************************************************
	 * @param fileName Name of the file to load
	 *
	 * @returns commands An arrayList with each element as a a command loaded
	 * from the file *********************************************************
	 */
	private String[] loadCommands(String fileName) {
		String[] commandsArray;
		//File file = new File(fileName);
		String content = "?";
		try {
			Scanner scanner = new Scanner(new File(fileName));
			content = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
		}
		System.out.println("CONTENT\n" + content);

		commandsArray = content.split("\n");
		
		return commandsArray;
	}

	/************************************************************
	 * @param String[] commands array of commands
	 *
	 * decrypts the code
	 * 
	 * @returns message.toString()
	 ************************************************************/
	public String decrypt(String[] commands) {

		for (int i = commands.length-1; i >= 0; i--) {

			//System.out.println(message.toString());
			String[] args = commandAsArray(commandList[i]);

			System.out.println("Command: " + args[0]);

			if (args[0] != " ") {
				// switches function based on initial letter command
				switch ((args[0].charAt(0))) {
				
				case 'b':
					message.deleteAtLocation(Integer.parseInt(args[2].trim()));
					break;

					// removes character at position
				case 'r':
					message.addAtLocation(args[2].charAt(0), Integer.parseInt(args[1].trim()));
					break;

				case 'w':
					message.swap(Integer.parseInt(args[1].trim()) - 1, Integer.parseInt(args[2].trim()) - 1);
					break;

				case 'a':
					message.deleteAtLocation(message.count());
					break;

				case 'x':
					for(int index = Integer.parseInt(args[1].trim())+1, j = 0; index <= Integer.parseInt(args[2].trim())+1; index++, j++){
						args[3] = args[3].replace('_', ' ');
						System.out.println(args[3]);
						message.addAtLocation(args[3].charAt(j),index-1);
					}
					break;
					
				case 'p': 
					System.out.println(args[2].length());
					for(int index = Integer.parseInt(args[1].trim())+1, j = 0; j < args[2].length(); j++){
						message.deleteAtLocation(index);
					}
					break;
					
					case 'c': break;
					case 'v': break;
					case 's': break;

				default:
					System.out.println("\n\tERROR: UNKNOWN COMMAND!\n");
				}

			} else {
				throw new IllegalArgumentException("\tCorrupt Encryption Key!");
			}
		}
		unMixed = message.ToStringNoSpaces();
		return message.toString();
	}
	/**************************************************************
	 * @param command raw string to be split
	 *
	 * takes command and splits into array
	 * @returns tempArray
	 * ***********************************************************/
	private String[] commandAsArray(String command) {
		String[] tempArray;

		tempArray = command.split(" ");

		return tempArray;
	}
}