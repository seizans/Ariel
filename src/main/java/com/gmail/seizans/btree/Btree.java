package com.gmail.seizans.btree;

import java.util.ArrayList;
import java.util.List;

public final class Btree<E extends Comparable<E>> {
	private final int degree;
	private final List<E> keys;
	private final List<Btree<E>> children;
	private boolean isLeaf;
	private final boolean isRoot;

	public Btree(int degree) {
		this.degree = degree;
		this.isLeaf = true;
		this.isRoot = true;
		keys = new ArrayList<E>(degree * 2 - 1);
		children = new ArrayList<Btree<E>>(degree * 2);
	}

	private Btree(int degree, boolean isLeaf) {
		this.degree = degree;
		this.isLeaf = isLeaf;
		this.isRoot = false;
		keys = new ArrayList<E>(degree * 2 - 1);
		children = new ArrayList<Btree<E>>(degree * 2);
	}

//	private Btree(int degree, boolean isLeaf, boolean isRoot) {
//		this.degree = degree;
//		this.isLeaf = isLeaf;
//		this.isRoot = isRoot;
//		keys = new ArrayList<E>(degree * 2 - 1);
//		children = new ArrayList<Btree<E>>(degree * 2);
//	}

	private void splitChild(int idx) {
		Btree<E> child = children.get(idx);
		if (! child.isFull()) {
			return;
		}

		// まんなかのノードを持ち上げる
		keys.add(idx, child.keys.get(degree - 1));

		// 右と中の間に右半分への参照を代入する
		Btree<E> newTree = new Btree<E>(degree, child.isLeaf);
		for (int i = degree; i < child.keys.size(); i++) {
			newTree.keys.add(child.keys.get(i));
		}
		children.add(idx + 1, newTree);

		// 左と中の間に左半分への参照を代入する
		for (int i = degree * 2 - 1; i <= degree - 1; i--) {
			child.keys.remove(i);
		}
	}

	private boolean isFull() {
		return keys.size() == degree * 2 - 1;
	}

	public boolean search(E e) {
		for (int i = 0; i < keys.size(); i++) {
			E key = keys.get(i);
			if (key.compareTo(e) == 0) {
				return true;
			}
			if (key.compareTo(e) < 0) {
				return children.get(i).search(e);
			}
		}
		return false;
	}

	public void insert(E e) {
		System.out.println(e);
		if (isRoot) {
			if (isFull()) {
				rootSplit();
			}
		}
		if (isLeaf) {
			leafInsert(e);
		} else {
			nodeInsert(e);
		}
	}

	private void leafInsert(E e) {
		for (int i = 0; i < keys.size(); i++) {
			if (e.equals(keys.get(i))) {
				throw new IllegalArgumentException("Duplication element");
			}
			if (e.compareTo(keys.get(i)) < 0) {
				keys.add(i, e);
			}
		}
		keys.add(e);
	}

	private void nodeInsert(E e) {
		for (int i = 0; i < keys.size(); i++) {
			if (e.equals(keys.get(i))) {
				throw new IllegalArgumentException("Duplication element");
			}
			if (e.compareTo(keys.get(i)) < 0) {
				splitChild(i);
				if (e.equals(keys.get(i))) {
					throw new IllegalArgumentException("Duplication element");
				}
				if (e.compareTo(keys.get(i)) < 0) {
					children.get(i).insert(e);
					return;
				} else {
					children.get(i + 1).insert(e);
					return;
				}
			}
		}
		children.get(children.size() - 1).insert(e);
	}

	private void rootSplit() {
		if (isLeaf) {
			leafRootSplit();
		} else {
			nodeRootSplit();
		}
	}

	private void leafRootSplit() {
		Btree<E> lhs = new Btree<E>(degree, isLeaf);
		Btree<E> rhs = new Btree<E>(degree, isLeaf);
		for (int i = 0; i < degree - 1; i++) {
			lhs.keys.add(0, keys.get(i));
		}
		for (int i = degree; i < degree * 2 - 1; i++) {
			rhs.keys.add(keys.get(i));
		}
		E temp = keys.get(degree - 1);
		keys.clear();
		keys.add(temp);
		children.clear();
		children.add(lhs);
		children.add(rhs);
		isLeaf = false;
	}

	private void nodeRootSplit() {
		Btree<E> lhs = new Btree<E>(degree, isLeaf);
		Btree<E> rhs = new Btree<E>(degree, isLeaf);
		for (int i = 0; i < degree - 1; i++) {
			lhs.keys.add(keys.get(i));
			lhs.children.add(children.get(i));
		}
		for (int i = degree; i < degree * 2 - 1; i++) {
			rhs.keys.add(keys.get(i));
			rhs.children.add(children.get(i));
		}
		E temp = keys.get(degree - 1);
		keys.clear();
		keys.add(temp);
		children.clear();
		children.add(lhs);
		children.add(rhs);
	}

	public void delete(E e) {
		
	}
}
