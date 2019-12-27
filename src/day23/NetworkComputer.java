package day23;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;

import util.IntCodeComputer;
import util.Touple;

public class NetworkComputer implements Runnable {

	private final List<Long> code;
	private final List<Queue<Long>> paramQueues;
	private final Consumer<Touple<Long, Long>> onResult;
	private final Function<Integer, Long> paramFunction;

	public NetworkComputer(List<Long> code, int adress, List<Queue<Long>> paramQueues,
			Consumer<Touple<Long, Long>> onResult) {
		this.code = code;
		this.paramQueues = paramQueues;
		this.onResult = onResult;

		paramFunction = (pointer) -> {
			Long param = paramQueues.get(adress).poll();

			try {
				Thread.sleep(10l);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (param == null) {
				return -1l;
			}

			return param;
		};
	}

	@Override
	public void run() {
		IntCodeComputer computer = new IntCodeComputer(code);

		List<Long> outputs = new ArrayList<>();

		while (computer.isRunning()) {

			outputs.add(computer.run(paramFunction));

			if (outputs.size() % 3 == 0) {

				int size = outputs.size();

				int target = outputs.get(size - 3).intValue();
				long x = outputs.get(size - 2);
				long y = outputs.get(size - 1);

				if (target == 255) {
					onResult.accept(new Touple<Long, Long>(x, y));
				} else {
					paramQueues.get(target).add(x);
					paramQueues.get(target).add(y);
				}

			}
		}
	}

}
