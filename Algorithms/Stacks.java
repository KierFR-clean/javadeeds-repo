package Algorithms;

public class Stacks {
	private int capacity;
	private int length;
	private int[] array;
	
	public Stacks() {
		capacity = 2;
		length = 0;
		this.array = new int[capacity];	
		}
	//O(1)
	public void push(int value) {
		if (isFull()) resize();
		
		array[length++] = value;
		
	}
	//O(1)
	public int pop() {
		int value = -1;
		if (length > 0) {
		 value = array[length - 1];
		length--;
		}
		
		return value;
	}
	
	public String toString() {
		String concat = "";
		if (length > 0) {
		concat += "[";
		for (int i = 0; i < length; i++) {
			if (array[i] == array[length - 1]) concat += array[i] + "]\n";
			else concat += array[i] + ",";
		}
		
		}
		return concat;
		
	}
	//O(1)
	public int peek() {
		return array[length - 1];
	}
	
	//O(n)
	public void resize() {
		capacity *= 2; //2n
		int[] newarray = new int[capacity];
		for (int i = 0; i < length; i++) newarray[i] = array[i];
		
		array = newarray;
	}
	
	
	public boolean isFull() {
		return length == capacity;
	}
	
	public static void main(String[] args) {
		Stacks stacks = new Stacks();
		
		stacks.push(1);
		stacks.push(2);
		stacks.push(3);
		stacks.push(4);
		stacks.push(5);
		
		System.out.println(stacks.peek());
		System.out.println(stacks);
		
		stacks.pop();
		System.out.println(stacks);

		}


}

