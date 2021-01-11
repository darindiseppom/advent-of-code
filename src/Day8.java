package it.prometeia.pca.events.listeners.impl;

import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Day8 {
	
	static int accumulator = 0;

	@Test
    public void test1() {
//      String pangram1 = "The quick brown fox jumps over the lazy dog.";
//      assertEquals(true, check(pangram1));
//      assertEquals(true, check("absbdhftrgerwqzxcvbnmlkopijuhygtfrdeswqazxcvmlasdoih"));
//      assertEquals(true, check("qwErtYuioplkjhgfdsazxcvbnm"));
//      assertEquals(false, check("qwErtYuioplkjhgfdsazxcbnm"));
//      assertEquals(true, check("hbjceptdmkmfs vpqki nxwylogza uv nrx"));
		List<Instruction> input = Arrays.asList(
				new Instruction("acc",50),
				new Instruction("acc",-11),
				new Instruction("nop",378),
				new Instruction("acc",15),
				new Instruction("jmp",508),
				new Instruction("acc",-3),
				new Instruction("jmp",1),
				new Instruction("jmp",135),
				new Instruction("jmp",1),
				new Instruction("acc",-6),
				new Instruction("acc",14),
				new Instruction("acc",32),
				new Instruction("jmp",315),
				new Instruction("acc",-16),
				new Instruction("jmp",249),
				new Instruction("jmp",283),
				new Instruction("acc",-14),
				new Instruction("acc",5),
				new Instruction("acc",29),
				new Instruction("jmp",366),
				new Instruction("acc",22),
				new Instruction("jmp",77),
				new Instruction("acc",19),
				new Instruction("jmp",496),
				new Instruction("acc",-2),
				new Instruction("acc",-16),
				new Instruction("nop",284),
				new Instruction("nop",36),
				new Instruction("jmp",178),
				new Instruction("jmp",281),
				new Instruction("acc",32),
				new Instruction("acc",45),
				new Instruction("acc",16),
				new Instruction("jmp",403),
				new Instruction("nop",86),
				new Instruction("nop",32),
				new Instruction("acc",10),
				new Instruction("jmp",47),
				new Instruction("acc",-13),
				new Instruction("acc",35),
				new Instruction("jmp",270),
				new Instruction("jmp",1),
				new Instruction("acc",34),
				new Instruction("acc",-3),
				new Instruction("nop",116),
				new Instruction("jmp",552),
				new Instruction("acc",27),
				new Instruction("nop",113),
				new Instruction("jmp",495),
				new Instruction("acc",-18),
				new Instruction("acc",47),
				new Instruction("acc",19),
				new Instruction("jmp",180),
				new Instruction("acc",-8),
				new Instruction("acc",-1),
				new Instruction("acc",-14),
				new Instruction("acc",17),
				new Instruction("jmp",431),
				new Instruction("acc",49),
				new Instruction("acc",22),
				new Instruction("acc",39),
				new Instruction("acc",28),
				new Instruction("jmp",74),
				new Instruction("jmp",-10),
				new Instruction("acc",-5),
				new Instruction("acc",35),
				new Instruction("jmp",251),
				new Instruction("acc",31),
				new Instruction("acc",-11),
				new Instruction("jmp",-49),
				new Instruction("acc",-12),
				new Instruction("acc",49),
				new Instruction("jmp",36),
				new Instruction("acc",-19),
				new Instruction("acc",-9),
				new Instruction("acc",11),
				new Instruction("acc",-1),
				new Instruction("jmp",419),
				new Instruction("jmp",307),
				new Instruction("acc",36),
				new Instruction("jmp",563),
				new Instruction("acc",32),
				new Instruction("acc",1),
				new Instruction("jmp",270),
				new Instruction("acc",17),
				new Instruction("jmp",464),
				new Instruction("jmp",133),
				new Instruction("acc",29),
				new Instruction("acc",31),
				new Instruction("jmp",394),
				new Instruction("acc",-2),
				new Instruction("jmp",94),
				new Instruction("acc",44),
				new Instruction("acc",28),
				new Instruction("acc",32),
				new Instruction("jmp",543),
				new Instruction("acc",18),
				new Instruction("jmp",325),
				new Instruction("acc",16),
				new Instruction("acc",42),
				new Instruction("jmp",315),
				new Instruction("acc",-6),
				new Instruction("jmp",371),
				new Instruction("acc",41),
				new Instruction("acc",29),
				new Instruction("jmp",44),
				new Instruction("acc",-19),
				new Instruction("jmp",393),
				new Instruction("acc",4),
				new Instruction("jmp",81),
				new Instruction("acc",25),
				new Instruction("jmp",108),
				new Instruction("acc",-18),
				new Instruction("jmp",1),
				new Instruction("jmp",1),
				new Instruction("acc",34),
				new Instruction("jmp",124),
				new Instruction("acc",25),
				new Instruction("acc",45),
				new Instruction("jmp",-46),
				new Instruction("acc",-11),
				new Instruction("acc",43),
				new Instruction("acc",50),
				new Instruction("jmp",6),
				new Instruction("acc",3),
				new Instruction("acc",-6),
				new Instruction("acc",38),
				new Instruction("acc",9),
				new Instruction("jmp",402),
				new Instruction("acc",26),
				new Instruction("nop",97),
				new Instruction("acc",26),
				new Instruction("jmp",115),
				new Instruction("acc",-1),
				new Instruction("acc",2),
				new Instruction("jmp",7),
				new Instruction("acc",38),
				new Instruction("nop",5),
				new Instruction("jmp",-75),
				new Instruction("acc",41),
				new Instruction("nop",470),
				new Instruction("jmp",15),
				new Instruction("acc",-15),
				new Instruction("acc",19),
				new Instruction("acc",22),
				new Instruction("jmp",240),
				new Instruction("acc",14),
				new Instruction("acc",26),
				new Instruction("jmp",71),
				new Instruction("acc",38),
				new Instruction("acc",25),
				new Instruction("jmp",349),
				new Instruction("acc",25),
				new Instruction("acc",31),
				new Instruction("acc",41),
				new Instruction("jmp",419),
				new Instruction("jmp",-69),
				new Instruction("acc",50),
				new Instruction("nop",218),
				new Instruction("jmp",-106),
				new Instruction("nop",225),
				new Instruction("jmp",307),
				new Instruction("acc",33),
				new Instruction("acc",-4),
				new Instruction("acc",36),
				new Instruction("jmp",-57),
				new Instruction("acc",14),
				new Instruction("acc",0),
				new Instruction("acc",-2),
				new Instruction("jmp",184),
				new Instruction("acc",47),
				new Instruction("nop",161),
				new Instruction("acc",-4),
				new Instruction("jmp",-149),
				new Instruction("jmp",103),
				new Instruction("acc",39),
				new Instruction("acc",25),
				new Instruction("acc",8),
				new Instruction("acc",2),
				new Instruction("jmp",364),
				new Instruction("acc",48),
				new Instruction("jmp",241),
				new Instruction("nop",432),
				new Instruction("acc",9),
				new Instruction("jmp",304),
				new Instruction("acc",20),
				new Instruction("jmp",223),
				new Instruction("acc",12),
				new Instruction("acc",21),
				new Instruction("jmp",121),
				new Instruction("acc",12),
				new Instruction("acc",47),
				new Instruction("acc",50),
				new Instruction("acc",8),
				new Instruction("jmp",283),
				new Instruction("jmp",1),
				new Instruction("jmp",81),
				new Instruction("acc",22),
				new Instruction("acc",-6),
				new Instruction("jmp",1),
				new Instruction("acc",-9),
				new Instruction("jmp",340),
				new Instruction("acc",-9),
				new Instruction("acc",5),
				new Instruction("acc",11),
				new Instruction("jmp",204),
				new Instruction("acc",-13),
				new Instruction("acc",12),
				new Instruction("jmp",322),
				new Instruction("acc",38),
				new Instruction("acc",50),
				new Instruction("nop",211),
				new Instruction("jmp",91),
				new Instruction("acc",31),
				new Instruction("acc",34),
				new Instruction("jmp",-95),
				new Instruction("acc",12),
				new Instruction("acc",13),
				new Instruction("jmp",-172),
				new Instruction("nop",419),
				new Instruction("jmp",1),
				new Instruction("nop",-191),
				new Instruction("acc",48),
				new Instruction("jmp",157),
				new Instruction("acc",22),
				new Instruction("acc",27),
				new Instruction("jmp",61),
				new Instruction("acc",23),
				new Instruction("nop",181),
				new Instruction("jmp",-121),
				new Instruction("nop",367),
				new Instruction("jmp",-168),
				new Instruction("jmp",1),
				new Instruction("nop",-218),
				new Instruction("jmp",-142),
				new Instruction("jmp",295),
				new Instruction("jmp",112),
				new Instruction("acc",9),
				new Instruction("acc",-12),
				new Instruction("jmp",114),
				new Instruction("acc",50),
				new Instruction("jmp",-28),
				new Instruction("acc",18),
				new Instruction("nop",-223),
				new Instruction("acc",37),
				new Instruction("acc",-14),
				new Instruction("jmp",169),
				new Instruction("acc",0),
				new Instruction("acc",42),
				new Instruction("jmp",115),
				new Instruction("acc",2),
				new Instruction("acc",31),
				new Instruction("jmp",-189),
				new Instruction("acc",7),
				new Instruction("acc",45),
				new Instruction("acc",-2),
				new Instruction("acc",34),
				new Instruction("jmp",-121),
				new Instruction("acc",-13),
				new Instruction("acc",4),
				new Instruction("nop",-94),
				new Instruction("acc",34),
				new Instruction("jmp",123),
				new Instruction("acc",-11),
				new Instruction("acc",-13),
				new Instruction("jmp",-29),
				new Instruction("acc",-11),
				new Instruction("nop",-169),
				new Instruction("acc",-11),
				new Instruction("nop",369),
				new Instruction("jmp",189),
				new Instruction("acc",-4),
				new Instruction("jmp",20),
				new Instruction("nop",19),
				new Instruction("acc",-13),
				new Instruction("nop",368),
				new Instruction("jmp",-79),
				new Instruction("acc",-19),
				new Instruction("acc",23),
				new Instruction("acc",-7),
				new Instruction("acc",-11),
				new Instruction("jmp",36),
				new Instruction("acc",-18),
				new Instruction("acc",31),
				new Instruction("nop",349),
				new Instruction("acc",11),
				new Instruction("jmp",-106),
				new Instruction("acc",43),
				new Instruction("jmp",185),
				new Instruction("acc",20),
				new Instruction("nop",297),
				new Instruction("jmp",138),
				new Instruction("acc",8),
				new Instruction("acc",26),
				new Instruction("acc",-2),
				new Instruction("jmp",-18),
				new Instruction("nop",-276),
				new Instruction("jmp",44),
				new Instruction("jmp",1),
				new Instruction("acc",39),
				new Instruction("jmp",314),
				new Instruction("acc",0),
				new Instruction("jmp",-194),
				new Instruction("acc",32),
				new Instruction("acc",17),
				new Instruction("acc",43),
				new Instruction("jmp",-298),
				new Instruction("acc",28),
				new Instruction("acc",-10),
				new Instruction("jmp",-103),
				new Instruction("acc",-17),
				new Instruction("acc",3),
				new Instruction("jmp",25),
				new Instruction("acc",35),
				new Instruction("acc",7),
				new Instruction("acc",-2),
				new Instruction("jmp",-39),
				new Instruction("acc",19),
				new Instruction("acc",19),
				new Instruction("acc",-8),
				new Instruction("jmp",-282),
				new Instruction("jmp",-275),
				new Instruction("acc",-7),
				new Instruction("jmp",196),
				new Instruction("acc",14),
				new Instruction("acc",5),
				new Instruction("jmp",6),
				new Instruction("acc",-7),
				new Instruction("jmp",29),
				new Instruction("nop",275),
				new Instruction("acc",-12),
				new Instruction("jmp",165),
				new Instruction("acc",21),
				new Instruction("acc",4),
				new Instruction("jmp",95),
				new Instruction("acc",15),
				new Instruction("jmp",-283),
				new Instruction("jmp",199),
				new Instruction("acc",-9),
				new Instruction("acc",0),
				new Instruction("jmp",-220),
				new Instruction("acc",28),
				new Instruction("acc",1),
				new Instruction("jmp",-313),
				new Instruction("acc",13),
				new Instruction("acc",-5),
				new Instruction("acc",38),
				new Instruction("jmp",62),
				new Instruction("acc",43),
				new Instruction("jmp",-159),
				new Instruction("acc",-14),
				new Instruction("acc",44),
				new Instruction("jmp",-314),
				new Instruction("acc",3),
				new Instruction("acc",34),
				new Instruction("jmp",47),
				new Instruction("jmp",-171),
				new Instruction("acc",27),
				new Instruction("acc",11),
				new Instruction("acc",16),
				new Instruction("jmp",16),
				new Instruction("acc",27),
				new Instruction("acc",40),
				new Instruction("jmp",66),
				new Instruction("acc",30),
				new Instruction("acc",-15),
				new Instruction("jmp",177),
				new Instruction("acc",36),
				new Instruction("acc",41),
				new Instruction("jmp",-189),
				new Instruction("acc",-19),
				new Instruction("jmp",106),
				new Instruction("nop",271),
				new Instruction("nop",-176),
				new Instruction("acc",13),
				new Instruction("jmp",40),
				new Instruction("nop",33),
				new Instruction("jmp",-324),
				new Instruction("acc",18),
				new Instruction("jmp",-76),
				new Instruction("acc",38),
				new Instruction("acc",39),
				new Instruction("acc",34),
				new Instruction("jmp",231),
				new Instruction("jmp",-131),
				new Instruction("acc",46),
				new Instruction("acc",38),
				new Instruction("acc",-3),
				new Instruction("jmp",-161),
				new Instruction("acc",31),
				new Instruction("acc",10),
				new Instruction("jmp",158),
				new Instruction("acc",-18),
				new Instruction("acc",46),
				new Instruction("jmp",-291),
				new Instruction("jmp",48),
				new Instruction("acc",18),
				new Instruction("acc",36),
				new Instruction("acc",16),
				new Instruction("jmp",-77),
				new Instruction("acc",9),
				new Instruction("jmp",-289),
				new Instruction("acc",38),
				new Instruction("jmp",-388),
				new Instruction("nop",137),
				new Instruction("acc",42),
				new Instruction("acc",17),
				new Instruction("nop",-37),
				new Instruction("jmp",-145),
				new Instruction("jmp",-336),
				new Instruction("acc",46),
				new Instruction("acc",-18),
				new Instruction("acc",-13),
				new Instruction("acc",21),
				new Instruction("jmp",-97),
				new Instruction("acc",49),
				new Instruction("nop",-189),
				new Instruction("acc",21),
				new Instruction("jmp",-186),
				new Instruction("acc",25),
				new Instruction("acc",37),
				new Instruction("jmp",193),
				new Instruction("jmp",1),
				new Instruction("acc",-14),
				new Instruction("acc",4),
				new Instruction("jmp",87),
				new Instruction("acc",3),
				new Instruction("nop",-95),
				new Instruction("jmp",-243),
				new Instruction("acc",30),
				new Instruction("acc",35),
				new Instruction("jmp",-128),
				new Instruction("jmp",1),
				new Instruction("nop",55),
				new Instruction("acc",48),
				new Instruction("jmp",129),
				new Instruction("jmp",1),
				new Instruction("acc",37),
				new Instruction("jmp",-326),
				new Instruction("acc",-2),
				new Instruction("acc",-13),
				new Instruction("acc",37),
				new Instruction("jmp",-72),
				new Instruction("acc",23),
				new Instruction("jmp",130),
				new Instruction("acc",18),
				new Instruction("acc",0),
				new Instruction("acc",36),
				new Instruction("jmp",-345),
				new Instruction("acc",0),
				new Instruction("acc",23),
				new Instruction("acc",10),
				new Instruction("jmp",1),
				new Instruction("jmp",-112),
				new Instruction("nop",-430),
				new Instruction("acc",8),
				new Instruction("acc",42),
				new Instruction("jmp",1),
				new Instruction("jmp",180),
				new Instruction("nop",-16),
				new Instruction("acc",22),
				new Instruction("jmp",1),
				new Instruction("acc",2),
				new Instruction("jmp",43),
				new Instruction("acc",29),
				new Instruction("acc",23),
				new Instruction("acc",-2),
				new Instruction("jmp",-364),
				new Instruction("acc",14),
				new Instruction("jmp",-250),
				new Instruction("acc",-11),
				new Instruction("nop",-359),
				new Instruction("jmp",132),
				new Instruction("jmp",-24),
				new Instruction("nop",90),
				new Instruction("acc",32),
				new Instruction("jmp",-461),
				new Instruction("jmp",-311),
				new Instruction("acc",11),
				new Instruction("acc",21),
				new Instruction("jmp",-320),
				new Instruction("jmp",-194),
				new Instruction("jmp",-165),
				new Instruction("acc",43),
				new Instruction("acc",5),
				new Instruction("acc",12),
				new Instruction("jmp",-419),
				new Instruction("jmp",-467),
				new Instruction("acc",47),
				new Instruction("acc",35),
				new Instruction("jmp",133),
				new Instruction("acc",10),
				new Instruction("nop",-394),
				new Instruction("acc",35),
				new Instruction("nop",-109),
				new Instruction("jmp",-298),
				new Instruction("acc",-10),
				new Instruction("nop",-451),
				new Instruction("jmp",-445),
				new Instruction("jmp",57),
				new Instruction("acc",31),
				new Instruction("nop",-1),
				new Instruction("jmp",-59),
				new Instruction("acc",19),
				new Instruction("acc",7),
				new Instruction("jmp",-5),
				new Instruction("acc",31),
				new Instruction("acc",0),
				new Instruction("acc",29),
				new Instruction("acc",-8),
				new Instruction("jmp",-118),
				new Instruction("jmp",-119),
				new Instruction("acc",35),
				new Instruction("jmp",-339),
				new Instruction("acc",14),
				new Instruction("nop",28),
				new Instruction("acc",0),
				new Instruction("acc",25),
				new Instruction("jmp",-265),
				new Instruction("acc",-9),
				new Instruction("acc",29),
				new Instruction("jmp",-365),
				new Instruction("nop",19),
				new Instruction("acc",31),
				new Instruction("acc",16),
				new Instruction("jmp",-116),
				new Instruction("jmp",-442),
				new Instruction("acc",24),
				new Instruction("acc",-3),
				new Instruction("jmp",-505),
				new Instruction("acc",-5),
				new Instruction("jmp",-485),
				new Instruction("acc",-12),
				new Instruction("acc",15),
				new Instruction("jmp",1),
				new Instruction("jmp",-16),
				new Instruction("acc",23),
				new Instruction("nop",-135),
				new Instruction("jmp",26),
				new Instruction("acc",-16),
				new Instruction("jmp",-374),
				new Instruction("jmp",-171),
				new Instruction("jmp",-518),
				new Instruction("acc",23),
				new Instruction("acc",23),
				new Instruction("jmp",-282),
				new Instruction("nop",-78),
				new Instruction("nop",-230),
				new Instruction("jmp",-285),
				new Instruction("acc",39),
				new Instruction("acc",31),
				new Instruction("jmp",-219),
				new Instruction("acc",-18),
				new Instruction("jmp",1),
				new Instruction("acc",43),
				new Instruction("jmp",-175),
				new Instruction("acc",46),
				new Instruction("nop",-391),
				new Instruction("jmp",-305),
				new Instruction("acc",-11),
				new Instruction("acc",41),
				new Instruction("acc",33),
				new Instruction("acc",-9),
				new Instruction("jmp",70),
				new Instruction("nop",-8),
				new Instruction("acc",-3),
				new Instruction("acc",-16),
				new Instruction("acc",8),
				new Instruction("jmp",-139),
				new Instruction("nop",-237),
				new Instruction("acc",1),
				new Instruction("nop",-405),
				new Instruction("acc",16),
				new Instruction("jmp",14),
				new Instruction("acc",0),
				new Instruction("acc",35),
				new Instruction("acc",26),
				new Instruction("acc",43),
				new Instruction("jmp",71),
				new Instruction("nop",-187),
				new Instruction("nop",-188),
				new Instruction("jmp",-7),
				new Instruction("acc",34),
				new Instruction("acc",11),
				new Instruction("nop",-35),
				new Instruction("jmp",-104),
				new Instruction("jmp",-37),
				new Instruction("jmp",1),
				new Instruction("acc",37),
				new Instruction("acc",1),
				new Instruction("nop",-78),
				new Instruction("jmp",19),
				new Instruction("acc",35),
				new Instruction("acc",35),
				new Instruction("acc",-3),
				new Instruction("acc",0),
				new Instruction("jmp",-377),
				new Instruction("acc",49),
				new Instruction("jmp",-519),
				new Instruction("acc",-18),
				new Instruction("acc",-5),
				new Instruction("acc",-15),
				new Instruction("nop",-76),
				new Instruction("jmp",-530),
				new Instruction("acc",7),
				new Instruction("acc",0),
				new Instruction("jmp",-19),
				new Instruction("acc",15),
				new Instruction("acc",37),
				new Instruction("jmp",-79),
				new Instruction("jmp",-339),
				new Instruction("nop",-398),
				new Instruction("acc",-16),
				new Instruction("jmp",20),
				new Instruction("acc",-15),
				new Instruction("acc",-5),
				new Instruction("acc",20),
				new Instruction("acc",-12),
				new Instruction("jmp",-21),
				new Instruction("acc",39),
				new Instruction("acc",32),
				new Instruction("acc",34),
				new Instruction("jmp",-330),
				new Instruction("acc",48),
				new Instruction("acc",2),
				new Instruction("acc",-8),
				new Instruction("acc",-15),
				new Instruction("jmp",-231),
				new Instruction("acc",35),
				new Instruction("acc",-16),
				new Instruction("acc",26),
				new Instruction("nop",-547),
				new Instruction("jmp",-548),
				new Instruction("acc",6),
				new Instruction("acc",20),
				new Instruction("acc",1),
				new Instruction("jmp",-439),
				new Instruction("jmp",-310),
				new Instruction("acc",7),
				new Instruction("acc",18),
				new Instruction("jmp",-58),
				new Instruction("nop",-444),
				new Instruction("jmp",-423),
				new Instruction("acc",-5),
				new Instruction("jmp",-40),
				new Instruction("acc",-14),
				new Instruction("acc",-11),
				new Instruction("nop",-283),
				new Instruction("jmp",-122),
				new Instruction("acc",13),
				new Instruction("acc",5),
				new Instruction("nop",-259),
				new Instruction("acc",12),
				new Instruction("jmp",1));
		
		// Part one
		int index = 0;
		Instruction instruction = input.get(index);
		while (!instruction.alreadyExecuted) {
			index = executeIntruction(instruction, index);
			instruction = input.get(index);
		}
		System.out.println(accumulator);
				
		// Part two
		index = 0;
		instruction = input.get(index);
		for (int i = 0; i < input.size(); i++) {
			accumulator = 0;
			Instruction el = input.get(i);
			if ("acc".equals(el.operator)) {
				continue;
			}

			List<Instruction> newInput;
			if ("jmp".equals(el.operator)) {
				newInput = clone(input);
				newInput.get(i).operator = "nop";
			} else {
				newInput = clone(input);
				newInput.get(i).operator = "jmp";
			}
			executeInstructions(newInput);
		}		
    }
	
	private List<Instruction> clone(List<Instruction> input) {
		List<Instruction> _return = new ArrayList<>();
		for (Instruction instruction : input) {
			// Senza alreadyExecuted
			_return.add(new Instruction(instruction.operator, instruction.argument));
		}
		return _return;
	}

	private void executeInstructions(List<Instruction> input) {
		int index = 0;
		Instruction instruction = input.get(index);
		try {
			while (!instruction.alreadyExecuted) {
				index = executeIntruction(instruction, index);
				instruction = input.get(index);
			}	
		} catch(IndexOutOfBoundsException e) {
			System.out.println(accumulator);
		}
	}
	
	private int executeIntruction(Instruction instruction, int index) {
		int _return = index;
		switch(instruction.operator) {
			case "acc": accumulator += instruction.argument; _return++; break;
			case "jmp": _return += instruction.argument; break;
			case "nop": _return++; break;
		}
		instruction.alreadyExecuted = true;
		return _return;
	}

	public boolean check(String sentence) {
		String[] tail = Utils.tail(sentence.split(""));
		List<String> sanitize = sanitize(tail);
		List<String> removeDuplicate = Utils.removeDuplicate(sanitize);
		return removeDuplicate.size() == 26;
	}
	
	private List<String> sanitize(String[] input) {
		List<String> result = new ArrayList<>();
		for (String string : input) {
			int code = (int) string.charAt(0);
			if (code > 96 && code < 123) {
				result.add(string);
			}
			if ((code > 64 && code < 91)) {
				code += 32;
				result.add(String.valueOf((char) code));
			}
		}
		return result;
	}

	class Instruction {
		String operator;
		int argument;
		boolean alreadyExecuted;
		Instruction(String operator, int argument) {
			this.operator = operator;
			this.argument = argument;
			this.alreadyExecuted = Boolean.FALSE;
		}
	}
}
