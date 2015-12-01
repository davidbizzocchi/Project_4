package project4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/******************************************************
 * 
 * @author David Bizzocchi
 * @version November 2015
 *
 * Mix class for user message
 *****************************************************/

public class Mix implements IMix{

	/** Linked list for user message to mix */
	private MyLinkedList<Character> message;

	/** Arraylist of commands, with clip board when appropriate */
	private ArrayList<String> commands = new ArrayList<String>();

	/************************************************************
	 * @param args
	 * 
	 * Main method used for generic testing
	 ***********************************************************/
	public static void main(String[] args){
		MyLinkedList<Character> list = new 
				MyLinkedList<Character>();

		list.addFirst(' ');
		list.addFirst('O');
		list.addFirst('L');
		list.addFirst('L');
		list.addFirst('E');
		list.addFirst('H');
		list.addAtEnd('W');
		list.addAtEnd('O');
		list.addAtEnd('R');
		list.addAtEnd('L');
		list.addAtEnd('D');
		list.addAtEnd('!');

		System.out.println(list);

		list.addAtLocation('d',9);
		//myInter.processCommand("b Q 9");

		System.out.println(list);

		list.deleteAtLocation(5);

		System.out.println(list);

		list.swap(1, 5);

		System.out.println(list);

		list.deleteGroup(1, 3);

		System.out.println(list);
	}

	/********************************************************
	 * @param message Message to mix within class
	 * 
	 * Sets initial message for mix class
	 *******************************************************/
	public void setInitialMessage(String message) {
		this.message = StringToLinkedList(message);
	}

	/********************************************************
	 * @param m string to be converted to char and into linked list
	 * @return linked list of char, same as original param string
	 * 
	 * Converts string to linked list of characters.
	 *******************************************************/
	private MyLinkedList<Character> StringToLinkedList(String m) {

		// temporary message to convert string to
		MyLinkedList<Character> msg = new MyLinkedList<Character>();

		//adds each element to temp linked list
		for(int i = m.length()-1; i >= 0; i--){
			msg.addFirst(m.charAt(i));
		}
		return msg;
	}

	/*************************************************************
	 * @param fileName name file will be saved under
	 * 
	 * Saves commands under user specified name
	 *************************************************************/
	public void save(String fileName){
		try {
			// new file writer with user defined name
			FileWriter saveFile = new FileWriter(fileName);

			// writes each command on seperate line in file
			for(int i = 0; i < commands.size(); i++)
				saveFile.write(commands.get(i) + "");

			saveFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**************************************************************
	 * @param command user specified mixing command
	 * 
	 * Takes user defined string and mixes linked list accordingly.
	 *************************************************************/
	public String processCommand(String command) {

		// splits command by spaces
		String [] split = command.split("\\s+");

		if(split[0].length() == 1){
			// switches function based on initial letter command
			switch((char)split[0].charAt(0)){
			
			// insert character at location
			case 'b': try{Integer.parseInt(split[2]);}
			catch(NumberFormatException e)
			{System.out.println
				("\n\tERROR: NON_NUMBER LOCATION!\n");
			break;}
			message.addAtLocation((char)split[1].charAt(0), 
					Integer.parseInt(split[2]));
			break;

			// removes character at position
			case 'r': 
				try{message.deleteAtLocation
					(Integer.parseInt(split[1]));}
				catch(NumberFormatException e)
				{System.out.println
					("\n\tERROR: NON_NUMBER LOCATION!\n");}
				break;

			//switch characters at positions
			case 'w': try{message.swap
				(Integer.parseInt(split[1])-1, 
						Integer.parseInt(split[2])-1);}
			catch(NumberFormatException e)
			{System.out.println("\n\tERROR: NON_NUMBER LOCATION!\n");}
			break;

			//cut to clip board
			case 'x': try{message.copyToClipBoard
			((Integer.parseInt(split[1])-1),
					(Integer.parseInt(split[2]))-1);
			message.deleteGroup
			((Integer.parseInt(split[1])-1),
					(Integer.parseInt(split[2]))-1);}
			catch(NumberFormatException e)
			{System.out.println("\n\tERROR: NON_NUMBER LOCATION!\n");}
			break;

			//paste from clip board
			case 'p': try{message.pasteFromClipboard
			(Integer.parseInt(split[1])-1);}
			catch(NumberFormatException e)
			{System.out.println("\n\tERROR: NON_NUMBER LOCATION!\n");}
			break;

			//copy from clip board
			case 'c': try{message.copyToClipBoard
			((Integer.parseInt(split[1])-1),
					(Integer.parseInt(split[2]))-1);}
			catch(NumberFormatException e)
			{System.out.println
				("\n\tERROR: NON_NUMBER LOCATION!\n");}
			break;

			//prints current clip board
			case 'v': System.out.println
			("Current Clip Board: " + (message.getCurrentClip()) + 
					"(" + message.getCurrentClip().length() 
					+ " char long)");
			break;

			//clears clip board
			case 'e': message.setCurrentClip(null);
			System.out.println("CLIP BOARD CLEARED");
			break;

			//adds node at end
			case 'a': message.addAtEnd((char)split[1].charAt(0));
			break;

			//saves commands
			case 's': save(split[1]);
			break;
			default: System.out.println("\n\tERROR: UNKNOWN COMMAND!\n");
			}
		//save command, and clip board if applicable, for saving to file
			if(message.getCurrentClip() != null)
				commands.add
				(command +"\t"+ message.getCurrentClip()+"\n");
			else
				commands.add(command + "\n");
		}
		else
			// if command is not known
			System.out.println("\n\tERROR: UNKNOWN COMMAND!\n");
		return command;
	}

	/***********************************************************
	 * @return readable string of linked list
	 * 
	 * Returns readable string of mixed message.
	 **********************************************************/
	@Override
	public String toString(){
		return(message.toString());
	}
}
