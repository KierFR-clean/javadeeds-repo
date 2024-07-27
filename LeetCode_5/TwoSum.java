package LeetCode_5;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
	
	public static void main(String[] args) {
		
		Solution s = new Solution();
		int[]nums = {3, 2, 4};
		
		
		
		
		System.out.println(Arrays.toString(s.twoSum(nums,6)));
	}
	static class Solution {
		
		public int[] twoSum(int[] nums, int target) {
			
			HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
			
			for (int i = 0; i < nums.length; i++) {
				if (!h.containsKey(target - nums[i])) { 
					h.put(nums[i], i);
				} else {
					 return new int[]{h.get(target - nums[i]), i};
				}
			}
			
		return new int[0];
		
		
	}
}
	
}
