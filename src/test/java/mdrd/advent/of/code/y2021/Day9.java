package mdrd.advent.of.code.y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day9 {

	private static final int X = 100;
	private static final int Y = 100;
	private static final String PATTERN_MATCHER = "(\\d)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void assertPartOne() throws IOException {
		int[][] input = getInput("2021/day9.txt");
		assertEquals(417, doPartOne(input));
	}

	@Test
	public void assertPartTwo() throws IOException {
		int[][] input = getInput("2021/day9.txt");
		assertEquals(1148965, doPartTwo(input));
	}

	private int doPartOne(int[][] input) {
		int sum = 0;
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length; x++) {
				if (isLowest(input, x, y)) {
					sum += (1 + input[y][x]);
				}
			}
		}
		return sum;
	}

	private int doPartTwo(int[][] input) {
		List<Integer> basins = new ArrayList<>();
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length; x++) {
				if (isLowest(input, x, y)) {
					basins.add(getBasinSize(input, x, y, new int[input.length][input[0].length]));
				}
			}
		}
		
		int result = 1;
		Collections.sort(basins);
		List<Integer> tail = Utils.tail(basins, 3);
		for (Integer el : tail) {
			result *= el;
		}
		return result;
	}

	private int getBasinSize(int[][] input, int x, int y, int[][] tracer) {
		int result = 1;
		if (tracer[y][x] == 1 || input[y][x] == 9) return 0;

		try {
			if (input[y][x] < input[y][x-1]) {
				result += getBasinSize(input, x-1, y, tracer);
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] < input[y-1][x]) {
				result += getBasinSize(input, x, y-1, tracer);
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] < input[y][x+1]) {
				result += getBasinSize(input, x+1, y, tracer);
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] < input[y+1][x]) {
				result += getBasinSize(input, x, y+1, tracer);
			}			
		} catch(Exception e) {}
		tracer[y][x] = 1;
		return result;
	}

	private boolean isLowest(int[][] input, int x, int y) {
		try {
			if (input[y][x] >= input[y][x-1]) {
				return false;
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] >= input[y-1][x]) {
				return false;
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] >= input[y][x+1]) {
				return false;
			}			
		} catch(Exception e) {}
		try {
			if (input[y][x] >= input[y+1][x]) {
				return false;
			}			
		} catch(Exception e) {}
		return true;
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
