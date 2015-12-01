package project4;

public class LinkedList<E> {
	
	private Node<E> top;

	private Node<E> tail;
	
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

	public void LinkListFirst() {
		top = null;
		tail = null;
	}

	public void addfirst (E data) {
		//top = new Node<E> (data, getTop());
		
		if(top == null){
			top = new Node<E>(data, top);
		}
		else{
			top = new Node<E>(data, top);
		}
		
		Node<E> temp = new Node<E>();
		temp.setData(data);
		temp.setNext(null);
		tail = temp;
	}
	
	public void addAtLocation (E data, int location){
		
		if(top == null)
			top = new Node<E>(data, top);
		
		Node<E> temp = top;
		for(int i = 0; i < location; i++){
			temp = temp.getNext();
		}
		
		temp = new Node<E>(data, temp.getNext());
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
}
