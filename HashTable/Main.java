package HashTable;
import java.util.*;


public class Main {
	
	final static Scanner sc = new Scanner(System.in);
	static String user_response;
	static int user_response_ByInt;
	public static void main(String[] args) {
		execute_program();
	}
	
	static void input(String prompt, String newline,  boolean String) {
		System.out.print(newline + prompt);
		

		if (!String) {

			while (true) {
				while (!sc.hasNextInt()) {
					System.err.println("Not an int datatype [eg. 1, 2, 3, 4] !!!");
					sc.next();
				}
				user_response_ByInt = sc.nextInt();
				if (user_response_ByInt > 0) {
					break;
				}

				System.out.println("User input a negative number... try a whole number next time. ");
				System.out.print(newline + "\n\n" + prompt);

			}
			return;
		}
			user_response = sc.next();
		
	}
	
	static void execute_program() {
		Hash_Methods methods = new Hash_Methods();
	 
		
		while (true) {
			print_interface();
			input("[]", "", false);
			
			
			switch (user_response_ByInt) {
			case 1 -> { 
				boolean escape = false;
				do {
				System.out.println("""
								--------------------
								|  Insertion Type  |
								--------------------
								| 1. Continuous    |
								| 2. One At A Time |
								--------------------
								|       3. Back    |
								--------------------
									""");
				input("Selection or command\n===>", "", false);
				switch (user_response_ByInt) {
				case 1 -> {
					while (true) {
						input("Enter a string value [eg. \"group\", \"professor\"]: ", "\nQuick press [E] to stop query. ", true);
						
						if (user_response.equalsIgnoreCase("e")) break;
						else methods.insert(user_response);
					}
				}
				
				case 2 -> {
					input("Enter a string value [eg. \"group\", \"professor\"]: ", "", true);
					methods.insert(user_response);
				}
				
				case 3 -> {
					System.err.println("""
							---------------------------------
							| Redirecting...                |
							|       [ Interface ]           |
							---------------------------------
						  """);
					
					
				} 
				
				
				default -> {
					System.err.println("""
										---------------------------------
										| Please                        |
										|   follow certain constraints! |
										---------------------------------
									  """);
						escape = true;
				}
				
				} 
				} while(escape);
			}
			
			case 2 -> {
				if (methods.not_initOrNoData()) {
					custom_violation("No data... insert first. ");
				} else {
				boolean escape = false;
				do {
				System.out.println("""
								---------------------
								|  Deletion  Type   |
								---------------------
								| 1. One At A Time  |
								| 2. Erase All Data | 
								---------------------
								|           3. Back |
								---------------------
									""");
				input("Selection or command\n===>", "", false);
				switch (user_response_ByInt) {
				case 1 -> {
					input("Enter a string value [eg. \"group\", \"professor\"]: ", "", true);
					methods.delete(user_response);
				}
				
				case 2 -> {
					
					System.out.println(methods.clear());
				}
				
				case 3 -> {
					System.err.println("""
							---------------------------------
							| Redirecting...                |
							|       [ Interface ]           |
							---------------------------------
						  """);
				} 
				
				
				default -> {
					System.err.println("""
										---------------------------------
										| Please                        |
										|   follow certain constraints! |
										---------------------------------
									  """);
						escape = true;
				}
				
				} 
				} while(escape);
				
				
			}
			}
			case 3 -> {
				if (methods.not_initOrNoData()) {
					custom_violation("No data... insert first. ");
				} else {
				input("Enter a string value [eg. \"group\", \"professor\"]: ", "", true);
				System.out.println(methods.search(user_response));
				}
			}
				
			
			case 4 -> {
				if (methods.not_initOrNoData()) {
					custom_violation("No data... insert first. ");
					
				} else {
				methods.display();
				}
				
			}
			case 5 ->  {
				System.out.print("Closing console in ");
				for (int index = 3; index >= 1; index--) {
					System.out.print(index + " ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.exit(0);
			}
			}
			
			
			
		}
		
		
		
	}	
	
	static void print_interface() {
		System.out.println("""
						-------------------------------------------
						| MAIN 	         Hash Table               |   
						|              Implementation             |
						|------------------------------------------
						|                 Main Menu               |
						|------------------------------------------
						|Select one of the following:             
						|  
						|   1. Insert
						|   2. Delete
						|   3. Search
						|   4. Display
						-------------------------------------------
						|   5. Exit    
						-------------------------------------------
						| Selection or command
						| ==>  
					    """);
					
		
	}
	
	static void custom_violation(String error_msg) {
		System.err.println("""
				        -------------------------------------------
				        |      %s
				        |
				        -------------------------------------------
				           """.formatted(error_msg));
	}
	

}

class Hash_Methods {
	final int INSERTION_LIMIT = 15;
	Hash_Table table = new Hash_Table(INSERTION_LIMIT);
	
	public boolean not_initOrNoData() {
		return table == null || table.getTheTable_size() <= 0;
	}

	public String insert(String value) {
	if (!table.reach_capacity()) {
		System.out.println(table.put(value));
		return "/'Inserted/'";
	}
	System.out.println("""
					------------------------
					|  Overflow Table      |
					|     Resizing...      |
					------------------------
						""");
	table.rehash(table.rehashed_capacity());
	return "/'Rehashing/'\n";

	}
	
	
	public void delete(String value) {
		String temp = table.remove(value);
		if (temp.equals(null)) {
			Main.custom_violation("Are you sure inputted the right data\n"
				+ "Seems that data is not recorder throughout the table");
	   }
		System.out.println("Data's been deleted...\nData= " + temp);
	
	}
	
	public void display() {
		
			 System.out.println("""
				--------------------------------------------------
				| MAIN                     FORMAT...TABULAR_FORM
				|              CUSTOM HASH TABLE
				| ...SIZE= %d
				| ...N_DATA= %d                     
				--------------------------------------------------
							""".formatted(table.getCapacity(),table.getTheTable_size()));
			 
			 table.table_toconsole();
	}
	
	
	public String search(String value) {
		String temp = table.get(value);
	return (temp.isBlank())? "Seems data not been recorded yet to the table": "Search result/s:  \n" + temp;  
	}
	public String clear() {
		int temp = table.clear();
		return (temp <= 0)? "Seems no one or more data in the table ":" No. of data been deleted: " + temp;
	}
	

	
}

class Hash_Table {
	private String[] theTable;
	private static int theTable_size; 
	private static int capacity;
	private final double LOAD_FACTOR = 0.70;

	

	public Hash_Table(int capacity) {
		this.theTable = new String[capacity];
		this.capacity = capacity;
		this.theTable_size = 0;
	}
	
	
	public String put(String value) {
		return ("Successfully inserted at index " + hashing(value));
		
	}
	
	public String remove(String value) {	
		for (int index = 0; index < capacity; index++) {
			int probing_index = formula(value, index, hashCode(value), false);
			if (!certainValue_IsNull(probing_index) && getTheTable()[probing_index].equals(value)) {
					getTheTable()[probing_index] = null;
					theTable_size--;
					return "\nSuccess: " + value + " is deleted. ";
			}
		}
		return null;
	}
	
	public int occurences(String value) {
		int times = 0;
		for (int index = 0; index < capacity; index++) {
			int probing_index = formula(value, index, hashCode(value), false);
			if (!certainValue_IsNull(probing_index) && getTheTable()[probing_index].equals(value)) {
					times++;
			}
		}
		return times;
		}
	
	
	public String get(String value) {
		String concat = "";
		for (int index = 0; index < capacity; index++) {
			int probing_index = formula(value, index, hashCode(value), false);
			if (!certainValue_IsNull(probing_index) && getTheTable()[probing_index].equals(value)) {
				if (occurences(value) <= 1 ) return "\nReturned value= " + getTheTable()[probing_index] + " at index " + probing_index;
				else concat += "\nReturned value= " + getTheTable()[probing_index] + " at index " + probing_index + "\n";
			}
		}
		return concat;
		}
	
	 public void table_toconsole() {
		 int increment = 0;
		 
		 int compute_jumpValue = (int) Math.ceil(((double) capacity) / 10);
		 for (int per_block = 0; per_block < compute_jumpValue; per_block++) {
			 increment += 10;
			 
			 for (int upper_heading = 0; upper_heading < 71; upper_heading++) System.out.print("-");
			 System.out.println();
			 
			 for (int print_index = increment - 10; print_index < increment; print_index++) {
					if (print_index < capacity) {
				 System.out.printf("| %3s " + " ", print_index);
					}
			 }
			 
			 System.out.println("|");
			 
			 for (int upper_heading = 0; upper_heading < 71; upper_heading++) System.out.print("-");
			 
			 System.out.println();
			 
			 for (int print_index = increment - 10; print_index < increment; print_index++) {
					if (print_index < capacity) {
				 System.out.print(getTheTable()[print_index] == null? "|      ":String.format("| %3s " + " ", getTheTable()[print_index]));
					}
			 }
			
			 System.out.println("|");
			
			 for (int upper_heading = 0; upper_heading < 71; upper_heading++) System.out.print("-");
			 
			 System.out.println();
		 	}
		 }
	
	public int hashing(String value) {
		for (int index = 0; index < capacity; index++) {
		 int hash_key = formula(value, index, hashCode(value), false);

		 if (getTheTable()[hash_key] == null) {
			 getTheTable()[hash_key] = value;
			 theTable_size++;
				return hash_key;
			}

		}
		
		return -1;
		
	}
	
	public int clear() {
		int temp = theTable_size;
		setTheTable(null);
		setTheTable(new String[capacity]);
		return temp;
	}
	
	public int formula(String value, int index, int hash, boolean other_notation) {
		return (other_notation)? (hash + value.charAt(index)) % capacity: (hash + index) % capacity;
	}
	
	public int hashCode(String value) {
		int  hash = 0; 
		for (int index = 0; index < value.length(); index++) {
			hash = formula(value, index, hash, true);
		}
		return hash;
	}
	
	public boolean isEmpty() {
		return  theTable_size == capacity; 
	}
	
	
	public int rehashed_capacity() {
		capacity /= LOAD_FACTOR;
		return ((isPrime(capacity))? capacity : find_nearestPrime(capacity));
		
	}
	
	public void rehash(int nowcomputed_capacity) {
		String[] temp = new String[nowcomputed_capacity];
		
		allocate_data(temp);
	}
	
	public void allocate_data(String[] temporary_collect) {
		for (int index = 0; index < getTheTable().length; index++) {
			temporary_collect[index] = getTheTable()[index];
		}
		setTheTable(temporary_collect);
	}
	
	public boolean isPrime(int rehash_value) {
		if (rehash_value == 2) return true;
		if ((rehash_value < 2) || ((rehash_value & 1) == 0)) return false;
		loop_throughPrimes(rehash_value);
		return true;
			
	}

	protected boolean loop_throughPrimes(int rehash_value) {
		for (int index = 3; index * index <= rehash_value; index += 2) {
			if ((rehash_value % index) == 0) return false;
		}
		return true;
	}
	
	public int find_nearestPrime(int non_primecapacity) {
		for (int index = non_primecapacity; true; index++) {
			if (isPrime(index)) return index;
		}
	}
	
	public boolean reach_capacity() {
		return theTable_size == capacity;
	}
	
	public boolean certainValue_IsNull(int index) {
		return getTheTable()[index]==(null);
	}
	

	public String[] getTheTable() {
		return theTable;
	}


	public void setTheTable(String[] theTable) {
		this.theTable = theTable;
	}


	public int getTheTable_size() {
		return theTable_size;
	}

	public static int getCapacity() {
		return capacity;
	}


}
