package com.gmail.seizans.btree;

interface INode<E extends Comparable<E>> {
	boolean search(E e);
	void insert(E e);
	void delete(E e);
	
	Tuple2<E, INode<E>> split();

	/*
	 * 前提条件：isFull()、自身がrootであること
	 * 自身を二分して、中央のkeyのみのnodeを新たなrootとして返す
	 */
	INode<E> rootSplit();
	boolean isFull();
}
