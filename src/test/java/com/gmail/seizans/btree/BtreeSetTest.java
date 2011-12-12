package com.gmail.seizans.btree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BtreeSetTest {

	@Test
	public void totalTest3() {
		try {
			new BtreeSet<Integer>(0);
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			new BtreeSet<Integer>(1);
			fail();
		} catch (IllegalArgumentException e) {
		}

		for (int i = 2; i < 10; i++) {
			totalTestTemplate(i);
		}
	}

	private void totalTestTemplate(int deg) {
		BtreeSet<Integer> set = new BtreeSet<Integer>(deg);
		for (int i = 60; i < 80; i++) {
			set.insert(i);
		}
		for (int i = 20; i < 40; i++) {
			set.insert(i);
		}
		for (int i = 0; i < 20; i++) {
			set.insert(i);
		}
		for (int i = 80; i < 100; i++) {
			set.insert(i);
		}
		for (int i = 40; i < 60; i++) {
			set.insert(i);
		}

		Iterator<Integer> iter = set.iterator();
		for (int i = 0; i < 100; i++) {
			assertThat(iter.hasNext(), is(true));
			assertThat(iter.next(), is(i));
		}
		assertThat(iter.hasNext(), is(false));
		try {
			iter.next();
			fail();
		} catch(NoSuchElementException e) {
		}

		assertThat(set.search(-1), is(false));
		assertThat(set.search(100), is(false));
		for (int i = 0; i < 100; i++) {
			assertThat(set.search(i), is(true));
		}

		try {
			set.insert(10);
			fail();
		} catch(RuntimeException e) {
		}

		for (int i = 20; i < 40; i++) {
			set.delete(i);
		}
		for (int i = 60; i < 80; i++) {
			set.delete(i);
		}
		for (int i = 80; i < 100; i++) {
			set.delete(i);
		}
		for (int i = 40; i < 60; i++) {
			set.delete(i);
		}
		for (int i = 0; i < 20; i++) {
			set.delete(i);
		}

		for (int i = 0; i < 100; i++) {
			assertThat(set.search(i), is(false));
		}

		Iterator<Integer> iter2 = set.iterator();
		assertThat(iter2.hasNext(), is(false));
	}

	@Test
	public void totalTest2() {
		BtreeSet<String> set = new BtreeSet<String>(3);
		set.insert("a");
		set.insert("c");
		set.insert("g");
		set.insert("j");
		set.insert("k");
		set.insert("d");
		set.insert("e");
		set.insert("m");
		set.insert("n");
		set.insert("o");
		set.insert("p");
		set.insert("r");
		set.insert("s");
		set.insert("x");
		set.insert("y");
		set.insert("z");
		set.insert("t");
		set.insert("u");
		set.insert("v");
		assertThat(set.toString(), is("[(a)(c)(d)(e)g(j)(k)m(n)(o)p(r)(s)(t)(u)(v)x(y)(z)]"));
		set.insert("b");
		assertThat(set.toString(), is("[(a)(b)(c)(d)(e)g(j)(k)m(n)(o)p(r)(s)(t)(u)(v)x(y)(z)]"));
		set.insert("q");
		assertThat(set.toString(), is("[(a)(b)(c)(d)(e)g(j)(k)m(n)(o)p(q)(r)(s)t(u)(v)x(y)(z)]"));
		set.insert("l");
		assertThat(set.toString(), is("[[(a)(b)(c)(d)(e)g(j)(k)(l)m(n)(o)]p[(q)(r)(s)t(u)(v)x(y)(z)]]"));
		set.insert("f");
		assertThat(set.toString(), is("[[(a)(b)c(d)(e)(f)g(j)(k)(l)m(n)(o)]p[(q)(r)(s)t(u)(v)x(y)(z)]]"));
		set.delete("f");
		assertThat(set.toString(), is("[[(a)(b)c(d)(e)g(j)(k)(l)m(n)(o)]p[(q)(r)(s)t(u)(v)x(y)(z)]]"));
		set.delete("m");
		assertThat(set.toString(), is("[[(a)(b)c(d)(e)g(j)(k)l(n)(o)]p[(q)(r)(s)t(u)(v)x(y)(z)]]"));
		set.delete("g");
		assertThat(set.toString(), is("[[(a)(b)c(d)(e)(j)(k)l(n)(o)]p[(q)(r)(s)t(u)(v)x(y)(z)]]"));
		set.delete("d");
		assertThat(set.toString(), is("[(a)(b)c(e)(j)(k)l(n)(o)p(q)(r)(s)t(u)(v)x(y)(z)]"));
		set.delete("b");
		assertThat(set.toString(), is("[(a)(c)e(j)(k)l(n)(o)p(q)(r)(s)t(u)(v)x(y)(z)]"));
	}

	@Test
	public void totalTest1() {
		final int N = 50;
		BtreeSet<Integer> set = new BtreeSet<Integer>(2);
		for (int i = 0; i < N; i++) {
			Integer inte = Integer.valueOf(i);
			set.insert(inte);
		}

		assertThat(set.search(-1), is(false));
		for (int i = 0; i < N; i++) {
			assertThat(set.search(i), is(true));
		}
		assertThat(set.search(N), is(false));
	}

//	@Test
//	public void test1() {
//		BtreeSet<Integer> set = new BtreeSet<Integer>(2);
//		set.insert(6);
//		System.out.println(set);
//		set.insert(10);
//		System.out.println(set);
//		set.insert(7);
//		System.out.println(set);
//		set.insert(4);
//		System.out.println(set);
//		set.insert(1);
//		System.out.println(set);
//		set.insert(9);
//		System.out.println(set);
//		set.insert(3);
//		System.out.println(set);
//		set.insert(5);
//		System.out.println(set);
//		set.insert(8);
//		System.out.println(set);
//		set.insert(2);
//		System.out.println(set);
//	}

}
