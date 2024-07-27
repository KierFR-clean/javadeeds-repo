package LeetCode_6;

import java.util.Arrays;

public class ReverseStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		
		
		s.reverse("  Hello World  ");
	}

	
	static class Solution {
		public void reverse(String str) {
			StringBuilder sb =  new StringBuilder();
		String[] s = str.split(" ");
		
		for (String w: s) {
			StringBuilder t = new StringBuilder(w);
			t.reverse();
			sb.append(t);
			sb.append(" ");
			
		}
		System.out.println(sb.toString().trim());
		}
	}
}
