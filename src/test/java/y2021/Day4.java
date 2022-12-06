package y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import commons.Utils;

public class Day4 {

	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String SPLIT_REGEX_NUMBERS = ",";
	private static final String SPLIT_REGEX_BOARDS = " +";

	@Test
	public void assertPartOne() throws IOException {
	    assertEquals(25023, doPartOne(getInput("2021/day4-1.txt")));
	}

	@Test
	public void assertPartTwo() throws IOException {		
	    assertEquals(2634, doPartTwo(getInput("2021/day4-2.txt")));
	}

	private Bingo getInput(String pathToFile) throws FileNotFoundException {
		Day4.Bingo bingo = new Day4.Bingo();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String[] split = scanner.next().split(SPLIT_REGEX_NUMBERS);
		while (scanner.hasNext()) {
			scanner.next();
			bingo.boards.add(new Day4.Board(
					filter(scanner.next().split(SPLIT_REGEX_BOARDS)), 
					filter(scanner.next().split(SPLIT_REGEX_BOARDS)), 
					filter(scanner.next().split(SPLIT_REGEX_BOARDS)), 
					filter(scanner.next().split(SPLIT_REGEX_BOARDS)), 
					filter(scanner.next().split(SPLIT_REGEX_BOARDS))));
		}
		scanner.close();

		for (String string : split) {
			bingo.numbers.add(Integer.valueOf(string));
		}
		return bingo;
	}

	private String[] filter(String[] row1) {
		return Arrays.stream(row1).filter(el -> !ObjectUtils.isEmpty(el)).toArray(String[]::new);
	}

	private int doPartOne(Bingo bingo) {
		for (Integer number : bingo.numbers) {
			bingo.markNumber(number);
			for (Board board : bingo.boards) {
				int winningBoard = board.isWinning();
				if (winningBoard != -1) {
					return winningBoard * number;
				}
			}
		}			
		return 1;
	}

	private int doPartTwo(Bingo bingo) {
		List<Integer> results = new ArrayList<>();
		for (Integer number : bingo.numbers) {
			bingo.markNumber(number);
			for (Board board : bingo.boards) {
				int winningBoard = board.isWinning();
				if (winningBoard != -1) {
					board.isWinner = true;
					results.add(winningBoard * number);
				}
			}
		}			
		return results.get(results.size() - 1);
	}

	public class Bingo {
		List<Integer> numbers = new ArrayList<Integer>();
		List<Board> boards = new ArrayList<Day4.Board>();
		
		public void markNumber(int number) {
			boards.forEach(el -> el.markNumber(number));
		}
	}

	public class Board {
		Slot[][] slots = new Slot[5][5];
		boolean isWinner = false;

		public Board(String[]... rows) {
			for (int i = 0; i < rows.length; i++) {
				for (int j = 0; j < rows[i].length; j++) {
					this.slots[i][j] = new Slot(Integer.valueOf(rows[i][j]), false);
				}
			}
		}
		
		public void markNumber(int number) {
			for (Slot[] row : slots) {
				for (Slot slot : row) {
					if (number == slot.number) {
						slot.marked = true;
					}
				}
			}
		}
		
		public int isWinning() {
			if (isWinner) {
				return -1;
			}
			Slot slot;
			boolean isWinning = false;
			boolean boardWinning = false;
			int unMarked = 0;
			for (int i = 0; i < 5; i++) {
				isWinning = true;
				for (int j = 0; j < 5; j++) {
					slot = slots[i][j];
					if (!slot.marked) {
						isWinning = false;
						unMarked += slot.number;
					}
				}
				if (isWinning) {
					boardWinning = true;
				}
			}
			for (int i = 0; i < 5; i++) {
				isWinning = true;
				for (int j = 0; j < 5; j++) {
					slot = slots[j][i];
					if (!slot.marked) {
						isWinning = false;
					}
					
				}
				if (isWinning) {
					boardWinning = true;
				}
			}
			return boardWinning ? unMarked : -1;
		}
	}
	
	public class Slot {
		int number;
		boolean marked;
		public Slot(int number, boolean marked) {
			this.number = number;
			this.marked = marked;
		}
	}

}
