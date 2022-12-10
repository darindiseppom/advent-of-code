package y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import commons.Utils;

public class Day02 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	private static final Integer SCORE_LOST = 0;
	private static final Integer SCORE_DRAW = 3;
	private static final Integer SCORE_WIN = 6; 

	private static final Map<String, Shape> stringToShape = new HashMap<>();
	private static final Map<Shape, Integer> shapeToScore = new HashMap<>();
	private static final Map<String, Integer> stringToResult = new HashMap<>();
	
	static {
		stringToShape.put("A", Shape.ROCK);
		stringToShape.put("B", Shape.PAPER);
		stringToShape.put("C", Shape.SCISSORS);
		stringToShape.put("X", Shape.ROCK);
		stringToShape.put("Y", Shape.PAPER);
		stringToShape.put("Z", Shape.SCISSORS);
		shapeToScore.put(Shape.ROCK, 1);
		shapeToScore.put(Shape.PAPER, 2);
		shapeToScore.put(Shape.SCISSORS, 3);
		stringToResult.put("X", SCORE_LOST);
		stringToResult.put("Y", SCORE_DRAW);
		stringToResult.put("Z", SCORE_WIN);
	}
	
	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(12794, count(getInput("2022/day02.txt")));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(14979, count(getInput2("2022/day02.txt")));
	}
	
	private List<Pair<Shape, Shape>> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<Pair<Shape, Shape>> inputList = new ArrayList<Pair<Shape, Shape>>();
		while (scanner.hasNext()) {
			String[] split = scanner.next().split(" ");
			Shape first = stringToShape.get(split[0]);
			Shape second= stringToShape.get(split[1]);
			inputList.add(new ImmutablePair<Shape, Shape>(first, second));
		}		
		scanner.close();
		return inputList;
	}

	private List<Pair<Shape, Shape>> getInput2(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<Pair<Shape, Shape>> inputList = new ArrayList<Pair<Shape, Shape>>();
		while (scanner.hasNext()) {
			String[] split = scanner.next().split(" ");
			Shape first = stringToShape.get(split[0]);
			Integer result = stringToResult.get(split[1]);
			Shape second;
			if (result == SCORE_DRAW) {
				second = first;
			} else if (result == SCORE_WIN) {
				second = getWinnerShapeAgainst(first);
			} else {
				second = getLoserShapeAgainst(first);
			}
			inputList.add(new ImmutablePair<Shape, Shape>(first, second));
		}		
		scanner.close();
		return inputList;
	}

	private Shape getWinnerShapeAgainst(Shape shape) {
		if (shape.equals(Shape.ROCK)) {
			return Shape.PAPER;
		} else if (shape.equals(Shape.PAPER)) {
			return Shape.SCISSORS;
		} else {
			return Shape.ROCK;
		}
	}

	private Shape getLoserShapeAgainst(Shape shape) {
		if (shape.equals(Shape.ROCK)) {
			return Shape.SCISSORS;
		} else if (shape.equals(Shape.PAPER)) {
			return Shape.ROCK;
		} else {
			return Shape.PAPER;
		}
	}

	private static int count(List<Pair<Shape, Shape>> list) {
		int counter = 0;
		for (Pair<Shape, Shape> pair : list) {	
			counter += getTotalScore(pair);
		}
		return counter;
	}
	
	private static int getTotalScore(Pair<Shape, Shape> pair) {
		return shapeToScore.get(pair.getRight()) + getScore(pair);
	}
	
	private static int getScore(Pair<Shape, Shape> pair) {
		if (pair.getRight().equals(Shape.ROCK)) {
			if (pair.getLeft().equals(Shape.ROCK)) {
				return SCORE_DRAW;
			} else if (pair.getLeft().equals(Shape.PAPER)) {
				return SCORE_LOST;
			} else {
				return SCORE_WIN;
			}
		} else if (pair.getRight().equals(Shape.PAPER)) {
			if (pair.getLeft().equals(Shape.ROCK)) {
				return SCORE_WIN;
			} else if (pair.getLeft().equals(Shape.PAPER)) {
				return SCORE_DRAW;
			} else {
				return SCORE_LOST;
			}
		} else {
			if (pair.getLeft().equals(Shape.ROCK)) {
				return SCORE_LOST;
			} else if (pair.getLeft().equals(Shape.PAPER)) {
				return SCORE_WIN;
			} else {
				return SCORE_DRAW;
			}
		}
	}

	enum Shape {
		ROCK,
		PAPER,
		SCISSORS
	}

}
