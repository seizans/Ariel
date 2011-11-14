//package com.gmail.seizans;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class HeapCopy<E extends Comparable<E>> {
//	private final List<E> heap;
//
//	public HeapCopy(E[] objects) {
//		heap = Arrays.asList(objects);
//	}
//
//	public E get(int idx) {
//		return heap.get(idx);
//	}
//
//	public int size() {
//		return heap.size();
//	}
//	private static HeapCopy<?> maxHeapify(HeapCopy<?> heap) {
//		return null;
//	}
//
//	private static HeapCopy<?> exchangeTwo(HeapCopy<?> heap, int idx1, int idx2) {
//		if (idx1 >= idx2) {
//			throw new IllegalArgumentException("idx1 must be greater than idx2");
//		}
//		Object[] array = new Object[heap.size()];
//		for (int i = 0; i < heap.size(); i++) {
//			array[i] = heap.get(i);
//		}
//		array[idx1] = heap.get(idx2);
//		array[idx2] = heap.get(idx1);
//		return new HeapCopy<?>(array);
//	}
//
//	private static HeapCopy<?> buildMaxHeap(HeapCopy<?> heap) {
//		// for (int i = )
//		return null;
//	}
//
//	public static HeapCopy<?> sort(HeapCopy<?> heap) {
//		return null;
//	}
//
//	private E parent(int i) {
//		if (i == 0) {
//			return null;
//		}
//		int parentIndex = (int) i / 2;
//		return heap.get(parentIndex);
//	}
//	
//	private E left(int i) {
//		int leftIndex = i * 2;
//		if (leftIndex >= heap.size()) {
//			return null;
//		}
//		return heap.get(leftIndex);
//	}
//	
//	private E right(int i) {
//		int rightIndex = i * 2 + 1;
//		if (rightIndex >= heap.size()) {
//			return null;
//		}
//		return heap.get(rightIndex);
//	}
//}
