package com.gmail.seizans.algorithm;

import java.util.Random;

public class EndlessRetryer {
	private final Random seed = new Random();

	public int random(int min, int max) {
		final int width = max - min;
		if (min < 0 || width < 0) throw new IllegalArgumentException();

		int random;
		do {
			random = 0;
			int bit = 1;
			while (0 < bit && bit <= width) {
				if (seed.nextBoolean()) random |= bit;
				bit <<= 1;
			}
		} while (random > width);

		return random + min;
	}
}