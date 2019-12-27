package day23;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Touple;

public class Day23Task1Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(23).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			List<Queue<Long>> paramQueues = new ArrayList<>();
			List<Thread> threads = new ArrayList<>();

			Consumer<Touple<Long, Long>> onResult = (coord) -> {
				try {
					AdventUtils.publishResult(23, 1, coord.getRight());

					for (Thread thread : threads) {
						thread.stop();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			};

			for (int i = 0; i < 50; i++) {

				Queue<Long> queue = new LinkedList<>();

				queue.add((long) i);
				paramQueues.add(queue);

				Thread thread = new Thread(new NetworkComputer(code, i, paramQueues, onResult));
				threads.add(thread);
			}

			for (Thread thread : threads) {
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
