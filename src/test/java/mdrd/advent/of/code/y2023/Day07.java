package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day07 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day07.txt";
	private static final Map<Boolean, Map<String, Integer>> DICTIONARY = new HashMap<>();

	static {
		Map<String, Integer> partOne = new HashMap<>();
		Map<String, Integer> partTwo = new HashMap<>();
		DICTIONARY.put(false, partOne);
		DICTIONARY.put(true, partTwo);
		partOne.put("A", 14);
		partOne.put("K", 13);
		partOne.put("Q", 12);
		partOne.put("J", 11);
		partOne.put("T", 10);
		partOne.put("9", 9);
		partOne.put("8", 8);
		partOne.put("7", 7);
		partOne.put("6", 6);
		partOne.put("5", 5);
		partOne.put("4", 4);
		partOne.put("3", 3);
		partOne.put("2", 2);
		partTwo.put("A", 14);
		partTwo.put("K", 13);
		partTwo.put("Q", 12);
		partTwo.put("J", 1);
		partTwo.put("T", 10);
		partTwo.put("9", 9);
		partTwo.put("8", 8);
		partTwo.put("7", 7);
		partTwo.put("6", 6);
		partTwo.put("5", 5);
		partTwo.put("4", 4);
		partTwo.put("3", 3);
		partTwo.put("2", 2);
	}
	
	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(253313241, getSum(getInput(PATH_TO_FILE, false)));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(253362743, getSum(getInput(PATH_TO_FILE, true)));
	}

	private Set<Hand> getInput(String pathToFile, boolean jokerRule) throws FileNotFoundException {
		SortedSet<Hand> hands = new TreeSet<>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while(scanner.hasNext()) {
			String row = scanner.next();
			Hand hand = new Hand();
			hand.cards = row.substring(0, 5);
			hand.bid = Integer.valueOf(row.substring(6));
			hand.jokerRule = jokerRule;
			hands.add(hand);
		}
		scanner.close();
		return hands;
	}

	private Integer getSum(Set<Hand> hands) {
		int counter = 0;
		int i = hands.size();
		for (Hand hand : hands) {
			counter += i * hand.bid;
			i--;
		}
		return counter;
	}

	class Hand implements Comparable<Hand> {
		String cards;
		int bid;
		boolean jokerRule;
		
		public int getPower() {
			if (cards == null) return -1;
			Map<String, Integer> map = new HashMap<>();
			String[] split = cards.split("");
			for (String string : split) {
				if (map.containsKey(string)) {
					map.put(string, map.get(string)+1);
				} else {
					map.put(string, 1);
				}
			}
			if (jokerRule && map.keySet().contains("J") && map.keySet().size() > 1) {
				Map<String, Integer> newMap = new HashMap<>(map);
				newMap.remove("J");
				Entry<String, Integer> entry = newMap.entrySet().stream()
						.max(Comparator.comparing(Map.Entry::getValue)).get();				
				map.put(entry.getKey(), entry.getValue() + map.get("J"));
				map.remove("J");
			}
			if (map.size() == 1) return 1;
			if (map.size() == 2) {
				for (String key : map.keySet()) {
					if (map.get(key) == 1 || map.get(key) == 4) {
						return 2;
					}
				}
				return 3;
			}
			if (map.size() == 3) {
				for (String key : map.keySet()) {
					if (map.get(key) == 3) {
						return 4;
					}
				}
				return 5;
			}
			if (map.size() == 4) return 6;
			if (map.size() == 5) return 7;
			return 1;
		}
		@Override
		public int compareTo(Hand o) {
			int power1 = this.getPower();
			int power2 = o.getPower();
			if (power1 == power2) {
				return isHigher(o) ? -1 : 1;
			}			
			return power1 > power2 ? 1 : -1;
		}
		private boolean isHigher(Hand hand) {
			String[] split1 = this.cards.split("");
			String[] split2 = hand.cards.split("");
			for (int i = 0; i < split1.length; i++) {
				if (DICTIONARY.get(jokerRule).get(split1[i]) > DICTIONARY.get(jokerRule).get(split2[i])) {
					return true;
				} else if (DICTIONARY.get(jokerRule).get(split1[i]) < DICTIONARY.get(jokerRule).get(split2[i])) {
					return false;
				}
			}
			return false;
		}
		
	}
}
