package LeetCode_22;

public class Binary_Search {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sn = new Solution();
		int[] n = {1, 2, 3, 4, 5, 12, 34, 43, 45, 56, 67, 68, 69, 70};
		int times= 0;
		for (int i = 0; i < n.length; i++) {
			int r = (int) (Math.random() * 70 - 1) + 1;
			System.out.println("V= "+r);
			int v = sn.binarySearch(n, r);
			
			System.out.println((v != -1?"Found= "+ v :"Not Found!"));
			if (v != -1) times++;
		}
		
		System.out.println(times + " times found");
	}	

}

class Solution {
	
	
	public int binarySearch(int[] num, int t) {
		int l = 1;
		int r = num.length - 1;
		
		
		while (l <= r) {
			int m = l + (r-l) /2;
			if (t == num[m]) return num[m];
			else if (num[m] > t) r = m - 1;
			else l = m + 1;
		}
		
		return -1;
		
	}
}
