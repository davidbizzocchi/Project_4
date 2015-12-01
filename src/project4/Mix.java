package project4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/******************************************************
 * 
 * @author David Bizzocchi
 * @version November 2015
 *
 *****************************************************/

public class Mix implements IMix{


	private MyLinkedList<Character> message;
	
	private ArrayList<String> commands = new ArrayList<String>();

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

	public void setInitialMessage(String message) {

		this.message = StringToLinkedList(message);

	}

	private MyLinkedList<Character> StringToLinkedList(String m) {

		MyLinkedList<Character> msg = new MyLinkedList<Character>();
		for(int i = m.length()-1; i >= 0; i--){
			msg.addFirst(m.charAt(i));
		}
		return msg;
	}
	
	public void save(String fileName){
		try {
			FileWriter saveFile = new FileWriter(fileName);
			
			for(int i = 0; i < commands.size(); i++)
				saveFile.write(commands.get(i));
			
			saveFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String processCommand(String command) {

		String [] split = command.split("\\s+");

		switch((char)split[0].charAt(0)){
		case 'b': message.addAtLocation((char)split[1].charAt(0), Integer.parseInt(split[2]));
		break;
		case 'r': message.deleteAtLocation(Integer.parseInt(split[1]));
		break;
		case 'w': message.swap(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		break;
		case 'x': message.copyToClipBoard((Integer.parseInt(split[1])-1), (Integer.parseInt(split[2]))-1);
			message.deleteGroup((Integer.parseInt(split[1])-1), (Integer.parseInt(split[2]))-1);
		break;
		case 'p': message.pasteFromClipboard(Integer.parseInt(split[1])-1);
		break;
		case 'c': message.copyToClipBoard((Integer.parseInt(split[1])-1), (Integer.parseInt(split[2]))-1);
		break;
		case 'v': System.out.println("Current Clip Board: " + (message.getCurrentClip()) + "(" + message.getCurrentClip().length() + " char long)");
		break;
		case 'a': message.setCurrentClip(null);
		break;
		case 's': save(split[1]);
		break;
		}
		
		if(message.getCurrentClip() != null)
			commands.add(command + "\t" + message.getCurrentClip() + "\n");
		else
			commands.add(command + "\n");
		
		return command;
	}

	@Override
	public String toString(){
		return(message.toString());
	}
}
