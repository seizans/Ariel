package com.gmail.seizans.btree;

import java.util.List;

abstract class NodeCommon<E extends Comparable<E>> implements INode<E> {
	protected int deg;
	List<E> keys;
	protected List<INode<E>> children;

	int childIndexOf(E e) {
		for (int i = 0; i < keys.size(); i++) {
			if (e.compareTo(keys.get(i)) < 0) {
				return i;
			}
		}
		return keys.size();
	}

	public boolean isFull() {
		return keys.size() == deg * 2 - 1;
	}

}
