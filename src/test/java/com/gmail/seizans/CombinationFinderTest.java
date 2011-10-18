package com.gmail.seizans;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class CombinationFinderTest {
	CombinationFinder finder = new CombinationFinder();

	@Test
	public void findCombinationTest() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(4);
		stack.push(3);
		stack.push(9);
		stack.push(0);
		stack.push(-2);
		Stack<Integer> answer1 = finder.findCombination(stack, 1);
		Stack<Integer> answer2 = finder.findCombination(stack, 16);
		Stack<Integer> answer3 = finder.findCombination(stack, 20);
		System.out.println(answer1);
		System.out.println(answer2);
		System.out.println(answer3);
	}

	@Test
	public void findCombinationTest2() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(7);
		stack.push(4);
		stack.push(2);
		stack.push(1);
		Stack<Integer> answer1 = finder.findCombination(stack, 15);
		System.out.println(answer1);
	}

	@Test
	public void dfsTest() {
		int[] a = new int[] {1,2,4,7};
		int n = 4;
		int k = 13;
		boolean answer = finder.dfs(a, k, n);
		System.out.println(answer);
	}

	@Test
	public void test() {
		System.out.println("hogehomu");
		fail("Not yet implemented");
	}

}
