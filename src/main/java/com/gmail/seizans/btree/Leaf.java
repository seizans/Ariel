package com.gmail.seizans.btree;

import java.util.ArrayList;

final class Leaf<E extends Comparable<E>> extends NodeCommon<E> implements INode<E> {

	Leaf(int deg) {
		this.deg = deg;
		this.keys = new ArrayList<E>(deg * 2 - 1);
		this.children = null;
	}

	public boolean search(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public void insert(E e) {
		if (keys.indexOf(e) != -1) {
			throw new RuntimeException("The element already exists.");
		}
		int idx = childIndexOf(e);
		keys.add(idx, e);
	}

	public void delete(E e) {
		// TODO Auto-generated method stub
		
	}

	public Tuple2<E, INode<E>> split() {
		Leaf<E> newLeaf = new Leaf<E>(deg);
		for (int i = 0; i < deg - 1; i++) {
			newLeaf.keys.add(keys.get(deg + i));
		}
		for (int i = 0; i < deg - 1; i++) {
			keys.remove(deg * 2 - 2 - i);
		}
		E center = keys.get(deg - 1);
		return new Tuple2<E, INode<E>>(center, newLeaf);
	}

	public INode<E> rootSplit() {
		// TODO Auto-generated method stub
		return null;
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
