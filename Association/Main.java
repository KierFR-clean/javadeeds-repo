
package Association;

import java.util.*;

/**
 * The Class Main.
 */
public class Main {

	/** The sc. */
	private static Scanner sc = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		LinkItem testItem = new LinkItem();
		LinkDataSet dataset = new LinkDataSet();
		
		testItem.appendList("keys");
		testItem.appendList("onion");
		testItem.appendList("rings");
		testItem.appendList("beans");
		
		runJavaAssociationDemo(testItem, dataset);
	}

	/**
	 * Run java association demo.
	 *
	 * @param testItem the test item
	 * @param dataset  the dataset
	 */
	protected static void runJavaAssociationDemo(LinkItem testItem, LinkDataSet dataset) {
		while (true) {
			System.out.print(menuTxtBlk());

			switch (sc.nextInt()) {
			case 1 -> {
				if (addItem(testItem))
					System.out.println("Successfully Added Items!!! ");
			}

			case 2 -> {

				if (emptyList(1, testItem, dataset)) {
					promptPanel("Add Item First!!!.... ");
					break;
				}

				ItemSelection(testItem);
				createDataSet(dataset, testItem);

			}
			case 3 -> {
				if (emptyList(2, testItem, dataset)) {
					promptPanel("Add Item First!!! or Create DataSet.... ");
					break;
				}
				dataset.printToConsole();
				while (true) {
					System.out.println("Enter position of dataset: ");
					int response = sc.nextInt();
					if (!dataset.outOfBoundsRange(response)) {
						dataset.remove(response);
						break;
					} else {
						System.err.println("Out of Bounds!!!...Repeat process!!! ");
					}
				}

				dataset.printToConsole();
			}
			case 4 -> {
				if (emptyList(2, testItem, dataset)) {
					promptPanel("Add Item First!!! or Create DataSet.... ");
					break;
				}

				dataset.printToConsole();
			}

			case 5 -> {
				
				if (emptyList(2, testItem, dataset)) {
					promptPanel("Add Item First!!! or Create DataSet.... ");
					break;
				}
				ItemSelection(testItem);
				while (true) {
					System.out.print("Enter number that corresponds for Item A: ");
					int A = sc.nextInt();
					System.out.print("Enter number that corresponds for Item B: ");
					int B = sc.nextInt();

					if (A <= 0 || B <= 0) {
						break;
					} else {
						String A_converted = testItem.find(A);
						String B_converted = testItem.find(B);
						dataset.support(A_converted, B_converted);
						System.out.println();
						break;
					}
				}

			}
			case 6 -> {
				
				if (emptyList(2, testItem, dataset)) {
					promptPanel("Add Item First!!! or Create DataSet.... ");
					break;
				}
				
				ItemSelection(testItem);
				while (true) {
					System.out.print("Enter number that corresponds for Item A: ");
					int A = sc.nextInt();
					System.out.print("Enter number that corresponds for Item B: ");
					int B = sc.nextInt();

					if (A <= 0 || B <= 0) {
						break;
					} else {
						String A_converted = testItem.find(A);
						String B_converted = testItem.find(B);
						System.out.println(dataset.association(A_converted, B_converted));
						System.out.println();
						break;
					}
				}

			}

			case 7 -> {
				promptPanel("Exiting console...");
				return;
			}

			}

		}
	}

	/**
	 * Empty list.
	 *
	 * @param numOfParam the num of param
	 * @param item       the item
	 * @param dataset    the dataset
	 * @return true, if successful
	 */
	static boolean emptyList(int numOfParam, LinkItem item, LinkDataSet dataset) {
		if (numOfParam == 1)
			return item.getHeadVtx() == null;
		else
			return item.getHeadVtx() == null || dataset.getHeadVtx() == null;
	}

	/**
	 * Prompt panel.
	 *
	 * @param msg the msg
	 */
	static void promptPanel(String msg) {
		System.out.printf("""
				+-----------------------------------------------------+
				| %s                                                  |
				+-----------------------------------------------------+
				%n""", msg);
	}

	/**
	 * Menu txt blk.
	 *
	 * @return the string
	 */
	static String menuTxtBlk() {
		return """
				  +--------------------------------------------+
				  |	 	    Java Association Checker           |
				  +--------------------------------------------+
				  |[1] Add Item    					           |
				  |[2] Create DataSet			               |
				  |[3] Delete Dataset	                       |
				  |[4] Display List of Datasets                |
				  |[5] Support Between Items                   |
				  |[6] Association Between Items               |
				  |[7] Terminate Program                       |
				  +--------------------------------------------+
				""";
	}

	/**
	 * Creates the data set.
	 *
	 * @param dataset the dataset
	 * @param item    the item
	 * @return true, if successful
	 */
	static boolean createDataSet(LinkDataSet dataset, LinkItem item) {
		System.out.println("Enter number that corresponds to the choices positions.  [~Press -1 to exit immediately~]");

		DataSetVertex newVtx = new DataSetVertex();
		for (int i = 0; true; i++) {
			System.out.print("[" + (i + 1) + "]. ");
			int response = sc.nextInt();
			String toString = item.find(response);
			if (escapeImmediately(response))
				break;
			System.out.println("==> " + toString);
			if (!" Not found!!! ".equals(toString))
				newVtx.appendList(toString);
			else
				continue;
		}

		dataset.appendList(newVtx);
		return true;
	}

	/**
	 * Escape immediately.
	 *
	 * @param response the response
	 * @return true, if successful
	 */
	static boolean escapeImmediately(int response) {
		return -1 == response;
	}

	/**
	 * Escape immediately.
	 *
	 * @param response the response
	 * @return true, if successful
	 */
	static boolean escapeImmediately(String response) {
		return "!".equals(response);
	}

	/**
	 * Adds the item.
	 *
	 * @param testItem the test item
	 * @return true, if successful
	 */
	static boolean addItem(LinkItem testItem) {
		boolean sizeLimiter = true;
		System.out.println("Enter your desired item [~Press ! to exit immediately~]");
		for (int i = 0; i < testItem.getMAX_SIZE(); i++) {
			if (!sizeLimiter) {
				System.out.println("Reached the input limit...");
				break;
			}
			System.out.print("[" + (i + 1) + "]. ");
			String response = sc.next();
			if (escapeImmediately(response))
				break;
			sizeLimiter = testItem.appendList(response);
		}

		return true;
	}

	/**
	 * Item selection.
	 *
	 * @param testItem the test item
	 */
	static void ItemSelection(LinkItem testItem) {

		System.out.println("+-----------------------------------------+");
		testItem.printToConsole();
		System.out.println("+-----------------------------------------+");

	}

	/**
	 * The Class ItemVertex.
	 */
	static class ItemVertex {

		/** The name of item. */
		private String nameOfItem;

		/** The to next. */
		private ItemVertex toNext;

		/**
		 * Instantiates a new item vertex.
		 *
		 * @param nameOfItem the name of item
		 */
		public ItemVertex(String nameOfItem) {
			this.nameOfItem = nameOfItem;
		}

		/**
		 * Gets the name of item.
		 *
		 * @return the name of item
		 */
		public String getNameOfItem() {
			return nameOfItem;
		}

		/**
		 * Sets the name of item.
		 *
		 * @param nameOfItem the new name of item
		 */
		public void setNameOfItem(String nameOfItem) {
			this.nameOfItem = nameOfItem;
		}

		/**
		 * Gets the to next.
		 *
		 * @return the to next
		 */
		public ItemVertex getToNext() {
			return toNext;
		}

		/**
		 * Sets the to next.
		 *
		 * @param toNext the new to next
		 */
		public void setToNext(ItemVertex toNext) {
			this.toNext = toNext;
		}

		/**
		 * To string.
		 *
		 * @return the string
		 */
		public String toString() {
			return "[ " + this.nameOfItem + " ]";
		}

		/**
		 * Transact contains.
		 *
		 * @param comparedWith the compared with
		 * @return true, if successful
		 */
		public boolean transact_contains(String comparedWith) {
			return getNameOfItem().trim().equals(comparedWith);
		}

		/**
		 * Find by recur.
		 *
		 * @param key   the key
		 * @param count the count
		 * @return the string
		 */
		public String findByRecur(int key, int count) {
			if (key == count) {
				return toString();
			}

			if (this.getToNext() != null) {
				return getToNext().findByRecur(key, count + 1);
			}

			return null;
		}

	}

	/**
	 * The Class LinkItem.
	 */
	static class LinkItem {

		/** The max size. */
		private final int MAX_SIZE = 10;

		/** The tail vtx. */
		private ItemVertex headVtx, tailVtx;

		/**
		 * Instantiates a new link item.
		 */
		public LinkItem() {
			this.headVtx = this.tailVtx = null;
		}

		/**
		 * Gets the max size.
		 *
		 * @return the max size
		 */
		public int getMAX_SIZE() {
			return MAX_SIZE;
		}

		/**
		 * If empty list.
		 *
		 * @return true, if successful
		 */
		private boolean ifEmptyList() {
			return this.headVtx == null;
		}

		/**
		 * If empty list.
		 *
		 * @param refthatStart the refthat start
		 * @return true, if successful
		 */
		protected static boolean ifEmptyList(ItemVertex refthatStart) {
			return refthatStart == null;
		}

		/**
		 * Gets the head vtx.
		 *
		 * @return the head vtx
		 */
		public ItemVertex getHeadVtx() {
			return headVtx;
		}

		/**
		 * Sets the head vtx.
		 *
		 * @param headVtx the new head vtx
		 */
		public void setHeadVtx(ItemVertex headVtx) {
			this.headVtx = headVtx;
		}

		/**
		 * Gets the tail vtx.
		 *
		 * @return the tail vtx
		 */
		public ItemVertex getTailVtx() {
			return tailVtx;
		}

		/**
		 * Sets the tail vtx.
		 *
		 * @param tailVtx the new tail vtx
		 */
		public void setTailVtx(ItemVertex tailVtx) {
			this.tailVtx = tailVtx;
		}

		/**
		 * Size.
		 *
		 * @return the int
		 */
		protected int size() {
			return this.sizeByRecur(this.headVtx);
		}

		/**
		 * Size by recur.
		 *
		 * @param base the base
		 * @return the int
		 */
		private int sizeByRecur(ItemVertex base) {
			if (base != null)
				return sizeByRecur(base.getToNext()) + 1;
			return 0;
		}

		/**
		 * Find.
		 *
		 * @param key the key
		 * @return the string
		 */
		public String find(int key) {
			return this.getHeadVtx().findByRecur(key, 1);
		}

		/**
		 * Append list.
		 *
		 * @param typeItem the type item
		 * @return true, if successful
		 */
		public boolean appendList(String typeItem) {
			if (size() >= 10)
				return false;
			ItemVertex typeItemTo_Obj = new ItemVertex(typeItem);
			if (!ifEmptyList()) {
				getTailVtx().setToNext(typeItemTo_Obj);
				setTailVtx(getTailVtx().getToNext());
				return true;
			}

			setHeadVtx(typeItemTo_Obj);
			setTailVtx(typeItemTo_Obj);
			return true;
		}

		/**
		 * Prints the to console.
		 */
		protected void printToConsole() {
			System.out.println(" ~~Item Lists~~ Size: " + size());
			this.displayByRecur(this.headVtx, 1);
		}

		/**
		 * Display by recur.
		 *
		 * @param base      the base
		 * @param numFormat the num format
		 */
		private void displayByRecur(ItemVertex base, int numFormat) {
			if (base != null) {
				System.out
						.println("[" + numFormat + "]. " + base + (base.getToNext() != null ? " <==  " : " <== tail "));
				displayByRecur(base.getToNext(), numFormat + 1);
			}
			return;
		}

	}

	/**
	 * The Class DataSetVertex.
	 */
	static class DataSetVertex {

		/** The data set vertex product. */
		private DataSetVertexProduct dataSetVertexProduct = new DataSetVertexProduct();

		/** The to next. */
		private DataSetVertex toNext;

		/**
		 * Instantiates a new data set vertex.
		 */
		public DataSetVertex() {
			dataSetVertexProduct.setHeadVtx(this.dataSetVertexProduct.getTailVtx());
		}

		/**
		 * Gets the head vtx.
		 *
		 * @return the head vtx
		 */
		public ItemVertex getHeadVtx() {
			return dataSetVertexProduct.getHeadVtx();
		}

		/**
		 * Gets the to next.
		 *
		 * @return the to next
		 */
		public DataSetVertex getToNext() {
			return toNext;
		}

		/**
		 * Sets the to next.
		 *
		 * @param toNext the new to next
		 */
		public void setToNext(DataSetVertex toNext) {
			this.toNext = toNext;
		}

		/**
		 * Sets the head vtx.
		 *
		 * @param headVtx the new head vtx
		 */
		public void setHeadVtx(ItemVertex headVtx) {
			dataSetVertexProduct.setHeadVtx(headVtx);
		}

		/**
		 * Gets the tail vtx.
		 *
		 * @return the tail vtx
		 */
		public ItemVertex getTailVtx() {
			return dataSetVertexProduct.getTailVtx();
		}

		/**
		 * Sets the tail vtx.
		 *
		 * @param tailVtx the new tail vtx
		 */
		public void setTailVtx(ItemVertex tailVtx) {
			dataSetVertexProduct.setTailVtx(tailVtx);
		}

		/**
		 * Prints the to console.
		 */
		protected void printToConsole() {
			dataSetVertexProduct.printToConsole();
		}

		/**
		 * Append list.
		 *
		 * @param typeItem the type item
		 * @return true, if successful
		 */
		public boolean appendList(String typeItem) {
			return dataSetVertexProduct.appendList(typeItem, this);
		}

		/**
		 * Display by recur.
		 *
		 * @param numFormat the num format
		 */
		public void displayByRecur(int numFormat) {
			if (this != null) {
				System.out.print("[" + numFormat + "].");
				dataSetVertexProduct.printToConsole();
				System.out.print(getToNext() != null ? " <== " : " <== tail ");

				if (getToNext() == null) {
					return;
				}
				System.out.println();
				getToNext().displayByRecur(numFormat + 1);
			}
			return;
		}

	}

	/**
	 * The Class LinkDataSet.
	 */
	static class LinkDataSet {

		/** The tail vtx. */
		private DataSetVertex headVtx, tailVtx;

		/**
		 * Instantiates a new link data set.
		 */
		public LinkDataSet() {
			this.headVtx = this.tailVtx = null;
		}

		/**
		 * Size.
		 *
		 * @return the int
		 */
		protected int size() {
			return this.sizeByRecur(this.headVtx);
		}

		/**
		 * Size by recur.
		 *
		 * @param base the base
		 * @return the int
		 */
		private int sizeByRecur(DataSetVertex base) {
			if (base != null)
				return sizeByRecur(base.getToNext()) + 1;
			return 0;
		}

		/**
		 * Gets the head vtx.
		 *
		 * @return the head vtx
		 */
		public DataSetVertex getHeadVtx() {
			return headVtx;
		}

		/**
		 * Sets the head vtx.
		 *
		 * @param headVtx the new head vtx
		 */
		public void setHeadVtx(DataSetVertex headVtx) {
			this.headVtx = headVtx;
		}

		/**
		 * Gets the tail vtx.
		 *
		 * @return the tail vtx
		 */
		public DataSetVertex getTailVtx() {
			return tailVtx;
		}

		/**
		 * Sets the tail vtx.
		 *
		 * @param tailVtx the new tail vtx
		 */
		public void setTailVtx(DataSetVertex tailVtx) {
			this.tailVtx = tailVtx;
		}

		/**
		 * Empty list.
		 *
		 * @return true, if successful
		 */
		private boolean emptyList() {
			return this.headVtx == null;
		}

		/**
		 * Append list.
		 *
		 * @param vtx the vtx
		 * @return true, if successful
		 */
		public boolean appendList(DataSetVertex vtx) {
			if (size() >= 10)
				return false;
			if (!emptyList()) {
				getTailVtx().setToNext(vtx);
				setTailVtx(getTailVtx().getToNext());
				return true;
			}

			setHeadVtx(vtx);
			setTailVtx(vtx);
			return true;
		}

		/**
		 * Prints the to console.
		 */
		protected void printToConsole() {
			this.headVtx.displayByRecur(1);
			System.out.println();
		}

		/**
		 * Out of bounds range.
		 *
		 * @param key the key
		 * @return true, if successful
		 */
		public boolean outOfBoundsRange(int key) {
			return key < 0 || key > size();
		}

		/**
		 * Removes the.
		 *
		 * @param key the key
		 */
		public void remove(int key) {
			headVtx = removeIteratively(this.headVtx, key - 1);
		}

		/**
		 * Removes the iteratively.
		 *
		 * @param theHead the the head
		 * @param key     the key
		 * @return the data set vertex
		 */
		private DataSetVertex removeIteratively(DataSetVertex theHead, int key) {
			if (key == 1)
				return theHead.getToNext();

			Main.DataSetVertex curr = removeObj(theHead, key);
			return theHead;
		}

		/**
		 * Removes the obj.
		 *
		 * @param theHead the the head
		 * @param key     the key
		 * @return the data set vertex
		 */
		private DataSetVertex removeObj(Main.DataSetVertex theHead, int key) {
			DataSetVertex curr = theHead;
			for (int counter = 1; curr != null && curr.getToNext() != null; counter++) {
				if (key == counter) {
					curr.setToNext(curr.getToNext().getToNext());
					break;
				} else
					curr = curr.getToNext();
			}
			return curr;
		}

		/**
		 * Support.
		 *
		 * @param A the a
		 * @param B the b
		 * @return the double
		 */
		public double support(String A, String B) {
			int theItems_frequency = supportProduct(A, B);
			double frequencyA_B = (double) theItems_frequency / size();

			System.out.println(
					"Support of Item " + A + " and Item " + B + " by fraction = " + theItems_frequency + "/" + size());
			System.out.printf("Support of Item %s and Item %s by decimal = %.2f", A, B, frequencyA_B);

			return frequencyA_B;

		}

		/**
		 * Support product.
		 *
		 * @param A the a
		 * @param B the b
		 * @return the int
		 */
		private int supportProduct(String A, String B) {
			int union_counter = 0;
			for (DataSetVertex eachData = this.getHeadVtx(); eachData != null; eachData = eachData.getToNext()) {
				boolean A_counter = false, B_counter = false;
				A_counter = supportProcess_A(A, eachData, A_counter);
				B_counter = supportProcess_B(B, eachData, B_counter);
				if (A_counter && B_counter) {
					union_counter++;
				}

			}
			return union_counter;
		}

		/**
		 * Support process B.
		 *
		 * @param B         the b
		 * @param eachData  the each data
		 * @param B_counter the b counter
		 * @return true, if successful
		 */
		private boolean supportProcess_B(String B, Main.DataSetVertex eachData, boolean B_counter) {
			for (ItemVertex eachItem = eachData.getHeadVtx(); eachItem != null; eachItem = eachItem.getToNext()) {
				if (eachItem.transact_contains(B)) {
					B_counter = true;
				}
			}
			return B_counter;
		}

		/**
		 * Support process A.
		 *
		 * @param A         the a
		 * @param eachData  the each data
		 * @param A_counter the a counter
		 * @return true, if successful
		 */
		private boolean supportProcess_A(String A, Main.DataSetVertex eachData, boolean A_counter) {
			for (ItemVertex eachItem = eachData.getHeadVtx(); eachItem != null; eachItem = eachItem.getToNext()) {
				if (eachItem.transact_contains(A)) {
					A_counter = true;
				}
			}
			return A_counter;
		}

		/**
		 * A counter by int.
		 *
		 * @param A the a
		 * @return the int
		 */
		private int single_counter_byInt(String A) {
			int A_counter_byInt = 0;
			for (DataSetVertex eachData = this.getHeadVtx(); eachData != null; eachData = eachData.getToNext()) {
				boolean A_counter = supportProcessSingle_A(A, eachData);
				if (A_counter) {
					A_counter_byInt++;
				}
			}
			return A_counter_byInt;
		}

		/**
		 * Support process single A.
		 *
		 * @param A        the a
		 * @param eachData the each data
		 * @return true, if successful
		 */
		private boolean supportProcessSingle_A(String A, Main.DataSetVertex eachData) {
			boolean A_counter = false;
			for (ItemVertex eachItem = eachData.getHeadVtx(); eachItem != null; eachItem = eachItem.getToNext()) {
				if (eachItem.transact_contains(A)) {
					A_counter = true;
				}
			}
			return A_counter;
		}
		
		public String association(String A, String B) {
			
			double assoc = Association_TwoItemsProduct(A, B);

			System.out.println(
					" ~~Association of Two Transcation items~~ ");
			System.out.printf("Association of Item %s and Item %s by decimal = %.2f", A, B, assoc);
			
			
			return (inspect_assoc(assoc))? " There is Association Between the Two Transactional Items ": " "
					+ "Unfortunately, There is no computed association here ";
		}
		
		private boolean inspect_assoc(double assoc) {
			return assoc >= 1;
		}
		
		
		private double Association_TwoItemsProduct(String A, String B) {
			double support_A = (double) single_counter_byInt(A), support_B =  (double) single_counter_byInt(B), frequencyA_B = (double) supportProduct(A, B);
			return frequencyA_B / (support_A * support_B) * size();

		}
	}

}
