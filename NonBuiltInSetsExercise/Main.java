package NonBuiltInSetsExercise;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		JavaSets set = new JavaSets();
		
		set.initializeSets();
		set.getResponseAndOperate();
	
	}

}


class JavaSets {
	
	final Scanner sc = new Scanner(System.in);
	DynamicSet def_struct = new DynamicSet();
	DynamicSet new_struct = new DynamicSet();
	DynamicSet merged_struct = new DynamicSet();
	int value;

	public void initializeSets() {
		System.out.println("\t*Java Sets*");
		String set = "A";
		for (int i = 0; i < 2; i++) {
			if (i == 1) set = "B";
			int eachSize = checkIssues("N-size for Set " + set + "?: ", Integer.MAX_VALUE, false, 0);
			int element = 0;

			for (int j = 0; j < eachSize ; j++) {
				if (i <= 0) { 
					element = checkIssues("Initialize Set " + set + " starting from elem " + (j + 1) + ": ", Integer.MAX_VALUE, true, 1);
					def_struct.pushback(element); 
				} else {  
					element = checkIssues("Initialize Set " + set + " starting from elem " + (j + 1) + ": ", Integer.MAX_VALUE, true, 2);
					new_struct.pushback(element); 
				}
			}
		}
		System.out.println("Default: ");
		printBoth();
	}
	
	public void getResponseAndOperate() {
		
		while (true) {
		System.out.println("""
						   [1] Push elements in any set.
						   [2] Print both set.
						   [3] Perform different set operations.
						   [4] End program.
							""");
		int response = checkIssues("Please select an operation: ", 4, false, 0);
		
		switch (response) {
		case 1 -> {
			int set = checkIssues("[1] Set A\n[2] Set B\nPush from ? set? ", 2, false, 0);
			
			if (set == 1) def_struct.pushback(checkIssues("Pust element to Set A with value of:", Integer.MAX_VALUE, true, 1));
			else new_struct.pushback(checkIssues("Pust element to Set B with value of:", Integer.MAX_VALUE,true, 2));
		}
		case 2 -> {
			printBoth();
		}
		case 3 -> {
			union();
			intersection();
			difference();
		}
		
		case 4 -> {
			System.out.println("End Program.");
			System.exit(0);
		}
		}
		
		}
		
		
	}
	
	public void union() {
		copy(def_struct);
		
		for ( Object eachElement : new_struct.getArray()) {
			if (eachElement != null && !merged_struct.contains((Integer) eachElement)) merged_struct.pushback((Integer) eachElement); 
		}
		System.out.print("Union: (A ∪ B)");
		merged_struct.print();
	
		System.out.println();
	}
	
	public void intersection() {
		clearAll();

		for ( Object eachElement : def_struct.getArray()) {
			if (eachElement != null && new_struct.contains((Integer) eachElement)) merged_struct.pushback((Integer) eachElement); 
		}
		System.out.print("Intersection (A ∩ B): ");
		merged_struct.print();
		System.out.println();
	}
	public void difference() {
		clearAll();
		
		for (Object eachElement: def_struct.getArray()) { 
			if (eachElement != null && !new_struct.contains((Integer) eachElement)) merged_struct.pushback((Integer) eachElement);
		}
		
		System.out.print("Difference (A - B): ");
		merged_struct.print();
		System.out.println();
		
		
	}
	

	public void clearAll() {
		merged_struct = null;
		merged_struct = new DynamicSet();
	}
	
	public void copy(DynamicSet set) {
		for (Object element : set.getArray()) {
			if (element != null) merged_struct.pushback((Integer) element);
		}
	}
	
	public void printBoth() {
		System.out.print("Set A: ");
		def_struct.print();
		System.out.println();
		System.out.print("Set B: ");
		new_struct.print();
		System.out.println();

	}
	
	public int checkIssues(String prompt, int rangesTo, boolean isForPush, int comm) {
		while (true) {
			System.out.println(prompt);
			if (!checkIfValid(rangesTo,  isForPush, comm)) continue;
			else break;
		}
		
		return value;
	}
	
	public boolean checkIfValid(int rangesTo, boolean isForPush, int comm) {
		boolean isValid = false;
		if (!sc.hasNextInt()) {
			System.err.println("InputMismatch: Invalid Datatype!");
			sc.next();
			return isValid;
		}
		value = sc.nextInt();
		boolean isWholeNumber = value >= 0;
		if (isWholeNumber) isValid = true;
		else System.err.println("IllegalArgument: Non-whole number entry!");
		
		if (value > rangesTo || value < 1) {
			isValid = false;
			System.err.println("OutOfBoundsException: Out-of-selection entry!");
		}
		
		if (isForPush && def_struct.contains(value) && comm == 1) {
			isValid = false;
			System.err.println("PresenceException:  Set A already contain the specified element");
		}
		
		if (isForPush && new_struct.contains(value) && comm == 2) {
			isValid = false;
			System.err.println("PresenceException:  Set B already contain the specified element");
		} 
		
		return isValid;
	}
	
}

class DynamicSet {
	private int length;
	private int capacity;
	private Object[] array;
	
	
	public DynamicSet() {
		capacity = 2;
		length = 0;
		array = new Object[2]; 
	}
	
	public void pushback(int element) {
		if (isArrayFull()) this.resize();
		
		array[length++] = element;
		
	}
	
	public void resize() {
		capacity = capacity * 2;
		Object[] newArray = new Object[capacity];
		
		for (int i = 0; i < length; i++) {
			newArray[i] = array[i];
		}
		setArray(newArray);
	}
	
	public Object get(int value) {
		if (value < length) return array[value];
		return null;
	}
	
	public void popBack() {
		if (length > 0) length--;
	}
	
	public void print() {
		if (length <= 0) {
			System.out.print("\t No current elements");
			return;
		}
		System.out.print("[ ");
		for (int i = 0; i < length; i++) {
			System.out.print("[" + array[i] + "]" + (i < length - 1? ",":" ]"));
		}
	}
	
	public boolean contains(int value) {
		
		for (Object object : array) {
			if (object != null && object.equals(value)) return true;
		}
		return false;
	
		
	}
	
	
	
	public boolean isArrayFull() { return length == capacity; }
	
	

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public int getLength() {
		return length;
	}

	public int getCapacity() {
		return capacity;
	}
	
	
	
	
	
	
	
	
	
}
