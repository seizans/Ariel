package com.gmail.seizans.btree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BtreeTest {
	
	@Test
	public void insertTest3() {
		Btree<Integer> btree = new Btree<Integer>(2);
		btree.insert(4);
		btree.insert(2);
		btree.insert(10);
		btree.insert(8);
		btree.insert(3);
		btree.insert(9);
		btree.insert(1);
		btree.insert(5);
	}

	@Test
	public void insertTest2() {
		Btree<Integer> btree = new Btree<Integer>(2);
		for (int i = 100; i > 50; i--) {
			Integer in = Integer.valueOf(i);
			btree.insert(in);
		}
		System.out.println(btree);
		for (Integer i : btree) {
			System.out.println(i);
		}
	}

	@Test
	public void zeroTest() {
		Btree<Integer> btree = new Btree<Integer>(4);
		for (Integer i: btree) {
			System.out.println(i);
		}
		btree.insert(0);
		btree.insert(1);
		btree.insert(2);
		btree.insert(3);
		btree.insert(4);
		btree.insert(5);
		btree.insert(6);
		btree.insert(7);
		btree.insert(8);
		for (Integer i: btree) {
			System.out.println(i);
		}
	}

	@Test
	public void insertTest() {
		final int N = 50;
		Btree<Integer> btree = new Btree<Integer>(2);
		for (int i = 0; i < N; i++) {
			btree.insert(i);
		}

		for (Integer i: btree) {
			if (!btree.search(i)) {
				System.out.println(i);
				System.out.println(btree);
			}
			assertThat(btree.search(i), is(true));
		}

		Iterator<Integer> iter = btree.iterator();
		for (int i = 0; i < N; i++) {
			assertThat(iter.hasNext(), is(true));
			assertThat(iter.next(), is(i));
		}
		assertThat(iter.hasNext(), is(false));
		try {
			iter.next();
			fail();
		} catch (NoSuchElementException e) {
		}

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
