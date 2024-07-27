package DataStructures2k24;

public class LinkList2k24 {

	public static void main(String[] args) {
		
		SinglyLinkedList list = new SinglyLinkedList(5);
		
		
		list.append(1);
		list.append(2);
		list.prepend(4);
		list.prepend(10);
		list.append(16);
		list.insert(1, 72);
		list.insert(4, 32);

		
		list.print();
		

	}


}

class SinglyLinkedNode {
	private int value;
	private SinglyLinkedNode next;
	
	public SinglyLinkedNode(int value) {
		this.value = value;
		this.next = null;
	}
	
	public SinglyLinkedNode(int value, SinglyLinkedNode next) {
		this.value = value;
		this.next = next;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public SinglyLinkedNode getNext() {
		return next;
	}

	public void setNext(SinglyLinkedNode next) {
		this.next = next;
	}
	
}

class SinglyLinkedList {
	private SinglyLinkedNode head, tail;
	private int length;
	
	public SinglyLinkedList(int value) {
		head = new SinglyLinkedNode(value);
		tail = head;
		length = 1;
	}
	
	public void prepend(int value) {
		head = new SinglyLinkedNode(value, head);
		length++;
	}
	
	public void append(int value) {
		SinglyLinkedNode temp = new SinglyLinkedNode(value);
		tail.setNext(temp);
		tail = temp;
		length++;
	}
	
	public void print() {
		System.out.printf("%2s", "head --> ");
		this.printRecursive(head);
	}
	
	public void insert(int index, int value) {
		index = wrapIndex(index);
		if (index == 0) {
			prepend(value);
			return;
		}
		
		if (index == length - 1) {
			append(value);
			return;
		}
		
	SinglyLinkedNode leader = traverseIndex(index - 1);
	SinglyLinkedNode nextNode = leader.getNext();
	
	SinglyLinkedNode temp = new SinglyLinkedNode(value, nextNode);
	leader.setNext(temp);
	length++;
	}
	
	public SinglyLinkedNode traverseIndex(int index) {
		index = wrapIndex(index);
		SinglyLinkedNode curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		
		return curr;

	}
	
	private void printRecursive(SinglyLinkedNode base) {
		if (base != null) {
			System.out.printf("|%3d | %2s ", base.getValue(), (base.getNext() != null? " -->" : " --> null") );
			printRecursive(base.getNext());
		}
		return;
		
	}
	
	
	private int wrapIndex(int index) {
		return Math.max(Math.min(index, length - 1), 0);
	}
	
	
	
}
