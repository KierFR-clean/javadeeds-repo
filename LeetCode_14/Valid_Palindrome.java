package LeetCode_14;

public class Valid_Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sn = new Solution();
		String word = "A man, a plan, a canal: Panama";
			
		
		
		System.out.println(sn.isPalindrome(word));
	}

}

class Solution {
	
	
	public boolean isPalindrome(String word) {
		word = word.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
		if (word.isBlank() && word.length()) return true;
		char[] characters = word.toCharArray();
		int start = 0;
		int end = characters.length - 1;
		boolean isValid = false;
		while (characters[start] == characters[end] && start < end) {
			
			System.out.println(characters[start] + "' " + characters[end]);
		start++;
		end--;
		
		}
		return isValid;
		
		
	}
}
