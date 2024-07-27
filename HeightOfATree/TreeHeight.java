package HeightOfATree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeHeight {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		
		System.out.println("Do you want to use predetermined data? \n[1] Okay! \n[2] Nope!");
		int store_initData = sc.nextInt();
		switch(store_initData) {
		case 1 -> {
			int[] array = {8, 3, 10, 1, 6, 14, 4, 7, 13}; 
			for (int anArray : array) {
				tree.add(anArray);
			}
		}
		
		case 2 -> {
			break;
		}
		}
		
		while(true) {
		System.out.println("Select from the following: ");
		System.out.println("[1] Add \n[2] Find Height \n[3] Terminate ");
		int response = sc.nextInt();
		
		switch (response) {
		case 1 -> {
			System.out.println("Please insert something: ");
			int input_add = sc.nextInt();
			
			tree.add(input_add);
			
		}
		case 2 -> {
			System.out.println("I found it = " + tree.find(tree.getTheRoot(), 3));
			tree.print();
			System.out.println("Find Height of a Vertex\nPlease Enter the Data Within The Vertex:  ");
			int theData = sc.nextInt();
			
			System.out.println("Finding Height Of " + theData + "...\nHeight: " + tree.height(theData));
			
		}
		case 3 -> {
			return;
		}
		}

	}
}

	static class Vertex {
		private Vertex leftChild, rightChild;
		private int theData;

		public Vertex(int theData) {
			this.theData = theData;
		}

		public Vertex getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Vertex leftChild) {
			this.leftChild = leftChild;
		}

		public Vertex getRightChild() {
			return rightChild;
		}

		public void setRightChild(Vertex rightChild) {
			this.rightChild = rightChild;
		}

		public int getTheData() {
			return theData;
		}

		public void setTheData(int theData) {
			this.theData = theData;
		}

		public String toString() {
			return "[" + this.theData + "]";
		}
		
		public boolean isLeftChildNull() {
			return this.getLeftChild() == null;
		}
		
		public boolean isRightChildNull() {
			return this.getRightChild() == null;
		}

	}

	static class BinaryTree {
		private Vertex theRoot;

		public BinaryTree() {
			this.theRoot =  null;
		}
			
		private boolean isEmpty() {
			return this.theRoot == null;
		}

		public Vertex getTheRoot() {
			return theRoot;
		}

		public void setTheRoot(Vertex theRoot) {
			this.theRoot = theRoot;
		}
		
		public void add(int data) {
			setTheRoot(insert(getTheRoot(), data));
		}
		
		
		private Vertex insert(Vertex start, int data) {
			if (start == null) return new Vertex(data);

			if (data < start.getTheData()) {
				start.setLeftChild(insert(start.getLeftChild(), data));
			}
			else {
				start.setRightChild(insert(start.getRightChild(), data));
			}
			return start;
		}
		
		
		
		public void print() {
			this.list(getTheRoot());
		}
		
		private void list(Vertex start) {
			int space = 9;
			int numFormat = 0;
			Queue<Vertex> myQueue = new LinkedList<Vertex>();
			myQueue.add(start);

			while (!myQueue.isEmpty()) {
				int size = myQueue.size();
				
				for (int j = 0; j < space; j++) {
					System.out.print(" ");
				}
				
				for (int i = 0; i < size; i++) {
					Vertex curr = myQueue.remove();
					
					System.out.print(curr + " ");
					
					if (!curr.isLeftChildNull()) {
						myQueue.add(curr.getLeftChild());
					}

					if (!curr.isRightChildNull()) {
						myQueue.add(curr.getRightChild());
					}
					
				}
				
				System.out.println("Level " + numFormat++ +  " \n");
				space -= size;
			}

		}
		
		private Vertex find(Vertex start, int theData) {

			if (start == null) return null;
			if (theData == start.getTheData()) return start;

			if (theData < start.getTheData()) {
				return find(start.getLeftChild(), theData);
			} else {
				return find(start.getRightChild(), theData);
			}

		}
		
		private int height(int theData) {
			Vertex temp = find(getTheRoot(), theData);
		
			if (temp.getTheData() == getTheRoot().getTheData())
				return 0; 

			Queue<Vertex> myQueue = new LinkedList<Vertex>();
			myQueue.add(temp);
			int count_Height = 0;

			while (!myQueue.isEmpty()) {

				Vertex curr = myQueue.remove();

				if (!curr.isLeftChildNull()) {
					myQueue.add(curr.getLeftChild());
				}

				if (!curr.isRightChildNull()) {
					myQueue.add(curr.getRightChild());
				}
				
				if (curr.getLeftChild() != null || curr.getRightChild() != null) {
				count_Height++;
				}
			}
			return count_Height;

		}

	}
}
