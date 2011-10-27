package com.gmail.seizans.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<BinaryTree<K, V>> {
	private BinaryTree<K, V> lhs;
	private BinaryTree<K, V> rhs;
	private K key;
	private V value;

	public BinaryTree(K key, V value) {
		setup(null, null, key, value);
	}

	private void setup(BinaryTree<K, V> lhs, BinaryTree<K, V> rhs, K key, V value) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.key = key;
		this.value = value;
	}

	public V search(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null.");
		}
		Iterator<BinaryTree<K, V>> iter = depthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<K, V> tree = iter.next();
			if (key.equals(tree.key)) {
				return tree.value;
			}
		}
		return null;
	}

	public void insert(K key, V value) {
		Iterator<BinaryTree<K, V>> iter = breadthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<K, V> tree = iter.next();
			if (tree.lhs == null) {
				tree.lhs = new BinaryTree<K, V>(key, value);
				return;
			}
			if (tree.rhs == null) {
				tree.rhs = new BinaryTree<K, V>(key, value);
				return;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Queue<BinaryTree<K, V>> queue = new LinkedList<BinaryTree<K,V>>();
		queue.add(this);
		while(!queue.isEmpty()) {
			BinaryTree<K, V> tree = queue.poll();
			sb.append("[key: " + tree.key + ", value: " + tree.value + "]");
			if (tree.lhs != null) {
				queue.add(tree.lhs);
			}
			if (tree.rhs != null) {
				queue.add(tree.rhs);
			}
		}
		return sb.toString();
	}

	public String toDString() {
		StringBuilder sb = new StringBuilder();
		Stack<BinaryTree<K, V>> stack = new Stack<BinaryTree<K,V>>();
		stack.push(this);
		while(!stack.empty()) {
			BinaryTree<K, V> tree = stack.pop();
			sb.append("[key: " + tree.key + ", value: " + tree.value + "]");
			if (tree.rhs != null) {
				stack.push(tree.rhs);
			}
			if (tree.lhs != null) {
				stack.push(tree.lhs);
			}
		}
		return sb.toString();
	}

	public String toSimpleString() {
		return "[key: " + key + ", value: " + value + "]";
	}

	public Iterator<BinaryTree<K, V>> iterator() {
		return depthFirstIterator();
	}

	public Iterator<BinaryTree<K, V>> depthFirstIterator() {
		return new DepthFirstIterator();
	}

	private class DepthFirstIterator implements Iterator<BinaryTree<K, V>> {

		private final Stack<BinaryTree<K, V>> stack = new Stack<BinaryTree<K,V>>();

		private DepthFirstIterator() {
			stack.push(BinaryTree.this);
		}

		public boolean hasNext() {
			return !stack.empty();
		}

		public BinaryTree<K, V> next() {
			BinaryTree<K, V> tree = stack.pop();
			if (tree.rhs != null) {
				stack.push(tree.rhs);
			}
			if (tree.lhs != null) {
				stack.push(tree.lhs);
			}
			return tree;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	public Iterator<BinaryTree<K, V>> breadthFirstIterator() {
		return new BreadthFirstIterator();
	}

	private class BreadthFirstIterator implements Iterator<BinaryTree<K, V>> {
		private Queue<BinaryTree<K, V>> queue = new LinkedList<BinaryTree<K,V>>();

		private BreadthFirstIterator() {
			queue.add(BinaryTree.this);
		}

		public boolean hasNext() {
			return ! queue.isEmpty();
		}

		public BinaryTree<K, V> next() {
			BinaryTree<K, V> tree = queue.poll();
			if (tree.lhs != null) {
				queue.add(tree.lhs);
			}
			if (tree.rhs != null) {
				queue.add(tree.rhs);
			}
			return tree;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

}
