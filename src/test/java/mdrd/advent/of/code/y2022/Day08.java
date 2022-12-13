package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day08 {

	private static final String PATH_TO_FILE = "2022/day08.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		int[][] grid = getGrid(PATH_TO_FILE);
		assertEquals(1870, getVisibles(grid));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		int[][] grid = getGrid(PATH_TO_FILE);
		assertEquals(517440, getMaxScenicScore(grid));
	}

	private int getMaxScenicScore(int[][] grid) {
		int max = -1;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				int scenicScore = getScenicScore(grid, x, y);
				if (max < scenicScore) {
					max = scenicScore;
				}
			}
		}
		return max;
	}

	private int getScenicScore(int[][] grid, int x, int y) {
		if (x == 0 || y == 0 || x == grid.length - 1 || y == grid.length - 1) {
			return 0;
		}
		int counter1 = 0;
		for (int i = x - 1; i >= 0; i--) {
			counter1++;
			if (grid[i][y] >= grid[x][y]) {
				break;
			}
		}
		int counter2 = 0;
		for (int i = y - 1; i >= 0; i--) {
			counter2++;
			if (grid[x][i] >= grid[x][y]) {
				break;
			}
		}
		int counter3 = 0;
		for (int i = x + 1; i < grid.length; i++) {
			counter3++;
			if (grid[i][y] >= grid[x][y]) {
				break;
			}
		}
		int counter4 = 0;
		for (int i = y + 1; i < grid.length; i++) {
			counter4++;
			if (grid[x][i] >= grid[x][y]) {
				break;
			}
		}
		return counter1 * counter2 * counter3 * counter4;
	}

	private int getVisibles(int[][] grid) {
		int counter = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				counter += isVisible(grid, x, y) ? 1 : 0;
			}
		}
		return counter;
	}

	private boolean isVisible(int[][] grid, int x, int y) {
		if (x == 0 || y == 0 || x == grid.length - 1 || y == grid.length - 1) {
			return true;
		}
		boolean result = true;
		for (int i = x - 1; i >= 0; i--) {
			if (grid[i][y] >= grid[x][y]) {
				result = false;
				break;
			}
		}
		if (result) {
			return true;
		}
		result = true;
		for (int i = y - 1; i >= 0; i--) {
			if (grid[x][i] >= grid[x][y]) {
				result = false;
				break;
			}
		}
		if (result) {
			return true;
		}
		result = true;
		for (int i = x + 1; i < grid.length; i++) {
			if (grid[i][y] >= grid[x][y]) {
				result = false;
				break;
			}
		}
		if (result) {
			return true;
		}
		for (int i = y + 1; i < grid.length; i++) {
			if (grid[x][i] >= grid[x][y]) {
				return false;
			}
		}
		return true;
	}

	private int[][] getGrid(String pathToFile) throws FileNotFoundException {
		int[][] result = null;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String line;
		int y = -1;
		while (scanner.hasNext()) {
			line = scanner.next();
			y++;
			if (result == null) {
				result = new int[line.length()][line.length()];
			}
			for (int x = 0; x < line.length(); x++) {
				result[x][y] = Integer.valueOf(line.substring(x, x + 1));
			}
		}
		scanner.close();
		return result;
	}

}
