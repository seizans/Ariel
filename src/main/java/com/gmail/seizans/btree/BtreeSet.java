package com.gmail.seizans.btree;

public final class BtreeSet<E extends Comparable<E>> {
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
	}

	@Override
	public String toString() {
		return root.toString();
	}
}
