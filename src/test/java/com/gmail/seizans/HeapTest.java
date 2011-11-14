package com.gmail.seizans;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTest {
	
	@Test
	public void sortTest() {
		Integer[] ints = new Integer[] {4,7,2,1,6,9,8,5};
		Heap<Integer> heap = new Heap<Integer>(ints);
		System.out.println(heap);
		heap.sort();
		System.out.println(heap);
	}

	@Test
	public void buildMaxHeapTest() {
		Integer[] ints = new Integer[] {4,7,2,1,6,9,8,5};
		Heap<Integer> heap = new Heap<Integer>(ints);
		System.out.println(heap);
		heap.buildMaxHeap();
		System.out.println(heap);
	}

	@Test
	public void test() {
		System.out.println(5 / 2);
		String[] objects = new String[] {"foo", "bar", "hoge"};
		Heap<String> heap = new Heap<String>(objects);
		fail("Not yet implemented");
	}

}
