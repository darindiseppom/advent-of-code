package commons;

import java.io.File;
import java.io.FileNotFoundException;
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

}
