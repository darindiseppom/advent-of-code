package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day03 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day03.txt";
	private static final Set<String> SYMBOLS = "@ # $ % & * + - = / ".chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toSet());
	private static final Set<String> NUMBERS = "0123456789".chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toSet());
	private static final String GEAR = "*";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(535078, getSum(getInput(PATH_TO_FILE), true));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(75312571, getSum(getInput(PATH_TO_FILE), false));
	}

	private String[][] getInput(String pathToFile) throws FileNotFoundException {
		String[][] grid = new String[500][500];
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int x = 0;
		while (scanner.hasNext()) {
			String[] row = scanner.next().split("");
			for (int i = 0; i < row.length; i++) {
				grid[x][i] = row[i];
			}
			x++;
		}
		scanner.close();
		return grid;
	}
	
	private int getSum(String[][] grid, boolean partOne) {
		int counter = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				if (partOne) {
					if (SYMBOLS.contains(grid[x][y])) {
						counter += getSum(grid, x, y, false);
					}					
				} else {
					if (GEAR.equals(grid[x][y])) {
						counter += getSum(grid, x, y, true);
					}
				}
			}			
		}
		return counter;
	}

	private int getSum(String[][] grid, int x, int y, boolean partTwo) {
		int counter = partTwo ? 1 : 0;
		int times = 0;
		int num = tryGetNumber(grid, x-1, y-1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x-1, y);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x-1, y+1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x, y-1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x, y+1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x+1, y-1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x+1, y);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}
		num = tryGetNumber(grid, x+1, y+1);
		if (num > 0) {
			times++;
			if (partTwo) {
				counter *= num;  
			} else {				
				counter += num;
			}
		}

		if (partTwo && times != 2) return 0;
		
		return counter;
	}

	private int tryGetNumber(String[][] grid, int x, int y) {
		try {
			if (NUMBERS.contains(grid[x][y])) {
				return getNumber(grid, x, y);			
			}				
		} catch (IndexOutOfBoundsException e) { }
		return 0;
	}

	private int getNumber(String[][] grid, int x, int y) {
		int old = y;
		String num = grid[x][y];
		grid[x][y] = ".";
		try {			
			while(NUMBERS.contains(grid[x][--y])) {
				num = grid[x][y].concat(num);
				grid[x][y] = ".";
			}
		} catch (IndexOutOfBoundsException e) { }
		y = old;
		try {			
			while(NUMBERS.contains(grid[x][++y])) {
				num = num.concat(grid[x][y]);
				grid[x][y] = ".";
			}
		} catch (IndexOutOfBoundsException e) { }
		return Integer.valueOf(num);
	}

}
