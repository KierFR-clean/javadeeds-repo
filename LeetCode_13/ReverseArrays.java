package LeetCode_13;

import java.util.Arrays;

public class ReverseArrays {

	public static void main(String[] args) {
		Solution sn = new Solution();
		int[] array = { 1, 2, 3, 4, 5, 6, 7};
		
		sn.reversingArray(array);
		System.out.println(Arrays.toString(array));
	}

}

class Solution {
	
	public void reversingArray(int[] array ) {
		int start = 0;
		int end = array.length - 1;
		
		while (start < end) {
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			
			start++;
			end--;
		}
	}
	
		
}
