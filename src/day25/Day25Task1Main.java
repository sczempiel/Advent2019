package day25;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.CollectionUtils;
import util.IntCodeComputer;

public class Day25Task1Main {
	private static final String COMMAND_PROMPT = "Command?\n";

	private static final String[] ITEMS = new String[] { "tambourine", "astrolabe", "klein bottle", "easter egg",
			"shell", "hypercube", "dark matter", "coin" };

	private static final Set<Set<String>> ITEM_COMPINATIONS;

	private static final String[] COLLECT_COMMANDS = new String[] { "north", "take " + ITEMS[0], "east",
			"take " + ITEMS[1], "east", "north", "take " + ITEMS[2], "north", "take " + ITEMS[3], "south", "south",
			"west", "south", "take " + ITEMS[4], "north", "west", "south", "south", "south", "take " + ITEMS[5],
			"north", "north", "west", "take " + ITEMS[6], "west", "north", "west", "take " + ITEMS[7], "south",
			"drop " + ITEMS[0], "drop " + ITEMS[1], "drop " + ITEMS[2], "drop " + ITEMS[3], "drop " + ITEMS[4],
			"drop " + ITEMS[5], "drop " + ITEMS[6], "drop " + ITEMS[7] };

	static {
		ITEM_COMPINATIONS = CollectionUtils.combinations(ITEMS);
		ITEM_COMPINATIONS.add(new HashSet<>());
	}

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(23).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			IntCodeComputer computer = new IntCodeComputer(code);
			String outputTillCommand = "";

			int collectPointer = 0;

			Iterator<Set<String>> combinationIterator = ITEM_COMPINATIONS.iterator();
			Iterator<String> itemIterator = null;
			Set<String> items = null;

			boolean take = true;
			boolean walk = false;
			boolean drop = false;

			while (computer.isRunning()) {
				long[] params;

				if (outputTillCommand.endsWith(COMMAND_PROMPT)) {
					String command = "";

					if (collectPointer < COLLECT_COMMANDS.length) {
						command = COLLECT_COMMANDS[collectPointer++];
					} else {

						if (drop) {
							if (itemIterator.hasNext()) {
								command = "drop " + itemIterator.next();
							} else {
								take = true;
								drop = false;
								itemIterator = null;
							}
						}

						if (take) {
							if (itemIterator == null) {
								if (!combinationIterator.hasNext()) {
									break;
								}

								items = combinationIterator.next();
								itemIterator = items.iterator();
							}

							if (itemIterator.hasNext()) {
								command = "take " + itemIterator.next();
							} else {
								take = false;
								walk = true;
							}

						}

						if (walk) {
							walk = false;
							drop = true;

							itemIterator = items.iterator();
							System.out.println("Items: " + items);
							command = "south";
						}

					}

					System.out.println(command);
					params = IntCodeComputer.asInstruction(command);
					outputTillCommand = "";
				} else {
					params = new long[0];
				}

				Long output = computer.run(params);

				if (output != null) {
					char out = (char) output.intValue();

					outputTillCommand += out;
					System.out.print(out);
				}
			}

			AdventUtils.publishResult(25, 1, scan.nextLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
