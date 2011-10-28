package com.gmail.seizans.Tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class BinaryTreeTest {

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
