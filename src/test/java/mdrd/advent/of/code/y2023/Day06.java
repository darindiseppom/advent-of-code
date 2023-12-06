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

public class Day06 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day06.txt";

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(440000, getNumberWays(getInput(PATH_TO_FILE)));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(26187338, getNumberWays(getInput2(PATH_TO_FILE)));
	}

	private Set<Race> getInput(String pathToFile) throws FileNotFoundException {
		Set<Race> races = new HashSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
        Pattern pattern = Pattern.compile("(\\d+)");
		Matcher timesMatcher = pattern.matcher(scanner.next());
		Matcher distancesMatcher = pattern.matcher(scanner.next());
        while (timesMatcher.find() && distancesMatcher.find()) {
        	races.add(new Race(Long.valueOf(timesMatcher.group(1)),
        			Long.valueOf(distancesMatcher.group(1))));
        }
		scanner.close();
		return races;
	}
	
	private Set<Race> getInput2(String pathToFile) throws FileNotFoundException {
		Set<Race> races = new HashSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		Race s = new Race(Long.valueOf(scanner.next().substring(11).replaceAll("\\s", "")), 
				Long.valueOf(scanner.next().substring(11).replaceAll("\\s", "")));
		races.add(s);
		scanner.close();
		return races;
	}	

	private Long getNumberWays(Set<Race> races) {
		Long result = 1L;
		for (Race race : races) {
			result *= getManyWins(race);
		}
		return result;
	}

	private Long getManyWins(Race race) {
		Long counter = 0L;
		for (Long i = 0L; i < race.time; i++) {
			if (canWin(race.time, i+1, race.distance)) {
				counter++;
			}
		}
		return counter;
	}

	private boolean canWin(Long totalTime, Long elapsedTime, Long distanceToBeat) {
		return (totalTime - elapsedTime) * elapsedTime > distanceToBeat;
	}

	class Race {
		Long time;
		Long distance;
		Race(Long time, Long distance) {
			this.time = time;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Race [t=" + time + ", d=" + distance + "]";
		}
		
	}
}
