package com.gmail.seizans.btree;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

abstract class AbsNode<E extends Comparable<E>> {
	int deg;
	List<E> keys;
	List<AbsNode<E>> children;

	int childIndexOf(E e) {
		for (int i = 0; i < keys.size(); i++) {
			if (e.compareTo(keys.get(i)) < 0) {
				return i;
			}
		}
		return keys.size();
	}

	int size() {
		return keys.size();
	}

	boolean isFull() {
		return keys.size() == deg * 2 - 1;
	}

	boolean isEmpty() {
		return keys.isEmpty();
	}

	Iterator<E> iterator() {
		return new SortedIterator();
	}

	abstract boolean search(E e);
	abstract void insert(E e);
	abstract void delete(E e);
	
	abstract Tuple2<E, AbsNode<E>> split();
	abstract E maxKey();
	abstract E minKey();
	abstract void addRight(E key, AbsNode<E> right);
	abstract void pushRight(E key, AbsNode<E> right);
	abstract void pushLeft(E key, AbsNode<E> left);
	abstract Tuple2<E, AbsNode<E>> popRight();
	abstract Tuple2<E, AbsNode<E>> popLeft();

	/*
	 * 前提条件：isFull()、自身がrootであること
	 * 自身を二分して、中央のkeyのみのnodeを新たなrootとして返す
	 */
	abstract AbsNode<E> rootSplit();

	abstract void pushForIterator(Stack<Object> stack);
	private class SortedIterator implements Iterator<E> {
		private final Stack<Object> stack = new Stack<Object>();

		private SortedIterator() {
			if (!isEmpty()) {
				stack.push(AbsNode.this);
			}
		}

		public boolean hasNext() {
			return !stack.empty();
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Object obj = stack.pop();
			if (obj instanceof Comparable<?>) {
				E next = (E)obj;
				return next;
			} else if (obj instanceof AbsNode<?>) {
				AbsNode<E> node = (AbsNode<E>)obj;
				node.pushForIterator(stack);
				return next();
			} else {
				throw new IllegalStateException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

}
