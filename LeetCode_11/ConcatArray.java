package LeetCode_11;

import java.util.Arrays;

public class ConcatArray {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] arr = { 1, 2, 3 };

		System.out.println(Arrays.toString(s.getConcatenation(arr)));

	}

	static class Solution {

		public int[] getConcatenation(int[] nums) {
			int[] temp = new int[2 * nums.length];

			for (int i = 0; i < nums.length; i++) {
				temp[i + nums.length] = temp[i] = nums[i];
			}
			return temp;

		}
	}
}
