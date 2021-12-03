
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Day1 {
	
	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(1713, countIncresements(getInput("2021/inputs/day1.txt")));
	}
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(1734, count3SumsIncresements(getInput("2021/inputs/day1.txt")));
	}
	
	private List<Integer> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(pathToFile));
		List<Integer> inputList = new ArrayList<Integer>();
		while (scanner.hasNext()) {
			inputList.add(Integer.valueOf(scanner.next()));
		}
		scanner.close();
		return inputList;
	}

	private static int countIncresements(List<Integer> measurements) {
		int count = 0;
		for (int i = 1; i < measurements.size(); i++) {
			Integer first = measurements.get(i - 1);
			Integer second = measurements.get(i);
			if (second > first) {
				count++;
			}
		}
		return count;
	}

	private static int count3SumsIncresements(List<Integer> measurements) {
		int count = 0;
		for (int i = 1; i < measurements.size() - 2; i++) {
			Integer first = measurements.get(i - 1);
			Integer second = measurements.get(i);
			Integer third = measurements.get(i + 1);
			Integer fourth = measurements.get(i + 2);
			if ((second + third + fourth) > (first + second + third)) {
				count++;
			}
		}
		return count;
	}

}
