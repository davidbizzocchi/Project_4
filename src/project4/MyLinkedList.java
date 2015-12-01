package project4;

/************************************************************
 * @author David Bizzocchi
 * @version November, 2015
 * 
 * Custom single linked list class
 ***********************************************************/

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.lang.StringBuilder;

public class MyLinkedList<E> {

	/** Top or starting node of linked list */
	private Node<E> top;

	/** Tail or ending node of linked list */
	private Node<E> tail;

	/** keeps track of current clipboard */
	private String currentClip = null;

	/*********************************************
	 * @return top node of list
	 * 
	 * Returns first node from linked list.
	 ********************************************/
	public Node<E> getTop() {
		return top;
	}

	/********************************************
	 * @return tail node of list
	 * 
	 * Returns last node from linked list.
	 *******************************************/
	public Node<E> getTail() {
		return tail;
	}

	/********************************************
	 * @param tail sets end of node list
	 * 
	 * sets param to end of linked list
	 *******************************************/
	public void setTail(Node<E> tail) {
		this.tail = tail;
	}

	/*****************************************************
	 * @param top node to set at beginning of linked list
	 * 
	 * sets param to front of linked list
	 *****************************************************/
	public void setTop(Node<E> top) {
		this.top = top;
	}

	/**********************************************
	 * @return current clip board
	 * 
	 * returns current clip board
	 *********************************************/
	public String getCurrentClip() {
		return currentClip;
	}

	/***********************************************
	 * @param currentClip
	 * 
	 * sets param to current clip board
	 ***********************************************/
	public void setCurrentClip(String currentClip) {
		this.currentClip = currentClip;
	}

	/***********************************************
	 * Constructor for linked list class
	 **********************************************/
	public MyLinkedList() {
		top = null;
		tail = null;
	}

	/***********************************************************
	 * @param data element to be added to front of linked list
	 * 
	 * Adds parameter to the front of the linked list
	 **********************************************************/
	public void addFirst (E data) {

		// check for empty list
		if(top == null){
			top = new Node<E>(data, null);
			tail = top;
		}
		else{
			Node<E> temp = new Node<E>();
			temp.setData(data);
			temp.setNext(top);
			top = temp;
		}
	}

	/*********************************************************
	 * @param data element to be added to linked list
	 * @param location location of data to be added
	 * 
	 * Adds data to specified location in linked list
	 ********************************************************/
	public void addAtLocation (E data, int location){

		// check for empty list
		if(top == null)
			top = new Node<E>(data, top);

		//check for invalid boundaries
		else if((location < 0) || (location > count()))
			System.out.println
			("\n\tERROR: COMMAND OUT OF BOUNDS!\n");

		//otherwise perform operation
		else{
			Node<E> temp = getTop();
			//set temp to space before desired position
			for(int i = 1; i < location-1; i++){
				temp = temp.getNext();
			}

			//set param data to location
			temp.setNext(new Node<E>(data,temp.getNext()));
		}
	}

	/*************************************
	 * Displays all of linked list.
	 ************************************/
	public void display() {

		Node<E> temp = getTop();
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

	/********************************************
	 * @return number of elements in linked list
	 * 
	 * Counts elements in linked list
	 *******************************************/
	public int count() {
		int count = 0;
		Node<E> temp = getTop();
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}

	/**********************************************************
	 * @param data element to be added to tail of linked list
	 * 
	 * Adds element to end or tail of linked list
	 *********************************************************/
	public void addAtEnd (E data) {

		if (top == null) {
			tail = top = new Node<E> (data, getTop());
		}

		else {
			tail.setNext(new Node<E>(data, null));
			tail = tail.getNext();
		}
	} 

	/********************************************************
	 * @param data element to be deleted
	 * @return data that was deleted
	 * 
	 * Deletes first element of specified data.
	 *******************************************************/
	public E delete (E data) {

		// missing list case
		if (top == null) 
			return null;

		// first in list matches
		if (top.getData().equals(data)) {
			top = top.getNext();
			if(top == null)
				tail = null;
			return data;
		}

		// match found later in list	
		Node<E> temp = top;
		while((temp.getNext() != null)){
			if(temp.getNext().getData().equals(data)){
				temp.setNext(temp.getNext().getNext());
				if(temp.getNext() == null)
					tail = temp;
				return data;
			}
			temp = temp.getNext();
		}

		// no match found
		return null;

	}

	/**************************************************
	 * @param data element to be deleted
	 * 
	 * Deletes all instances of element in linked list.
	 *************************************************/
	public void deleteAll (E data) {
		for(int i = 0; i <= count(); i++){
			delete(data);
		}
	}

	/********************************************************
	 * @param location location of node that will be deleted
	 * 
	 * deleted specific single node in linked list.
	 *******************************************************/
	public void deleteAtLocation (int location){

		try{
			//ensures valid location
			if(location > 0 && location <= count()){
				// empty list check
				if(top == null)
					System.out.println("EMPTY LIST");
				else if(location == 1)
					top = top.getNext();
				else{
					Node<E> temp = top;
					//set node to space before location
					for(int i = 1; i < location-1; i++){
						temp = temp.getNext();
					}
					//skips specified node in linked list
					temp.setNext(temp.getNext().getNext());
				}
			}
			else
				System.out.println
				("\n\tERROR: UNABLE TO REACH LOCATION!\n");
		}
		catch(NumberFormatException e){
			System.out.println("\n\tERROR: NON_NUMBER LOCATION!\n");
		}

	}

	/**********************************************************
	 * @param start beginning of node group
	 * @param end end of node group
	 * 
	 * deletes group of nodes between specified start and end
	 *********************************************************/
	public void deleteGroup (int start, int end){

		Node<E> temp = top;

		//empty list
		if(top == null)
			System.out.println("EMPTY LIST");

		//check boundary exceptions
		else if((start < 0) || (end > count()))
			System.out.println
			("\n\tERROR: COMMAND OUT OF BOUNDS!\n");

		//check start is before end
		else if(end < start)
			System.out.println
			("\n\tERROR: CANNOT END BEFORE START!\n");

		//otherwise perform operation
		else{
			//set node to before start
			for(int i = 0; i < start-1; i++){
				temp = temp.getNext();
			}

			//set reference node
			Node<E> tempEnd = temp;

			//sets reference node to after group
			for(int i = start-1; i <= (start+(end-start)); i++){
				tempEnd = tempEnd.getNext();
			}

			//linked list points over the specified group
			temp.setNext(tempEnd);
		}
	}

	/*********************************************
	 * @param location location of desired node
	 * @return node at desired location
	 * 
	 * Returns node at specified location
	 ********************************************/
	public Node<E> get(int location){

		Node<E> temp = getTop();
		for(int i = 0; i < location; i++){
			temp = temp.getNext();
		}

		return temp;
	}

	/*******************************************
	 * @param x location of first swap value
	 * @param y location of second swap value
	 * 
	 * swaps nodes at the two given locations
	 ******************************************/
	public void swap(int x, int y){

		// check for invalid boundaries
		if((x < 0) || (x >= count()) ||
				(y < 0) || (y >= count()))
			System.out.println
			("\n\tERROR: COMMAND OUT OF BOUNDS!\n");
		
		//otherwise swap nodes
		else{
			//set first data in temp variable
			E temp = get(x).getData();
			//set first node to second
			get(x).setData(get(y).getData());
			//set second node to temp variable
			get(y).setData(temp);
		}
	}

	/******************************************************
	 * @param start starting position of copy to clip board
	 * @param finish ending position of copy to clip board
	 * @return string that will be the current clip board
	 * 
	 * Copies specified nodes to a clip board.
	 */
	public String copyToClipBoard(int start, int finish){

		//temporary clip board
		String clipped = "";

		Node<E> temp = top;

		//empty list
		if(top == null)
			System.out.println("EMPTY LIST");

		//check boundary exceptions
		else if((start < 0) || (finish > count()))
			System.out.println
			("\n\tERROR: COMMAND OUT OF BOUNDS!\n");

		//check start is before end
		else if(finish < start)
			System.out.println
			("\n\tERROR: CANNOT END BEFORE START!\n");

		//otherwise perform operation
		else{
			//sets node to space before start
			for(int i = 1; i <= start; i++){
				temp = temp.getNext();
			}

			//copies data until specified end to clip board
			for(int i = start; i < (start+(finish-start)+1); i++){
				clipped += temp.getData();
				temp = temp.getNext();
			}
			//sets clip board
			currentClip = clipped;
			return clipped;
		}

		return null;
	}

	/*****************************************************************
	 * @param location space in message to paste clip board to
	 * 
	 * pastes current clip board to linked list in specified position
	 ****************************************************************/
	public void pasteFromClipboard(int location){
		Node<E> temp = getTop(); 
		Node<E> temp2 = getTop();

		// check for empty list
		if(top == null)
			System.out.println("EMPTY LIST");

		//check for invalid boundaries
		else if((location < 0) || (location > count()))
			System.out.println
			("\n\tERROR: COMMAND OUT OF BOUNDS!\n");

		//otherwise perform operation
		else{
			//sets temp variables to space before location
			for(int i = 0; i < location; i++){
				temp = temp.getNext();
				temp2 = temp2.getNext();
			}
			//stores next value in temp
			temp2 = temp2.getNext();

			//pastes each individual values at start location
			for(int j = 0; j < currentClip.length(); j++){
				temp.setNext(new Node(currentClip.charAt(j),null));
				temp = temp.getNext();
			}
			//sets end of string to end
			temp.setNext(temp2);
		}
	}

	/********************************************************
	 * @return readable string
	 * 
	 * returns linked list to readable, numbered string
	 *******************************************************/
	@Override
	public String toString(){

		StringBuilder display = new StringBuilder();

		display.append("Current Message:\n");

		for(int i = 0; i < count(); i++){
			display.append(get(i).getData() + "  ");
		}
		display.append("\n");
		for(int i = 0; i < count(); i++){
			display.append((i + 1) + " ");
			if(i < 9)
				display.append(" ");
		}

		return display.toString();
	}

}
