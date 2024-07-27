package BingoProject;

import java.util.*;

public class Main {

	public static void main(String[] args) {
	Bingo newGame = new Bingo();
	
	newGame.run_bingo();
	

	}
	
}

class Bingo {
	private static Board mainBoard = new Board();
	private static Board AIBoard = new Board();
	private static HashSet<Integer> forStoringShuffledNum = new HashSet<>();
	private static HashSet<Integer> forStoringShuffledNumAI = new HashSet<>();
	private final static Scanner sc = new Scanner(System.in);
	
	public void run_bingo() {
		Menu();
	}
	public void Menu() {
		System.out.println("""
						---------------------------
						|       Play Bingo        |
						|-------------------------|
						|    [1] Play Game        |
						|    [2] Exit Game        |
						---------------------------
						""");
		int menuResponse = scanIssuesThenStore_Int("Enter chosen operation: ", "int");
		
		switch(menuResponse) {
		case 1 -> {
			shuffle();
		}
		case 2 -> {
			exitGame();
		}
		}
		
	}
	
	private void exitGame() {
		System.out.println("""
						-----------------------
						| Exit Game?          |
						-----------------------
						| [1] Yes             |
						| [2] No              |
						-----------------------
							""");
		int endResponse = scanIssuesThenStore_Int("Enter chosen operation: ", "int");
		
		switch(endResponse) {
		case 1-> {
			System.out.print("\tLeaving The Game");
			for (int i = 0; i < 3; i++) {
				System.out.print(".");
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			System.exit(0);
		}
		case 2-> {
			run_bingo();
		}
		}
	}
	
	private void aITurn() {
		while (true) {
			 int shuffledNumber = produceNum();
			if (forStoringShuffledNumAI.contains(shuffledNumber)) {
				System.out.println("(AI) Reshuffling...");
			} else {
				System.out.println("(AI) Shuffled Num= " + shuffledNumber);
				AIBoard.updatedBoard(shuffledNumber);
				break;
			}
			}
		
	}
	
	private void shuffle() {
		while (true) {
		System.out.println("""
				---------------------------
				|     *  Play Game        |
				|-------------------------|
				|    [1] Shuffle          |
				|    [2] Go Back          |
				---------------------------
				""");
		int playResponse = scanIssuesThenStore_Int("Enter chosen operation: ", "int");
		
		switch (playResponse) {
		case 1 -> {
			while (true) {
				 int shuffledNumber = produceNum();
				if (forStoringShuffledNum.contains(shuffledNumber)) {
					System.out.println("Reshuffling...");
				} else {
					System.out.println("Shuffled Num= " + shuffledNumber);
					mainBoard.updatedBoard(shuffledNumber);
					break;
				}
			}
			
			aITurn();
			
			
		}
		
		case 2 -> {
			run_bingo();
		}
		}
		}
	}
	
	private int produceNum() {
		return (int) (Math.random() * (75 - 1) + 1);
	}
	
	private int scanIssuesThenStore_Int(String promptMsg, String datatype) { 
		System.out.println(promptMsg);
		while (!sc.hasNextInt()) { 
			System.err.println("""
				             --------------------------------
				             | Invalid Data Type?           |
				             |-------------------------------
							 | ~ Only enter datatype (%s)
							 |    necessary for specific
							 |    operation.
							 -------------------------------- 
								""".formatted(datatype));
			sc.next();
		}
		return Math.abs(sc.nextInt());
	}
}

class Board {
		private int[][] myBoard;
		private static final int ROWS = 5, COLUMN = 5;
		private int winByHorizontal, winByVertical, winByPrincipalDiag = 1, winBySecondaryDiag = 1;
		
		
	public Board() {
		myBoard = new int[ROWS][COLUMN];
		addNumbers();

		
	}
	
	
	private void addNumbers() {
		int markMid = 0;
		for (int[] aMyBoard : myBoard) {
			for (int j = 0; j < myBoard[0].length; j++) {
				int elem;
				do {
					elem = (int) (Math.random() * (15 - 1) + (j * 15) + 1);
				} while (contains(elem));
				
				if (markMid == 2 && j == 2) aMyBoard[j] = -1;
				else aMyBoard[j] = elem;
			}
			markMid++;
		}
	}
	
	private boolean contains(int thisElem) {
		for (int[] aMyBoard : myBoard) {
			for (int j = 0; j < myBoard[0].length; j++) {
				if (aMyBoard[j] == thisElem) return true;
			}
		}
		return false;
	}
	
	public void print() {
		boardHeader();
		boardBody();
		boardFooter();
	}
	
	public int markMatch(int shuffledNumber) {
		for (int[] aMyBoard : myBoard) {
			for (int j = 0; j < myBoard[0].length; j++) {
				if (aMyBoard[j] == shuffledNumber) {
					aMyBoard[j] = 0;
					return 0;
				}
			}
		}
		return -1;
	}
	
	protected void updatedBoard(int shuffledNumber) {
		if (markMatch(shuffledNumber) == 0)  System.out.println("Found Match!! ");
		else System.out.println("Keep going!!");
		
		print();
		detectABingo();
	}
	
	protected void detectABingo() {
		for (int i = 0; i < myBoard.length; i++) {
			winByHorizontal = 0;
			winByVertical = 0;
			for (int j = 0; j < myBoard[0].length; j++) {
				winByHorizontal += myBoard[i][j] == 0 || myBoard[i][j] == -1? 1:0;
				winByVertical += myBoard[j][i] == 0? 1:0;
				
				winByPrincipalDiag += ((i == j) && myBoard[i][j] == 0)? 1: 0;
				winBySecondaryDiag += ((i + j) == (myBoard.length -1) && myBoard[i][j] == 0)? 1:0;
				
				
				
			}
			
			if (winByHorizontal == 5 || winByVertical == 5) {
				checker();
				return;
			}
			
		}
		
		checker();
		System.out.println(winByHorizontal);
		System.out.println(winByVertical);
		System.out.println(winByPrincipalDiag);

		System.out.println(winBySecondaryDiag);

		
	
	}
	
	private void checker() {
		if (winByHorizontal >= 5) {
			System.out.println("Winner by Horizontal!");
			return;
		}
		if (winByVertical >= 5) {
			System.out.println("Winner by Vertical!");
			return;
		}
		if (winByPrincipalDiag >= 4) {
			System.out.println("Winner by Left Diagonal!");
			return;
		}
		if (winBySecondaryDiag >= 4) {
			System.out.println("Winner by Right Diagonal!");
			return;
		}
	}

	protected void boardBody() {
		for (int[] aMyBoard : myBoard) {
			for (int j = 0; j < myBoard[0].length; j++) {
				if (aMyBoard[j] == -1) System.out.printf("|%3s %s", "X", "" );
				else if (aMyBoard[j] != 0) System.out.printf("|%3d ",  aMyBoard[j]);
				else System.out.printf("|%3s %s", "X", "" );
				System.out.print((j >= 4)? "|":"");
			}			
				
			System.out.println();

		}
	}


	protected void boardHeader() {
		System.out.println("--------------------------");
		System.out.printf("|%3s |%3s |%3s |%3s |%3s |%n"	,"B", "I", "N", "G", "O");
		System.out.println("--------------------------");
	}
	
	protected void boardFooter() {
		System.out.println("--------------------------");
		System.out.printf("|%3s |%3s |%3s |%3s |%3s |%n"	,"B", "I", "N", "G", "O");
		System.out.println("--------------------------");
		System.out.printf("%6s %10s %3s %n %3s %1s %n", "|", "Play Now", "|", "", "----------------");
	}
		
		
	}



