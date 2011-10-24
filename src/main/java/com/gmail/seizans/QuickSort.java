package com.gmail.seizans;

import java.util.ArrayList;
import java.util.List;

public class QuickSort<E extends Comparable<E>> {

	private List<E> list;

	public QuickSort(List<E> list) {
		this.list = new ArrayList<E>(list);
	}

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
