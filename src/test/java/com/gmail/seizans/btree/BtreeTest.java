package com.gmail.seizans.btree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BtreeTest {

	@Test
	public void insertTest() {
		final int N = 50;
		Btree<Integer> btree = new Btree<Integer>(3);
		for (int i = 0; i < N; i++) {
			btree.insert(i);
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
