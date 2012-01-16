package com.gmail.seizans.bstree;

public interface BinarySearchSet<E extends Comparable<E>> {
	void add(E e);
	boolean contains(E e);
	void remove(E e);
	E getMin();
	E getMax();
}
