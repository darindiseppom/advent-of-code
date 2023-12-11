package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day10 {

	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day10.txt";
	private static final String[][] MATRIX = new String[140][140];
	private static int COUNTER = 0;

	@Test
	public void partOne() throws FileNotFoundException {
		getInput(PATH_TO_FILE);
		getStepsFarthest();
		assertEquals(6951, COUNTER / 2);
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		getInput(PATH_TO_FILE);
		getStepsFarthest();
		assertEquals(563, getNumberWays());
	}

	private int getNumberWays() {
		int counter = 0;
		String s;
		boolean mode = false;
		for (int x = 0; x < MATRIX.length; x++) {
			for (int y = 0; y < MATRIX.length; y++) {
				s = MATRIX[x][y];
				if ("!".equals(s)) {
					mode = !mode;
					continue;
				} 
				if (mode && !"?".equals(s)) {
					counter++;
				}
			}
		}
		return counter;
	}

	private void getStepsFarthest() {
		Direction dir = null;
		MutablePair<Integer, Integer> startingPosition = getStartingPosition("S");
		if (Arrays.asList("F", "|", "7").contains(get(startingPosition, Direction.UP))) {
			dir = Direction.UP;
		} else if (Arrays.asList("-", "J", "7").contains(get(startingPosition, Direction.RIGHT))) {
			dir = Direction.RIGHT;
		} else if (Arrays.asList("J", "|", "L").contains(get(startingPosition, Direction.DOWN))) {
			dir = Direction.DOWN;
		} else {
			dir = Direction.LEFT;
		}
		dir = go(startingPosition, dir);
		while (dir != null) {
			dir = doStep(startingPosition, dir);
		}
	}

	private String get(Pair<Integer, Integer> pos, Direction direction) {
		int x = pos.getLeft();
		int y = pos.getRight();
		switch (direction) {
		case UP:
			x--;
			break;
		case RIGHT:
			y++;
			break;
		case DOWN:
			x++;
			break;
		case LEFT:
			y--;
			break;
		}
		try {
			return MATRIX[x][y];
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return null;
	}

	private Direction doStep(MutablePair<Integer, Integer> pos, Direction from) {
		Direction newDirection = null;
		String pipe = MATRIX[pos.getLeft()][pos.getRight()];
		COUNTER++;
		if (pipe.equals("S")) {
			System.out.println("STOP");
		} else {
			switch (from) {
			case LEFT: {
				if (pipe.equals("J")) {
					return go(pos, Direction.UP);
				} else if (pipe.equals("-")) {
					return go(pos, Direction.RIGHT);
				} else if (pipe.equals("7")) {
					return go(pos, Direction.DOWN);
				} else {
					System.out.println("Sbagliato strada");
				}
				break;
			}
			case UP: {
				if (pipe.equals("J")) {
					return go(pos, Direction.LEFT);
				} else if (pipe.equals("L")) {
					return go(pos, Direction.RIGHT);
				} else if (pipe.equals("|")) {
					return go(pos, Direction.DOWN);
				} else {
					System.out.println("Sbagliato strada");
				}
				break;
			}
			case RIGHT: {
				if (pipe.equals("-")) {
					return go(pos, Direction.LEFT);
				} else if (pipe.equals("L")) {
					return go(pos, Direction.UP);
				} else if (pipe.equals("F")) {
					return go(pos, Direction.DOWN);
				} else {
					System.out.println("Sbagliato strada");
				}
				break;
			}
			case DOWN: {
				if (pipe.equals("7")) {
					return go(pos, Direction.LEFT);
				} else if (pipe.equals("F")) {
					return go(pos, Direction.RIGHT);
				} else if (pipe.equals("|")) {
					return go(pos, Direction.UP);
				} else {
					System.out.println("Sbagliato strada");
				}
				break;
			}
			}
		}
		return newDirection;
	}

	private Direction go(MutablePair<Integer, Integer> pos, Direction direction) {
		MATRIX[pos.getLeft()][pos.getRight()] = convert(MATRIX[pos.getLeft()][pos.getRight()]);
		switch (direction) {
			case UP: pos.setLeft(pos.getLeft() - 1); return Direction.DOWN;
			case RIGHT: pos.setRight(pos.getRight() + 1); return Direction.LEFT;
			case DOWN: pos.setLeft(pos.getLeft() + 1); return Direction.UP;
			case LEFT: pos.setRight(pos.getRight() - 1); return Direction.RIGHT;
		}
		return null;
	}
	private String convert(String string) {
		switch (string) {
			case "S":
			case "7":
			case "F":
			case "|": return "!";
			case "J":
			case "L":
			case "-": return "?";
		}
		return string;
	}

	private MutablePair<Integer, Integer> getStartingPosition(String starting) {
		for (int x = 0; x < MATRIX.length; x++) {
			for (int y = 0; y < MATRIX.length; y++) {
				if (starting.equals(MATRIX[x][y])) {
					return new MutablePair<>(x, y);
				}
			}
		}
		return null;
	}

	private void getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int x = -1;
		while (scanner.hasNext()) {
			x++;
			String[] row = scanner.next().split("");
			for (int i = 0; i < row.length; i++) {
				MATRIX[x][i] = row[i];
			}
		}
		scanner.close();
	}

	enum Direction {
		UP, RIGHT, DOWN, LEFT
	}
}
