package y2020;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Day15 {

	private static final int TURN1 = 2020;
	private static final int TURN2 = 30000000;
	static List<Integer> test1 = Arrays.asList(1,3,2);
	static List<Integer> test2 = Arrays.asList(2,1,3);
	static List<Integer> test3 = Arrays.asList(1,2,3);
	static List<Integer> test4 = Arrays.asList(2,3,1);
	static List<Integer> test5 = Arrays.asList(3,2,1);
	static List<Integer> test6 = Arrays.asList(3,1,2);
	static List<Integer> input = Arrays.asList(8,0,17,4,1,12);

	static List<Integer> test7 = Arrays.asList(0,3,6);
	static List<Integer> test8 = Arrays.asList(1,3,2);
	static List<Integer> test9 = Arrays.asList(2,1,3);
	static List<Integer> test10 = Arrays.asList(1,2,3);
	static List<Integer> test11 = Arrays.asList(2,3,1);
	static List<Integer> test12 = Arrays.asList(3,2,1);
	static List<Integer> test13 = Arrays.asList(3,1,2);

	@Test
	public void test() {
		// Part one
		assertEquals(1, getNthNumber(TURN1, test1));
		assertEquals(10, getNthNumber(TURN1, test2));
		assertEquals(27, getNthNumber(TURN1, test3));
		assertEquals(78, getNthNumber(TURN1, test4));
		assertEquals(438, getNthNumber(TURN1, test5));
		assertEquals(1836, getNthNumber(TURN1, test6));
		
		assertEquals(981, getNthNumber(TURN1, input));
		
		// Part two
		assertEquals(175594, getNthNumber(TURN2, test7));
		assertEquals(2578, getNthNumber(TURN2, test8));
		assertEquals(3544142, getNthNumber(TURN2, test9));
		assertEquals(261214, getNthNumber(TURN2, test10));
		assertEquals(6895259, getNthNumber(TURN2, test11));
		assertEquals(18, getNthNumber(TURN2, test12));
		assertEquals(362, getNthNumber(TURN2, test13));
		
		assertEquals(164878, getNthNumber(TURN2, input));
		
	}

	private int getNthNumber(int nTh, List<Integer> startingNumbers) {
		Map<Integer, Spoken> map = new HashMap<Integer, Spoken>();
		int turn = 0;
		while (turn < startingNumbers.size()) {
			Spoken value = new Spoken();
			value.lastSpoken = turn + 1;
			map.put(startingNumbers.get(turn), value);
			turn++;
		}
		int lastNumber = 0;
		Spoken previous = map.get(startingNumbers.get(turn - 1));
		turn++;
		while (turn < (nTh + 1)) {
			if (previous.beforeSpoken == 0) {
				// Nuovo
				int number = 0;
				previous = updateSpoken(map, turn, number);
				lastNumber = number;
			} else {
				// Già detto
				int number = previous.getDiff();
				previous = updateSpoken(map, turn, number);
				lastNumber = number;
			}
			turn++;
		}
		return lastNumber;
	}

	private Spoken updateSpoken(Map<Integer, Spoken> map, int turn, int number) {
		Spoken spoken;
		if (map.containsKey(number)) {
			spoken = map.get(number);
			spoken.beforeSpoken = spoken.lastSpoken;
			spoken.lastSpoken = turn;
		} else {
			spoken = new Spoken();
			spoken.lastSpoken = turn;
			map.put(number, spoken);
		}
		return spoken;
	}

	class Spoken {
		int lastSpoken;
		int beforeSpoken;

		int getDiff() {
			return lastSpoken - beforeSpoken;
		}

		@Override
		public String toString() {
			return "[last=" + lastSpoken + ", before=" + beforeSpoken + "]";
		}
		
	}

}
