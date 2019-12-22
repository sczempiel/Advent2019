package day22;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day22Task2Main {

	private static final long LONG_STACK_SIZE = 119315717514047l;
	private static final int STACK_SIZE = 10007;

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(22);

			List<Integer> stack = new ArrayList<>(STACK_SIZE);
			for (int i = 0; i < STACK_SIZE; i++) {
				stack.add(i);
			}

			for (String instruction : startValue) {
				if (instruction.startsWith("deal into")) {

					stack = dealIntoStack(stack);

				} else if (instruction.startsWith("cut")) {

					stack = cutCards(stack, getNumberOfInstruction(instruction));

				} else if (instruction.startsWith("deal with")) {

					stack = dealIncrement(stack, getNumberOfInstruction(instruction));

				} else {
					throw new IllegalStateException("Can't deal with this shit.");
				}
			}

			AdventUtils.publishResult(22, 1, stack.indexOf(2019));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int getNumberOfInstruction(String instruction) {
		String[] splitted = instruction.split(" ");
		return Integer.valueOf(splitted[splitted.length - 1]);
	}

	private static List<Integer> dealIntoStack(List<Integer> stack) {
		int stackSize = stack.size();
		List<Integer> newStack = new ArrayList<>(stackSize);

		for (int i = 0; i < stackSize; i++) {
			newStack.add(null);
		}

		for (int i = 0; i < stackSize; i++) {
			newStack.set(stackSize - 1 - i, stack.get(i));
		}

		return newStack;
	}

	private static List<Integer> cutCards(List<Integer> stack, int cut) {
		if (cut == 0) {
			return stack;
		}

		int stackSize = stack.size();
		List<Integer> newStack = new ArrayList<>(stackSize);

		if (cut < 0) {
			for (int i = stackSize + cut; i < stackSize; i++) {
				newStack.add(stack.get(i));
			}
			for (int i = 0; i < stackSize + cut; i++) {
				newStack.add(stack.get(i));
			}
		} else {
			for (int i = cut; i < stackSize; i++) {
				newStack.add(stack.get(i));
			}
			for (int i = 0; i < cut; i++) {
				newStack.add(stack.get(i));
			}
		}

		return newStack;
	}

	private static List<Integer> dealIncrement(List<Integer> stack, int increment) {
		int stackSize = stack.size();
		List<Integer> newStack = new ArrayList<>(stackSize);

		for (int i = 0; i < stackSize; i++) {
			newStack.add(null);
		}

		for (int i = 0; i < stackSize; i++) {
			newStack.set((i * increment) % stackSize, stack.get(i));
		}

		return newStack;
	}

}
