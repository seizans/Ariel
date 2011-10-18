package com.gmail.seizans;

import java.util.ArrayList;
import java.util.List;

public final class Hanoi {

	public static List<Move> hanoi(int n, Move move) {
		if (n == 1) {
			List<Move> moves = new ArrayList<Move>();
			moves.add(move);
			return moves;
		}

		List<Move> moves = new ArrayList<Move>();
		switch (move) {
		case OneTwo:
			moves.addAll(hanoi(n - 1, Move.OneThree));
			moves.add(Move.OneTwo);
			moves.addAll(hanoi(n - 1, Move.ThreeTwo));
			break;
		case OneThree:
			moves.addAll(hanoi(n - 1, Move.OneTwo));
			moves.add(Move.OneThree);
			moves.addAll(hanoi(n - 1, Move.TwoThree));
			break;
		case TwoOne:
			moves.addAll(hanoi(n - 1, Move.TwoThree));
			moves.add(Move.TwoOne);
			moves.addAll(hanoi(n - 1, Move.ThreeOne));
			break;
		case TwoThree:
			moves.addAll(hanoi(n - 1, Move.TwoOne));
			moves.add(Move.TwoThree);
			moves.addAll(hanoi(n - 1, Move.OneThree));
			break;
		case ThreeOne:
			moves.addAll(hanoi(n - 1, Move.ThreeTwo));
			moves.add(Move.ThreeOne);
			moves.addAll(hanoi(n - 1, Move.TwoOne));
			break;
		case ThreeTwo:
			moves.addAll(hanoi(n - 1, Move.ThreeOne));
			moves.add(Move.ThreeTwo);
			moves.addAll(hanoi(n - 1, Move.OneTwo));
			break;
		default:
			throw new IllegalArgumentException();
		}
		return moves;
	}

	public static void main(String[] args) {
		int n = 0;
		try {
			n = Integer.valueOf(args[0]);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			System.exit(9);
		}
		List<Move> moves = hanoi(n, Move.OneThree);
		for (Move move: moves) {
			System.out.println(move);
		}
	}

}
