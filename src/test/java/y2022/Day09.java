package y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import commons.Utils;

public class Day09 {

	private static final String PATH_TO_FILE = "2022/day09.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		Grid grid = getInput(PATH_TO_FILE, 2);
		assertEquals(5683, countPositionsVisistedByTailLeastOnce(grid));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		Grid grid = getInput(PATH_TO_FILE, 10);
		assertEquals(2372, countPositionsVisistedByTailLeastOnce(grid));
	}

	private Integer countPositionsVisistedByTailLeastOnce(Grid grid) {
		int counter = 0;
		for (int x = 0; x < grid.matrix.length; x++) {
			for (int y = 0; y < grid.matrix.length; y++) {
				counter += grid.matrix[x][y] == 1 ? 1 : 0;
			}
		}
		return counter;
	}

	private Grid getInput(String pathToFile, int nKnots) throws FileNotFoundException {
		Grid grid = new Grid(nKnots);
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String[] line;
		while (scanner.hasNext()) {
			line = scanner.next().split(" ");
			grid.moveHead(line[0], Integer.valueOf(line[1]));
		}
		scanner.close();
		return grid;
	}

	class Grid {
		private static final int GRID_SIZE = 500;
		private static final int START = GRID_SIZE / 2;
		int[][] matrix;
		List<MutablePair<Integer, Integer>> knotsList = new ArrayList<MutablePair<Integer, Integer>>();
		ImmutablePair<Integer, Integer> start;

		public Grid(int knots) {
			matrix = new int[GRID_SIZE][GRID_SIZE];
			start = new ImmutablePair<Integer, Integer>(START, START);
			for (int i = 0; i < knots; i++) {
				knotsList.add(new MutablePair<Integer, Integer>(START, START));
			}
			matrix[start.getLeft()][start.getRight()] = 1;
		}

		public void moveHead(String direction, Integer steps) {
			for (int i = 0; i < steps; i++) {
				moveHead(direction);
			}
		}

		private void moveHead(String direction) {
			MutablePair<Integer, Integer> head = knotsList.get(0);
			switch (direction) {
			case "L":
				head.setLeft(head.getLeft() - 1);
				break;
			case "U":
				head.setRight(head.getRight() - 1);
				break;
			case "R":
				head.setLeft(head.getLeft() + 1);
				break;
			case "D":
				head.setRight(head.getRight() + 1);
				break;
			}
			for (int i = 1; i < knotsList.size(); i++) {
				if (!areAdjacent(knotsList.get(i - 1), knotsList.get(i))) {
					moveKnot(i);
				} else {
					break;
				}
			}
		}

		private void moveKnot(int knotIndex) {
			MutablePair<Integer, Integer> last = knotsList.get(knotIndex);
			MutablePair<Integer, Integer> first = knotsList.get(knotIndex - 1);
			int dX = first.getLeft() - last.getLeft();
			int dY = first.getRight() - last.getRight();
			dX = dX > 0 ? 1 : dX < 0 ? -1 : 0;
			dY = dY > 0 ? 1 : dY < 0 ? -1 : 0;
			last.setLeft(last.getLeft() + dX);
			last.setRight(last.getRight() + dY);
			if (knotIndex == knotsList.size() - 1) {
				matrix[last.getLeft()][last.getRight()] = 1;
			}
		}

		private boolean areAdjacent(Pair<Integer, Integer> first, Pair<Integer, Integer> last) {
			if (Math.abs(last.getLeft() - first.getLeft()) > 1) {
				return false;
			}
			if (Math.abs(last.getRight() - first.getRight()) > 1) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < matrix.length; y++) {
				for (int x = 0; x < matrix.length; x++) {
					int index = knotsList.indexOf(new MutablePair<Integer, Integer>(x, y));
					if (index != -1) {
						sb.append(index);
					} else if (x == start.getLeft() && y == start.getRight()) {
						sb.append("s");
					} else if (matrix[x][y] == 1) {
						sb.append("#");
					} else {
						sb.append(".");
					}
				}
				sb.append("\n");
			}
			return sb.toString();
		}

	}

}
