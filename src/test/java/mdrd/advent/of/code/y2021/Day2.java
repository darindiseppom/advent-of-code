package mdrd.advent.of.code.y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Utils;

public class Day2 {
	
	@Test
	public void partTwo() throws FileNotFoundException {
		assertEquals(1685186100, getMultiplyFinalPositionsComplex(getInput("2021/day2-2.txt")));
	}
	
	private List<Day2.Command> getInput(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter("\n");
		List<Day2.Command> inputList = new ArrayList<Day2.Command>();
		String[] split;
		while (scanner.hasNext()) {
			split = scanner.next().split(" ");
			inputList.add(new Day2.Command(split[0], Integer.valueOf(split[1])));
		}
		scanner.close();
		return inputList;
	}

	private static int getMultiplyFinalPositionsComplex(List<Day2.Command> commands) {
		Day2.Submarine submarine = new Day2(). new Submarine();
		for (Command command : commands) {
			submarine.doCommand(command);
		}
		return submarine.depthPosition * submarine.horizontalPosition;
	}
	

	public class Command {
		public String direction;
		public int units;

		public Command(String direction, int units) {
			this.direction = direction;
			this.units = units;
		}
	}

	public class Submarine {
		public int horizontalPosition;
		public int depthPosition;
		public int aim;

		public Submarine() {
			this.horizontalPosition = 0;
			this.depthPosition = 0;
			this.aim = 0;
		}

		public void doCommand(Command command) {
			if ("forward".equals(command.direction)) {
				horizontalPosition += command.units;
				depthPosition += aim * command.units;
			}
			if ("down".equals(command.direction)) {
				aim += command.units;
			}
			if ("up".equals(command.direction)) {
				aim -= command.units;
			}
		}
	}
}
