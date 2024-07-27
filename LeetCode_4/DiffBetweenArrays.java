package LeetCode_4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiffBetweenArrays {

	public static void main(String[] args) {
		int[] num1 = {1, 2, 3}, num2 = {2, 4, 6};
		Solution s = new Solution();
		
		
		System.out.println(s.findDifference(num1, num2));
	}

}

class Solution {
	public List<List<Integer>> findDifference (int[] num1, int[] num2) {
		List<List<Integer>> l = new ArrayList<>();
		Set<Integer> m1 = new HashSet<>();
		Set<Integer> m2 = new HashSet<>();
		
		Set<Integer> r1 = new HashSet<>();
		Set<Integer> r2 = new HashSet<>();

		for (int i: num1) {
			m1.add(i); 
		}
		
		
		for (int i: num2) {
			m2.add(i); 
		}
		

		
		for (int i: num1) 
			if (!m2.contains(i)) r1.add(i);
		
		for (int i: num2) 
			if (!m1.contains(i)) r2.add(i);
		
		
		l.add(new ArrayList<>(r1));
		l.add(new ArrayList<>(r2));

		return l;
		
		
			
		
		
	}
}
