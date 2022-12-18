package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day14 {

	private static final String PATH_TO_FILE = "2022/day14.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static char[][] matrix;
	private static final int SAND_X = 500;
	private static final int SAND_Y = 0;
	private static int minY = 0;
	private static int maxY = 0;
	private static int minX = Integer.MAX_VALUE;
	private static int maxX = 0;

	@Test
	public void partOne() throws FileNotFoundException {
		initInput(PATH_TO_FILE, false);
		execute(false);
		assertEquals(737, countGrains());
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		initInput(PATH_TO_FILE, true);
		execute(true);
		assertEquals(28145, countGrains());
	}

	private int countGrains() {
		int counter = 0;
		for (int y = 0; y < matrix[0].length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				counter += matrix[x][y] == 'o' ? 1 : 0;
			}
		}
		return counter;
	}

	private void execute(boolean secondPart) {
		while (matrix[SAND_X - minX + maxY][SAND_Y] != '~') {
			MutablePair<Integer, Integer> grain = new MutablePair<Integer, Integer>(SAND_X - minX + maxY, SAND_Y);
//			print();
			while (!isRest(grain) && (!inVoid(grain))) {
				fallGrain(grain);
			}
			if (grain.getLeft().equals(SAND_X - minX + maxY) && grain.getRight().equals(SAND_Y)) {
				matrix[SAND_X - minX + maxY][SAND_Y] = 'o';
				break;
			}
		}
	}

	private void fallGrain(MutablePair<Integer, Integer> grain) {
		if (!isOccupied(matrix[grain.getLeft()][grain.getRight() + 1])) {
			grain.setRight(grain.getRight() + 1);
		} else if (!isOccupied(matrix[grain.getLeft() - 1][grain.getRight() + 1])) {
			grain.setRight(grain.getRight() + 1);
			grain.setLeft(grain.getLeft() - 1);
		} else if (!isOccupied(matrix[grain.getLeft() + 1][grain.getRight() + 1])) {
			grain.setRight(grain.getRight() + 1);
			grain.setLeft(grain.getLeft() + 1);
		} else {
			System.out.println("FallGrain");
		}
	}

	private boolean inVoid(Pair<Integer, Integer> grain) {
		if (grain.getRight() >= maxY + 2) {
			matrix[SAND_X - minX + maxY][SAND_Y] = '~';
			return true;
		}
		return false;
	}

	private boolean isRest(Pair<Integer, Integer> grain) {
		char c = matrix[grain.getLeft()][grain.getRight()];
		if (c == 'o') {
			return true;
		}
		char c1 = matrix[grain.getLeft()][grain.getRight() + 1];
		char c2 = matrix[grain.getLeft() - 1][grain.getRight() + 1];
		char c3 = matrix[grain.getLeft() + 1][grain.getRight() + 1];
		if (isOccupied(c1) && isOccupied(c2) && isOccupied(c3)) {
			if (grain.getLeft() != SAND_X && grain.getRight() != SAND_Y) {
				matrix[grain.getLeft()][grain.getRight()] = 'o';
			}
			return true;
		}
		return false;
	}

	private boolean isOccupied(char grain) {
		return grain == 'o' || grain == '#';
	}

	private void initInput(String pathToFile, boolean secondPart) throws FileNotFoundException {
		List<Pair<List<Object>, List<Object>>> result = new ArrayList<Pair<List<Object>, List<Object>>>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			String line1 = scanner.next();
			Matcher matcher = Pattern.compile("(\\d+),(\\d+)").matcher(line1);
			while (matcher.find()) {
				Integer x = Integer.valueOf(matcher.group(1));
				Integer y = Integer.valueOf(matcher.group(2));
				maxY = y > maxY ? y : maxY;
				maxX = x > maxX ? x : maxX;
				minX = x < minX ? x : minX;
			}
		}
		scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		matrix = new char[maxX - minX + 2 * maxY][maxY + 5]; // Da valutare
		init();
		while (scanner.hasNext()) {
			String line1 = scanner.next();
			Matcher matcher = Pattern.compile("(\\d+),(\\d+)").matcher(line1);
			Integer x1;
			Integer x2 = null;
			Integer y1;
			Integer y2 = null;
			while (matcher.find()) {
				x1 = x2;
				x2 = Integer.valueOf(matcher.group(1));
				y1 = y2;
				y2 = Integer.valueOf(matcher.group(2));
				if (x1 != null) {
					draw(x1 - minX + maxY, y1 - minY, x2 - minX + maxY, y2 - minY);
				}
			}
		}
		matrix[SAND_X - minX + maxY][SAND_Y] = '+';
		if (secondPart) {
			draw(0, maxY + 2, matrix.length - 1, maxY + 2);
		}
//		print();
	}

	private void print() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < matrix[0].length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				sb.append(matrix[x][y]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private void init() {
		for (int y = 0; y < matrix[0].length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				matrix[x][y] = '.';
			}
		}
	}

	private void draw(Integer x1, Integer y1, Integer x2, Integer y2) {
		int max;
		int min;
		if (x1.equals(x2)) {
			min = y1 > y2 ? y2 : y1;
			max = y1 > y2 ? y1 : y2;
			for (int y = min; y <= max; y++) {
				matrix[x1][y] = '#';
			}
		} else {
			min = x1 > x2 ? x2 : x1;
			max = x1 > x2 ? x1 : x2;
			for (int x = min; x <= max; x++) {
				matrix[x][y1] = '#';
			}
		}
	}

}
