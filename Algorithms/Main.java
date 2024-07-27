package Algorithms;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Link link = new Link();
		link.run();

	}

}

class Link {
	SinglyLinkedList list = new SinglyLinkedList();
	Scanner sc = new Scanner(System.in);

	public void run() {
		while(true) {
			System.out.println("""
								----------------------
								| Singly Linked List |
								|---------------------
								| [1]Append.         |
								| [2]Remove          |
								| [3]Remove All      |
								| [4]Display         |
								| [5]End Program     |
								----------------------
								""");
			
			
			int response = sc.nextInt();
			
			switch (response) {
			case 1 -> {
				System.out.println("Enter a value: ");
				int value = sc.nextInt();
				list.insertEnd(value);
			}
			case 2 -> {
				System.out.println("Enter the index of the node: ");
				int index = sc.nextInt();
				list.remove(index);
			}
			case 3 -> list.removeAll();
			case 4 -> list.print();
			case 5 -> 	System.exit(0);
			default -> 	System.err.println("Invalid entry!");
			
			}
			}
	}
}


class ListNode {
	private int value;
	private ListNode next;
	
	public ListNode(int val) {
		this.value = val;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
	
	public String toString() {
		return "[%3d]".formatted(value);
	}
	
}

class SinglyLinkedList {
	ListNode head, tail;
	
	public SinglyLinkedList() {
		head = new ListNode(-1);
		tail = head;
	}
	
	public void insertEnd(int val) {
		tail.setNext(new ListNode(val));
		tail = tail.getNext();
	}
	
	public void remove(int index) {
		index--;
		int i = 0;
		ListNode curr = head;
		while (i < index && curr != null) {
			i++;
			curr = curr.getNext();
		}
		
		if (curr != null) curr.setNext(curr.getNext().getNext());
	}
	
	public void removeAll() {
		head = new ListNode(-1);
		tail = head;
		
	}
	
	public void print() {
		System.out.printf("%6s%s%n","", "Curr List");
		this.print_recur(head.getNext());
		System.out.print("null\n");
	}
	
	public void print_recur(ListNode node) {
		if (node != null) {
			System.out.print(node + "->");
			print_recur(node.getNext());
		}
	}
	
	
}



