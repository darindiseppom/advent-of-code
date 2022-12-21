package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day13 {

	private static final String PATH_TO_FILE = "2022/day13.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		List<Pair<List<Object>, List<Object>>> pairs = getInput(PATH_TO_FILE);
		assertEquals(5506, getOrderedPairIndexesSum(pairs));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		List<List<Object>> pairs = getInput2(PATH_TO_FILE);
		pairs.add(stringToList("[[2]]"));
		pairs.add(stringToList("[[6]]"));
		pairs.sort(new Comparator<List<Object>>() {
			@Override
			public int compare(List<Object> o1, List<Object> o2) {
				return isOrdered(o1, o2) ? -1 : 1;
			}
		});
		pairs.stream().filter(el -> el.equals(stringToList("[[2]]")));
		int counter = 1;
		for (int i = 0; i < pairs.size(); i++) {
			if (pairs.get(i).equals(stringToList("[[2]]"))
					|| pairs.get(i).equals(stringToList("[[6]]"))) {
				counter *= i + 1;
			}
			
		}
		assertEquals(21756, counter);
	}

	private int getOrderedPairIndexesSum(List<Pair<List<Object>, List<Object>>> pairs) {
		int result = 0;
		for (int i = 0; i < pairs.size(); i++) {
			result += isOrdered(pairs.get(i).getLeft(), pairs.get(i).getRight()) ? i + 1 : 0;
		}
		return result;
	}

	private List<Pair<List<Object>, List<Object>>> getInput(String pathToFile) throws FileNotFoundException {
		List<Pair<List<Object>, List<Object>>> result = new ArrayList<Pair<List<Object>, List<Object>>>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			String line1 = scanner.next();
			if (line1.startsWith("[")) {
				String line2 = scanner.next();
				result.add(new MutablePair<List<Object>, List<Object>>(stringToList(line1), stringToList(line2)));
			}
		}
		return result;
	}

	private List<List<Object>> getInput2(String pathToFile) throws FileNotFoundException {
		List<List<Object>> result = new ArrayList<List<Object>>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			String line = scanner.next();
			if (line.startsWith("[")) {
				result.add(stringToList(line));
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Boolean isOrdered(List<Object> left, List<Object> right) {
		for (int i = 0; i < left.size(); i++) {
			if (right.size() <= i) {
				return false;
			}
			Object l = left.get(i);
			Object r = right.get(i);
			if (l.getClass() != r.getClass()) {
				if (l instanceof Integer) {
					List<Object> newL = new ArrayList<Object>();
					newL.add(l);
					l = newL;
				} else {
					List<Object> newR = new ArrayList<Object>();
					newR.add(r);
					r = newR;
				}
				Boolean res = isOrdered((List<Object>) l, (List<Object>) r);
				if (res != null) {
					return res;
				}
			}
			if (l instanceof List) {
				Boolean res = isOrdered((List<Object>) l, (List<Object>) r);
				if (res != null) {
					return res;
				}
			} else {
				Integer li = (Integer) l;
				Integer ri = (Integer) r;
				if (li.equals(ri)) {
					continue;
				} else {
					return li < ri;
				}
			}
		}
		return left.size() < right.size() ? true : null;
	}

	private List<Object> stringToList(String string) {
		List<Object> result = new ArrayList<Object>();
		Queue<List<Object>> innested = Collections.asLifoQueue(new ArrayDeque<List<Object>>());
		List<Object> child;
		innested.add(result);
		for (int i = 1; i < string.split("").length; i++) {
			char el = string.charAt(i);
			if (el == '[') {
				child = new ArrayList<Object>();
				innested.element().add(child);
				innested.add(child);
			} else if (el == ',') {
				continue;
			} else if (el == ']') {
				innested.remove();
			} else {
				StringBuilder sb = new StringBuilder();
				while (el != ',' && el != ']') {
					sb.append(el);
					el = string.charAt(++i);
				}
				--i;
				innested.element().add(Integer.valueOf(sb.toString()));
			}
		}
		return result;
	}

}
