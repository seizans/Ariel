package com.gmail.seizans;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void quickSortTest() {
		QuickSort<Integer> list = new QuickSort<Integer>(Arrays.asList(new Integer[]{2,8,7,1,3,5,6,4}));
		System.out.println(list);
		list.quickSort(0, list.size() - 1);
		System.out.println(list);
		fail("Not yet implemented");
	}

}
