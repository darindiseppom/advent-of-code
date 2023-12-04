package mdrd.advent.of.code.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day04 {
	
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final String PATH_TO_FILE = "2023/day04.txt";

	@Test
	public void partOne() throws FileNotFoundException {
		Queue<Card> cards = getInput(PATH_TO_FILE);
		assertEquals(19855, getSum(cards));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(10378710, getCount(getInput(PATH_TO_FILE)));
	}

	private Queue<Card> getInput(String pathToFile) throws FileNotFoundException {
		Queue<Card> result = new ArrayDeque<Card>();
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			String row = scanner.next();
	        Pattern pattern = Pattern.compile("Card +(\\d+)");
			Matcher matcher = pattern.matcher(row);
	        matcher.find();
	        Card card = new Card(Integer.valueOf(matcher.group(1)));	        
	        pattern = Pattern.compile("Card +\\d+:(.+)\\|(.+)");
	        matcher = pattern.matcher(row);
	        matcher.find();
	        card.winningNumbers = getNumbers(matcher.group(1));
	        card.cardNumbers = getNumbers(matcher.group(2));
			result.add(card);
		}
		scanner.close();
		return result;
	}
	
	private Set<Integer> getNumbers(String str) {
		Set<Integer> result = new HashSet<>();
		String[] split = str.split(" ");
		for (int i = 0; i < split.length; i++) {
			try {
				result.add(Integer.valueOf(split[i]));				
			} catch (NumberFormatException e) {}
		}
		return result;
	}

	private int getSum(Queue<Card> cards) {
		int counter = 0;
		for (Card card : cards) {
			card.winningNumbers.retainAll(card.cardNumbers);
			counter += Math.pow(2, card.winningNumbers.size()-1);
		}
		return counter;
	}

	private int getCount(Queue<Card> cards) {
		int counter = 0;
		while (!cards.isEmpty()) {
			Card head = cards.poll();
			counter++;
			head.winningNumbers.retainAll(head.cardNumbers);
			int matches = head.winningNumbers.size();
			for (int i = 0; i < matches; i++) {
				final int cardId = head.id + i + 1;
				Card find = cards.stream().filter(el -> el.id == cardId).findFirst().get();
				cards.add(find.clone());
			}
		}
		
		return counter;
	}

	class Card {
		int id;
		Set<Integer> winningNumbers = new HashSet<Integer>();
		Set<Integer> cardNumbers = new HashSet<Integer>();
		
		Card(int id) {
			this.id = id;
		}
		protected Card clone() {
			Card clone = new Card(this.id);
			clone.winningNumbers = this.winningNumbers;
			clone.cardNumbers = this.cardNumbers;
			return clone;
		}
	}
	

}
