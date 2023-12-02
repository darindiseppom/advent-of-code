package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day01 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day01.txt";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(54159, getInput(PATH_TO_FILE, false));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(53866, getInput(PATH_TO_FILE, true));
	}

	private Integer getInput(String pathToFile, boolean isPartTwo) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int counter = 0;
		while (scanner.hasNext()) {
			if (isPartTwo) {
				counter = addToCounter(counter, convert(scanner.next()));
			} else {				
				counter = addToCounter(counter, scanner.next());
			}
		}
		scanner.close();
		return counter;
	}

	private int addToCounter(int counter, String row) {
		String[] split = row.split("");
		int num1 = 0;
		int num2 = 0;
		for (int i = 0; i < split.length; i++) {
			try {
				num1 = Integer.valueOf(split[i]);
				break;
			} catch (NumberFormatException e) {
			}
		}
		for (int i = split.length - 1; i > -1; i--) {
			try {
				num2 = Integer.valueOf(split[i]);
				break;
			} catch (NumberFormatException e) {
			}
		}
		counter += Integer.valueOf("" + num1 + num2);
		return counter;
	}

	private String convert(String source) {		
		source = source.replaceAll("one", "o1e");
		source = source.replaceAll("two", "t2o");
		source = source.replaceAll("three", "t3e");
		source = source.replaceAll("four", "4");
		source = source.replaceAll("five", "5e");
		source = source.replaceAll("six", "6");
		source = source.replaceAll("seven", "7n");
		source = source.replaceAll("eight", "e8t");
		return source.replaceAll("nine", "n9e");
	}	

}
