package com.gmail.seizans.btree;

public final class BtreeSet<E extends Comparable<E>> {
	private INode<E> root;
	public BtreeSet(int deg) {
		root = new Leaf<E>(deg);
	}

	public boolean search(E e) {
		return false;
	}

	public void insert(E e) {
		if (root.isFull()) {
			root = root.rootSplit();
		}
		root.insert(e);
	}

	public void delete(E e) {
		
	}

	@Override
	public String toString() {
		return root.toString();
	}
}
