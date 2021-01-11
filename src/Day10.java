package it.prometeia.pca.events.listeners.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.aspose.p2cbca448.di;

public class Day10 {
	
	static List<Integer> inputTest1 = Arrays.asList(16,10,15,5,1,11,7,19,6,12,4);
	static List<Integer> inputTest2 = Arrays.asList(28,33,18,42,31,14,46,20,48,47,24,23,49,45,19,38,39,11,1,32,25,35,8,17,7,9,4,2,34,10,3);
	static List<Integer> inputTest3 = Arrays.asList(1, 2, 3, 4);
	static List<Integer> input = Arrays.asList(152,18,146,22,28,133,114,67,19,37,66,14,90,163,26,149,71,106,46,143,145,12,151,105,58,130,93,49,74,83,129,122,63,134,86,136,166,169,159,3,178,88,103,97,110,53,125,128,9,15,78,1,50,87,56,89,60,139,113,43,36,118,170,96,135,23,144,153,150,142,95,180,35,179,80,13,115,2,171,32,70,6,72,119,29,79,27,47,107,73,162,172,57,40,48,100,64,59,175,104,156,94,77,65);
	static Map<List<Integer>, Boolean> map = new HashMap<>();
	static Map<String, Boolean> mapS = new HashMap<>();
	
	@Test
	public void test() {
		// Part one
		assertEquals(getMultiply(inputTest1), 35);
		assertEquals(getMultiply(inputTest2), 220);
		assertEquals(getMultiply(inputTest3), 4);
		assertEquals(getMultiply(input), 2574);
		
		// Part two
		assertEquals(getFattoriale(3), 6);
		assertEquals(getFattoriale(4), 24);
		assertEquals(getCoefficienteBinomiale(5, 3), 10);
		assertEquals(getCoefficienteBinomiale(5, 5), 1);
		assertEquals(getCoefficienteBinomiale(5, 0), 1);

		assertEquals(getDiffList(inputTest3), Arrays.asList(1, 1, 1));
		assertEquals(getDiffList(inputTest2), Arrays.asList(1, 1, 1, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 3, 1, 1, 3, 3, 1, 1, 1, 1, 3, 1, 3, 3, 1, 1, 1, 1));
		assertEquals(getDiffList(Arrays.asList(1, 2, 3, 4, 0, 7)), Arrays.asList(1, 1, 1, 1, 3));
		assertEquals(getArrangements(inputTest1), 8);
		assertEquals(getArrangements(inputTest2), 19208);
		System.out.println(getArrangements(input));

	}
	
	private int getMultiply(List<Integer> input) {
		List<Integer> newInput = new ArrayList<>(input);
		newInput.add(0);
		int count1Jolt = 0;
		int count3Jolt = 0;
		Collections.sort(newInput);
		int max = newInput.get(newInput.size()-1);
		newInput.add(max+3);
		for (int i = 0; i < newInput.size()-1; i++) {
			Integer diff = newInput.get(i+1) - newInput.get(i);
			switch(diff) {
				case 1: count1Jolt++; break;
				case 2: break;
				case 3: count3Jolt++; break;
				default: throw new RuntimeException();
			}
		}
		return count1Jolt * count3Jolt;
	}
	
	private int getArrangements(List<Integer> input) {
		List<Integer> newInput = new ArrayList<>(input);
		newInput.add(0);
		Collections.sort(newInput);
		int max = newInput.get(newInput.size()-1);
		newInput.add(max+3);
//		List<Integer> diffList = getDiffList(newInput);
		String diffList = getDiffListS(newInput);
		
		return getArrangemenetsDiff2(diffList);
	}

	private int getArrangemenetsDiff(List<Integer> diffList) {
		if (map.get(diffList) != null) {
			return 0;
		}
		int result = 1;
		for (int i = 0; i < diffList.size()-2; i++) {
			Integer first = diffList.get(i);
			Integer second = diffList.get(i+1);
			if (first == 1 && second == 1) {
				
				// Vedere se funziona
				List<Integer> clone = new ArrayList<>(diffList);
				
				clone.remove(i+1);
				clone.set(i, 2);
				result += getArrangemenetsDiff(clone);
			}

			if ((first == 1 && second == 2) || (first == 2 && second == 1)) {
				
				// Vedere se funziona
				List<Integer> clone = new ArrayList<>(diffList);
				
				clone.remove(i+1);
				clone.set(i, 3);
				result += getArrangemenetsDiff(clone);
			}
		}
		map.put(diffList,  true);
		return result;
	}

	private int getArrangemenetsDiff(String diffList) {
		if (mapS.get(diffList) != null) {
			return 0;
		}
		int result = 1;
		for (int i = 0; i < diffList.length()-2; i++) {
			Integer first = Integer.valueOf(diffList.substring(i, i+1));
			Integer second = Integer.valueOf(diffList.substring(i+1, i+2));
			if (first == 1 && second == 1) {
				
				// Vedere se funziona
				String clone = diffList.substring(0, i) + "2" + diffList.substring(i+2);
				
				result += getArrangemenetsDiff(clone);
			}

			if ((first == 1 && second == 2) || (first == 2 && second == 1)) {
				
				// Vedere se funziona
				String clone = diffList.substring(0, i) + "3" + diffList.substring(i+2);
				
				result += getArrangemenetsDiff(clone);
			}
		}
		mapS.put(diffList,  true);
		return result;
	}	

	private int getArrangemenetsDiff2(String diffList) {
		
		// Guardia della ricorsione
		if (diffList.length() < 2) {
			return 1;
		}
		
		String last1 = diffList.substring(diffList.length()-1);
		if ("3".equals(last1)) {
			// Tolgo l'ultimo e NON conto il caso in cui mi trovo
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
		}
		String last2 = diffList.substring(diffList.length()-2);
		if ("12".equals(last2)) {
//			String substring = diffList.substring(0, diffList.length()-2);
//			substring += "3";
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2)) +
					getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
		}
		if ("22".equals(last2)) {
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
		}
		if ("32".equals(last2)) {
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2));
		}
		if ("31".equals(last2)) {
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2));
		}
		if ("21".equals(last2)) {
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2)) +
					getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
		}
		if (diffList.length() > 2) {
			String last3 = diffList.substring(diffList.length()-3);
			if ("111".equals(last3)) {
				return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-3)) +
						getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2)) +
						getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
			}
		}
		if ("11".equals(last2)) {
			return getArrangemenetsDiff2(diffList.substring(0, diffList.length()-2)) +
					getArrangemenetsDiff2(diffList.substring(0, diffList.length()-1));
		}
		throw new RuntimeException();
		
	}
	
	private List<Integer> getDiffList(List<Integer> list) {
		Collections.sort(list);
		List<Integer> _return = new ArrayList<>();
		for (int i = 1; i < list.size(); i++) {
			_return.add(list.get(i) - list.get(i-1));
		}
		return _return;
	}
	
	private String getDiffListS(List<Integer> list) {
		Collections.sort(list);
		String _return = "";
		for (int i = 1; i < list.size(); i++) {
			_return += (list.get(i) - list.get(i-1));
		}
		return _return;
	}
	
	private long getCoefficienteBinomiale(int n, int k) {
		if (k > n || 0 > k) {
			throw new IllegalArgumentException();
		}
		long num = getFattoriale(n);
		long den = getFattoriale(k)*getFattoriale(n-k);
		return num/den;
	}
	
	private long getFattoriale(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 0) {
			return 1;
		}
		return n * getFattoriale(n-1);
	}

}
