package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day03 {

	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(7878, count(getInput("2022/day03.txt")));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(2760, count(getInput2("2022/day03.txt")));
	}

	private Object count(List<String> list) {
		int counter = 0;
		for (String el : list) {
			counter += getPriority(el);
		}
		return counter;
	}

	private int getPriority(String el) {
		int n = el.charAt(0);
		if (n < 96) {
			n -= 38;
		} else {
			n -= 96;
		}
		return n;
	}

	private List<String> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<String> inputList = new ArrayList<String>();
		while (scanner.hasNext()) {
			String snacks = scanner.next();
			String[] split = snacks.split("");
			Set<String> rucksack1 = new HashSet<String>();
			Set<String> rucksack2 = new HashSet<String>();
			for (int i = 0; i < split.length / 2; i++) {
				rucksack1.add(split[i]);
				rucksack2.add(split[split.length / 2 + i]);
			}
			rucksack1.retainAll(rucksack2);
			inputList.add((String) rucksack1.toArray()[0]);
		}
		scanner.close();
		return inputList;
	}
	
	private List<String> getInput2(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<String> inputList = new ArrayList<String>();
		while (scanner.hasNext()) {
			String el1 = scanner.next();
			String el2 = scanner.next();
			String el3 = scanner.next();
			Set<String> rucksack1 = new HashSet<String>(Arrays.asList(el1.split("")));
			Set<String> rucksack2 = new HashSet<String>(Arrays.asList(el2.split("")));
			Set<String> rucksack3 = new HashSet<String>(Arrays.asList(el3.split("")));
			rucksack1.retainAll(rucksack2);
			rucksack1.retainAll(rucksack3);
			inputList.add((String) rucksack1.toArray()[0]);
		}
		scanner.close();
		return inputList;
	}

}
