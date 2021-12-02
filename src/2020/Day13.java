package it.prometeia.pca.events.listeners.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Day13 {
	
	static int minutes = 1000104;
	static List<Integer> busIDsOne = Arrays.asList(41,37,659,23,13,19,29,937,17);
	static List<String> busIDsTwo = Arrays.asList("41","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","37","x","x","x","x","x","659","x","x","x","x","x","x","x","23","x","x","x","x","13","x","x","x","x","x","19","x","x","x","x","x","x","x","x","x","29","x","937","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","17");
	static List<String> test1 = Arrays.asList("17","x","13","19");
	static List<String> test2 = Arrays.asList("67","7","59","61");
	static List<String> test3 = Arrays.asList("67","x","7","59","61");
	static List<String> test4 = Arrays.asList("67","7","x","59","61");
	static List<String> test5 = Arrays.asList("1789","37","47","1889");
	static List<String> test6 = Arrays.asList("3","5","7");
	
	@Test
	public void test() {
		// Part one
		assertEquals(115, getPartOne());
		
		// Part two
		assertEquals(3417, getPartTwo(test1));
		assertEquals(754018, getPartTwo(test2));
		assertEquals(779210, getPartTwo(test3));
		assertEquals(1261476, getPartTwo(test4));
		assertEquals(1202161486, getPartTwo(test5));
		assertEquals(54, getPartTwo(test6));
		assertEquals(6, MCD(12,18));
		assertEquals(2, MCD(4,14));
		assertEquals(5, MCD(5,0));
		assertEquals(42, mcm(21,6));
		assertEquals(24, mcm(12,8));
		assertEquals(24, mcm(12,8,2));
		assertEquals(24, mcm(12,8,2,4,6));
		assertNotEquals(24, mcm(12,8,5));
		assertEquals(756261495958122L, getPartTwo(busIDsTwo));
		
	}
	
	private int getPartOne() {
		int min = minutes;
		int minId = 0;
		int mod;
		for (Integer id : busIDsOne) {
			mod = minutes % id;
			mod = Math.abs(mod - id);
			if (mod < min) {
				min = mod;
				minId = id;
			}
		}
		return min * minId;
	}
	
	private long getPartTwo(List<String> input) {
		long found = 0;
		long value = 0;
		while (found == 0) {
			int index = 1;
			while (index < input.size()) {
				String el = input.get(index);
				if ("x".equals(el)) {
					index++;
					continue;
				}
				int numEl = Integer.valueOf(el);
				if ((value + index) % numEl != 0) {
					value += mcm(new ArrayList<>(input.subList(0, index)));
					break;
				}
				index++;
			}
			if (index == input.size()) {
				found = value;
			}
		}
		return found;
	}
	
	private long mcm(int a, int b) {
		return (a*b)/MCD(a, b);
	}
	
	private long mcm(long a, long b) {
		return (a*b)/MCD(a, b);
	}
	
	private void removeAllXes(List<String> list) {
		while (list.remove("x")) {
		}
	}
	
	private long mcm(List<String> list) {
		removeAllXes(list);
		int[] a = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = Integer.valueOf(list.get(i));			
		}
		long result = a[0];
		for (int i = 1; i < a.length; i++) {
			result = mcm(result, a[i]);
		}
		return result;
	}
	
	private long mcm(int... a) {
		long result = a[0];
		for (int i = 1; i < a.length; i++) {
			result = mcm(result, a[i]);
		}
		return result;
	}
	
	private long MCD(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		int tmp;
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		while (b != 0) {
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	private long MCD(long a, long b) {
		a = Math.abs(a);
		b = Math.abs(b);
		long tmp;
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		while (b != 0) {
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
	
}
