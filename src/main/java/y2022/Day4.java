package y2022;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import com.google.common.collect.Range;

import commons.Utils;

public class Day4 {

	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(494, count("2022/day4.txt"));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(833, count2("2022/day4.txt"));
	}

	private int count(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int counter = 0;
		while (scanner.hasNext()) {
			String[] pair = scanner.next().split(",");
			String[] first = pair[0].split("-");
			String[] last = pair[1].split("-");
			Range<Integer> rFirst = Range.closed(Integer.valueOf(first[0]), Integer.valueOf(first[1]));
			Range<Integer> rLast = Range.closed(Integer.valueOf(last[0]), Integer.valueOf(last[1]));
			if (rFirst.encloses(rLast) || rLast.encloses(rFirst)) {
				counter++;
			}
		}
		scanner.close();
		return counter;
	}

	private int count2(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int counter = 0;
		while (scanner.hasNext()) {
			String[] pair = scanner.next().split(",");
			String[] first = pair[0].split("-");
			String[] last = pair[1].split("-");
			Range<Integer> rFirst = Range.closed(Integer.valueOf(first[0]), Integer.valueOf(first[1]));
			Range<Integer> rLast = Range.closed(Integer.valueOf(last[0]), Integer.valueOf(last[1]));
			if (rFirst.isConnected(rLast)) {
				counter++;
			}
		}
		scanner.close();
		return counter;
	}

}
