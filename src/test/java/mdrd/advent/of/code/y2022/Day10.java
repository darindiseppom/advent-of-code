package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;

import mdrd.advent.of.code.commons.Utils;

public class Day10 {

	private static final String PATH_TO_FILE = "2022/day10.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(14420,
				getSumSignalStrenghts(getRegister(PATH_TO_FILE), Arrays.asList(20, 60, 100, 140, 180, 220)));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		getCRT(getRegister(PATH_TO_FILE), 40);
	}

	private int getSumSignalStrenghts(int[] register, Collection<Integer> cycles) {
		int counter = 0;
		for (Integer cycle : cycles) {
			counter += register[cycle - 1] * cycle;
		}
		return counter;
	}

	private String getCRT(int[] register, int lineLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < register.length; i++) {
			if (i % lineLength == 0) {
				sb.append("\n");
			}
			Range<Integer> sprite = Range.closed(register[i] - 1, register[i] + 1);
			sb.append(sprite.contains(i % lineLength) ? "#" : " ");

		}
		return sb.toString();
	}

	private int[] getRegister(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int[] register = new int[241];
		register[0] = 1;
		int cycle = 1;
		while (scanner.hasNext()) {
			String line = scanner.next();
			if (line.startsWith("noop")) {
				register[cycle] = register[cycle - 1];
			} else {
				register[cycle] = register[cycle - 1];
				register[cycle + 1] += register[cycle++] + Integer.valueOf(line.split(" ")[1]);
			}
			cycle++;
		}
		return register;
	}

}
