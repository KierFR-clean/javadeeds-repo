package LeetCode8;

import java.util.HashSet;

public class Single_Number {

	public static void main(String[] args) {
		int nums[] = {4,1,2,1, 2};
			
		int r = 0;
		
		for (int num: nums) {
			System.out.println("r=" + r);
			System.out.println("o=" + (num ^ r));
			r = num ^ r;
		}
		
		System.out.println(r);
		
		
		
		
//		  boolean r; 
//	        int n = 0;
//
//	        for (int num : nums) {
//	            r = false;
//	            for (int j = 1; j < nums.length -1; j++) {
//	                if (num == nums[j]) r = true;  
//	            }
//	            if(!r){ n = num;
//	            }
//	        }
//
//	        System.out.println(n);


		
	}

}
