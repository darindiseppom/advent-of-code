package y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import commons.Utils;

public class Day5 {

	private static final String PATH_TO_FILE = "2022/day5.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		List<LinkedList<String>> queues = getQueues(PATH_TO_FILE);
		List<Triple<Integer, Integer, Integer>> moves = getMoves(PATH_TO_FILE);
		executeCrateMover9000(queues, moves);
		assertEquals("QMBMJDFTD", getFirsts(queues));
	}
	
	@Test
	public void partTwo() throws FileNotFoundException {
		List<LinkedList<String>> queues = getQueues(PATH_TO_FILE);
		List<Triple<Integer, Integer, Integer>> moves = getMoves(PATH_TO_FILE);
		executeCreateMover9001(queues, moves);
		assertEquals("NBTVTJNFJ", getFirsts(queues));
	}

	private String getFirsts(List<LinkedList<String>> queues) {
		StringBuilder sb = new StringBuilder();
		queues.forEach(el -> sb.append(el.pop()));
		return sb.toString();
	}

	private void executeCrateMover9000(List<LinkedList<String>> queues, List<Triple<Integer, Integer, Integer>> moves) {
		for (Triple<Integer, Integer, Integer> move : moves) {
			LinkedList<String> queueFrom = queues.get(move.getMiddle() - 1);
			LinkedList<String> queueTo = queues.get(move.getRight() - 1);
			for (int i = 0; i < move.getLeft(); i++) {
				queueTo.push(queueFrom.pop());
			}
		}
	}
	
	private void executeCreateMover9001(List<LinkedList<String>> queues, List<Triple<Integer, Integer, Integer>> moves) {
		for (Triple<Integer, Integer, Integer> move : moves) {
			LinkedList<String> queueFrom = queues.get(move.getMiddle() - 1);
			LinkedList<String> queueTo = queues.get(move.getRight() - 1);
			Integer howMany = move.getLeft();
			LinkedList<String> invQueue = new LinkedList<>();
			for (int i = 0; i < howMany; i++) {
				invQueue.push(queueFrom.pop());
			}
			for (int i = 0; i < howMany; i++) {
				queueTo.push(invQueue.pop());
			}
		}
	}

	private List<LinkedList<String>> getQueues(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		scanner.next();
		String line = scanner.next();
		while (!line.startsWith(" ")) {
			line = scanner.next();
		}
		scanner.close();
		String[] split = line.split("   ");
		Integer size = Integer.valueOf(split[split.length - 1].trim());
		List<LinkedList<String>> queues = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < size; i++) {
			scanner = Utils.getScanner(this.getClass(), pathToFile);
			scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
			line = scanner.next();
			LinkedList<String> queue = new LinkedList<String>();
			do {
				String substring = line.substring(i * 4, i * 4 + 3);
				String el = substring.substring(1, 2);
				if (!el.equals(" ")) {
					queue.add(substring.substring(1, 2));
				}
				line = scanner.next();
			} while (!line.startsWith(" "));
			scanner.close();
			queues.add(queue);
		}
		return queues;
	}

	private List<Triple<Integer, Integer, Integer>> getMoves(String pathToFile) throws FileNotFoundException {
		List<Triple<Integer, Integer, Integer>> moves = new ArrayList<Triple<Integer, Integer, Integer>>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		scanner.next();
		String line = scanner.next();
		while (!line.startsWith(" ")) {
			line = scanner.next();
		}
		scanner.next();
		while (scanner.hasNext()) {
			line = scanner.next();

	        Pattern pattern = Pattern.compile("\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)");	        
	        Matcher matcher = pattern.matcher(line);

	        while(matcher.find()) {	        
	        	Integer howMany = Integer.valueOf(matcher.group(1));
	        	Integer queueFrom = Integer.valueOf(matcher.group(2));
	        	Integer queueTo = Integer.valueOf(matcher.group(3));
	        	moves.add(new ImmutableTriple<Integer, Integer, Integer>(howMany, queueFrom, queueTo));
	        }
		}
		return moves;
	}

}
