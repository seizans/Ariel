package com.gmail.seizans.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

public class EndlessRetryerTest {
	
	@Test
	public void randomTest() throws Exception {
		int min = 10;
		int max = 10;
		int numberOfTrials = 50;
		EndlessRetryer er = new EndlessRetryer();
		boolean isOk = true;
		for (int i = 0; i < numberOfTrials; i++) {
			int rand = er.random(min, max);
			if (rand < min || max < rand) {
				isOk = false;
			}
			System.out.println(rand);
		}
		assertTrue(isOk);
	}

}
