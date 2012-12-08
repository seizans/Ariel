package com.gmail.seizans.algorithm;

import java.util.Random;

public class Utility {
	public static int[] mergeSort(int[] xs) {
		if (xs.length == 1) {
			return xs;
		}
		int n = xs.length;
		xs = mergeArray(mergeSort(sliceArray(xs, 0, n / 2 - 1 ) ), mergeSort(sliceArray(xs, n / 2, n - 1) ) );
		return xs;
//		System.out.println(arrayToString(xs) );
	}
	
	public static int[] mergeArray(int[] xs, int[] ys) {
		int m = xs.length;
		int n = ys.length;
		int i = 0;
		int j = 0;
		int[] result = new int[m + n];
		while (i < m && j < n) {
			if (xs[i] < ys[j] ) {
				result[i + j] = xs[i];
				i++;
			} else {
				result[i + j] = ys[j];
				j++;
			}
		}
		while (i < m) {
			result[i + j] = xs[i];
			i++;
		}
		while (j < n) {
			result[i + j] = ys[j];
			j++;
		}
		return result;
	}
	
	public static int[] sliceArray(int[] xs, int si, int ei) {
		if (si < 0 || ei < si || xs.length < si || xs.length < ei) {
			throw new IllegalArgumentException();
		}
		int n = ei - si + 1;
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = xs[si + i];
		}
		return array;
	}
	
	// This method means 'RandomizeInPlace(A)'.    not 'PermuteBySorting(A)'.
	public static void randomizeArray(Object[] array) {
		Object obj;
		int n = array.length;
		for (int i = 0; i < n; i++) {
			int rand = random(i, n - 1);
			obj = array[i];
			array[i] = array[rand];
			array[rand] = obj;
		}
	}
	
	public static void permuteBySorting(Object[] array) {
		Object obj;
		int n = array.length;
		int[] keyArray = new int[n];
		for (int i = 0; i < n; i++) {
			keyArray[i] = random(1, (int) Math.pow(n, 3) );
		}
		
	}
	
	public static boolean randomBoolean() {
		boolean a = true;
		boolean b = true;
		double p = 0.7;		// Any p is OK. s.t. 0 < p < 1
		while (!(a ^ b) ) {
			a = randomBooleanByProbability(p);
			b = randomBooleanByProbability(p);
		}
		return a;
	}
	
	public static boolean randomBooleanByProbability(double p) {
		if (Math.random() < p ) return true;
		else return false;
	}
	
	// This method means Random(0, 1)
	public static int randomZeroOne() {
		Random random = new Random();
		int result = random.nextInt(2);
		return result;
	}
	
	public static int log2(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		int result = (int) Math.ceil(Math.log(n) / Math.log(2) );
		return result;
	}
	
	public static int random(int a, int b) {
		if (a > b || a < 0) {
			throw new IllegalArgumentException();
		}
		if (a == b) {
			return a;
		}
		int result = 0;
		int m = log2(b - a);
		while (true) {
			for (int i = 0; i < m; i++) {
				result += randomZeroOne() * Math.pow(2, i);
			}
			result += a;
			if (result <= b) {
				return result;
			} else {
				result = 0;
			}
		}
	}
	
	public static void insertionSort(int[] xs) {
		for (int j = 1; j < xs.length; j++ ) {
			int key = xs[j];
			int i = j - 1;
			while (i >= 0 && key < xs[i] ) {
				xs[i + 1] = xs[i];
				i--;
			}
			xs[i + 1] = key;
		}
		return;
	}
	
	public static String arrayToString(int[] xs) {
		String retString = "";
		for(int i = 0; i < xs.length; i++ ) {
			retString += xs[i] + ", ";
		}
		return retString;
	}
	
	public static void test(int[] xs) {
		int[] ys = {1,2,3,4,5};
		xs = ys;
		System.out.println(arrayToString(ys));
	}
	
	public static void test2(Integer[] xs) {
		Integer[] ys = {new Integer(3), new Integer(8)};
		System.out.println(ys[1]);
	}
	
	public static void main(String args[]) {
		//int[] zs = {3,4,5,8,7,1};
		//test(zs);
		//System.out.println(arrayToString(zs));
		Integer[] zs = {new Integer(1), new Integer(2)};
		test2(zs);
		System.out.println(zs[1]);
		
		int[] xs = {7, 3, 9, 1, 5};
		System.out.println(arrayToString(xs) );
		insertionSort(xs);
		System.out.println(arrayToString(xs) );
		return;
	}

}
