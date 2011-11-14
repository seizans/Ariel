package com.gmail.seizans;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class HanoiTest {

	@Test
	public void hanoiTest() {
		long s = System.nanoTime();
		List<Move> moves = Hanoi.hanoi(13, Move.OneThree);
		for (Move move: moves) {
			System.out.println(move);
		}
		long e = System.nanoTime();
		System.out.println((e - s) / 1000 / 1000);
	}

}
