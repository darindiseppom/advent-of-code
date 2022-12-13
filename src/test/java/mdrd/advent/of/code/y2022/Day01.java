package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day01 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	
	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(68442, mostCalories(getInput("2022/day01.txt")));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(204837, sumTop(getInput("2022/day01.txt"), 3));
	}

	private List<Integer> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<Integer> inputList = new ArrayList<Integer>();
		int counter = 0;
		while (scanner.hasNext()) {
			try {
				counter += Integer.valueOf(scanner.next());
			} catch (NumberFormatException e) {
				inputList.add(counter);			
				counter = 0;
				
			}
		}
		inputList.add(counter);			
		scanner.close();
		return inputList;
	}

	private static int mostCalories(List<Integer> list) {
		return Collections.max(list);
	}
	
	private static int sumTop(List<Integer> list, int top) {
		Collections.sort(list);
		int counter = 0;
		for (int i = 0; i < top; i++) {
			counter += list.get(list.size() - 1 - i);
			
		}
		return counter;
	}

}
