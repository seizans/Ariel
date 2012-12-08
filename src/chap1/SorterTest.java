package chap1;

import static org.junit.Assert.*;

import org.junit.Test;


public class SorterTest {
	@Test
	public void mergeSortTest() throws Exception {
		int[] xs = {3,5,7,6,4,1};
		Sorter sorter = new Sorter();
		sorter.mergeSort(xs, 0, xs.length-1);
		for (int i = 0; i < xs.length; i++) {
			System.out.println(xs[i]);
		}
		System.out.println(5/2);
		
		int[] ys = {};
		int[] zs = null;
		sorter.mergeSort(ys, 0, -1);
		
	}
	
	@Test
	public void mergeTest() throws Exception {
		int[] xs = {4,7,5,6};
		Sorter sorter = new Sorter();
		sorter.merge(xs, 0, 1, 3);
		for (int i = 0; i < xs.length; i++) {
			System.out.println(xs[i]);
		}
	}
	
	@Test
	public void rotateTest() throws Exception {
		Sorter sorter = new Sorter();
		int[] xs = {1,2,3};
		System.out.println(xs[1]);
		sorter.rotate(xs);
		System.out.println(xs[1]);
	}


}
