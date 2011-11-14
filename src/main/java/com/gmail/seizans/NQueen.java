package com.gmail.seizans;

import java.util.ArrayList;
import java.util.List;

public final class NQueen {

	private static boolean canPut(Pos pos, Pos queen) {
		if (pos.getX() == queen.getX()) {
			return false;
		} else if (pos.getY() == queen.getY()) {
			return false;
		} else if (pos.getX() + pos.getY() == queen.getX() + queen.getY()) {
			return false;
		} else if (pos.getX() - pos.getY() == queen.getX() - queen.getY()) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean canPut(Pos pos, List<Pos> queens) {
		for (Pos queen : queens) {
			if (!canPut(pos, queen)) {
				return false;
			}
		}
		return true;
	}

	public static List<List<Pos>> nqueenAll(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive.");
		}
		Pos pos = new Pos(n, n);
		List<Pos> queens = new ArrayList<NQueen.Pos>();
		return nqueenAllHelp(pos, n, queens);
	}

	private static List<List<Pos>> nqueenAllHelp(Pos pos, int n, List<Pos> queens) {
		if (pos.getX() == 1 && pos.getY() == 1) {
			if (canPut(pos, queens)) {
				List<Pos> queensClone = new ArrayList<NQueen.Pos>(queens);
				queensClone.add(pos);
				List<List<Pos>> queensList = new ArrayList<List<Pos>>();
				queensList.add(queensClone);
				return queensList;
			} else {
				return new ArrayList<List<Pos>>();
			}
		} else if (pos.getX() == 1) {
			if (canPut(pos, queens)) {
				List<Pos> queensClone = new ArrayList<NQueen.Pos>(queens);
				queensClone.add(pos);
				return nqueenAllHelp(new Pos(n, pos.getY() - 1), n, queensClone);
			} else {
				return new ArrayList<List<Pos>>();
			}
		} else if (pos.getY() == 1) {
			if (canPut(pos, queens)) {
				List<Pos> queensClone = new ArrayList<NQueen.Pos>(queens);
				queensClone.add(pos);
				List<List<Pos>> queensList = new ArrayList<List<Pos>>();
				queensList.add(queensClone);
				queensList.addAll(nqueenAllHelp(new Pos(pos.getX() - 1, 1), n, queens));
				return queensList;
			} else {
				return nqueenAllHelp(new Pos(pos.getX() - 1, 1), n, queens);
			}
		} else {
			if (canPut(pos, queens)) {
				List<List<Pos>> queensList = new ArrayList<List<Pos>>();
				List<Pos> queensClone = new ArrayList<NQueen.Pos>(queens);
				queensClone.add(pos);
				queensList.addAll(nqueenAllHelp(new Pos(n, pos.getY() - 1), n, queensClone));
				queensList.addAll(nqueenAllHelp(new Pos(pos.getX() - 1, pos.getY()), n, queens));
				return queensList;
			} else {
				return nqueenAllHelp(new Pos(pos.getX() - 1, pos.getY()), n, queens);
			}
		}
	}

	// TODO implement
	public static List<Pos> nqueen(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive.");
		}
		throw new UnsupportedOperationException();
	}

	public static void main(String args[]) {
		int n = 0;
		try {
			n = Integer.valueOf(args[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.exit(9);
		}

		List<List<Pos>> queensList = nqueenAll(n);
		for (List<Pos> queens : queensList) {
			System.out.print("[");
			for (Pos queen : queens) {
				System.out.print(queen + ", ");
			}
			System.out.println("]");
		}
		System.out.println("Number of answer is " + queensList.size());
	}

	private static final class Pos {
		private int x;
		private int y;

		Pos(int x, int y) {
			if (x <= 0) {
				throw new IllegalArgumentException("x must be positive value.");
			}
			if (y <= 0) {
				throw new IllegalArgumentException("y must be positive value.");
			}
			this.x = x;
			this.y = y;
		}

		int getX() {
			return x;
		}

		int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "(" + getX() + ", " + getY() + ")";
		}
	}
}
