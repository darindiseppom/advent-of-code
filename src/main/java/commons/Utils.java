package commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Utils {

	public static String[] tail(String[] split) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<String> removeDuplicate(List<String> sanitize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Scanner getScanner(Class<?> clazz, String pathToFile) throws FileNotFoundException {
		return new Scanner(new File(clazz.getClassLoader().getResource(pathToFile).getFile()));
	}
	
	public static <T> void print(Iterable<T> iterable, String delimiter) {
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString() + (iterator.hasNext() ? delimiter : ""));
		}
		System.out.println();
	}

	public static boolean contains(String source, String els) {
		for (int i = 0; i < els.length(); i++) {
			if (!source.contains(els.subSequence(i, i+1))) {
				return false;
			}
		}
		return true;
	}

	public static <T> List<T> tail(List<T> list, int offset) {
		assert list.size() >= offset;

		List<T> arrayList = new ArrayList<>();
		for (int i = 1; i <= offset; i++) {
			arrayList.add(list.get(list.size() - i));
		}
		return arrayList;
	}

}
