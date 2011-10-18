package com.gmail.seizans;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class HanoiTest {

	@Test
	public void hanoiTest() {
		List<Move> moves = Hanoi.hanoi(4, Move.OneThree);
		for (Move move: moves) {
			System.out.println(move);
		}
	}

}
