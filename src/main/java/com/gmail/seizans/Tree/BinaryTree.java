package com.gmail.seizans.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<BinaryTree<K, V>> {
	private BinaryTree<K, V> lhs;
	private BinaryTree<K, V> rhs;
	private K key;
	private V value;
	private boolean isRoot;

	private BinaryTree(K key, V value, boolean isRoot) {
		setup(null, null, key, value, isRoot);
	}

	public BinaryTree(K key, V value) {
		setup(null, null, key, value, true);
	}

	private void setup(BinaryTree<K, V> lhs, BinaryTree<K, V> rhs, K key, V value, boolean isRoot) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.key = key;
		this.value = value;
		this.isRoot = isRoot;
	}

	public V search(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null.");
		}
		BinaryTree<K, V> tree = searchHelp(key);
		return tree == null ? null : tree.value;
	}

	/*
	 * key で search した結果の subTree を返す。
	 * 見つからなかった場合は null を返すので、返り値への null チェックが必要。
	 */
	private BinaryTree<K, V> searchHelp(K key) {
		Iterator<BinaryTree<K, V>> iter = depthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<K, V> tree = iter.next();
			if (key.equals(tree.key)) {
				return tree;
			}
		}
		return null;
	}

	public void insert(K key, V value) {
		Iterator<BinaryTree<K, V>> iter = breadthFirstIterator();
		while (iter.hasNext()) {
			BinaryTree<K, V> tree = iter.next();
			if (tree.lhs == null) {
				tree.lhs = new BinaryTree<K, V>(key, value, false);
				return;
			}
			if (tree.rhs == null) {
				tree.rhs = new BinaryTree<K, V>(key, value, false);
				return;
			}
		}
	}

	public void remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		BinaryTree<K, V> tree = searchHelp(key);
		if (tree == null) {
			throw new NoSuchElementException();
		}
		removeHelp(tree);
	}

	private void removeHelp(BinaryTree<K, V> tree) {
		Iterator<BinaryTree<K, V>> iter = tree.depthFirstIterator();
		BinaryTree<K, V> last = null;
		while (iter.hasNext()) {
			BinaryTree<K, V> t = iter.next();
			if (t.lhs == null && t.rhs == null) {
				if (t.isRoot) {
					t.key = null;
					t.value = null;
				} else if (last != null && last.lhs.equals(t)) {
					last.lhs = null;
				} else if (last != null  && last.rhs.equals(t)) {
					last.rhs = null;
				}
				return;
			} else if (t.lhs == null) {
				t.lhs = t.rhs.lhs;
				t.key = t.rhs.key;
				t.value = t.rhs.value;
				t.rhs = t.rhs.rhs;
				return;
			} else if (t.rhs == null) {
				t.rhs = t.lhs.rhs;
				t.key = t.lhs.key;
				t.value = t.lhs.value;
				t.lhs = t.lhs.lhs;
				return;
			} else {
				t.key =  t.lhs.key;
				t.value = t.lhs.value;
				last = t;
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
		private BinaryTree<K, V> last;
		private BinaryTree<K, V> present;

		private DepthFirstIterator() {
			last = null;
			present = BinaryTree.this;
			stack.push(BinaryTree.this);
		}

		public boolean hasNext() {
			return ! stack.empty();
		}

		public BinaryTree<K, V> next() {
			if (stack.isEmpty()) {
				throw new NoSuchElementException();
			}
			BinaryTree<K, V> tree = stack.pop();
			if (tree.rhs != null) {
				stack.push(tree.rhs);
			}
			if (tree.lhs != null) {
				stack.push(tree.lhs);
			}
			last = present;
			present = tree;
			return tree;
		}

		public void remove() {
			if (present.equals(last)) {
				throw new IllegalStateException();
			}
			BinaryTree.this.remove(present.key);
			present = last;
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
			if (queue.isEmpty()) {
				throw new NoSuchElementException();
			}
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
			throw new UnsupportedOperationException();
		}
	}

}
