package com.gmail.seizans;

import java.util.List;
import java.util.Stack;

public class CombinationFinder {

	public String answer(List<Integer> args, int sum) {
		return null;
	}

	/*
	 * @param args
	 * @param sum
	 * args の中の組み合わせで sum になるものがあればそれをListで返す
	 * そのような組み合わせが存在しない場合は null を返す
	 * args が emptyList だった場合は、sum が 0 なら emptyList を返す
	 */
	public Stack<Integer> findCombination(Stack<Integer> args, int sum) {
		if (args == null) {
			throw new IllegalArgumentException();
		}
		if (args.isEmpty()) {
			if (sum == 0) {
				return args;
			} else {
				return null;
			}
		}
		int present = args.peek();
		args.pop();
		Stack<Integer> clone = (Stack<Integer>)args.clone();
		Stack<Integer> next = findCombination(clone, sum - present);
		if (next != null) {
			next.push(present);
			return next;
		} else {
			return findCombination(args, sum);
		}
	}

	public static final int MAX_N = 20;
	int k;
	int n;
	int[] a;
	private boolean dfs(int i, int sum) {
		if (i == n) {
			return sum == k;
		}
		if (dfs(i + 1, sum)) {
			return true;
		}
		if (dfs(i + 1, sum + a[i])) {
			return true;
		}
		return false;
	}
	
	public boolean dfs(int[] a, int k, int n) {
		this.k = k;
		this.n = n;
		this.a = a;
		return dfs(0, 0);
	}

	public static class CombinationAnswer {
		private int sum;
		private List<Integer> args;

		public CombinationAnswer(int sum, List<Integer> args) {
			this.sum = sum;
			this.args = args;
		}

		public int getSum() { return sum; }
		public List<Integer> getArgs() { return args; }
	}

}
