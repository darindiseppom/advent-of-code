package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day05 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day05.txt";

	@Test
	public void partOne() throws Exception {
		assertEquals(486613012, getInput(PATH_TO_FILE));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(56931769, getInput2(PATH_TO_FILE));
	}

	private Long getInput(String pathToFile) throws FileNotFoundException {
		Set<Long> seeds = new HashSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String[] split = scanner.next().substring(7).split(" ");
		for (int i = 0; i < split.length; i++) {
			seeds.add(Long.valueOf(split[i]));
		}
		Map sTS = getMap(pathToFile, "seed-to-soil");
		Map sTF = getMap(pathToFile, "soil-to-fertilizer");
		Map fTW = getMap(pathToFile, "fertilizer-to-water");
		Map wTL = getMap(pathToFile, "water-to-light");
		Map lTT = getMap(pathToFile, "light-to-temperature");
		Map tTH = getMap(pathToFile, "temperature-to-humidity");
		Map hTL = getMap(pathToFile, "humidity-to-location");
		scanner.close();
		
		SortedSet<Long> res = new TreeSet<>();
		for (Long seed : seeds) {
			res.add(hTL.map(tTH.map(lTT.map(wTL.map(fTW.map(sTF.map(sTS.map(seed))))))));
		}
		return res.first();
	}
	private Long getInput2(String pathToFile) throws FileNotFoundException {
		Set<Pair<Long, Long>> seeds = new HashSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String[] split = scanner.next().substring(7).split(" ");
		for (int i = 0; i < split.length; i += 2) {
			seeds.add(new ImmutablePair<Long, Long>(Long.valueOf(split[i]), Long.valueOf(split[i])-1+Long.valueOf(split[i+1])));
		}
		Map sTS = getMap(pathToFile, "seed-to-soil");
		Map sTF = getMap(pathToFile, "soil-to-fertilizer");
		Map fTW = getMap(pathToFile, "fertilizer-to-water");
		Map wTL = getMap(pathToFile, "water-to-light");
		Map lTT = getMap(pathToFile, "light-to-temperature");
		Map tTH = getMap(pathToFile, "temperature-to-humidity");
		Map hTL = getMap(pathToFile, "humidity-to-location");
		scanner.close();
		
		Long index = 0L;
		while (true) {
			Long seed = sTS.reverseMap(sTF.reverseMap(fTW.reverseMap(wTL.reverseMap(lTT.reverseMap(tTH.reverseMap(hTL.reverseMap(index)))))));
			if (seedExists(seed, seeds)) {
				return index;
			}
			index++;
		}
	}

	private boolean seedExists(Long seed, Set<Pair<Long, Long>> ranges) {
		for (Pair<Long, Long> range : ranges) {
			if (seed >= range.getLeft() && seed <= range.getRight()) {
				return true;
			}
		}
		return false;
	}

	private Map getMap(String pathToFile, String name) throws FileNotFoundException {
		Map map = new Map();
		map.name = name;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String row = scanner.next();
		while (!row.startsWith(name)) {
			row = scanner.next();
		}
		row = scanner.next();
		while (row.length() != 0) {
			String[] split = row.split(" ");
			map.ranges.add(new ImmutableTriple<>(Long.valueOf(split[0]), Long.valueOf(split[1]), Long.valueOf(split[2])));
			row = scanner.hasNext() ? scanner.next() : "";
		}
		scanner.close();
		return map;
	}
	
	class Map {
		String name;
		SortedSet<Triple<Long, Long, Long>> ranges = new TreeSet<>();
		
		public Long map(Long source) {
			for (Triple<Long, Long, Long> range : ranges) {
				if (source >= range.getMiddle() && source < range.getMiddle() + range.getRight()) {
					return source - range.getMiddle() + range.getLeft();
				}
			}
			return source;
		}

		public Long reverseMap(Long destination) {
			for (Triple<Long, Long, Long> range : ranges) {
				if (destination >= range.getLeft() && destination < range.getLeft() + range.getRight()) {
					return destination - range.getLeft() + range.getMiddle();
				}
			}
			return destination;			
		}
	}
	

}
