package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	// field variables
	private static Scanner sc;
	private static BinarySearchTree bst = new BinarySearchTree();

	public static void main(String[] args) {
		bst.Insert(bst.getRoot(), 3);
		bst.Insert(bst.getRoot(), 5);
		bst.Insert(bst.getRoot(), 1);
		bst.Insert(bst.getRoot(), 6);
		bst.Insert(bst.getRoot(), 2);
		bst.Insert(bst.getRoot(), 0);
		bst.Insert(bst.getRoot(), 8);
		bst.Insert(bst.getRoot(), 7);
		bst.Insert(bst.getRoot(), 4);
		
		Node p = new Node(5);
		Node q = new Node(4);

		
		System.out.println(bst.lca(bst.getRoot(), p, q));
		
		
		//MenuScreen();
	}// end main

	// methods
	public static void MenuScreen() {
		System.out.print(PrintMenuChoices());

		switch (GetUserInput(PrintMenuChoices())) {// ask for user input while passing in the menu choices printing
		case 1:// insert
			System.out.print("Enter An Integer Element To Insert 》 ");
			bst.Insert(bst.getRoot(), GetUserInput("Enter An Integer Element To Insert 》 "));
			break;
		case 2:// Display
			Display();
			break;
		case 3:// Lowest Common Ancestor
			LCA();
			break;
		case 4:// exit
			System.out.println("「Exiting now...」");
			System.exit(0);
			break;
		default:
			// @formatter:off
			System.out.println("\n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
								"┇ Error: \n" +
								"┇ Input is not a valid Menu choice. \n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
								"┇ Msg: \n" +
								"┇ \033[3mPlease enter only 1 to 4 as input\033[0m \n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			// @formatter:on
			break;
		}// end switch

		MenuScreen();// loop MenuScreen
	}// end method

	public static void LCA() {// method to operate the LCA
		if (bst.IsTreeNotAvailable(bst.getRoot())) {// check if a tree is available
			// @formatter:off
			System.out.println("\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Error: \n" +
							"┇ There is no tree available yet. \n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Msg: \n" +
							"┇      \033[3mPlease create a tree first.\033[0m \n" +
							"┇ \033[3m(Tree:Root and leftChild or rightChild)\033[0m \n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			// @formatter:on
			return;// return if no tree is available
		} // end if

		// If there is a tree available, then run the LCA code below
		System.out.print(PrintLCA_Menu());
		Node A = LCA_NodeCheck(PrintLCA_Menu(), "A", 0);// checks if input is valid and is available on tree

		System.out.print(PrintLCA_Menu() + A.getKey() + "\n┇ Node B 》 ");
		Node B = LCA_NodeCheck(PrintLCA_Menu() + A.getKey() + "\n┇ Node B 》 ", "B", A.getKey());

		Node LCA = bst.LowestCommonAncestor(bst.getRoot(), A, B);// call LCA method to get the LCA
		// @formatter:off
			System.out.print("" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Output: \n" +
						"┇ LCA: " + LCA.getKey() + "\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			System.out.println();
			// @formatter:on
	}// end method

	// check if node is on tree and can be put on node A or B
	public static Node LCA_NodeCheck(String prompt, String nodeOf, int nodeAValue) {
		Node node = new Node(GetUserInput(prompt));// gets user input and checks if valid
		if (bst.IsKeyAvailable(bst.getRoot(), node.getKey()) != null) {// checks user the key if its on the tree
			return node;// if it is, return this node
		} // end if

		// if key is valid but not available on the tree, run code below
		// @formatter:off
			System.out.print("\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Error: \n" +
							"┇ Value of node "+ nodeOf +" was not found. \n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Msg: \n" +
							"┇ \033[3mThe value/s of the tree are the following:\033[0m \n" +
							"┇ ");
								bst.Display(bst.getRoot());//display available nodes on the tree
			System.out.println("\n⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃");
		// @formatter:on

		if (nodeOf == "A") {// checks if node A is the one using the method
			System.out.print(PrintLCA_Menu());
		} else {// else, its node B
			System.out.print(PrintLCA_Menu() + nodeAValue + "\n┇ Node B 》 ");
		} // end if else

		return LCA_NodeCheck(prompt, nodeOf, nodeAValue);// recursive call this method to ask user again
	}// end method

	public static int GetUserInput(String prompt) {// basically scans and returns the user input
		sc = new Scanner(System.in);

		if (sc.hasNextInt()) {// check if input is integer
			int key = sc.nextInt();// store it in key if it is
			return key;// return that value
		} // end if

		// If user tried to enter non integer input, this code below runs
		// @formatter:off
		System.out.println("\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Error: \n" +
						"┇ Input is not an integer value. \n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Msg: \n" +
						"┇ \033[3mPlease enter integer/s input only\033[0m \n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
		// @formatter:on
		System.out.print(prompt);// ask user again for input
		return GetUserInput(prompt);// call this method again to get the next user input
	}// end method

	public static void Display() {// displays the nodes in the tree
		// @formatter:off
		System.out.print("\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Display: \n" +
						"┇ Binary Search Tree \n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" + 
						"┇ Output: ");
		// @formatter:on
		bst.Display(bst.getRoot());
		System.out.println("\n⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
		
		bst.DisplayStructureTree(bst.getRoot());
		System.out.println();
	}// end method

	public static String PrintLCA_Menu() {// for menu looks only
		// @formatter:off
		String menuAsString = "\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ LCA:	\n" +
							"┇ Please put values to Node A and Node B. \n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Input: \n" +
							"┇ Node A 》 ";
		// @formatter:on
		return menuAsString;
	}// end method

	public static String PrintMenuChoices() {// for menu looks only
		//@formatter:off
		String menuAsString = "" + 
						"━━━━━━━━━━━━━━━━━\n"+ 
						"┃ 【 1 】 Insert \n" +
						"┃ 【 2 】 Display \n" + 
						"┃ 【 3 】 LCA \n" +
						"┃ 【 4 】 Exit \n" + 
						"━━━━━━━━━━━━━━━━━\n" + 
						"》 ";
		//@formatter:on
		return menuAsString;
	}// end method
}// end class


 class Node {
	// field variables
	private int key;
	private Node leftChild;
	private Node rightChild;

	public Node(int key) {
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
	}// end constructor

	public int getKey() {
		return key;
	}// end method

	public void setKey(int key) {
		this.key = key;
	}// end method

	public Node getLeftChild() {
		return leftChild;
	}// end method

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}// end method	

	public Node getRightChild() {
		return rightChild;
	}// end method

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}// end method
	
	public String toString() {
		return "Key=" + key;
	}

}// end class

class BinarySearchTree {
	// field variables
	private Node root;
	private Scanner sc;

	// root getter setter
	public Node getRoot() {
		return root;
	}// end method

	public void setRoot(Node root) {
		this.root = root;
	}// end method

	// insert
	public Node Insert(Node pointer, int key) {
		if (root == null) {// if current root is null
			// @formatter:off
			System.out.println("\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Error:			   ┇\n" +
							"┇ There is no root created yet.    ┇\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
							"┇ Msg: 				   ┇\n" +
							"┇ \033[3mCreating key as new root...Done!\033[0m ┇\n" +
							"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			// @formatter:on
			CreateRoot(key);// call create root
			return null;// stop insert method and return nothing
		} // end if

		if (pointer == null) {// insert condition
			pointer = new Node(key);// put the new key to pointer current node
			return pointer;// return the pointer thats added;
		} // end if

		// If the key to be inserted is less than the key of the current root
		if (key < pointer.getKey()) {
			// recursively insert the key into the left subtree of the current root.
			pointer.setLeftChild(Insert(pointer.getLeftChild(), key));

		} else if (key > pointer.getKey()) {// If the key is greater
			pointer.setRightChild(Insert(pointer.getRightChild(), key));// recursively insert it into the right subtree.
		} // end if else
		return pointer; // return the modified root
	}// end of method Node insert

	/*
	 * This is a method for displaying the nodes and printing it consecutively using
	 * Level Order Traversal
	 * 
	 * The Display method performs a level-order traversal of a binary tree starting
	 * from the root, printing the keys of each node. If the tree is empty (no
	 * root), it outputs an error message. Using a queue for efficient traversal,
	 * the method dequeues each node, prints its key, and enqueues its left and
	 * right children if present. The process continues until all nodes have been
	 * processed, resulting in a display of the binary tree's keys in a structured
	 * order.
	 */
	public void Display(Node pointer) {
		// Check if the root of the tree is null
		if (root == null) {
			System.out.print("No root available"); // Print an error message if the root is null
			return;
		} // end if

		Queue<Node> queue = new LinkedList<>(); // Initialize a queue for level-order traversal
		queue.add(getRoot()); // Enqueue the root node to start traversal

		while (!queue.isEmpty()) {
			Node current = queue.remove(); // Dequeue the current node
			System.out.print(current.getKey() + "  > "); // Print the key of the current node

			// Enqueue the left child if it exists
			if (current.getLeftChild() != null) {
				queue.add(current.getLeftChild());
			} // end if

			// Enqueue the right child if it exists
			if (current.getRightChild() != null) {
				queue.add(current.getRightChild());
			} // end if
		} // end while
	} // end method

	// This is a method for displaying the structure of the tree and its edges using
	// level order traversal
	/*
	 * Like the Display() method it uses the same logic, it stores the values of every nodes including null 
	 * values in a queue and printing it. If the node is null it adds a new node that has a value of 0 to the queue
	 * while if the node is not null then it adds the current node in the queue. If the node is null it prints a single 
	 * space length while if the node is not null it prints the value of node. Each printing has a space that is unique in each level,
	 * for example if the true height of the tree is 3, then we have three levels. The bottom level has a two space length 
	 * and multiply it by two in each level upward.
	 * 
	 */
	public void DisplayStructureTree(Node pointer) {
		if (root == null) {
			return;
		} // end if

		Queue<Node> queue = new LinkedList<>();
		queue.add(getRoot());

		int height = 0;
		do {
			int counter = queue.size();// get the size

			if (counter == 0) {// base case
				break;
			}
			// call this method to create spaces and store it in String space
			String space = createSpace(getHeight(root), height, "");

			// call this method to print the arrow lines each level
			printArrowLines(height, counter, queue, space);

			System.out.println();
			while (counter > 0) {
				System.out.print(space);// print the String space
				Node current = queue.remove();

				if (current.getKey() == 0) {// if the node is null
					System.out.print(" ");
				} else {
					System.out.print(current.getKey());
				} // end if else

				// adding the nodes to the queue: if the node is null, add new node that
				// contains 0 value
				if (current.getLeftChild() != null) {
					queue.add(current.getLeftChild());
				} else {
					queue.add(new Node(0));
				} // end if else

				if (current.getRightChild() != null) {
					queue.add(current.getRightChild());
				} else {
					queue.add(new Node(0));
				} // end if else
				counter--;
				System.out.print(space);// print the String space
			} // end while
			System.out.println();
			height++;

		} while (height <= getHeight(root));// base case
	}// end method

	// lca
	/*
	 * The LCA method needs a pointer node, and a node A and B for the method to
	 * find their lowest ancestor. It first check if the pointer is null/if its left
	 * == the key or its right == the key, if the key was found it will instantly
	 * return the node pointer if that value. Now if the key was not found by the
	 * first block of code, the next codes will. Those nodes stores the value for
	 * each side, if no key is found it returns a null and if a key is found it
	 * returns that key.
	 */
	public Node LowestCommonAncestor(Node pointer, Node A, Node B) {
		// check if pointer is null or pointer key is equal to A or B keys
		if (pointer == null || pointer.getKey() == A.getKey() || pointer.getKey() == B.getKey()) {
			return pointer;
		} // end if

		Node tempLeftChild = LowestCommonAncestor(pointer.getLeftChild(), A, B);// store leftChild for checking
		Node tempRightChild = LowestCommonAncestor(pointer.getRightChild(), A, B);// store rightChild for checking

		if (tempLeftChild == null) {// if leftChild is null, return rightChild
			return tempRightChild;

		} else if (tempRightChild == null) {// if rightChild is null, return leftChild
			return tempLeftChild;
		} // end if else

		return pointer;// if both is not null then it is the node that we are trying to find
	}// end method
	
	public Node lca (Node root, Node p, Node q) {
		if (root == null) return null;
		
		if (root.getKey() > p.getKey() && root.getKey() > q.getKey()) 
			return lca(root.getLeftChild(), p, q);
		
		if (root.getKey() < p.getKey() && root.getKey() < q.getKey()) 
			return lca(root.getRightChild(), p, q);
		
		return root;
	}

	// custom methods
	/*
	 * This method just creates a root if called. A key is passed as parameter to
	 * set that key as new node.
	 */
	public void CreateRoot(int key) {
		Node tempNode = new Node(key);
		root = tempNode;
		Main.Display();
	}// end method

	/*
	 * This method checks if the root has no child, meaning the LCA is not possible
	 */
	public boolean IsTreeNotAvailable(Node pointer) {
		return pointer == null || pointer.getLeftChild() == null && pointer.getRightChild() == null;
	}// end method

	// check if the key that is passed in, is available in the tree
	/*
	 * This method checks the tree if it contains the key that is passed in this
	 * method. If no key is found, it returns null if key is found, it returns that
	 * node.
	 */
	public Node IsKeyAvailable(Node pointer, int key) {
		while (key != pointer.getKey()) {
			if (key < pointer.getKey()) {
				pointer = pointer.getLeftChild();
			} else if (key > pointer.getKey()) {
				pointer = pointer.getRightChild();
			} // end if else

			if (pointer == null) {
				return null;
			} // end if
		} // end while

		return pointer;
	}// end method

	/* finding the height of the tree
	 * 
	 * This method uses recursive function to traverse the left child and right child nodes of the tree
	 * Compare which has the highest values between left nodes and right nodes 
	 * and return the value obtained add 1 because we include the root 
	 */
	public int getHeight(Node pointer) {
		if (pointer != null) {
			// compare left height to right height and return the highest number
			return Math.max(getHeight(pointer.getLeftChild()), getHeight(pointer.getRightChild()) + 1); // add 1 in each traversal
		} // end if

		return 0; // if null return 0
	}// end method

	// creating spaces to adjust each printing of nodes and arrow lines for the structure
	/*
	 * This method starts with getting the total number of levels (true height) of the tree.
	 * Think of the bottom level of the the tree has a two space length, 
	 * and every level upward is multiplied by two so that we can create a triangle structure
	 * The parameter height in this method is the determinant on what level we are now in the tree
	 * 
	 */
	public String createSpace(int trueHeight, int height, String spacing) {
		int space = 1;
		// the bottom level of the tree has one space and each level upward is
		// multiplied the space by 2
		for (int i = height; i < trueHeight; i++) {
			space *= 2;
		} // end for

		// add one tab space
		for (int i = 0; i < space; i++) {
			spacing += "	";
		} // end for

		return spacing;
	}// end method

	// printing the arrow lines in each level
	/*
	 * This method starts with getting the node value and determining whether it is left node or right node
	 * 
	 * We can determine whether a node is right or left by thinking that in each level of the tree we have 
	 * an even number nodes including the null/zero values except for the level zero or the root.
	 * With this in mind we can use the counter parameter which is the total number of nodes in 
	 * each level. The counter value decreases as it prints "/" or "\" in the loop. 
	 * Counter parameter has a starting value of even, and each even value is where the left node located and 
	 * every odd value is where the right located in each level.
	 */
	public void printArrowLines(int height, int counter, Queue<Node> queue, String space) {
		if (height != 0) {

			while (counter > 0) {//base case
				Node current = queue.remove();

				if (current.getKey() != 0) {
					if (counter % 2 == 0) {//if counter is even number, it is left, print slash
						System.out.print(space + "/");
					} // end if
					if (counter % 2 != 0) {//if counter is odd number, it is right, print back slash 
						System.out.print(space + "\\");
					} // end if
					System.out.print(space);
				} else { // if the node is equal to zero or null
					System.out.print(space + " ");
					System.out.print(space);
				} // end if else
				queue.add(current);// add the current node back again to the queue
				counter--;

			} // end while
			counter = queue.size();// make the counter go back to its original value
		} // end If
	}// end method
}// end class