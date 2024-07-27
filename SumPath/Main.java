package SumPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Main.BinaryTree mytree_one = mytree1();
		Main.BinaryTree mytree_two = mytree2();
		Main.BinaryTree mytree_three = mytree3();
		System.out.println("""
				-------------------
				|  Sum Odd Path 1 |
				-------------------
				""");
		mytree_one.display();
		
		System.out.print(mytree_one.sum());
		System.out.println();		
		System.out.println("""
				-------------------
				|  Sum Odd Path 2 |
				-------------------
				""");
		mytree_two.display();
		
		System.out.print(mytree_two.sum());
		System.out.println();		

		System.out.println("""
				-------------------
				|  Sum Odd Path 3 |
				-------------------
				""");
		mytree_three.display();
		
		System.out.print(mytree_three.sum());
		
	}

	private static Main.BinaryTree mytree1() {
		BinaryTree mytree = new BinaryTree();
		mytree.insert(5);
		mytree.insert(7);
		mytree.insert(3);
		mytree.insert(15);
		mytree.insert(17);
		mytree.insert(9);
		mytree.insert(11);
		return mytree;
	}
	
	private static Main.BinaryTree mytree2() {
		BinaryTree mytree = new BinaryTree();
		mytree.insert(5);
		mytree.insert(7);
		mytree.insert(2);
		mytree.insert(15);
		mytree.insert(17);
		mytree.insert(9);
		mytree.insert(11);
		return mytree;
	}
	
	private static Main.BinaryTree mytree3() {
		BinaryTree mytree = new BinaryTree();
		mytree.insert(5);
		mytree.insert(7);
		mytree.insert(2);
		mytree.insert(4);
		mytree.insert(6);
		mytree.insert(9);
		mytree.insert(11);
		return mytree;
	}
	
	static class Vertex {
		private Vertex leftVertex, rightVertex;
		private int data;
		
		public Vertex(int data) {
			this.data = data;
		}
		public Vertex getLeftVertex() {
			return leftVertex;
		}
		public void setLeftVertex(Vertex leftVertex) {
			this.leftVertex = leftVertex;
		}
		public Vertex getRightVertex() {
			return rightVertex;
		}
		public void setRightVertex(Vertex rightVertex) {
			this.rightVertex = rightVertex;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public String toString() {
			return String.format("[ %d ]",data);
		}
		
		public int toInt() {
			return data;
		}
		
	}
	
	static class BinaryTree {
		private Vertex root;
		static int count;
	  List<Integer> path = new ArrayList<>(); 
		
		 public BinaryTree() {
			 this.root = null;
		 }

		public Vertex getRoot() {
			return root;
		}

		public void setRoot(Vertex root) {
			this.root = root;
		}
		 
		 private boolean checkIfEmpty() {
			 return this.root == null;
		 }
		 
		 public void insert(int input) {
			 insertBFS(getRoot(), input);
		 }
		 
		 public void display() {
			 displayBFS(getRoot());
		 }
		 
		 private void insertBFS(Vertex start, int input) {
			 Vertex data = new Vertex(input);
			 if (!checkIfEmpty()) {
				 Queue<Vertex> queue = new LinkedList<>();
				 queue.add(start);
				 
				 while (!queue.isEmpty()) {
					Vertex curr = queue.remove();
					
					if (curr.getLeftVertex() == null) {
						curr.setLeftVertex(data);
						return;
					} else { 
						queue.add(curr.getLeftVertex());
					}
					
					if (curr.getRightVertex() == null) {
						curr.setRightVertex(data);
						return;
					} else { 
						queue.add(curr.getRightVertex());
					}
					
				 }
				return;
			}
			setRoot(data);
			return;
			 
		 }
		 
			private void displayBFS(Vertex start) {
				int space = 9;
				if (!checkIfEmpty()) {
					Queue<Vertex> queue = new LinkedList<>();
					queue.add(start);

					while (!queue.isEmpty()) {
						int size = queue.size();
						for (int j = 0; j < space; j++) {
							System.out.print(" ");
						}
						for (int i = 0; i < size; i++) {
							Vertex curr = queue.remove();
							
							System.out.print(curr);

							if (curr.getLeftVertex() != null) {
								queue.add(curr.getLeftVertex());
							}

							if (curr.getRightVertex() != null) {
								queue.add(curr.getRightVertex());
							}

						}
						System.out.println();
						space -= size + size;
					

					}

					return;
				}
				System.out.println(" No currently data in the binary tree \n~ "
						+ "Possible Fixes \n Input data first, refer to the first operation. ");
				return;

			}
			
			public int sum() {
				path.add(getRoot().toInt());
				count = getRoot().toInt();
				int sumTotal =  maxSumPath(getRoot());
				
				path.forEach(integer -> System.out.print(integer.toString() + " --> "));
				System.out.print(" Sum = ");
				return sumTotal;
				
			}
			
			private int maxSumPath(Vertex start) {
				if (start.getLeftVertex() == null || start.getRightVertex() == null) return count;	
				
				if (start.getLeftVertex() != null && start.getRightVertex() != null && start.getRightVertex().toInt() % 2 != 0 || start.getLeftVertex().toInt() % 2 != 0) {
					if((start.getRightVertex().toInt() % 2 != 0) && (start.getLeftVertex().toInt() % 2 != 0) && start.getLeftVertex().toInt() > start.getRightVertex().toInt()) { 
						path.add(start.getRightVertex().toInt());
						count += start.getRightVertex().toInt();
						return maxSumPath(start.getRightVertex());
					} else {
						path.add(start.getLeftVertex().toInt());
						count += start.getLeftVertex().toInt();
						return maxSumPath(start.getLeftVertex());
					}
				}
				return count;
			}
		 
		 
		 
	}
	

}
