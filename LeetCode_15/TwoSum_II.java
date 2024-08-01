package LeetCode_15;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum_II {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sn = new Solution();
		int[] array = {5, 25, 75};
		int target = 100;
		
		
		
		System.out.println(Arrays.toString(sn.twoSumII(array, target)));
		
		
		
	}

}

class Solution {
	public int[] twoSumII(int[] n, int t) {
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();

        for (int i = 0; i < n.length; i++) {
            if (!h.containsKey(t - n[i])) h.put(n[i], i);
            else return new int[]{h.get(t - n[i]) + 1, i + 1};
        }
            return new int[0];

	
	}
	
}
