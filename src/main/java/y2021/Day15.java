package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import commons.Utils;

public class Day15 {

	private static final String PATTERN_MATCHER = "(\\d)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final int X = 100;
	private static final int Y = 100;
	private static final int MAX = 100;
	
	private static int lowestRisk;

	@Test
	public void assertPartOne() throws IOException {
		int[][] input = getInput("2021/day15.txt");
		assertEquals(40, doPartOne(input));
	}

	private int[][] clone(int[][] input) {
		int[][] result = new int[input.length][input[0].length];
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length; x++) {
				result[y][x] = input[y][x];
			}
		}
		return result;
	}

//	private void print(int[][] input) {
//		for (int[] is : input) {
//			for (int is2 : is) {
//				System.out.print(is2 + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

	private Object doPartOne(int[][] input) {
		input[0][0] = MAX;
		lowestRisk = MAX;
		int r1 = getRisk(input, 0, 1, 0);
		int r2 = getRisk(input, 1, 0, 0);
		return Math.min(r1, r2);
	}

	private int getRisk(int[][] input, int y, int x, int currentRisk) {
		try {
			currentRisk += input[y][x];
		} catch (IndexOutOfBoundsException e) {
			return MAX;
		}
		if (y == (Y - 1) && x == (X - 1)) {
			lowestRisk = currentRisk;
			return currentRisk;
		}
		if (currentRisk > lowestRisk) {
			return currentRisk;
		}
		int[][] clone = clone(input);
		clone[y][x] = MAX;
		List<Integer> risks = new ArrayList<>();
		risks.add(getRisk(clone, y, x + 1, currentRisk));
		risks.add(getRisk(clone, y + 1, x, currentRisk));
		risks.add(getRisk(clone, y, x - 1, currentRisk));
		risks.add(getRisk(clone, y - 1, x, currentRisk));
		Collections.sort(risks);
		return risks.get(0);
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
