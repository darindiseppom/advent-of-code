package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import commons.Utils;

public class Day11 {

	private static final String PATTERN_MATCHER = "(\\d)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final int X = 10;
	private static final int Y = 10;


	@Test
	public void assertPartOne() throws IOException {
		int[][] input = getInput("2021/day11.txt");
		assertEquals(1757, doPartOne(input, 100));
	}

	@Test
	public void assertPartTwo() throws IOException {
		int[][] input = getInput("2021/day11.txt");
		assertEquals(422, doPartTwo(input));
	}

	private int doPartOne(int[][] input, int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			counter += doStep(input, true);
		}
		return counter;
	}

	private int doPartTwo(int[][] input) {
		int counter = 0;
		int allFlashes = 0;
		while (allFlashes == 0) {
			counter++;
			allFlashes = doStep(input, false);
		}
		return counter;
	}

	private int doStep(int[][] input, boolean partOne) {
		int[][] trace = new int[Y][X];
		int highlighted = 0;
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length; x++) {
				input[y][x]++;
			}
		}
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length; x++) {
				if (input[y][x] == 10) {
					highlighted += doFlash(input, y, x, trace);
				}
			}
		}
		int allFlashes = 1;
		for (int y = 0; y < trace.length; y++) {
			for (int x = 0; x < trace[y].length; x++) {
				if (trace[y][x] == 0) {
					allFlashes = 0;
				}
			}
		}
		
		if (partOne) {			
			return highlighted;
		} else {
			return allFlashes;
		}
	}

	private int doFlash(int[][] input, int y, int x, int[][] trace) {
		int highlighted = 1;
		trace[y][x] = 1;
		input[y][x] = 0;
		try {
			highlighted += checkFlash(input, y - 1, x, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y - 1, x + 1, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y - 1, x - 1, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y, x + 1, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y, x - 1, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y + 1, x - 1, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y + 1, x, trace);
		} catch (Exception e) {
		}
		try {
			highlighted += checkFlash(input, y + 1, x + 1, trace);
		} catch (Exception e) {
		}
		return highlighted;
	}

	private int checkFlash(int[][] input, int y, int x, int[][] trace) {
		int highlighted = 0;
		if (trace[y][x] == 0 && input[y][x] != 10) {
			input[y][x]++;
			if (input[y][x] == 10) {
				highlighted += doFlash(input, y, x, trace);
			}
		}
		return highlighted;
	}

	private int[][] getInput(String pathToFile) throws FileNotFoundException {
		int[][] input = new int[Y][X];
		Matcher matcher;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int y = -1;
		int x = -1;
		while (scanner.hasNext()) {
			y++;
			matcher = PATTERN.matcher(scanner.next());
			x = -1;
			while (matcher.find()) {
				x++;
				input[y][x] = Integer.valueOf(matcher.group(1));
			}
		}
		scanner.close();
		return input;
	}

}
