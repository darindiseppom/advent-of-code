package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import commons.Utils;

public class Day6 {

	private static final String SCANNER_DELIMITER_PATTERN = ",";

	@Test
	public void assertPartOne() throws IOException {
		long[] input = getInput("2021/day6.txt");
		assertEquals(390011, func(input, 80));
		assertEquals(1746710169834L, func(input, 256));
	}

	private long[] getInput(String pathToFile) throws FileNotFoundException {
		long[] input = new long[9];
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			input[Integer.valueOf(scanner.next())]++;
		}
		scanner.close();
		return input;
	}

	private long func(long[] map, int days) {
		long count = 0;
		for (int key = 0; key < map.length; key++) {
			count += (func(key, days) * map[key]);
		}
		return count;
	}

	private long func(int fish, int days) {
		if (days <= fish) {
			return 1;
		}
		return func(6, days - fish - 1) + func(8, days - fish - 1);
	}

}
