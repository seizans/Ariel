package com.gmail.seizans.btree;

public final class Tuple2<E, F> {
	private E e;
	private F f;
	public Tuple2(E e, F f) {
		this.e = e;
		this.f = f;
	}
	public E fst() { return e; }
	public F snd() { return f; }
}
