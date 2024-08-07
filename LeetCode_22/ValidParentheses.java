package LeetCode_22;

import java.util.Stack;

public class ValidParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Answer sn = new Answer();

		String str = "(])";
		System.out.println(sn.validParentheses(str));
	}

}	

class Answer {

	public boolean validParentheses(String str) {
		Stack<Character> stack = new Stack();

		for (char c : str.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			}

			else if (stack.size() == 0) {
				return false;
			}

			else if (c == ')') {
				if (stack.peek().equals(Character.valueOf('('))) {
					stack.pop();
				} else {
					stack.push(c);
				}
			}

			else if (c == '}') {
				if (stack.peek().equals(Character.valueOf('{'))) {
					stack.pop();
				} else {
					stack.push(c);
				}
			}

			else if (c == ']') {
				System.out.println("run");
				if (stack.peek().equals(Character.valueOf('['))) {
					System.out.println("run2");
					stack.pop();
				} else {
					stack.push(c);
				}

			}

		}

		return stack.size() == 0;
	}

}