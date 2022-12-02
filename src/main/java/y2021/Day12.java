package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import commons.Utils;

public class Day12 {

	private static final String PATTERN_MATCHER = "(.*)-(.*)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
//	private static final int X = 10;
//	private static final int Y = 10;

	@Test
	public void assertPartOne() throws IOException {
		Map<Cave, List<Cave>> input = getInput("2021/day12.txt");
		Utils.print(input, "\t");
		System.out.println();
//		Cave cave = input.get(new Cave("c")).get(0);
//		cave.alreadyVisited = true;
//		Utils.print(input, "\t");
		Map<Cave, List<Cave>> hashMap = clone(input);
		
		searchCave(hashMap, new Cave("A")).alreadyVisited = true;
		Utils.print(input, "\t");
		System.out.println();
		Utils.print(hashMap, "\t");
		
		assertEquals(10, getPaths(input));
	}

	private Map<Cave, List<Cave>> clone(Map<Cave, List<Cave>> map) {
		Map<Cave, List<Cave>> result = new HashMap<Cave, List<Cave>>();
		for (Entry<Cave, List<Cave>> entry : map.entrySet()) {
			entry.getKey().alreadyVisited = false;
			List<Cave> value = new ArrayList<>();
			for (Cave cave : entry.getValue()) {
				value.add(new Cave(cave.name, cave.alreadyVisited));
			}
			result.put(new Cave(entry.getKey().name, entry.getKey().alreadyVisited), value);
		}
		return result;
	}

	private int getPaths(Map<Cave, List<Cave>> input) {
		int result = 0;
		List<Cave> list = input.get(new Cave("start"));
		for (Cave cave : list) {
			System.out.print("start,");
			result += getPaths(input, cave);
//			resetVisitedCaves(input);
			System.out.println();
		}
		return result;
	}


	private int getPaths(Map<Cave, List<Cave>> map, Cave c) {
		Map<Cave, List<Cave>> input = clone(map);
		Cave cave = searchCave(input, c);
		int result = 0;
		System.out.print(cave.name+":"+cave.alreadyVisited+",");
		if (cave.isEnd()) {
			System.out.print("1");
			return 1;
		}
		if (cave.alreadyVisited && cave.isSmall()) {
			System.out.print("già visitata 0");
			return 0;			
		}
		if (cave.isStart()) {
			System.out.print("0");
			return 0;			
		}
		cave.alreadyVisited = true;
		List<Cave> caves = input.get(cave);
		for (Cave el : caves) {
//			System.out.print(el.name+"-\t");
			result += getPaths(input, el);
			System.out.print("\n\t"+cave.name+":"+cave.alreadyVisited+",");
		}
		return result;
	}

//	@Test
//	public void assertPartTwo() throws IOException {
//		int[][] input = getInput("2021/day11.txt");
//		assertEquals(422, doPartTwo(input));
//	}

	private Map<Cave, List<Cave>> getInput(String pathToFile) throws FileNotFoundException {
		Map<Cave, List<Cave>> input = new TreeMap<>();
		Matcher matcher;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			matcher = PATTERN.matcher(scanner.next());
			matcher.find();
			Cave key = new Cave(matcher.group(1));
			Cave value = new Cave(matcher.group(2));
			putCave(input, key, value);
			putCave(input, value, key);
		}
		scanner.close();
		return input;
	}

	private void putCave(Map<Cave, List<Cave>> input, Cave key, Cave value) {
		Cave cave = searchCave(input, value);
		if (input.containsKey(key)) {
			List<Cave> list = new ArrayList<>(input.get(key));
			list.add(cave);
			input.put(key, list);
		} else {
			input.put(key, Arrays.asList(cave));
		}
	}

	private Cave searchCave(Map<Cave, List<Cave>> input, Cave value) {
		for (Entry<Cave, List<Cave>> entry : input.entrySet()) {
			if (entry.getKey().name.equals(value.name)) {
				return entry.getKey();
			}
			for (Cave cave : entry.getValue()) {
				if (cave.name.equals(value.name)) {
					return cave;
				}
			}
		}
		return value;
	}

//	private void resetVisitedCaves(Map<Cave, List<Cave>> input) {
//		for (Entry<Cave, List<Cave>> entry : input.entrySet()) {
//			entry.getKey().alreadyVisited = false;
//			for (Cave cave : entry.getValue()) {
//				cave.alreadyVisited = false;
//			}
//		}		
//	}

	class Cave implements Comparable<Cave> {
		String name;
		boolean alreadyVisited;

		public Cave(String name) {
			this.name = name;
		}
		public Cave(String name, boolean alreadyVisited) {
			this.name = name;
			this.alreadyVisited = alreadyVisited;
		}

		public boolean isSmall() {
			return (this.name.charAt(0) > 96 && this.name.charAt(0) < 123) ? true : false;
		}

		public boolean isBig() {
			return !this.isSmall();
		}

		public boolean isStart() {
			return this.name.equalsIgnoreCase("start");
		}

		public boolean isEnd() {
			return this.name.equalsIgnoreCase("end");
		}

		@Override
		public String toString() {
			return name + ":" + (alreadyVisited ? "1" : "0");
		}

		@Override
		public int compareTo(Cave o) {
			return o.name.compareTo(this.name);
		}
	}

}
