package mdrd.advent.of.code.y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day7 {

	private static final String SCANNER_DELIMITER_PATTERN = ",";

	@Test
	public void assertPartOne() throws IOException {
		List<Integer> input = getInput("2021/day7-1.txt");
		assertEquals(340056, doPartOne(input));
	}

	@Test
	public void assertPartTwo() throws IOException {
		List<Integer> input = getInput("2021/day7-2.txt");
		assertEquals(96592275, doPartTwo(input));
	}

	private int doPartOne(List<Integer> input) {
		int threshold = Integer.MAX_VALUE;
		int counter;
		for (Integer n1 : input) {
			counter = 0;
			for (Integer n2 : input) {
				counter += Math.abs(n1 - n2);
			}
			if (counter <= threshold) {
				threshold = counter;
			} else {
				return threshold;
			}
		}
		return threshold;
	}

	private int doPartTwo(List<Integer> input) {
		int threshold = Integer.MAX_VALUE;
		int counter;
		for (int i = input.get(0); i <= input.get(input.size() - 1); i++) {
			counter = 0;
			for (Integer n2 : input) {
				counter += func(i, n2, 1);
			}
			if (counter <= threshold) {
				threshold = counter;
			} else {
				return threshold;
			}
		}
		return threshold;
	}


	private int func(Integer n1, Integer n2, int i) {
		int compare = n1.compareTo(n2);
		if (compare == 0) {
			return 0;
		} else if (compare > 0) {
			return i + func(--n1, n2, ++i);
		} else {
			return i + func(n1, --n2, ++i);
		}
	}

	private List<Integer> getInput(String pathToFile) throws FileNotFoundException {
		List<Integer> input = new ArrayList<Integer>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			input.add(Integer.valueOf(scanner.next()));
		}
		scanner.close();
		Collections.sort(input);
		return input;
	}

}
