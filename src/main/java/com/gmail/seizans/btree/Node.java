package com.gmail.seizans.btree;

import java.util.ArrayList;
import java.util.Stack;

final class Node<E extends Comparable<E>> extends AbsNode<E> {

	Node(int deg) {
		if (deg < 1) {
			throw new IllegalArgumentException("Degree must be greater than or equal to 1.");
		}
		this.deg = deg;
		this.keys = new ArrayList<E>(deg * 2 - 1);
		this.children = new ArrayList<AbsNode<E>>(deg * 2);
	}

	@Override
	boolean search(E e) {
		if (keys.indexOf(e) != -1) {
			return true;
		}
		return children.get(childIndexOf(e)).search(e);
	}

	@Override
	void insert(E e) {
		if (keys.indexOf(e) != -1) {
			throw new RuntimeException("The element already exists.");
		}
		int index = childIndexOf(e);
		if (children.get(index).isFull()) {
			childSplit(index);
			if (e.equals(keys.get(index))) {
				throw new RuntimeException("The element already exists.");
			} else if (e.compareTo(keys.get(index)) < 0) {
				children.get(index).insert(e);
			} else {
				children.get(index + 1).insert(e);
			}
		} else {
			children.get(index).insert(e);
		}
	}

	private void childSplit(int index) {
		Tuple2<E, AbsNode<E>> tuple = children.get(index).split();
		keys.add(index, tuple.fst());
		children.add(index + 1, tuple.snd());
	}

	@Override
	void delete(E e) {
		int keyIndex = keys.indexOf(e);
		if (keyIndex == -1) {
			deleteIfNotExist(e);
		} else {
			deleteIfExist(e);
		}
	}

	private void deleteIfExist(E e) {
		int index = keys.indexOf(e);
		if (children.get(index).size() >= deg) {
			E e2 = children.get(index).maxKey();
			keys.remove(index);
			keys.add(index, e2);
			children.get(index).delete(e2);
			return;
		} else if (children.get(index + 1).size() >= deg) {
			E e2 = children.get(index + 1).minKey();
			keys.remove(index);
			keys.add(index, e2);
			children.get(index + 1).delete(e2);
			return;
		} else {
			mergeChild(index);
			children.get(index).delete(e);
		}
	}

	@Override
	E maxKey() {
		return children.get(children.size() - 1).maxKey();
	}

	@Override
	E minKey() {
		return children.get(0).minKey();
	}

	private void deleteIfNotExist(E e) {
		int index = childIndexOf(e);
		if (children.get(index).size() != deg - 1) {
			children.get(index).delete(e);
			return;
		}
		if (index == 0) {
			if (children.get(index + 1).size() != deg - 1) {
				moveToLeft(index + 1);
			} else {
				mergeChild(index);
			}
			children.get(index).delete(e);
		} else if (index == children.size() - 1) {
			if (children.get(index - 1).size() != deg - 1) {
				moveToRight(index - 1);
				children.get(index).delete(e);
			} else {
				mergeChild(index - 1);
				children.get(index - 1).delete(e);
			}
		} else {
			if (children.get(index + 1).size() != deg - 1) {
				moveToLeft(index + 1);
			} else if (children.get(index - 1).size() != deg - 1) {
				moveToRight(index - 1);
			} else {
				mergeChild(index);
			}
			children.get(index).delete(e);
		}
	}

	private void mergeChild(int index) {
		children.get(index).addRight(keys.get(index), children.get(index + 1));
		keys.remove(index);
		children.remove(index + 1);
	}

	@Override
	void addRight(E key, AbsNode<E> right) {
		keys.add(key);
		keys.addAll(right.keys);
		children.addAll(right.children);
	}

	@Override
	void pushRight(E key, AbsNode<E> right) {
		keys.add(key);
		children.add(right);
	}

	@Override
	void pushLeft(E key, AbsNode<E> left) {
		keys.add(0, key);
		children.add(0, left);
	}

	private void moveToRight(int index) {
		Tuple2<E, AbsNode<E>> tuple = children.get(index).popRight();
		E key = keys.get(index);
		keys.remove(index);
		keys.add(index, tuple.fst());
		children.get(index + 1).pushLeft(key, tuple.snd());
	}

	private void moveToLeft(int index) {
		Tuple2<E, AbsNode<E>> tuple = children.get(index).popLeft();
		E key = keys.get(index - 1);
		keys.remove(index - 1);
		keys.add(index - 1, tuple.fst());
		children.get(index - 1).pushRight(key, tuple.snd());
	}

	@Override
	Tuple2<E, AbsNode<E>> popRight() {
		E key = keys.get(keys.size() - 1);
		AbsNode<E> child = children.get(children.size() - 1);
		keys.remove(keys.size() - 1);
		children.remove(children.size() - 1);
		return new Tuple2<E, AbsNode<E>>(key, child);
	}

	@Override
	Tuple2<E, AbsNode<E>> popLeft() {
		E key = keys.get(0);
		AbsNode<E> child = children.get(0);
		keys.remove(0);
		children.remove(0);
		return new Tuple2<E, AbsNode<E>>(key, child);
	}

	@Override
	Tuple2<E, AbsNode<E>> split() {
		Node<E> newNode = new Node<E>(deg);
		for (int i = 0; i < deg - 1; i++) {
			newNode.keys.add(keys.get(deg + i));
			newNode.children.add(children.get(deg + i));
		}
		newNode.children.add(children.get(deg * 2 - 1));

		children.remove(deg * 2 - 1);
		for (int i = 0; i < deg - 1; i++) {
			keys.remove(deg * 2 - 2 - i);
			children.remove(deg * 2 - 2 - i);
		}

		E center = keys.get(deg - 1);
		keys.remove(deg - 1);
		return new Tuple2<E, AbsNode<E>>(center, newNode);
	}

	@Override
	AbsNode<E> rootSplit() {
		Node<E> root = new Node<E>(deg);
		Node<E> lhs = new Node<E>(deg);
		Node<E> rhs = new Node<E>(deg);
		for (int i = 0; i < deg - 1; i++) {
			lhs.keys.add(keys.get(i));
			lhs.children.add(children.get(i));
			rhs.keys.add(keys.get(deg + i));
			rhs.children.add(children.get(deg + i));
		}
		lhs.children.add(children.get(deg - 1));
		rhs.children.add(children.get(deg * 2 - 1));
		root.keys.add(keys.get(deg - 1));
		root.children.add(lhs);
		root.children.add(rhs);
		return root;
	}

	@Override
	void pushForIterator(Stack<Object> stack) {
		for (int i = 0; i < keys.size(); i++) {
			stack.push(children.get(children.size() - 1 - i));
			stack.push(keys.get(keys.size() - 1 - i));
		}
		stack.push(children.get(0));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < keys.size(); i++) {
			sb.append(children.get(i).toString());
			sb.append(keys.get(i));
		}
		sb.append(children.get(keys.size()).toString());
		sb.append("]");
		return sb.toString();
	}

}
