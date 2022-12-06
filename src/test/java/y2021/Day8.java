package y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import commons.Utils;

public class Day8 {

	private static final String PATTERN_MATCHER = "(.+) (.+) (.+) (.+) (.+) (.+) (.+) (.+) (.+) (.+) \\| (.+) (.+) (.+) (.+)";
	private static final Pattern PATTERN = Pattern.compile(PATTERN_MATCHER);
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void assertPartOne() throws IOException {
		int input = getInputPartOne("2021/day8-1.txt");
		assertEquals(519, input);
	}

	@Test
	public void assertPartTwo() throws IOException {
		List<Entry> input = getInputPartTwo("2021/day8-2.txt");
		assertEquals(1027483, doPartTwo(input));
	}

	private int doPartTwo(List<Entry> input) {
		int counter = 0;
		for (Entry entry : input) {
			counter += entry.getDecodedDisplays();
		}
		return counter;
	}

	private Wire getWireToSegmentA(String number7, String number1) {
		assert number7.length() == 3;
		assert number1.length() == 2;
		for (int i = 0; i < number1.length(); i++) {
			number7 = number7.replace(number1.substring(i, i + 1), "");
		}
		return new Wire(number7.replace(number1, ""), "a");
	}

	private Wire getWireToSegmentF(String number5, String number4, String number1, String number7) {
		Wire wireToSegmentFG = getWireToSegmentFG(number5, number4, number1, number7);
		Wire wireToSegmentCF = getWireToSegmentCF(number1);
		if (wireToSegmentCF.code.contains(wireToSegmentFG.code.substring(0, 1))) {
			return new Wire(wireToSegmentFG.code.substring(0, 1), "f");
		} else {
			return new Wire(wireToSegmentFG.code.substring(1, 2), "f");
		}
	}

	private Wire getWireToSegmentG(String number5, String number4, String number1, String number7, String number8) {
		Wire wireToSegmentFG = getWireToSegmentFG(number5, number4, number1, number7);
		Wire wireToSegmentEG = getWireToSegmentEG(number8, number4, number7, number1);
		if (wireToSegmentEG.code.contains(wireToSegmentFG.code.substring(0, 1))) {
			return new Wire(wireToSegmentFG.code.substring(0, 1), "g");
		} else {
			return new Wire(wireToSegmentFG.code.substring(1, 2), "g");
		}
	}

	private Wire getWireToSegmentE(String number5, String number4, String number1, String number7, String number8) {
		Wire wireToSegmentG = getWireToSegmentG(number5, number4, number1, number7, number8);
		Wire wireToSegmentEG = getWireToSegmentEG(number8, number4, number7, number1);
		if (wireToSegmentG.code.equals(wireToSegmentEG.code.substring(0, 1))) {
			return new Wire(wireToSegmentEG.code.substring(1, 2), "e");
		} else {
			return new Wire(wireToSegmentEG.code.substring(0, 1), "e");
		}
	}

	private Wire getWireToSegmentC(String number5, String number4, String number1, String number7) {
		Wire wireToSegmentF = getWireToSegmentF(number5, number4, number1, number7);
		Wire wireToSegmentCF = getWireToSegmentCF(number1);
		if (wireToSegmentF.code.equals(wireToSegmentCF.code.substring(0, 1))) {
			return new Wire(wireToSegmentCF.code.substring(1, 2), "c");
		} else {
			return new Wire(wireToSegmentCF.code.substring(0, 1), "c");
		}
	}


	private Wire getWireToSegmentBD(String number4, String number1) {
		assert number4.length() == 4;
		assert number1.length() == 2;
		for (int i = 0; i < number1.length(); i++) {
			number4 = number4.replace(number1.substring(i, i + 1), "");
		}
		return new Wire(number4, "bd");
	}

	private Wire getWireToSegmentCF(String number1) {
		return new Wire(number1, "cf");
	}

	private Wire getWireToSegmentEG(String number8, String number4, String number7, String number1) {
		assert number8.length() == 7;
		assert number4.length() == 4;
		String wireToSegmentA = getWireToSegmentA(number7, number1).code;
		for (int i = 0; i < number4.length(); i++) {
			number8 = number8.replace(number4.substring(i, i + 1), "");
		}
		number8 = number8.replace(wireToSegmentA, "");
		return new Wire(number8, "eg");
	}

	private Wire getWireToSegmentFG(String number5, String number4, String number1, String number7) {
		assert number5.length() == 5;
		Wire wireToSegmentBD = getWireToSegmentBD(number4, number1);
		Wire wireToSegmentA = getWireToSegmentA(number7, number1);
		for (int i = 0; i < wireToSegmentBD.code.length(); i++) {
			number5 = number5.replace(wireToSegmentBD.code.substring(i, i + 1), "");
		}
		for (int i = 0; i < wireToSegmentA.code.length(); i++) {
			number5 = number5.replace(wireToSegmentA.code.substring(i, i + 1), "");
		}
		return new Wire(number5, "fg");
	}

	private int getInputPartOne(String pathToFile) throws FileNotFoundException {
		Matcher matcher;
		int counter = 0;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			matcher = PATTERN.matcher(scanner.next());
			matcher.find();
			for (int i = 11; i <= matcher.groupCount(); i++) {
				if (matcher.group(i).length() == 2) {
					counter++;
				}
				if (matcher.group(i).length() == 3) {
					counter++;
				}
				if (matcher.group(i).length() == 4) {
					counter++;
				}
				if (matcher.group(i).length() == 7) {
					counter++;
				}
			}
		}
		scanner.close();
		return counter;
	}
	

	private List<Entry> getInputPartTwo(String pathToFile) throws FileNotFoundException {
		List<Entry> input = new ArrayList<>();
		Matcher matcher;
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			matcher = PATTERN.matcher(scanner.next());
			Entry entry = new Entry();
			matcher.find();
			for (int i = 1; i <= 10; i++) {
				String number = matcher.group(i);
				if (entry.numbers.containsKey(number.length())) {
					List<String> value = new ArrayList<>(entry.numbers.get(number.length()));
					value.add(number);
					entry.numbers.put(number.length(), value);
				} else {
					List<String> value = Arrays.asList(number);
					entry.numbers.put(number.length(), value);
				}
			}
			for (int i = 11; i <= 14; i++) {
				entry.displays.add(matcher.group(i));
			}
			input.add(entry);

		}
		scanner.close();
		return input;
	}

	class Entry {
		Map<Integer, List<String>> numbers = new HashMap<>();
		List<String> displays = new ArrayList<>();
		Map<String, String> wiresToSegment = new HashMap<>();

		public int getDecodedDisplays() {
			String decodeDisplays = "";
			String number1 = getNumber1();
			String number4 = getNumber4();
			String number7 = getNumber7();
			String number8 = getNumber8();
			Wire wireToSegmentBD = getWireToSegmentBD(number4, number1);
			String number5 = getNumber5(wireToSegmentBD);
			Wire wireToSegmentC = getWireToSegmentC(number5, number4, number1, number7);
			Wire wireToSegmentE = getWireToSegmentE(number5, number4, number1, number7, number8);
			for (String display : displays) {
				int displayLength = display.length();
				if (displayLength == 2) {
					decodeDisplays += "1";
				} else if (displayLength == 4) {
					decodeDisplays += "4";
				} else if (displayLength == 3) {
					decodeDisplays += "7";
				} else if (displayLength == 7) {
					decodeDisplays += "8";
				} else if (displayLength == 5) {
					if (display.contains(wireToSegmentE.code)) {
						decodeDisplays += "2";
					} else if (display.contains(wireToSegmentC.code)) {
						decodeDisplays += "3";
					} else {
						decodeDisplays += "5";						
					}
				} else {
					if (!display.contains(wireToSegmentC.code)) {
						decodeDisplays += "6";
					} else if (display.contains(wireToSegmentE.code)) {
						decodeDisplays += "0";
					} else {
						decodeDisplays += "9";						
					}
				}
			}
			return Integer.valueOf(decodeDisplays);

		}

		public String getNumber1() {
			return numbers.get(2).get(0);
		}

		public String getNumber4() {
			return numbers.get(4).get(0);
		}

		public String getNumber7() {
			return numbers.get(3).get(0);
		}

		public String getNumber8() {
			return numbers.get(7).get(0);
		}

		public String getNumber5(Wire wireToSegmentBD) {
			List<String> list = numbers.get(5);
			for (String string : list) {
				if (Utils.contains(string, wireToSegmentBD.code)) {
					return string;
				}
			}
			return null;
		}

	}

	class Wire {
		String code;
		String segment;

		public Wire(String left, String segment) {
			this.code = left;
			this.segment = segment;
		}
	}

}
