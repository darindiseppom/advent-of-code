package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import org.junit.Test;

import commons.Utils;

public class Day10 {

	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void assertPartOne() throws IOException {
		assertEquals(265527, getTotalSyntaxErrorScore("2021/day10.txt", true));
	}

	@Test
	public void assertPartTwo() throws IOException {
		assertEquals(3969823589L, getTotalSyntaxErrorScore("2021/day10.txt", false));
	}

	private long getTotalSyntaxErrorScore(String pathToFile, boolean partOne) throws FileNotFoundException {
		long counter = 0;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		Stack<Character> closingChunk;
		List<Long> totalScores = new ArrayList<>();
		boolean errore;
		while (scanner.hasNext()) {
			closingChunk = new Stack<>();
			String next = scanner.next();
			errore = false;
			for (int i = 0; i < next.length(); i++) {
				char charAt = next.charAt(i);
				switch (charAt) {
				case '(':
					closingChunk.add(')');
					break;
				case '[':
					closingChunk.add(']');
					break;
				case '{':
					closingChunk.add('}');
					break;
				case '<':
					closingChunk.add('>');
					break;
				default:
					char lastClosingChunk = closingChunk.pop();
					if (charAt != lastClosingChunk) {
						counter += getSyntaxErrorScore(charAt);
						errore = true;
					}
				}
				if (errore) {
					break;
				}
				if (i + 1 == next.length()) {
					Collections.reverse(closingChunk);
					totalScores.add(getCompletionScore(closingChunk));
				}
			}
		}
		scanner.close();
		if (partOne) {
			return counter;
		} else {
			Collections.sort(totalScores);
			return totalScores.get((totalScores.size() - 1) / 2);
		}
	}

	private long getCompletionScore(Stack<Character> closingChunk) {
		long result = 0;
		for (Character character : closingChunk) {
			switch (character) {
			case ')':
				result *= 5;
				result += 1;
				break;
			case ']':
				result *= 5;
				result += 2;
				break;
			case '}':
				result *= 5;
				result += 3;
				break;
			case '>':
				result *= 5;
				result += 4;
				break;
			}
		}
		return result;
	}

	private int getSyntaxErrorScore(char charAt) {
		switch (charAt) {
		case ')':
			return 3;
		case ']':
			return 57;
		case '}':
			return 1197;
		case '>':
			return 25137;
		}
		return -1;
	}

}
