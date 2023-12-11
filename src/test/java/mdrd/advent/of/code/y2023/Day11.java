package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day11 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day11.txt";
	private static String[][] MATRIX = new String[140][140];

	@BeforeEach
	public void init() throws FileNotFoundException {
		getInput(PATH_TO_FILE);			
	}
	
	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(10289334, getSum(1));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(649862989626L, getSum(999999));
	}

	private Long getSum(int weight) {
		Long counter = 0L;
		LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
		for (int x = 0; x < MATRIX.length; x++) {
			for (int y = 0; y < MATRIX[0].length; y++) {
				if ("#".equals(MATRIX[x][y])) {
					list.add(new ImmutablePair<>(x, y));
				}
			}
		}
		while (!list.isEmpty()) {
			counter += getLengthShortestPath(list.poll(), list, weight);
		}
		return counter;
	}

	private Long getLengthShortestPath(Pair<Integer, Integer> gal1, List<Pair<Integer, Integer>> list, int weight) {
		Long counter = 0L;
		for (Pair<Integer, Integer> gal2 : list) {
			counter += getDistance(gal1, gal2, weight);
		}
		return counter;
	}

	private Long getDistance(Pair<Integer, Integer> gal1, Pair<Integer, Integer> gal2, int weight) {
		Long counter = 0L;
		int minX = Math.min(gal1.getLeft(), gal2.getLeft());
		int maxX = Math.max(gal1.getLeft(), gal2.getLeft());
		for (int i = minX + 1; i <= maxX; i++) {
			counter += rowHasNoGalaxies(i) ? weight + 1 : 1;
		}
		int minY = Math.min(gal1.getRight(), gal2.getRight());
		int maxY = Math.max(gal1.getRight(), gal2.getRight());
		for (int i = minY + 1; i <= maxY; i++) {
			counter += columnHasNoGalaxies(i) ? weight + 1 : 1;
		}
		return counter;
	}

	private void getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int x = -1;
		while (scanner.hasNext()) {
			x++;
			String[] split = scanner.next().split("");
			for (int i = 0; i < split.length; i++) {
				MATRIX[x][i] = split[i];
			}
		}
		scanner.close();
	}

	private boolean columnHasNoGalaxies(int i) {
		for (int z = 0; z < MATRIX.length; z++) {
			if ("#".equals(MATRIX[z][i])) {
				return false;
			}
		}
		return true;
	}

	private boolean rowHasNoGalaxies(int i) {
		for (int z = 0; z < MATRIX[0].length; z++) {
			if ("#".equals(MATRIX[i][z])) {
				return false;
			}
		}
		return true;
	}
	
}
