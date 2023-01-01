package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day18 {

	private static final String PATH_TO_FILE = "2022/day18.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	
	@Test
	public void partOne() throws FileNotFoundException {
		List<Cube3D> input = getInput(PATH_TO_FILE);
		assertEquals(64, input.stream().mapToInt(v -> v.freeFaces).sum());
	}

	private List<Cube3D> getInput(String pathToFile) throws FileNotFoundException {
		List<Cube3D> result = new ArrayList<Cube3D>();
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		while (scanner.hasNext()) {
			Matcher matcher = Pattern.compile("(\\d+),(\\d+),(\\d+)").matcher(scanner.next());
			while (matcher.find()) {
				Cube3D cube = new Cube3D(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)));
				result.forEach(c -> {
					if (c.isAdjacent(cube)) {
						c.freeFaces--;
						cube.freeFaces--;
					}
				});
				result.add(cube);
			}
		}
		return result;
	}
	
	class Cube3D {
		int x;
		int y;
		int z;
		int freeFaces = 6;
		
		Cube3D(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		boolean isAdjacent(Cube3D cube) {
			return Math.abs(cube.x - this.x) + Math.abs(cube.y - this.y) + Math.abs(cube.z - this.z) == 1; 
		}
	}

}
