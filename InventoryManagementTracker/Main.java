package InventoryManagementTracker;

import java.time.LocalTime;
import java.util.*;



public class Main {

	public static void main(String[] args) {
		InventorySystem system = new InventorySystem();
		
		system.runnable();
	}

	static class Items {
		private int productCode, stockquantity;
		private String product_name;
		private double price;
		private String manufacturer;
		private Items nextItem;

		public Items(String product_name, double price, int stockquantity, String manufacturer) {
			setProductCode();
			this.product_name = product_name;
			this.price = price;
			this.stockquantity = stockquantity;
			this.manufacturer = manufacturer;
		}

		public Items getNextItem() {
			return nextItem;
		}

		public void setNextItem(Items nextItem) {
			this.nextItem = nextItem;
		}

		public int getProductCode() {
			return productCode;
		}

		public void setProductCode() {
			this.productCode = (int) (Math.random() * 10000 - 1000 + 1) + 1000;
		}

		public int getStockquantity() {
			return stockquantity;
		}

		public void setStockquantity(int stockquantity) {
			this.stockquantity = stockquantity;
		}

		public String getProduct_name() {
			return product_name;
		}

		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String toString() {
			return """
					------------------------------------------------
					|  Code = %d                                   |
					|            %s                                |
					------------------------------------------------
					|  Price = %.2f                                |
					|  Quantity = %d                               |
					|  Manufacturer = %s                           |
					------------------------------------------------
					|  per package = %.2f          	 	  		   |
					------------------------------------------------
					%n""".formatted(productCode, product_name, price, stockquantity, manufacturer, package_Cost());
		}
		
		private double package_Cost() {
			return this.price * this.stockquantity;
		}
		
		public String code_Ref() {
			return """
					------------------------------------------------
					|  Code = %d                                   |
					|            %s                                |
					------------------------------------------------
					%n""".formatted(productCode, product_name);

		}

	}

	static class LinkInventoryStruc {
		private Items firstItem, lastItem;

		public LinkInventoryStruc() {
			this.firstItem = this.lastItem = null;
		}

		public Items getFirstItem() {
			return firstItem;
		}

		public void setFirstItem(Items firstItem) {
			this.firstItem = firstItem;
		}

		public Items getLastItem() {
			return lastItem;
		}

		public void setLastItem(Items lastItem) {
			this.lastItem = lastItem;
		}

		private boolean empty() {
			return this.firstItem == null;
		}

		public boolean add_anItem(String product_name, double price, int stockquantity, String manufacturer) {
			Items new_item = new Items(product_name, price, stockquantity, manufacturer);
			if (!empty()) {
				getLastItem().setNextItem(new_item);
				setLastItem(getLastItem().getNextItem());
				return true;
			}
			setFirstItem(new_item);
			setLastItem(new_item);
			return true;
		}

		public void print() {
			this.displayRecursively(getFirstItem());
		}

		private void displayRecursively(Items base) {
			if (base != null) {
				System.out.println(base);
				displayRecursively(base.getNextItem());
			}
			return;
		}
		
		
		public void remove(int product_code) {
			
		}
		
		
		private Items removeIteratively(Items start, int product_code) {
			if (product_code == start.getProductCode())
				return start.getNextItem();
			
			return start;
		}
		
		
		private Items removeItem(Items start, int product_code) {
			Items currentItem = start;
			for(int counter = 1; currentItem != null && currentItem.getNextItem() != null; counter++) {
				if (product_code == currentItem.getProductCode()) {
					currentItem.setNextItem(currentItem.getNextItem().getNextItem());
					break;
				} else {
					currentItem = currentItem.getNextItem();
				}
			}
			
			return currentItem;
	}
		
		
	public Items find(int product_code) {
		 return findRecursive(getFirstItem(), product_code);
	}
	
	private Items findRecursive(Items start, int product_code) {
		if (start != null) {
			if (product_code == start.getProductCode()) return start;
			findRecursive(start.getNextItem(), product_code);
		}

		return null;
	}

	public void codes() {
		if (empty()) {
			System.out.println("""
					------------------------
					|  Database is empty   |
					------------------------
					""");
			return;
		}
		this.listOfProductCodes(getFirstItem());
	}

	private void listOfProductCodes(Items start) {
		if (start != null) {
			System.out.println(start.code_Ref());
			listOfProductCodes(start.getNextItem());
		}
		return;
	}
		

	
	}
	
	static class InventorySystem {
		static Scanner sc = new Scanner(System.in);
		LinkInventoryStruc init_struc = new LinkInventoryStruc();
		String user_name = "default2024", temp;
		int assured_input, user_operationInput;

		public int getUser_operationInput() {
			return user_operationInput;
		}

		public void setUser_operationInput(int user_operationInput) {
			this.user_operationInput = user_operationInput;
		}
		
		public void runnable() {
			show_greetings();
			while (true) {
			print_interface();
			}
		}

		public void show_greetings() {

			System.out.println("""
					------------------------------------
					|    Please enter your username    |
					|               :)                 |
					------------------------------------
					""");
			temp = sc.next();
			while (assured_input != 1 && assured_input != 2) {
				System.out.println("""
						------------------------------------
						|           You sure?              |
						|            [1] Yes               |
						|            [2] No                |
						|               :)                 |
						------------------------------------
						""");
				assured_input = inspect_Int_typeinputs();

				switch (assured_input) {
				case 1 -> {
					user_name = temp;
					System.out.printf("""
							------------------------------------
							| .... Hmmmm....        ;)         |
							|          	 Welcome !!            |
							|               %s                 |
							|               :)                 |
							------------------------------------
							%n""", user_name);
				}

				case 2 -> System.out.printf("""
						------------------------------------
						| .... Hmmmm....    Okay    ;)     |
						|          	 Welcome !!            |
						|               %s                 |
						|               :)                 |
						------------------------------------
						%n""", user_name);
				default -> System.out.println("""
						------------------------------------
						|      Only Type 1.. and 2 :)      |
						------------------------------------
						""");
				}
			}
		}

		private void print_interface() {
			while (true) {
				System.out.printf("""
						-------------------------------------------------
						| User: %s                                 | X |
						-------------------------------------------------
						|  Welcome To Java Inventory Management System  |
						-------------------------------------------------
						|[1] Add an Item                                |
						|[2] Remove an Item                             |
						|[3] View current list of Item                  |
						|[4] Find an Item                               |
						|[5] Leave Program                              |
						-------------------------------------------------
						|Type the operation desired:                    |
						-------------------------------------------------
						""", user_name);
				
				operation_cases();

			}

		}

		private int store_response() {
			setUser_operationInput(inspect_Int_typeinputs());
			return getUser_operationInput();
		}

		private void operation_cases() {
			switch (store_response()) {
			case 1 -> {
				int count = 0;
				while (true) {
					count++;
					System.out.println("Adding Process Step " + count + "*\n" + "Product Name:\n");

					String product_name = sc.next();

					count++;
					System.out.println("Adding Process Step " + count + "*\n" + "Type the product manufacturer:\n");

					String manufacturer = sc.next();
	
					count++;
					System.out.println("Adding Process Step " + count + "*\n" + "Product Price: ");

					double product_price = inspect_price();
					
					count++;
					System.out.println("Adding Process Step " + count + "*\n" + "How many quatities?: ");

					int product_quantity = inspect_Int_typeinputs();

						boolean validator = product_name.isEmpty() || product_price < 100.00 || product_quantity <= 0
							|| manufacturer.isEmpty();

					if (validator) {
						System.out.println("""
								--------------------------------------------------
								|        Invalid registry                        |
								|  ~ Check if product name is empty              |
								|  ~ Check if product price is less than 100.00  |
								|  ~ Check if product quantity is empty          |
								   |  ~ Check if product manufacturer is empty      |
								--------------------------------------------------
								""");
					} else {

						init_struc.add_anItem(product_name, product_price, product_quantity, manufacturer);
						System.out.println("""
								------------------------------
								|  Done Adding to Inventory  |
								------------------------------
								""");
						break;
					}

				}
					
			}
			
			case 2 -> {
				while(true) {
					System.out.println("Please Type the Product Code of the Item you desired to removed: ");
					int the_code = inspect_Int_typeinputs();
					
					if (the_code <= 0) {
						System.out.println("""
											--------------------------------------------
											| Make sure you input a valid product code |
											--------------------------------------------
											""");
					} else {
						init_struc.remove(the_code);
						System.out.println("Successfully remove a product!!! ");
						break;
					}
					
				}
				
			}
			
			case 3 -> {
				System.out.println("""
								-------------------------------------
								|   List of Current Products        |
								-------------------------------------
									""");
				
				init_struc.print();
				LocalTime time = LocalTime.now();
				System.out.println("Recorded as of: " + time);
				
			}
			
			case 4 -> {
				init_struc.codes();
				System.out.println("Please type the product_code ");
				int code = inspect_Int_typeinputs();
				Items temp = init_struc.find(code);
				System.out.println(temp != null? "Found It!!!" : "Unfortunately not found it");
				System.out.println(temp);
			}
			
			case 5 -> {
				System.out.println("""
									-----------------------------
									| Please wait...            | 
									|    Closing the console ;) | 
									-----------------------------
									""");
				System.exit(0);
			}
			

			}
		}



		private double inspect_price() {
			while (!sc.hasNextDouble()) {
				System.err.println("""
						-------------------------------------
						|           Mismatch Error          |
						|         Acceptable inputs:        |
						| ~ int [0, 1, 23, 4,...]           |
						| ~ double [23.00, 4.00, 12.00,...] |
						-------------------------------------
						%n""");
				sc.next();
			}
			return sc.nextDouble();
		}

		private int inspect_Int_typeinputs() {
			while (!sc.hasNextInt()) {
				System.err.println("""
						-------------------------------------
						|           Mismatch Error          |
						|         Acceptable inputs:        |
						| ~ whole numbers [0, 1, 23, 4,...] |
						-------------------------------------
						%n""");
				sc.next();
			}
			return sc.nextInt();
		}

	}
}
