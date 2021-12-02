package my.project.advent.of.code.commons;

public class Submarine {

	public int horizontalPosition;
	public int depthPosition;

	public Submarine() {
		this.horizontalPosition = 0;
		this.depthPosition = 0;
	}

	public void doCommand(Command command) {
		if (Direction.FORWARD.equals(command.direction)) {
			horizontalPosition += command.units;
		}
		if (Direction.DOWN.equals(command.direction)) {
			depthPosition += command.units;
		}
		if (Direction.UP.equals(command.direction)) {
			depthPosition -= command.units;
		}
	}


}
