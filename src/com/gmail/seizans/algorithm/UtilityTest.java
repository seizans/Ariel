package com.gmail.seizans.algorithm;

import static org.hamcrest.CoreMatchers.is;
import static com.gmail.seizans.algorithm.Utility.log2;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.gmail.seizans.algorithm.Utility;

public class UtilityTest {
	
	@Test
	public void hontotest() throws Exception {
		int[] xs = {1,2,3,4,5,6,7};
		System.out.println(Utility.arrayToString(Utility.sliceArray(xs, 3, 3)));
		
	}
	
	@Test
	public void mergeSortTest() throws Exception {
		int[] xs = {5, 6, 4, 1, 2, 9};
		xs = Utility.mergeSort(xs);
		int[] expectedArray = {1, 2, 4, 5, 6, 9};
		assertThat(xs, is(expectedArray));
	}
	
	@Test
	public void mergeArrayTest() throws Exception {
		int[] array1 = {6, 4, 2};
		int[] array2 = {5, 1, 9};
		int[] mergedArray = Utility.mergeArray(array1, array2);
		int[] expectedArray = {5, 1, 6, 4, 2, 9};
		assertThat(mergedArray, is(expectedArray));
	}
	
	@Test
	public void sliceArrayTest() throws Exception {
		int[] xs = {7,6,5,4,3,2,1};
		int[] ys = Utility.sliceArray(xs, 2, 4);
		int[] expectedArray = {5,4,3};
		assertThat(ys, is(expectedArray) );
	}
	
	@Test
	public void randomizeArrayTest() throws Exception {
		int n = 20;
		/*
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = (int) Math.pow(i, 2);
		}
		*/
		Integer[] array = new Integer[n];
		for (int i = 0; i < n; i++) {
			array[i] = new Integer((int) Math.pow(i, 2));
		}
		Utility.randomizeArray(array);
		//Object[] objArray = array;
		//Utility.randomizeArray(objArray);
		for (int i = 0; i < n; i++) {
			//System.out.println(objArray[i]);
			System.out.println(array[i]);
		}
	}
	
	@Test
	public void randomBooleanTest() throws Exception {
		int numberOfTrials = 20000;
		double minProb = 0.95;
		double maxProb = 1.05;
		int counter = 0;
		boolean isOk = false;
		for (int i = 0; i < numberOfTrials; i++) {
			if (Utility.randomBoolean() ) counter++;
		}
		if (numberOfTrials * minProb < counter * 2 && counter * 2 < numberOfTrials * maxProb ) {
			isOk = true;
		}
		assertTrue(isOk);
	}
	
	@Test
	public void randomBooleanByProbabilityTest() throws Exception {
		double p = 0.66666;
		int numberOfTrials = 20000;
		double minProb = 0.95;
		double maxProb = 1.05;
		boolean isOk = false;
		int counter = 0;
		for (int i = 0; i < numberOfTrials; i++) {
			if (Utility.randomBooleanByProbability(p) ) counter++;
		}
		if (p * numberOfTrials * minProb < counter && counter < p * numberOfTrials * maxProb) {
			isOk = true;
		}
		assertTrue(isOk);
	}
	
	@Test
	public void log2Test() throws Exception {
		assertThat(log2(1), is(0) );
		assertThat(log2(2), is(1) );
		assertThat(log2(3), is(2) );
		assertThat(log2(4), is(2) );
		assertThat(log2(5), is(3) );
		assertThat(log2(6), is(3) );
		assertThat(log2(7), is(3) );
		assertThat(log2(8), is(3) );
	}
	
	@Test
	public void randomTest() throws Exception {
		int a = 20;
		int b = 30;
		boolean isOk = true;
		int numberOfTrials = 50;
		for (int i = 0; i < numberOfTrials; i++) {
			int r = Utility.random(a, b);
			if (r < a || b < r) {
				isOk = false;
			}
			System.out.println(r);
		}
		assertTrue(isOk);
	}
	
	@Test
	public void insertionSortTest() throws Exception {
		int[] xs = {7, 3, 9, 1, 5};
		Utility.insertionSort(xs);
		int[] xsAfter = {1,3,5,7,9};
		assertThat(xs, CoreMatchers.is(xsAfter) );
		//assertTrue(xs.equals(xsAfter));
		
		int[] ys = {5};
		int[] ysAfter = {5};
		assertThat(ys, CoreMatchers.is(ysAfter) );
		//assertTrue(ys.equals(ysAfter));
		
		int[] zs = {};
		int[] zsAfter = {};
		assertThat(zs, CoreMatchers.is(zsAfter) );
		//assertTrue(zs.equals(zsAfter));
	}

}
