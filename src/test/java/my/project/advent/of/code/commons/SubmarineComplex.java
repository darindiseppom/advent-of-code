package my.project.advent.of.code.commons;

public class SubmarineComplex {

	public int horizontalPosition;
	public int depthPosition;
	public int aim;

	public SubmarineComplex() {
		this.horizontalPosition = 0;
		this.depthPosition = 0;
		this.aim = 0;
	}

	public void doCommand(Command command) {
		if (Direction.FORWARD.equals(command.direction)) {
			horizontalPosition += command.units;
			depthPosition += aim * command.units;
		}
		if (Direction.DOWN.equals(command.direction)) {
			aim += command.units;
		}
		if (Direction.UP.equals(command.direction)) {
			aim -= command.units;
		}
	}


}
