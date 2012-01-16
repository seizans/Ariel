package com.gmail.seizans.bstree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BtreeBsTreeTest {

	@Test
	public void totalTest1() {
		final int N = 50;
		BtreeBsTree<Integer> set = new BtreeBsTree<Integer>();
		for (int i = 0; i < N; i++) {
			Integer inte = Integer.valueOf(i);
			set.add(inte);
		}

		assertThat(set.contains(-1), is(false));
		for (int i = 0; i < N; i++) {
			assertThat(set.contains(i), is(true));
		}
		assertThat(set.contains(N), is(false));

		assertThat(set.getMax(), is(49));
		assertThat(set.getMin(), is(0));
	}

}
