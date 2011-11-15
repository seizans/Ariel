package com.gmail.seizans.btree;

import java.util.ArrayList;

final class Leaf<E extends Comparable<E>> extends AbsNode<E> {

	Leaf(int deg) {
		this.deg = deg;
		this.keys = new ArrayList<E>(deg * 2 - 1);
		this.children = null;
	}

	boolean search(E e) {
		return keys.indexOf(e) != -1;
	}

	void insert(E e) {
		if (keys.indexOf(e) != -1) {
			throw new RuntimeException("The element already exists.");
		}
		int idx = childIndexOf(e);
		keys.add(idx, e);
	}

	void delete(E e) {
		int index = keys.indexOf(e);
		if (index == -1) {
			throw new IllegalArgumentException("The element does not exist.");
		}
		keys.remove(index);
	}

	Tuple2<E, AbsNode<E>> split() {
		Leaf<E> newLeaf = new Leaf<E>(deg);
		for (int i = 0; i < deg - 1; i++) {
			newLeaf.keys.add(keys.get(deg + i));
		}

		for (int i = 0; i < deg - 1; i++) {
			keys.remove(deg * 2 - 2 - i);
		}

		E center = keys.get(deg - 1);
		keys.remove(deg - 1);
		return new Tuple2<E, AbsNode<E>>(center, newLeaf);
	}

	AbsNode<E> rootSplit() {
		Node<E> root = new Node<E>(deg);
		Leaf<E> lhs = new Leaf<E>(deg);
		Leaf<E> rhs = new Leaf<E>(deg);
		for (int i = 0; i < deg - 1; i++) {
			lhs.keys.add(keys.get(i));
			rhs.keys.add(keys.get(deg + i));
		}
		root.keys.add(keys.get(deg - 1));
		root.children.add(lhs);
		root.children.add(rhs);
		return root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (E e: keys) {
			sb.append("(" + e + ")");
		}
		return sb.toString();
	}
}
