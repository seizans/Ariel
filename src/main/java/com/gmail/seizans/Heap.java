package com.gmail.seizans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap<E extends Comparable<E>> {
	private List<E> heap;
	private int size;

	public Heap(E[] objects) {
		heap = Arrays.asList(objects);
		size = heap.size();
	}

	public Heap(List<E> list) {
		heap = new ArrayList<E>(list);
		size = heap.size();
	}

	private void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;
		if (l < size && heap.get(i).compareTo(heap.get(l)) < 0) {
			largest = l;
		}
		if (r < size && heap.get(largest).compareTo(heap.get(r)) < 0) {
			largest = r;
		}
		if (largest == i) {
			return;
		}
		exchangeTwo(i, largest);
		maxHeapify(largest);
	}

	private void exchangeTwo(int idx1, int idx2) {
		if (idx1 >= idx2) {
			throw new IllegalArgumentException("idx1 must be greater than idx2");
		}
		E temp = heap.get(idx1);
		heap.set(idx1, heap.get(idx2));
		heap.set(idx2, temp);
	}

	public void buildMaxHeap() {
		for (int i = (int) size / 2; i > 0; i--) {
			maxHeapify(i - 1);
		}
	}

	public void sort() {
		buildMaxHeap();
		for (int i = size - 1; i > 0; i--) {
			exchangeTwo(0, i);
			size--;
			maxHeapify(0);
		}
	}

//	private int parent(int i) {
//		return (int) (i + 1) / 2 - 1;
//	}

	private int left(int i) {
		return (i + 1) * 2 - 1;
	}

	private int right(int i) {
		return (i + 1) * 2;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (E e : heap) {
			sb.append(e);
			sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
