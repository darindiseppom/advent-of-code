package y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import commons.Utils;

public class Day6 {

	private static final String PATH_TO_FILE = "2022/day6.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(1542, getFirstMarker(PATH_TO_FILE, 4));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(3153, getFirstMarker(PATH_TO_FILE, 14));
	}

	private int getFirstMarker(String pathToFile, int markerSize) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String line = "";
		while (scanner.hasNext()) {
			line = scanner.next();
		}
		scanner.close();

		LinkedList<Character> queue = new LinkedList<Character>();
		for (int i = 0; i < markerSize - 1; i++) {			
			queue.add(line.charAt(i));
		}

		for (int i = markerSize - 1; i < line.length(); i++) {
			queue.add(line.charAt(i));
			if (isMarker(queue)) {
				return i + 1;
			} else {
				queue.removeFirst();
			}
		}
		return -1;
	}

	private boolean isMarker(LinkedList<Character> queue) {
		for (int i = 0; i < queue.size(); i++) {
			for (int j = i + 1; j < queue.size(); j++) {
				if (queue.get(i).equals(queue.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

}
