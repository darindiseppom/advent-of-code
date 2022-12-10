package y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import commons.Node;
import commons.Utils;

public class Day07 {

	private static final String PATH_TO_FILE = "2022/day07.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";
	private static final int DIR_SIZE = 100000;
	private static final int DISK_SPACE = 70000000;
	private static final int UNUSED_SPACE = 30000000;

	@Test
	public void partOne() throws FileNotFoundException {
		assertEquals(1749646, getSum(getDirectoriesAtMostSize(getFileSystem(PATH_TO_FILE), DIR_SIZE)));
	}

	@Test
	public void partTwo() throws FileNotFoundException {
		Node<String> root = getFileSystem(PATH_TO_FILE);
		assertEquals(1498966, getSmallest(getDirectoriesAtLeastSize(root, UNUSED_SPACE - DISK_SPACE + root.getTreeWeight())));
	}

	private int getSum(List<Node<String>> dirs) {
		return dirs.stream().mapToInt(Node::getTreeWeight).sum();
	}
	
	private int getSmallest(List<Node<String>> dirs) {
		return Collections.min(dirs, Node.getWeightComparator()).getTreeWeight();
	}

	private List<Node<String>> getDirectoriesAtMostSize(Node<String> root, int size) {
		List<Node<String>> result = new ArrayList<Node<String>>();
		for (Node<String> child : root.getChildren()) {
			if (child.hasChildren()) {
				if (child.getTreeWeight() <= size) {
					result.add(child);
				}
				result.addAll(getDirectoriesAtMostSize(child, size));
			}
		}
		return result;
	}

	private List<Node<String>> getDirectoriesAtLeastSize(Node<String> root, int size) {
		List<Node<String>> result = new ArrayList<Node<String>>();
		for (Node<String> child : root.getChildren()) {
			if (child.hasChildren()) {
				if (child.getTreeWeight() >= size) {
					result.add(child);
					result.addAll(getDirectoriesAtLeastSize(child, size));
				}
			}
		}
		return result;
	}

	private Node<String> getFileSystem(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(this.getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		String line = scanner.next();
		Node<String> currentDir = new Node<String>("/");
		while (scanner.hasNext()) {
			line = scanner.next();
			String[] split = line.split(" ");
			if (line.startsWith("$ cd")) {
				currentDir = split[2].equals("..") ? currentDir.getParent() : currentDir.getChild(split[2]);
			} else if (line.startsWith("$ ls")) {
				// Do nothing
			} else if (line.startsWith("dir")) {
				currentDir.addChild(split[1]);
			} else {
				currentDir.addChild(split[1], Integer.valueOf(split[0]));
			}
		}
		scanner.close();
		while (currentDir.hasParent()) {
			currentDir = currentDir.getParent();
		}
		return currentDir;
	}

}
