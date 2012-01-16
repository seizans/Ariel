package com.gmail.seizans.bstree;

import java.util.Iterator;

import com.gmail.seizans.btree.BtreeSet;

public final class BtreeBsTree<E extends Comparable<E>> implements BinarySearchSet<E>, Iterable<E> {

	private BtreeSet<E> bs = new BtreeSet<E>(1);

	public void add(E e) {
		bs.insert(e);
	}

	public boolean contains(E e) {
		return bs.search(e);
	}

	public void remove(E e) {
		bs.delete(e);
	}

	public E getMin() {
		return bs.getMin();
	}

	public E getMax() {
		return bs.getMax();
	}

	public Iterator<E> iterator() {
		return bs.iterator();
	}

}
