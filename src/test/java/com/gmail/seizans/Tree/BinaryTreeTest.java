package com.gmail.seizans.Tree;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BinaryTreeTest {

	private final String VAL_PRE = "ValuePrefix_";

	/*
	 * 
	 */
	@Test
	public void constructorTest() {
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(0, VAL_PRE + 0);
		String value1 = tree.search(0);
		assertThat(value1, is(VAL_PRE + 0));
		String value2 = tree.search(1);
		assertThat(value2, is(nullValue()));
		tree.remove(0);
		String value3 = tree.search(0);
		assertThat(value3, is(nullValue()));
	}

	@Test
	public void searchTest() {
		final int N = 50;
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(0, VAL_PRE + 0);
		for (int i = 1; i < N; i++) {
			int key = i;
			String value = VAL_PRE + i;
			tree.insert(key, value);
		}
		for (int i = 0; i < N; i++) {
			int key = i;
			String value = tree.search(key);
			assertThat(value, is(VAL_PRE + i));
		}
		String value1 = tree.search(-1);
		assertThat(value1, is(nullValue()));
		String value2 = tree.search(N + 1);
		assertThat(value2, is(nullValue()));

		try {
			tree.remove(-1);
			fail();
		} catch(NoSuchElementException e) {
		}

		try {
			tree.remove(51);
			fail();
		} catch(NoSuchElementException e) {
		}

		for (int i = 0; i < N; i++) {
			tree.remove(i);
		}
		
		for (int i = 0; i< N; i++) {
			String s = tree.search(i);
			assertThat(s, is(nullValue()));
		}

		//System.out.println(tree.toDString());
	}

	@Test
	public void test2() {
		assertThat("hoge", is("hoge"));
		System.out.println("hoge".equals(null));
	}

	@Test
	public void insertTest() {
		BinaryTree<Integer, String> root = new BinaryTree<Integer, String>(10, "ten");
		root.insert(5, "five");
		root.insert(13, "thirteen");
		root.insert(2, "two");
		root.insert(6, "six");
		root.insert(12, "twelve");
		root.insert(20, "twenty");
		System.out.println(root);
		System.out.println(root.toDString());
		for (BinaryTree<Integer, String> node: root) {
			System.out.println(node.toSimpleString());
		}
		System.out.println(root.search(6));
		root.remove(6);
		System.out.println(root);
		root.remove(5);
		System.out.println(root);
	}

	@Test
	public void BreadthFirstIteratorTest() {
		BinaryTree<Integer, String> root = new BinaryTree<Integer, String>(10, "ten");
		root.insert(5, "five");
		root.insert(13, "thirteen");
		root.insert(2, "two");
		root.insert(6, "six");
		root.insert(12, "twelve");
		root.insert(20, "twenty");
		Iterator<BinaryTree<Integer, String>> iter = root.breadthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<Integer, String> tree = iter.next();
			System.out.println(tree);
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
