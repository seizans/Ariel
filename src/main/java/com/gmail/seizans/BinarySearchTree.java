package com.gmail.seizans;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>, V> {
	private List<KeyValue> list;

	public BinarySearchTree(K key, V value) {
		list = new ArrayList<KeyValue>();
		list.add(new KeyValue(key, value));
	}

	public V search(K key) {
		return null;
	}

	public void delete(K key) {
		
	}

	public void insert(K key, V value) {
		
	}

	private final class KeyValue {
		private K key;
		private V value;
		KeyValue(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() { return key; }
		public V getValue() { return value; }
	}
}
