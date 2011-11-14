package com.gmail.seizans.btree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public final class Btree<E extends Comparable<E>> implements Iterable<E> {
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
		
		// TODO 以下の検証時用のバグ判別if文を後で除去する
		if (child.keys.size() != degree * 2 - 1) {
			System.out.println(child.keys.size());
			System.out.println(child.keys);
			throw new IllegalStateException();
		}

		// まんなかのノードを持ち上げる
		keys.add(idx, child.keys.get(degree - 1));

		// 右と中の間に右半分への参照を代入する
		Btree<E> newLhs = new Btree<E>(degree, child.isLeaf);
		if (child.isLeaf) {
			for (int i = 0; i < degree - 1; i++) {
				newLhs.keys.add(child.keys.get(i));
			}
		} else {
			for (int i = 0; i < degree - 1; i++) {
				newLhs.keys.add(child.keys.get(i));
				newLhs.children.add(child.children.get(i));
			}
			newLhs.children.add(child.children.get(degree - 1));
		}
		
		Btree<E> newRhs = new Btree<E>(degree, child.isLeaf);
//		for (int i = degree; i < child.keys.size(); i++) {
		if (child.isLeaf) {
			for (int i = degree; i < degree * 2 - 1; i++) {
				newRhs.keys.add(child.keys.get(i));
			}
		} else {
			for (int i = degree; i < degree * 2 - 1; i++) {
				newRhs.keys.add(child.keys.get(i));
				newRhs.children.add(child.children.get(i));
			}
			newRhs.children.add(child.children.get(degree * 2 - 1));
		}
		child.keys.remove(degree - 1);

		children.remove(idx);
		children.add(idx, newRhs);
		children.add(idx, newLhs);

		// 左と中の間に左半分への参照を代入する
//		for (int i = degree * 2 - 2; i >= degree; i--) {
//			child.keys.remove(i);
//		}

		// TODO 以下の検証時用のバグ判別if文を後で除去する
		if (children.get(idx).keys.size() != degree - 1) {
			System.out.println(children.get(idx).keys.size());
			System.out.println(children.get(idx + 1).keys.size());
			System.out.println(children.get(idx).keys);
			System.out.println(children.get(idx + 1).keys);
			throw new IllegalStateException();
		}
	}

	private boolean isFull() {
		return keys.size() >= degree * 2 - 1;
	}

	public boolean search(E e) {
		if (isLeaf) {
			for (int i = 0; i < keys.size(); i++) {
				E key = keys.get(i);
				if (e.equals(key)) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < keys.size(); i++) {
				E key = keys.get(i);
				if (e.equals(key)) {
					return true;
				}
				if (e.compareTo(key) < 0) {
					return children.get(i).search(e);
				}
			}
			return children.get(keys.size()).search(e);
		}
	}

	public void insert(E e) {
		if (isRoot) {
			if (isFull()) {
				rootSplit();
			}
		}
		if (isLeaf) {
			leafInsert(e);
		} else {
			// TODO 削除する 検証用コード
			if (children.size() == 0) {
				System.out.println("fuga");
			}
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
				return;
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
		if ( children.size() == 0) {
			System.out.println(e);
			System.out.println(keys);
			System.out.println(children);
			System.out.println("hoge");
		}
		
		int size = children.size();
		splitChild(children.size() - 1);
		if (size != children.size()) {
			if (e.equals(keys.get(size - 1))) {
				throw new IllegalArgumentException("Duplication element");
			}
			if (e.compareTo(keys.get(size - 1)) < 0) {
				children.get(size - 1).insert(e);
			} else {
				children.get(size).insert(e);
			}
		} else {
			children.get(children.size() - 1).insert(e);
		}
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
			lhs.keys.add(keys.get(i));
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
		lhs.children.add(children.get(degree - 1));
		for (int i = degree; i < degree * 2 - 1; i++) {
			rhs.keys.add(keys.get(i));
			rhs.children.add(children.get(i));
		}
		rhs.children.add(children.get(degree * 2 - 1));
		E temp = keys.get(degree - 1);
		keys.clear();
		keys.add(temp);
		children.clear();
		children.add(lhs);
		children.add(rhs);
	}

	public void delete(E e) {
		
	}

//	@Override
//	public String toString() {
//		return 
//	}

	public Iterator<E> iterator() {
		return new OrderIterator();
	}

	private class OrderIterator implements Iterator<E> {
		private final Stack<Object> stack = new Stack<Object>();

		private OrderIterator() {
			Btree<E> root = Btree.this;
			if (root.isLeaf) {
				if (root.keys.size() == 0) {
					return;
				}
				leafPush(root);
			} else {
				nodePush(root);
			}
		}

		private void leafPush(Btree<E> btree) {
			ListIterator<E> iter = btree.keys.listIterator(btree.keys.size());
			while (iter.hasPrevious()) {
				stack.push(iter.previous());
			}
		}

		private void nodePush(Btree<E> btree) {
			for (int i = btree.keys.size() - 1; i >= 0; i--) {
				stack.push(btree.children.get(i + 1));
				stack.push(btree.keys.get(i));
			}
			stack.push(btree.children.get(0));
		}

		public boolean hasNext() {
			return ! stack.empty();
		}

		public E next() {
			if (! hasNext()) {
				throw new NoSuchElementException();
			}
			Object obj = stack.pop();
			if (obj instanceof Comparable<?>) {
				E key = (E)obj;
				return key;
			} else if (obj instanceof Btree<?>) {
				Btree<E> child = (Btree<E>) obj;
				if (child.isLeaf) {
					leafPush(child);
				} else {
					nodePush(child);
				}
				return next();
			} else {
				throw new IllegalStateException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public String toString() {
		if (keys.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if (!isLeaf) {
			for (int i = 0; i < keys.size(); i++) {
				sb.append("[" + children.get(i) + "]");
				sb.append("(" + keys.get(i) + ")");
			}
			sb.append("[" + children.get(children.size() - 1) + "]");
		} else {
			for (int i = 0; i < keys.size(); i++) {
				sb.append("(" + keys.get(i) + ")");
			}
		}
		return sb.toString();
	}
}
