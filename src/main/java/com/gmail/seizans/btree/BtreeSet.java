package com.gmail.seizans.btree;

import java.util.Iterator;

public final class BtreeSet<E extends Comparable<E>> implements Iterable<E> {
	private AbsNode<E> root;
	public BtreeSet(int deg) {
		root = new Leaf<E>(deg);
	}

	public boolean search(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		return root.search(e);
	}

	public void insert(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}

		if (root.isFull()) {
			root = root.rootSplit();
		}
		root.insert(e);
	}

	public void delete(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		root.delete(e);
		if (root.isEmpty() && root instanceof Node) {
			root = root.children.get(0);
		}
	}

	public E getMin() {
		return root.minKey();
	}

	public E getMax() {
		return root.maxKey();
	}

	public Iterator<E> iterator() {
		return root.iterator();
	}

	@Override
	public String toString() {
		return root.toString();
	}

}
