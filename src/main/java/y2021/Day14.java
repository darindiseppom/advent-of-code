package y2021;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import commons.Utils;

public class Day14 {

	private static final String PATTERN_MATCHER = "(..) -> (.)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
//	private static final int X = 100;
//	private static final int Y = 100;
//	private static final int MAX = 100;

	private static Map<String, Integer> template;
	private static Map<String, Long> counter = new HashMap<>();
	private static List<Pair<String, String>> pairs;
//	private static Map<String, Long> map;

	@Test
	public void assertPartOne() throws IOException {
		getInput("2021/day14.txt");
//		assertEquals(1588, doPartOne(10));
//		assertEquals(3906, doPartOne(10));
//		getInput("2021/day14.txt");
		assertEquals(2188189693529L, doPartOne(40));
	}

	private long doPartOne(int times) {
//		getMap();
		for (int i = 0; i < times; i++) {
			System.out.println(i);
			iterate();
		}
		Entry<String, Long> min = sortByComparator(counter, true).entrySet().stream().findFirst().orElse(null);
		Entry<String, Long> max = sortByComparator(counter, false).entrySet().stream().findFirst().orElse(null);
		return max.getValue() - min.getValue();
	}

	private static Map<String, Long> sortByComparator(Map<String, Long> unsortMap, boolean ascending) {

		List<Entry<String, Long>> list = new LinkedList<Entry<String, Long>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Entry<String, Long>>() {
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				if (ascending) {
					return o1.getValue().compareTo(o2.getValue());					
				} else {
					return o2.getValue().compareTo(o1.getValue());	
				}
			}
		});

		Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		for (Entry<String, Long> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}


	private void iterate() {
		List<Pair<String, String>> toInsert = new ArrayList<>();
		for (Pair<String, String> pair : pairs) {
			if (template.containsKey(pair.getKey())) {
				toInsert.add(pair);
			}
		}
		Integer times;
		Map<String, Integer> newMap = new HashMap<>(template);
		for (Pair<String, String> pair : toInsert) {
			times = template.get(pair.getKey());
//			newMap.remove(pair.getKey());
			newMap.put(pair.getKey(), newMap.get(pair.getKey()) - times);
			String s1 = pair.getKey().substring(0, 1) + pair.getValue();
			newMap.put(s1, newMap.getOrDefault(s1, 0) + times);
			s1 = pair.getValue() + pair.getKey().substring(1);
			newMap.put(s1, newMap.getOrDefault(s1, 0) + times);
			increaseCounter(pair.getValue(), times);
		}
		template = newMap;
	}

	private void getInput(String pathToFile) throws FileNotFoundException {
		Matcher matcher;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String next = scanner.next();
		template = toMap(next);
		scanner.next();
		pairs = new ArrayList<>();
		while (scanner.hasNext()) {
			next = scanner.next();
			matcher = PATTERN.matcher(next);
			while (matcher.find()) {
				pairs.add(new ImmutablePair<>(matcher.group(1), matcher.group(2)));
			}
		}
		scanner.close();
	}

	private Map<String, Integer> toMap(String next) {
		Map<String, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < next.length()-1; i++) {
			increaseCounter(next.substring(i, i + 1), 1);
			String substring = next.substring(i, i+2);
			hashMap.put(substring, hashMap.getOrDefault(substring, 1));
		}
		increaseCounter(next.substring(next.length() - 1), 1);
		return hashMap;
	}

	private void increaseCounter(String seq, Integer times) {
		for (Integer i = 0; i < times; i++) {
			counter.put(seq, counter.getOrDefault(seq, 0L) + 1);			
		}
	}

}
