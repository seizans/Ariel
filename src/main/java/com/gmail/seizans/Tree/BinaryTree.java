package com.gmail.seizans.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<BinaryTree<K, V>> {
//	private BinaryTree<K, V> p;
	private BinaryTree<K, V> lhs;
	private BinaryTree<K, V> rhs;
	private K key;
	private V value;

//	private BinaryTree(BinaryTree<K, V> p, BinaryTree<K, V> lhs, BinaryTree<K, V> rhs, K key, V value) {
//		setup(p, lhs, rhs, key, value);
//	}

//	private BinaryTree(BinaryTree<K, V> p, K key, V value) {
//		setup(p, null, null, key, value);
//	}

	public BinaryTree(K key, V value) {
		setup(null, null, key, value);
	}

//	public BinaryTree<K, V> getP() { return p; }
//	public BinaryTree<K, V> getLhs() { return lhs; }
//	public BinaryTree<K, V> getRhs() { return rhs; }
//	public K getKey() { return key; }
//	public V getValue() { return value; }
//
//	public void setLhs(BinaryTree<K, V> lhs) { this.lhs = lhs; }
//	public void setRhs(BinaryTree<K, V> rhs) { this.rhs = rhs; }

	private void setup(BinaryTree<K, V> lhs, BinaryTree<K, V> rhs, K key, V value) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.key = key;
		this.value = value;
	}

	public void insert(K key, V value) {
		Queue<BinaryTree<K, V>> queue = new LinkedList<BinaryTree<K,V>>();
		queue.add(this);
		BinaryTree<K, V> tree;
		while ((tree = queue.poll()) != null) {
			if (tree.lhs == null) {
				tree.lhs = new BinaryTree<K, V>(key, value);
				return;
			}
			if (tree.rhs == null) {
				tree.rhs = new BinaryTree<K, V>(key, value);
				return;
			}
			queue.add(tree.lhs);
			queue.add(tree.rhs);
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

	public Iterator<BinaryTree<K, V>> BreadthFirstIterator() {
		return new BreadthFirstIterator();
	}

	private class BreadthFirstIterator implements Iterator<BinaryTree<K, V>> {

		private Queue<BinaryTree<K, V>> queue = new LinkedList<BinaryTree<K,V>>();

		private BreadthFirstIterator() {
			queue.add(BinaryTree.this);
		}

//		private Queue<BinaryTree<K, V>> setupQueue(BinaryTree<K, V> tree) {
//			Queue<BinaryTree<K, V>> queue = new LinkedList<BinaryTree<K,V>>();
//			if (tree.lhs != null) {
//				queue.addAll(setupQueue(tree.lhs));
//			}
//			queue.add(tree);
//			if (tree.rhs != null) {
//				queue.addAll(setupQueue(tree.rhs));
//			}
//			return queue;
//		}

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
