package LeetCodeDay_1;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

	public static void main(String[] args) {

		Solution n = new Solution();
		n.insert(1);
		n.insert(1);
		n.insert(0);
		n.insert(7);
		n.insert(-8);
		n.insert(-7);
		n.insert(9);

		n.display(n.root);

		System.out.println(n.maxLevelSum(n.root));

		Solution n2 = new Solution();
		n2.insert(989);
		n2.insert(10250);
		n2.insert(98693);
		n2.insert(-89388);
		n2.insert(-32127);
		n2.display(n2.root);

		System.out.println(n2.maxLevelSum(n2.root));

		Solution n3 = new Solution();
		n3.insert(1);
		n3.insert(2);
		n3.insert(3);

		n3.display(n3.root);

		System.out.println(n3.maxLevelSum(n3.root));
	}

	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	TreeNode root;

	public Solution() {
		root = null;
	}

	public void insert(int data) {
		insertBFS(root, data);
	}

	public void insertBFS(TreeNode start, int input) {
		TreeNode data = new TreeNode(input);
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(start);

			while (!queue.isEmpty()) {
				TreeNode curr = queue.remove();

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

	public void display(TreeNode root) {
		Queue<TreeNode> q = new LinkedList();
		q.add(root);

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				TreeNode c = q.remove();

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

	public int maxLevelSum(TreeNode root) {

		if (root == null)
			return 0;

		Queue<TreeNode> q = new LinkedList();
		q.add(root);
		int r = Integer.MIN_VALUE, lvl = 0, ans = 0;

		while (!q.isEmpty()) {
			lvl++;
			int size = q.size();
			int s = 0;
			for (int i = 0; i < size; i++) {

				TreeNode c = q.remove();
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