package com.gmail.seizans.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;


public class RandomManagerTest {
	@Test
	public void randomTest() throws Exception {
		int numberOfTrial = 20;
		int min = 3;
		int max = 9;
		RandomManager rm = new RandomManager();
		for (int i = 0; i < numberOfTrial; i++) {
			int random = rm.random(min, max);
			System.out.println(random);
		}
	}

}
