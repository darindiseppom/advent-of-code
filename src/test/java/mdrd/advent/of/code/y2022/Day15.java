package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day15 {

	private static final String PATH_TO_FILE = "2022/day15.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static char[][] matrix;
	private static int minY = Integer.MAX_VALUE;
	private static int maxY = Integer.MIN_VALUE;
	private static int minX = Integer.MAX_VALUE;
	private static int maxX = Integer.MIN_VALUE;
	private static int maxGap = Integer.MIN_VALUE;
	private static Set<Integer> result = new HashSet<Integer>();
	private static Collection<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> result2 = new ArrayList<>();

//	@Test
//	public void partOne() throws FileNotFoundException {
//		assertEquals(5716881, countNot(PATH_TO_FILE, 2000000));
//	}

//	@Test
//	public void partOne() throws FileNotFoundException {
//		parseInput(PATH_TO_FILE);
////		assertEquals(5716881, countNot(PATH_TO_FILE, 2000000));
//	}

	@Test
	public void partThree() throws FileNotFoundException {
		int y = 0;
		getInput(PATH_TO_FILE);
		xCan(y);
		while (result.size() == 0) {
			System.out.println(y);
			xCan(++y);
		}
		System.out.println(result);
		int x = (Integer) result.toArray()[0];
		assertEquals(56000011, x * 4000000 + y);
	}

	private void getInput(String pathToFile) throws FileNotFoundException {
//		Collection<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> result2 = new ArrayList<>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			Matcher matcher = Pattern.compile(".*=(-*\\d+),.*=(-*\\d+):.*=(-*\\d+),.*=(-*\\d+)")
					.matcher(scanner.next());
			while (matcher.find()) {
				Integer xS = Integer.valueOf(matcher.group(1));
				Integer yS = Integer.valueOf(matcher.group(2));
				Integer xB = Integer.valueOf(matcher.group(3));
				Integer yB = Integer.valueOf(matcher.group(4));
				maxX = xS > maxX ? xS : maxX;
				Pair<Integer, Integer> s = new ImmutablePair<>(xS, yS);
				Pair<Integer, Integer> b = new ImmutablePair<>(xB, yB);
				Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> line = new ImmutablePair<>(s, b);
				result2.add(line);
			}
		}
//		return result2;
	}

	private void xCan(int y) {
		result = new HashSet<Integer>(IntStream.rangeClosed(0, maxX < 1000000 ? maxX : 1000000).boxed().toList());

		result2.forEach(el -> {
			Integer xS = Integer.valueOf(el.getLeft().getLeft());
			Integer yS = Integer.valueOf(el.getLeft().getRight());
			Integer xB = Integer.valueOf(el.getRight().getLeft());
			Integer yB = Integer.valueOf(el.getRight().getRight());
			int gap = Math.abs(xS - xB) + Math.abs(yS - yB);
			int diff = gap - Math.abs(yS - y);
			if (diff >= 0) {
				for (int i = 0; i <= diff; i++) {
					result.remove(xS + i);
					result.remove(xS - i);
				}
			}
		});

	}

	private int countNot(String pathToFile, int y) throws FileNotFoundException {
		Set<Integer> result = new HashSet<Integer>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			Matcher matcher = Pattern.compile(".*=(-*\\d+),.*=(-*\\d+):.*=(-*\\d+),.*=(-*\\d+)")
					.matcher(scanner.next());
			while (matcher.find()) {
				Integer xS = Integer.valueOf(matcher.group(1));
				Integer yS = Integer.valueOf(matcher.group(2));
				Integer xB = Integer.valueOf(matcher.group(3));
				Integer yB = Integer.valueOf(matcher.group(4));
				int gap = Math.abs(xS - xB) + Math.abs(yS - yB);
				int diff = gap - Math.abs(yS - y);
				if (diff >= 0) {
					for (int i = 0; i <= diff; i++) {
						if (xS + i != xB || y != yB) {
							result.add(xS + i);
						}
						if (xS - i != xB || y != yB) {
							result.add(xS - i);
						}
					}
				}
			}
		}
		return result.size();
	}

	private void parseInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			Matcher matcher = Pattern.compile(".*=(-*\\d+),.*=(-*\\d+):.*=(-*\\d+),.*=(-*\\d+)")
					.matcher(scanner.next());
			while (matcher.find()) {
				Integer xS = Integer.valueOf(matcher.group(1));
				Integer yS = Integer.valueOf(matcher.group(2));
				Integer xB = Integer.valueOf(matcher.group(3));
				Integer yB = Integer.valueOf(matcher.group(4));
//				System.out.println("Sensor at x="+(10 + xS)+", y="+(10+yS)+": closest beacon is at x="+(xB+10)+", y="+(10+yB));
				maxX = xS > maxX ? xS : maxX;
//				maxX = xB > maxX ? xB : maxX;
				maxY = yS > maxY ? yS : maxY;
//				maxY = yB > maxY ? yB : maxY;
				minX = xS < minX ? xS : minX;
//				minX = xB < minX ? xB : minX;
				minY = yS < minY ? yS : minY;
//				minY = yB < minY ? yB : minY;
				int gap = Math.abs(xS - xB) + Math.abs(yS - yB);
				maxGap = gap > maxGap ? gap : maxGap;
			}
		}
		scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
//		matrix = new char[4000000][4000000];
//		matrix = new char[maxX - minX + 2 * maxGap][maxY - minY + 2 * maxGap];
		matrix = new char[maxX - minX+1][maxY - minY+1];
		init();
		while (scanner.hasNext()) {
			Matcher matcher = Pattern.compile(".*=(-*\\d+),.*=(-*\\d+):.*=(-*\\d+),.*=(-*\\d+)")
					.matcher(scanner.next());
			while (matcher.find()) {
				Integer xS = Integer.valueOf(matcher.group(1));
				Integer yS = Integer.valueOf(matcher.group(2));
				Integer xB = Integer.valueOf(matcher.group(3));
				Integer yB = Integer.valueOf(matcher.group(4));
//				matrix[xS - minX + maxGap][yS - minY + maxGap] = 'S';
				try {
					matrix[xS - minX][yS - minY] = 'S';
//				matrix[xB - minX + maxGap][yB - minY + maxGap] = 'B';
					matrix[xB - minX][yB - minY] = 'B';
//				draw(xS - minX + maxGap, yS - minY + maxGap, xB - minX + maxGap, yB - minY + maxGap);					
				} catch (Exception e) {}
				draw(xS - minX, yS - minY, xB - minX, yB - minY);
			}
		}
		print();
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

	private void draw(int xS, int yS, int xB, int yB) {
		int gap = Math.abs(xS - xB) + Math.abs(yS - yB);
		for (int i = 0; i < gap; i++) {
			for (int j = 1; j <= gap - i; j++) {
				secureDraw(xS + i, yS + j);
				secureDraw(xS - i, yS + j);
				secureDraw(xS + j, yS + i);
				secureDraw(xS + j, yS - i);
				secureDraw(xS - i, yS - j);
				secureDraw(xS - j, yS - i);
			}
		}
	}

	private void secureDraw(int x, int y) {
		try {
			if (matrix[x][y] == '.') {
				matrix[x][y] = '#';
			}
		} catch (Exception e) {}
	}

}
