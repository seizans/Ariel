package com.gmail.seizans.btree;

import java.util.ArrayList;

final class Node<E extends Comparable<E>> extends NodeCommon<E> implements INode<E> {

	Node(int deg) {
		this.deg = deg;
		this.keys = new ArrayList<E>(deg * 2 - 1);
		this.children = new ArrayList<INode<E>>(deg * 2);
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
		if (children.get(idx).isFull()) {
			children.get(idx).split();
			if ()
		}
		// TODO Auto-generated method stub
		
	}

	public void delete(E e) {
		// TODO Auto-generated method stub
		
	}

	public Tuple2<E, INode<E>> split() {
		// TODO Auto-generated method stub
		
	}

	public INode<E> rootSplit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			sb.append(children.get(i).toString());
			sb.append("[" + keys.get(i) + "]");
		}
		sb.append(children.get(keys.size()).toString());
		return sb.toString();
	}

}
