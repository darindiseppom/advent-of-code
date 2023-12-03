package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day02 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day02.txt";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(2377, getInput(PATH_TO_FILE));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(71220, getInput2(PATH_TO_FILE));
	}

	private Integer getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		Set<Integer> impossibleGameIds = new HashSet<>();
		Set<Integer> allGameIds = new HashSet<>();
		while (scanner.hasNext()) {
			String row = scanner.next();
	        Pattern pattern = Pattern.compile("Game (\\d+)");
			Matcher matcher = pattern.matcher(row);
	        matcher.find();
	        int gameId = Integer.valueOf(matcher.group(1));
	        allGameIds.add(gameId);
	        pattern = Pattern.compile("(\\d+)\\s+(\\w+)");
	        matcher = pattern.matcher(row);
	        while (matcher.find()) {
	        	if (isImpossibile(Integer.valueOf(matcher.group(1)), matcher.group(2))) {
	        		impossibleGameIds.add(gameId);
	        		break;
	        	}
	        }
		}
		scanner.close();
		allGameIds.removeAll(impossibleGameIds);		
		return allGameIds.stream().reduce(0, Integer::sum);
	}

	private Integer getInput2(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		int counter = 0;
		while (scanner.hasNext()) {
			String row = scanner.next();
	        Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)");
	        Matcher matcher = pattern.matcher(row);
	        counter += getPower(matcher);	        
		}
		scanner.close();
		return counter;
	}

	private int getPower(Matcher matcher) {
		int minRed = 0;
		int minGreen = 0;
		int minBlue = 0;
        while (matcher.find()) {
        	if ("red".equals(matcher.group(2)) && Integer.valueOf(matcher.group(1)) > minRed) {
    			minRed = Integer.valueOf(matcher.group(1));
    		}
        	if ("green".equals(matcher.group(2)) && Integer.valueOf(matcher.group(1)) > minGreen) {
    			minGreen = Integer.valueOf(matcher.group(1));
    		}
        	if ("blue".equals(matcher.group(2)) && Integer.valueOf(matcher.group(1)) > minBlue) {
    			minBlue = Integer.valueOf(matcher.group(1));
    		}
        }
		return minRed * minGreen * minBlue;
	}

	private boolean isImpossibile(int num, String colour) {
		if ("red".equals(colour) && num > 12) {
			return true;
		}
		if ("green".equals(colour) && num > 13) {
			return true;
		}
		if ("blue".equals(colour) && num > 14) {
			return true;
		}
		return false;
	}

}
