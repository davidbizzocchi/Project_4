package project4;

/**********************************************************
 * @author David Bizzocchi
 * @version NOvmber, 2015
 *
 * @param <E> Type of data for linked list
 * 
 * Creates nodes of generic type for single linked list
 *********************************************************/

public class Node<E> {
	
		public E data;
		public Node<E> next;
		
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}

		public Node() {
		}

		public E getData() {
			return data;
		}
		
		public void setData(E data) {
			this.data = data;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
}
