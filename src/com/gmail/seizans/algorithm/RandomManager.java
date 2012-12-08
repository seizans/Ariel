package com.gmail.seizans.algorithm;

import java.util.Random;

public class RandomManager {
	private final Random seed = new Random();
	
	public int random(int min, int max) {
		int width = max - min;
		if (min < 0 || width < 0) throw new IllegalArgumentException();
		
		int random;
		do {
			random = 0;
			for (int bit = 1; bit <= width; bit <<= 1) {
				if (seed.nextBoolean() )
					random |= bit;
			}
		} while (random > width);
		
		return min + random;
	}

}
