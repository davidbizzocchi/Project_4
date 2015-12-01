package project4;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.lang.StringBuilder;

public class MyLinkedList<E> {

	private Node<E> top;

	private Node<E> tail;
	
	private String currentClip = null;
	
	public Node<E> getTop() {
		return top;
	}

	public Node<E> getTail() {
		return tail;
	}

	public void setTail(Node<E> tail) {
		this.tail = tail;
	}

	public void setTop(Node<E> top) {
		this.top = top;
	}
	

	public String getCurrentClip() {
		return currentClip;
	}

	public void setCurrentClip(String currentClip) {
		this.currentClip = currentClip;
	}

	public MyLinkedList() {
		top = null;
		tail = null;
	}

	public void addFirst (E data) {

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

	public void addAtLocation (E data, int location){

		if(top == null)
			top = new Node<E>(data, top);
		else{
			Node<E> temp = getTop();
			for(int i = 1; i < location-1; i++){
				temp = temp.getNext();
			}

			temp.setNext(new Node<E>(data,temp.getNext()));
		}
	}

	public void display() {

		Node<E> temp = getTop();
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

	public int count() {
		int count = 0;
		Node<E> temp = getTop();
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}

	public void addAtEnd (E data) {

		if (top == null) {
			tail = top = new Node<E> (data, getTop());
		}

		else {
			tail.setNext(new Node<E>(data, null));
			tail = tail.getNext();
		}
	} 


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

	public void deleteAll (E data) {
		for(int i = 0; i <= count(); i++){
			delete(data);
		}
	}
	
	public void deleteAtLocation (int location){
		
		if(top == null)
			System.out.println("EMPTY LIST");
		else{
		Node<E> temp = top;
		for(int i = 1; i < location-1; i++){
			temp = temp.getNext();
		}
		temp.setNext(temp.getNext().getNext());
		}
	}
	
	public void deleteGroup (int start, int end){
		
		Node<E> temp = top;
		if(top == null)
			System.out.println("EMPTY LIST");
//		if(start == 1){
//			top = top.getNext();
//			start++;
//		}
		
		for(int i = 0; i < start-1; i++){
			temp = temp.getNext();
		}
		
		Node<E> tempEnd = temp;
		
		for(int i = start-1; i <= (start+(end-start)); i++){
			tempEnd = tempEnd.getNext();
		}
		temp.setNext(tempEnd);
		
	}

	public Node<E> get(int location){

		Node<E> temp = getTop();
		for(int i = 0; i < location; i++){
			temp = temp.getNext();
		}

		return temp;
	}
	
	public void swap(int x, int y){
		
		//TODO check for negative values
		
		try{
		x-=1;y-=1;
		E temp = get(x).getData();
		get(x).setData(get(y).getData());
		get(y).setData(temp);
		}
		catch(NullPointerException e){
			JFrame warning = new JFrame ("WARNING");
			JOptionPane.showMessageDialog(warning,
		    "Values not in list",
		    "CANNOT SWAP VALUES",
		    JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public String copyToClipBoard(int start, int finish){
		String clipped = "";
		Node<E> temp = top;
		
		if(top == null)
			System.out.println("EMPTY LIST");
		
		for(int i = 1; i <= start; i++){
			temp = temp.getNext();
		}
		
		for(int i = start; i < (start+(finish-start)+1); i++){
			clipped += temp.getData();
			temp = temp.getNext();
		}
		currentClip = clipped;
		return clipped;
	}
	
	public void pasteFromClipboard(int location){
		Node<E> temp = getTop();
		
		for(int i = 0; i < location; i++){
			temp = temp.getNext();
		}
		Node<E> temp2 = temp.getNext();
		
		for(int j = 0; j < currentClip.length(); j++){
			temp.setNext(new Node(currentClip.charAt(j),null));
			temp = temp.getNext();
		}
		temp.setNext(temp2);
	}

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
