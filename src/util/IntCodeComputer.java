package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class IntCodeComputer {

	private final List<Long> code;
	private long[] initParams;

	private long codePointer = 0;
	private int relativeBase = 0;
	private boolean running = true;

	public IntCodeComputer(List<Long> code, long... initParams) {
		if (code == null) {
			throw new IllegalStateException("Code must not be null.");
		}

		this.code = new ArrayList<>(code);
		this.initParams = initParams;
	}

	public IntCodeComputer(List<Long> code) {
		this(code, null);
	}

	public Long run() {
		return run(new long[0]);
	}

	public Long run(List<Long> params) {
		if (params == null) {
			return run();
		}

		return run(params.stream().mapToLong(Long::longValue).toArray());
	}

	public Long run(long... runParams) {
		long[] params = getParams(runParams);

		return run(pointer -> params[pointer]);
	}

	public Long run(Function<Integer, Long> paramFunction) {

		int paramPointer = 0;

		while (running) {

			String inst = String.valueOf(getValue(codePointer++));

			String optcode = inst.length() > 1 ? inst.substring(inst.length() - 2) : inst.substring(inst.length() - 1);

			String modeParam1 = inst.length() > 2 ? inst.substring(inst.length() - 3, inst.length() - 2) : "0";
			String modeParam2 = inst.length() > 3 ? inst.substring(inst.length() - 4, inst.length() - 3) : "0";
			String modeParam3 = inst.length() > 4 ? inst.substring(inst.length() - 5, inst.length() - 4) : "0";

			long value1;
			long value2;
			long target;

			switch (optcode) {
			case "99":
				running = false;
				return null;
			case "1":
			case "01":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);
				target = getTarget(modeParam3);

				writeValue(target, value1 + value2);
				break;
			case "2":
			case "02":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);
				target = getTarget(modeParam3);

				writeValue(target, value1 * value2);
				break;
			case "3":
			case "03":
				target = getTarget(modeParam1);
				writeValue(target, paramFunction.apply(paramPointer++));
				break;
			case "4":
			case "04":
				return getValue(modeParam1);
			case "5":
			case "05":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);

				if (value1 != 0) {
					codePointer = value2;
				}
				break;
			case "6":
			case "06":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);

				if (value1 == 0) {
					codePointer = value2;
				}
				break;
			case "7":
			case "07":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);
				target = getTarget(modeParam3);

				if (value1 < value2) {
					writeValue(target, 1);
				} else {
					writeValue(target, 0);
				}
				break;
			case "8":
			case "08":
				value1 = getValue(modeParam1);
				value2 = getValue(modeParam2);
				target = getTarget(modeParam3);

				if (value1 == value2) {
					writeValue(target, 1);
				} else {
					writeValue(target, 0);
				}
				break;
			case "9":
			case "09":
				value1 = getValue(modeParam1);
				relativeBase += value1;
				break;
			default:
				throw new IllegalStateException("Illegal optcode: " + optcode);
			}
		}

		return null;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private long getValue(String mode) {
		long pointer = codePointer++;
		rangeCheck(pointer);

		if (mode.equals("0")) {
			long target = getValue(pointer);
			rangeCheck(target);

			return getValue(target);
		}

		if (mode.equals("2")) {
			long target = getValue(pointer) + relativeBase;
			rangeCheck(target);

			return getValue(target);
		}

		return getValue(pointer);
	}

	private long getTarget(String mode) {
		long pointer = codePointer++;

		rangeCheck(pointer);

		if (mode.equals("0")) {
			return getValue(pointer);
		}

		if (mode.equals("2")) {
			return getValue(pointer) + relativeBase;
		}

		throw new IllegalStateException("Mode for target needs to be 0 or 2.");
	}

	private long getValue(long target) {
		return code.get((int) target);
	}

	private void writeValue(long target, long value) {
		rangeCheck(target);
		code.set((int) target, value);
	}

	private void rangeCheck(long target) {
		while (target >= code.size()) {
			code.add(0l);
		}
	}

	private long[] getParams(long[] run) {
		long[] params = run;

		if (params == null) {
			params = new long[0];
		}

		if (initParams == null) {
			return params;
		}

		long[] withInitial = new long[initParams.length + params.length];

		int toFill = 0;

		for (long param : initParams) {
			withInitial[toFill++] = param;
		}

		for (long param : params) {
			withInitial[toFill++] = param;
		}

		// only use the initial params in the first run
		initParams = null;

		return withInitial;
	}

	public static long[] asInstruction(String... moves) {
		List<Long> instructions = new ArrayList<>();

		for (String move : moves) {
			for (int i = 0; i < move.length(); i++) {
				instructions.add((long) move.charAt(i));
			}

			instructions.add((long) '\n');
		}

		return instructions.stream().mapToLong(Long::longValue).toArray();
	}

}
