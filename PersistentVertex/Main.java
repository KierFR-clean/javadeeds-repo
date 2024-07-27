package PersistentVertex;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Persistent list = new Persistent();
		while (true) {
			System.out.println("""
								-------------------------------------
								|  Welcome To Java Persistent List  |
								-------------------------------------
								|[1] Append                         |
								|[2] Delete                         |
								|[3] Change Value                   |
								|[4] Display                        |
								|[5] Node History                   |
								|[6] List History                   |
								|[7] Terminate Program              |
								-------------------------------------
								|Type the operation desired:        |
								-------------------------------------
								""");
			int response = list.validateInt();
			switch(response) {
			case 1 -> {
				list.Append();
			}
			case 2 -> {
				list.Delete();
			}
			case 3 -> {
				list.Change_Value();
			}
			case 4 -> {
				list.Display();
			}
			case 5 -> {
				list.Node_History();
			}
			case 6 -> {
				list.List_History();
			}
			case 7 -> {
				System.out.println("""
									-----------------------
									|   Exiting Console   |
									-----------------------
									""");
				return;
			}
			
			}
		}
		
	}
	
	static class Vertex {
		private int data;
		private boolean state;
		private LinkedVertex recent;
		private Vertex to_next;
		
		public Vertex(int data) {
			recent = new LinkedVertex();
			this.data = data;
			this.state = false;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Vertex getTo_next() {
			return to_next;
		}
		public void setTo_next(Vertex to_next) {
			this.to_next = to_next;
		}
		public String toString() {
			return " [" + this.data + "|next] ";
		}
		
		public boolean isState() {
			return state;
		}
		public void setState(boolean state) {
			this.state = state;
		}
		public LinkedVertex getRecent() {
			return recent;
		}
		public void setRecent(LinkedVertex recent) {
			this.recent = recent;
		}
		public int toInt() {
			return this.data;
		}
		
		

	}
	
	static class LinkedVertex {
		private Vertex headvertex, tailvertex;

		public LinkedVertex() {
			this.headvertex = this.tailvertex = null;
		}

		public Vertex getHeadvertex() {
			return headvertex;
		}

		public void setHeadvertex(Vertex headvertex) {
			this.headvertex = headvertex;
		}

		public Vertex getTailvertex() {
			return tailvertex;
		}

		public void setTailvertex(Vertex tailvertex) {
			this.tailvertex = tailvertex;
		}
		
		private boolean empty() {
			return this.headvertex == null;
		}
		
		private boolean append(int input) {
			Vertex data = new Vertex(input);
			if (!empty()) {
				getTailvertex().setTo_next(data);
				setTailvertex(getTailvertex().getTo_next());
				return true;
			}
			setHeadvertex(data);
			setTailvertex(data);
			return true;
		}
		
		private boolean prepend(int input) {
			Vertex data = new Vertex(input);
			if (!empty()) {
				data.setTo_next(getHeadvertex());
				setHeadvertex(data);
				return true;
			}
			setHeadvertex(data);
			return true;
		}
		
		
		public boolean delete(int key) {
			return  delete(getHeadvertex(), --key, 0);
		}
		
		private boolean delete(Vertex start ,int key, int count) {
			if (start.isState()) {
				return delete(start.getTo_next(), key, count);
			}
			
			if (key == count) {
				start.setState(true);
				System.out.println("Data To Be Deleted: " + start.toInt());
				return true;
			}
			
			if (start.getTo_next() != null) {
				return delete(start.getTo_next(), key, count + 1);
			}
			return false;
		}
		
		public boolean modify(int key, int replaceWith) {
			return modify(getHeadvertex(), --key, 0, replaceWith);
		}
		
		private boolean modify(Vertex start ,int key, int count, int replaceWith) {
			if (start.isState()) {
				return modify(start.getTo_next(), key, count, replaceWith);
			}
			if (key == count) {
				System.out.println("Data To Be Changed: " + start.toInt());
				start.getRecent().prepend(start.toInt());
				start.setData(replaceWith);
				System.out.println("Changed To: " + start.toInt());
				return true;
			}
			if (start.getTo_next() != null) {
				return delete(start.getTo_next(), key, count + 1);
			}
			return false;
		}
		
		protected void print() {
			if (empty()) return;
			System.out.println("""
								  -----------------------
								  |     Linked List     |
								  -----------------------
								""");
			this.display(getHeadvertex());;
		}
		
		private void display(Vertex start) {
			if (start != null) {
				System.out.print(start + (start.getTo_next() != null ? "--->" : "---> null"));
				display(start.getTo_next());
			}
			return;
		}
		
		private void displayNonDeleted(Vertex start) {
			if (start == null) {
				return;
			}
			if (start.isState()) {
				displayNonDeleted(start.getTo_next());
				return;
			}
			System.out.print(start + (start.getTo_next() != null ? "--->" : "---> null"));
				displayNonDeleted(start.getTo_next());
			
	
		}
		
		public boolean printFatNodes(int key) {
			return displayNodeHistory(getHeadvertex(), --key, 0);
		}
		
		private boolean displayNodeHistory(Vertex  start, int key, int count) {
			if (start.isState()) {
				return displayNodeHistory(start.getTo_next(), key, count);
			}
			if (key == count) {
				System.out.printf("""
									-----------------------------
									|   Current Data  =  %d     |
									-----------------------------
									%n""", start.toInt());
				System.out.println("""
									-----------------------------
									|   List of Recent Data     |
									-----------------------------
									""");
				start.getRecent().print();
				return true;
			}
			if (start.getTo_next() != null) {
				return displayNodeHistory(start.getTo_next(), key, count + 1);
			}
			return false;
		
		}
	}
	
	static class Persistent {
		static Scanner sc = new Scanner(System.in);
		LinkedVertex link = new LinkedVertex();
		
		private int validateInt() {
			while (!sc.hasNextInt()) {
				System.err.println("""
									---------------------
									|   Mismatch Type   |
									---------------------
									""");
				sc.next();
			}
			
			return sc.nextInt();
		}
		
		public boolean Append() {
			System.out.println("Type data to be added in the list: ");
			int input = validateInt();
			
			link.append(input);
			System.out.println("""
								--------------------
								|    Added Data    |
								--------------------
								""");
			return true;
		}
		
		public boolean Delete() {
			if (link.empty()) {
				System.out.println("""
									--------------------------------------------------------------------
									|        Empty List, kindly do the append operation first          |
									--------------------------------------------------------------------
									""");
				return false;
			}
			List_History();
			System.out.println("Type key position of the data you want to delete in the list above: ");
			int input = validateInt();
			
			link.delete(input);
			System.out.println("""
					--------------------
					|   Deleted Data   |
					--------------------
					""");
			  return true;
		}
		
		public boolean Change_Value() {
			if (link.empty()) {
				System.out.println("""
									--------------------------------------------------------------------
									|        Empty List, kindly do the append operation first          |
									--------------------------------------------------------------------
									""");
				return false;
			}
			
			System.out.println("Type key position of the data you want to change in the list above: ");
			int input = validateInt();
			
			System.out.println("Now, change it with what value ?"
					+ "\n+Acceptable integer input\n"
					+ "~ Whole Number");
			int replacement = validateInt();
			
			link.modify(input, replacement);
			System.out.println("""
					--------------------
					|   Altered Data   |
					--------------------
					""");
			  return true;
		}
		
		public void Display() {
			if (link.empty()) return;
			System.out.println("""
								  ---------------------------------------------
								  |     Linked List (Excluded Tombstones)     |
								  ---------------------------------------------
								""");
			link.displayNonDeleted(link.getHeadvertex());
			System.out.println();
		}
		

		
		public void Node_History() {
			if (link.empty()) return;
			System.out.println("""
								------------------------------
								|    Display Fat Nodes       |
								------------------------------
								""");
			System.out.println("Type key position of the data you want to view in the list above: ");
			int input = validateInt();
			
			link.printFatNodes(input);
			System.out.println();
		}
		
		
		public void List_History() {
			if (link.empty()) return;
			link.print();
			System.out.println();
		}
	
		
		
	}
	
	

}
