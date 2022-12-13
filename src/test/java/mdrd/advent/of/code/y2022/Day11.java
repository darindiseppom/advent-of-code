package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day11 {

	private static final String PATH_TO_FILE = "2022/day11.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		List<Monkey> monkeys = getMonkeys(PATH_TO_FILE);
		doRounds(monkeys, 20, true);
		monkeys.sort(new Comparator<Monkey>() {
			@Override
			public int compare(Monkey o1, Monkey o2) {
				return Integer.compare(o2.nInspects, o1.nInspects);
			}
		});
		assertEquals(54036, monkeys.get(0).nInspects * monkeys.get(1).nInspects);
	}

//	@Test
//	public void partTwo() throws FileNotFoundException {
//		List<Monkey> monkeys = getMonkeys(PATH_TO_FILE);
//		doRounds(monkeys, 10000, false);
//		monkeys.sort(new Comparator<Monkey>() {
//			@Override
//			public int compare(Monkey o1, Monkey o2) {
//				return Integer.compare(o2.nInspects, o1.nInspects);
//			}
//		});
//		assertEquals(52166, monkeys.get(0).nInspects);
//		assertEquals(52013, monkeys.get(1).nInspects);
//	}
	
	private void doRounds(List<Monkey> monkeys, int rounds, boolean isBored) {
		for (int i = 0; i < rounds; i++) {	
			monkeys.forEach(m -> m.doTurn(isBored));
		}
	}

	private List<Monkey> getMonkeys(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		List<Monkey> monkeys = new ArrayList<Monkey>();
		while (scanner.hasNext()) {
			String line = scanner.next();
			if (line.startsWith("Monkey")) {
				Monkey monkey = new Monkey();
				Matcher matcher = Pattern.compile("(\\d+)").matcher(scanner.next());
				while (matcher.find()) {
					monkey.items.add(Integer.valueOf(matcher.group()));
				}
				String string = scanner.next().split("old ")[1];
				boolean old = false;
				if (string.substring(2).equals("old")) {
					old = true;
				}
				switch (string.substring(0, 1)) {
				case "*":
					monkey.operation = old ? x -> x * x : x -> x * Integer.valueOf(string.substring(2));
					break;
				case "+":
					monkey.operation = old ? x -> x + x : x -> x + Integer.valueOf(string.substring(2));
					break;
				default:
					throw new NotImplementedException();
				}
				matcher = Pattern.compile("(\\d+)").matcher(scanner.next());
				if (matcher.find()) {
					monkey.test = Integer.valueOf(matcher.group());
				}
				matcher = Pattern.compile("(\\d+)").matcher(scanner.next());
				if (matcher.find()) {
					monkey.monkeyTrueId = Integer.valueOf(matcher.group());
				}
				matcher = Pattern.compile("(\\d+)").matcher(scanner.next());
				if (matcher.find()) {
					monkey.monkeyFalseId = Integer.valueOf(matcher.group());
				}
				monkeys.add(monkey);
			}
		}
		monkeys.forEach(monkey -> {
			monkey.monkeyTrue = monkeys.get(monkey.monkeyTrueId);
			monkey.monkeyFalse = monkeys.get(monkey.monkeyFalseId);
		});
		return monkeys;
	}

	class Monkey {
		LinkedList<Integer> items = new LinkedList<Integer>();
		Function<Integer, Integer> operation;
		Function<Integer, Integer> bored = x -> Math.round(x / 3);
		Integer test;
		int monkeyTrueId;
		int monkeyFalseId;
		Monkey monkeyTrue;
		Monkey monkeyFalse;
		int nInspects;

		@Override
		public String toString() {
			return "Monkey [items=" + items + ", test=" + test + ", monkeyTrueId=" + monkeyTrueId + ", monkeyFalseId="
					+ monkeyFalseId + ", nInspects=" + nInspects + "]";
		}

		void doTurn(boolean isBored) {
			int length = items.size();
			for (int i = 0; i < length; i++) {
				Integer item = items.removeFirst();
				nInspects++;
//				StringBuilder sb = new StringBuilder("" + item);
				item = operation.apply(item);
//				sb.append(" diventa " + item);
				item = isBored ? bored.apply(item) : item;
				if (item % test == 0) {
//					if (item < 100000000 && item > 0) {
//						sb.append(" -> Passato alla scimmia (" + monkeyTrueId + ") perché divisibile per " + test);				
////						System.out.println(sb);						
//					}
					monkeyTrue.addItem(item);
				} else {
//					if (item < 100000000 && item > 0) {
//						sb.append(" -> Passato alla scimmia (" + monkeyFalseId + ")");				
////						System.out.println(sb);							
//					}
					monkeyFalse.addItem(item);
				}

			}
		}

		private void addItem(Integer item) {
			items.add(item);
		}

	}

}
