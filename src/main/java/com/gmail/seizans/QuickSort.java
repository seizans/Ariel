package com.gmail.seizans;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QuickSort<E extends Comparable<E>> {

	private List<E> list;

	public QuickSort(List<E> list) {
		this.list = new ArrayList<E>(list);
	}

	/*
	 * QuickSort Recursion Edition
	 */
	public void quickSort(int s, int e) {
		if (s >= e) {
			return;
		}
		int p = partition(s, e);
		quickSort(s, p - 1);
		quickSort(p + 1, e);
	}

	public int size() {
		return list.size();
	}

	private int partition(int s, int e) {
		E base = list.get(e);
		int part = s - 1;
		for (int idx = s; idx < e; idx++) {
			if (base.compareTo(list.get(idx)) > 0) {
				part++;
				exchangeTwo(part, idx);
			}
		}
		exchangeTwo(part + 1, e);
		return part + 1;
	}

	/*
	 * QuickSort Loop Edition
	 */
	public void loopQuickSort() {
		Stack<LoopCondition> stack = new Stack<QuickSort.LoopCondition>();
		stack.push(new LoopCondition(0, size() - 1));
		while (!stack.isEmpty()) {
			LoopCondition loop = stack.pop();
			loopQuickSortSub(loop.getS(), loop.getE(), stack);
		}
	}

	private void loopQuickSortSub(int s, int e, Stack<LoopCondition> stack) {
		if (s >= e) {
			return;
		}
		int part = partition(s, e);
		stack.push(new LoopCondition(s, part - 1));
		stack.push(new LoopCondition(part + 1, e));
	}

	private static class LoopCondition {
		private int s;
		private int e;
		LoopCondition(int s, int e) {
			this.s = s;
			this.e = e;
		}
		int getS() { return s; }
		int getE() { return e; }
	}
	private void exchangeTwo(int idx1, int idx2) {
		if (idx1 > idx2) {
			throw new IllegalArgumentException("idx1 must be smaller than or equal to idx2.");
		}
		if (idx1 == idx2) {
			return;
		}
		E temp = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, temp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (E e : list) {
			sb.append(e);
			sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
