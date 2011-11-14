package com.gmail.seizans.btree;

import java.util.ArrayList;

final class Node<E extends Comparable<E>> extends NodeCommon<E> implements INode<E> {

	Node(int deg) {
		this.deg = deg;
		this.keys = new ArrayList<E>(deg * 2 - 1);
		this.children = new ArrayList<INode<E>>(deg * 2);
	}

	public boolean search(E e) {
		if (keys.indexOf(e) != -1) {
			return true;
		}
		return children.get(childIndexOf(e)).search(e);
	}

	public void insert(E e) {
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
		Tuple2<E, INode<E>> tuple = children.get(index).split();
		keys.add(index, tuple.fst());
		children.add(index + 1, tuple.snd());
	}

	public void delete(E e) {
		// TODO Auto-generated method stub
		
	}

	public Tuple2<E, INode<E>> split() {
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
		return new Tuple2<E, INode<E>>(center, newNode);
	}

	public INode<E> rootSplit() {
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
