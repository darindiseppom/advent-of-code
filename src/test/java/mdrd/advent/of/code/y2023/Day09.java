package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day09 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day09.txt";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(1806615041, getSum(getInput(PATH_TO_FILE), true));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(1211, getSum(getInput(PATH_TO_FILE), false));
	}

	private Set<Integer[]> getInput(String pathToFile) throws FileNotFoundException {
		Set<Integer[]> rows = new HashSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			String[] str = scanner.next().split(" ");
			Integer[] in = new Integer[str.length];
			for (int i = 0; i < str.length; i++) {
				in[i] = Integer.valueOf(str[i]);
			}
			rows.add(in);			
		}
		scanner.close();
		return rows;
	}

	private int getSum(Set<Integer[]> rows, boolean partOne) {
		int counter = 0;
		for (Integer[] row : rows) {
			counter += getNext(row, partOne);
		}
		return counter;		
	}
	
	private int getNext(Integer[] row, boolean partOne) {
		if (allZeroes(row)) {
			return 0;
		} else {
			return partOne 
				? (row[row.length-1] + getNext(getDiff(row), partOne)) 
				: (row[0] - getNext(getDiff(row), partOne));
		}
	}

	private boolean allZeroes(Integer[] row) {
		for (int i = 0; i < row.length; i++) {
			if (row[i] != 0) {
				return false;
			}
		}
		return true;
	}

	private Integer[] getDiff(Integer[] row) {
		Integer[] result = new Integer[row.length-1];
		for (int i = 0; i < row.length - 1; i++) {
			result[i] = row[i+1] - row[i];
		}
		return result;
	}
}
