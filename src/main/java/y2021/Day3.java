package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.util.ObjectUtils;

import commons.Utils;

public class Day3 {

	@Test
	public void assertPartOne() throws IOException {		
	    assertEquals(1458194, doPartOne(getInput("2021/day3-1.txt")));
	}
	
	@Test
	public void assertPartTwo() throws IOException {		
	    assertEquals(2829354, doPartTwo(getInput("2021/day3-2.txt")));
	}

	private List<String> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		List<String> inputList = new ArrayList<String>();
		while (scanner.hasNext()) {
			inputList.add(scanner.next());
		}
		scanner.close();
		return inputList;
	}

	private int doPartOne(List<String> inputList) {
		int countZeros;
		int countOnes;
		String gammaRate = "";
		String epsilonRate = "";
		List<String> nextList;
		while(!ObjectUtils.isEmpty(inputList.get(0))) {
			countZeros = 0;
			countOnes = 0;
			nextList = new ArrayList<String>();
			for (String string : inputList) {
				if (string.charAt(0) == '0') {
					countZeros++;
				} else {
					countOnes++;
				}					
				nextList.add(string.substring(1));
			}
			inputList = nextList;
			gammaRate += countZeros > countOnes ? "0" : "1";
			epsilonRate += countZeros > countOnes ? "1" : "0";
		}
		int gamma = Integer.parseInt(gammaRate, 2);
		int epsilon = Integer.parseInt(epsilonRate, 2);
		return gamma * epsilon;
	}

	private int doPartTwo(List<String> inputList) {
		return getRate(new ArrayList<String>(inputList), true) * getRate(new ArrayList<String>(inputList), false);
	}
	
	private int getRate(List<String> inputList, boolean isOxygen) {
		int countZeros;
		int countOnes;
		List<String> onesList;
		List<String> zerosList;
		int length = inputList.get(0).length();
		for (int i = 0; i < length; i++) {
			if (inputList.size() == 1) {
				break;
			}
			countZeros = 0;
			countOnes = 0;
			zerosList = new ArrayList<String>();
			onesList = new ArrayList<String>();
			for (String string : inputList) {
				if (string.charAt(i) == '0') {
					countZeros++;
					zerosList.add(string);
				} else {
					countOnes++;
					onesList.add(string);
				}					
			}
			inputList = isOxygen
					? (countZeros > countOnes ? zerosList : onesList)
					: (countZeros > countOnes ? onesList : zerosList);
		}
		return Integer.parseInt(inputList.get(0), 2);
	}

}
