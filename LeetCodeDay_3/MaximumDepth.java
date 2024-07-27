package LeetCodeDay_3;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepth {

	public static void main(String[] args) {
		Solution n_3 = new Solution();
		
		n_3.insert(1);
		n_3.insert(2);
	n_3.insert(3);
		n_3.insert(4);
		
		n_3.insert(5);
		
		
		
		n_3.display(n_3.root);
		System.out.println(n_3.depth(n_3.root));
		
		

//		Solution n = new Solution();
//		n.insert(1);
//		n.insert(1);
//		n.insert(0);
//		n.insert(7);
//		n.insert(-8);
//		n.insert(-7);
//		n.insert(9);
//
//		n.display(n.root);
//
//		System.out.println(n.maxLevelSum(n.root));
//
//		Solution n2 = new Solution();
//		n2.insert(989);
//		n2.insert(10250);
//		n2.insert(98693);
//		n2.insert(-89388);
//		n2.insert(-32127);
//		n2.display(n2.root);
//
//		System.out.println(n2.maxLevelSum(n2.root));
//
//		Solution n3 = new Solution();
//		n3.insert(1);
//		n3.insert(2);
//		n3.insert(3);
//
//		n3.display(n3.root);
//
//		System.out.println(n3.maxLevelSum(n3.root));
	}

	int val;
	MaximumDepth left;
	MaximumDepth right;

	MaximumDepth() {
	}

	MaximumDepth(int val) {
		this.val = val;
	}

	MaximumDepth(int val, MaximumDepth left, MaximumDepth right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	MaximumDepth root;

	public Solution() {
		root = null;
	}

	public void insert(int data) {
		insertBFS(root, data);
	}

	public void insertBFS(MaximumDepth start, int input) {
		MaximumDepth data = new MaximumDepth(input);
		if (root != null) {
			Queue<MaximumDepth> queue = new LinkedList<>();
			queue.add(start);

			while (!queue.isEmpty()) {
				MaximumDepth curr = queue.remove();

				if (curr.left == null) {
					curr.left = data;
					return;
				} else {
					queue.add(curr.left);
				}

				if (curr.right == null) {
					curr.right = data;
					return;
				} else {
					queue.add(curr.right);
				}

			}
			return;
		}
		root = data;

		return;

	}

	public void display(MaximumDepth root) {
		Queue<MaximumDepth> q = new LinkedList();
		q.add(root);

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				MaximumDepth c = q.remove();

				System.out.print(c.val + " ");

				if (c.left != null) {
					q.add(c.left);
				}

				if (c.right != null) {
					q.add(c.right);
				}
			}

			System.out.println();

		}
	}
	
	
	
	public int depth(MaximumDepth root) {
		if (root == null) return 0;
		
		return Math.max(depth(root.left), depth(root.right)) + 1;

	}
	

	public int maxLevelSum(MaximumDepth root) {

		if (root == null)
			return 0;

		Queue<MaximumDepth> q = new LinkedList();
		q.add(root);
		int r = Integer.MIN_VALUE, lvl = 0, ans = 0;

		while (!q.isEmpty()) {
			lvl++;
			int size = q.size();
			int s = 0;
			for (int i = 0; i < size; i++) {

				MaximumDepth c = q.remove();
				s += c.val;

				if (c.left != null) {
					q.add(c.left);
				}

				if (c.right != null) {
					q.add(c.right);
				}

			}

			System.out.println("Sum: " + s);

			
			if (r < s) {
				r = Math.max(s, r);
				ans = lvl;
			}
//			
		}
		return ans;

	}
}