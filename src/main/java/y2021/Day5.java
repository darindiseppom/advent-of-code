package y2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import commons.Utils;

public class Day5 {

	private static final String PATTERN_MATCHER = "(\\d+),(\\d+) -> (\\d+),(\\d+)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void assertPartOne() throws IOException {
		assertEquals(6856, countAbove(getInput("2021/day5-1.txt", true), 1));
	}

	@Test
	public void assertPartTwo() throws IOException {
		assertEquals(20666, countAbove(getInput("2021/day5-2.txt", false), 1));
	}

	private int[][] getInput(String pathToFile, boolean isPartOne) throws FileNotFoundException {
		int[][] matrix = new int[1000][1000];
		Matcher matcher;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			matcher = PATTERN.matcher(scanner.next());
			matcher.find();
			if (isPartOne) {
				if (!matcher.group(1).equals(matcher.group(3)) && !matcher.group(2).equals(matcher.group(4))) {
					continue;
				}
				drawHoriVertLine(matrix, Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)),
						Integer.valueOf(matcher.group(4)));	
			} else {
				drawAllLine(matrix, Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)),
						Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(4)));
			}
		}
		scanner.close();
		return matrix;
	}

	private void drawHoriVertLine(int[][] matrix, Integer x1, Integer y1, Integer x2, Integer y2) {
		if (x1.equals(x2)) {
			drawVerticalLine(matrix, x1, y1, y2);
		} else {
			drawHorizontalLine(matrix, y1, x1, x2);
		}
	}

	private void drawHorizontalLine(int[][] matrix, Integer pivot, Integer x1, Integer x2) {
		drawLine(matrix, pivot, x1, x2, true);
	}
	
	private void drawVerticalLine(int[][] matrix, Integer pivot, Integer y1, Integer y2) {
		drawLine(matrix, pivot, y1, y2, false);
	}

	private void drawLine(int[][] matrix, Integer pivot, Integer c1, Integer c2, boolean isHorizontal) {
		Integer tmp;
		if (c1 > c2) {
			tmp = c2;
			c2 = c1;
			c1 = tmp;
		}
		for (int i = c1; i < c2 + 1; i++) {
			matrix[isHorizontal ? pivot : i][isHorizontal ? i : pivot]++;
		}
	}

	private void drawAllLine(int[][] matrix, Integer x1, Integer y1, Integer x2, Integer y2) {
		Integer tmp;
		boolean fun;
		Integer startingX;
		Integer startingY;
		if (x1.equals(x2)) {
			drawVerticalLine(matrix, x1, y1, y2);
		} else if (y1.equals(y2)) {
			drawHorizontalLine(matrix, y1, x1, x2);
		} else {
			assertTrue(isDiagonal(x1, y1, x2, y2));
			tmp = Math.abs(x1 - x2);
			fun = y1 > y2;
			fun = (x1 > x2) ? fun : !fun;
			startingX = Math.min(x1, x2);
			startingY = x1 > x2 ? y2 : y1;
			for (int i = 0; i < tmp + 1; i++) {
				matrix[doIncDec(fun, startingY, i)][startingX + i]++;
			}
		}
	}

	private int doIncDec(boolean fun, int n1, int n2) {
		return fun ? (n1 + n2) : (n1 - n2);
	}

	private boolean isDiagonal(Integer x1, Integer y1, Integer x2, Integer y2) {
		return Math.abs(x1 - x2) == Math.abs(y1 - y2);
	}

	private int countAbove(int[][] matrix, int above) {
		int count = 0;
		for (int[] row : matrix) {
			for (int cell : row) {
				if (cell > above) {
					count++;
				}
			}
		}
		return count;
	}
}
