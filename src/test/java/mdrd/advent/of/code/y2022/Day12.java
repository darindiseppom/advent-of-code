package mdrd.advent.of.code.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import mdrd.advent.of.code.commons.Dijkstra;
import mdrd.advent.of.code.commons.Graph;
import mdrd.advent.of.code.commons.Node2;
import mdrd.advent.of.code.commons.Utils;

public class Day12 {

	private static final char START = 'S';
	private static final char START_VALUE = 'a';
	private static final char END = 'E';
	private static final char END_VALUE = 'z';
	private static final String PATH_TO_FILE = "2022/day12.txt";
	private static final String SCANNER_DELIMITER_PATTERN = "\n";

	@Test
	public void partOne() throws FileNotFoundException {
		char[][] grid = getGrid(PATH_TO_FILE);
		Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes = new HashMap<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>>();
		Graph<Triple<Integer, Integer, Character>> graph = getGraph(grid, insertedNodes);
		Triple<Integer, Integer, Character> start = findFirst(grid, START);
		Triple<Integer, Integer, Character> end = findFirst(grid, END);
		Node2<Triple<Integer, Integer, Character>> source = insertedNodes.get(start.getLeft()).get(start.getMiddle());
		Node2<Triple<Integer, Integer, Character>> destination = insertedNodes.get(end.getLeft()).get(end.getMiddle());
		graph = Dijkstra.calculateShortestPathFromSource(graph, source);
		assertEquals(468, destination.getDistance());
	}	
	
	@Test
	public void partTwo() throws FileNotFoundException {
		char[][] grid = getGrid(PATH_TO_FILE);
		Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes = new HashMap<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>>();
		Graph<Triple<Integer, Integer, Character>> graph = getGraph(grid, insertedNodes);
		List<Triple<Integer, Integer, Character>> sources = findAll(grid, 'a');
		sources.add(findFirst(grid, START));
		Triple<Integer, Integer, Character> end = findFirst(grid, END);
		Node2<Triple<Integer, Integer, Character>> destination = insertedNodes.get(end.getLeft()).get(end.getMiddle());
		List<Integer> shortestPaths = new ArrayList<Integer>();
		for (Triple<Integer, Integer, Character> el : sources) {
			Node2<Triple<Integer, Integer, Character>> source = insertedNodes.get(el.getLeft()).get(el.getMiddle());			
			graph = Dijkstra.calculateShortestPathFromSource(graph, source);
			shortestPaths.add(destination.getDistance());
		}
		Collections.sort(shortestPaths);
		assertEquals(459, shortestPaths.get(0));
	}

	private Graph<Triple<Integer, Integer, Character>> getGraph(char[][] grid,
			Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes) {
		Graph<Triple<Integer, Integer, Character>> graph = new Graph<Triple<Integer, Integer, Character>>();
		for (int y = 0; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				Node2<Triple<Integer, Integer, Character>> node = retrieveNode(grid, x, y, insertedNodes);
				graph.addNode(node);
				addDestinations(grid, node, insertedNodes);
			}
		}
		return graph;
	}

	private void addDestinations(char[][] grid, Node2<Triple<Integer, Integer, Character>> node,
			Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes) {
		int x = node.getName().getLeft();
		int y = node.getName().getMiddle();
		// Esco se sono E
		if (node.getName().getRight() == END) {
			return;
		}
		if (y > 0 && canCross(grid, x, y, x, y - 1)) {
			addDestination(grid, x, y - 1, node, insertedNodes);
		}
		if (x < grid.length - 1 && canCross(grid, x, y, x + 1, y)) {
			addDestination(grid, x + 1, y, node, insertedNodes);
		}
		if (y < grid[0].length - 1 && canCross(grid, x, y, x, y + 1)) {
			addDestination(grid, x, y + 1, node, insertedNodes);
		}
		if (x > 0 && canCross(grid, x, y, x - 1, y)) {
			addDestination(grid, x - 1, y, node, insertedNodes);
		}
	}

	private void addDestination(char[][] grid, int x, int y, Node2<Triple<Integer, Integer, Character>> source,
			Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes) {
		source.addDestination(retrieveNode(grid, x, y, insertedNodes), 1);
	}

	private Node2<Triple<Integer, Integer, Character>> retrieveNode(char[][] grid, int x, int y,
			Map<Integer, Map<Integer, Node2<Triple<Integer, Integer, Character>>>> insertedNodes) {
		if (insertedNodes.get(x) == null) {
			insertedNodes.put(x, new HashMap<Integer, Node2<Triple<Integer, Integer, Character>>>());
		}
		Node2<Triple<Integer, Integer, Character>> find = insertedNodes.get(x).get(y);
		if (find == null) {
			find = new Node2<Triple<Integer, Integer, Character>>(
					new ImmutableTriple<Integer, Integer, Character>(x, y, grid[x][y]));
		}
		insertedNodes.get(x).put(y, find);
		return find;
	}

	private Triple<Integer, Integer, Character> findFirst(char[][] grid, char c) {
		for (int y = 0; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				if (grid[x][y] == c) {
					return new ImmutableTriple<Integer, Integer, Character>(x, y, c);
				}
			}
		}
		return null;
	}
	
	private List<Triple<Integer, Integer, Character>> findAll(char[][] grid, char c) {
		List<Triple<Integer, Integer, Character>> result = new ArrayList<Triple<Integer, Integer, Character>>();
		for (int y = 0; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				if (grid[x][y] == c) {
					result.add(new ImmutableTriple<Integer, Integer, Character>(x, y, c));
				}
			}
		}
		return result;
	}

	private boolean canCross(char[][] grid, int x1, int y1, int x2, int y2) {
		char first = grid[x1][y1] == START ? START_VALUE : grid[x1][y1];
		char last = grid[x2][y2] == END ? END_VALUE : grid[x2][y2];
		return first + 1 >= last;
	}

	/**
	 * <b>TODO</b>:
	 * <ol>
	 * <li><a href="https://github.com/darindiseppom/advent-of-code/issues/19">#19</a> - Permettere di ricavare le dimensioni della matrice dal file in input</li>
	 * <ol>
	 * <li>{@code scanner.next().length()} per <b>X</b></li>
	 * <li>contatore sullo scanner per <b>Y</b></li>
	 * </ol>
	 * </ol>
	 * 
	 * @param pathToFile Il path del file in input
	 * @return La matrice dell'albero
	 * @throws FileNotFoundException Se il file non esiste
	 */
	private char[][] getGrid(String pathToFile) throws FileNotFoundException {
		Scanner scanner = Utils.getScanner(getClass(), pathToFile);
		scanner.useDelimiter(SCANNER_DELIMITER_PATTERN);
		char[][] grid = new char[143][41];
		int y = 0;
		while (scanner.hasNext()) {
			int x = 0;
			for (String string : scanner.next().split("")) {
				grid[x++][y] = string.charAt(0);
			}
			y++;
		}
		return grid;
	}

}
