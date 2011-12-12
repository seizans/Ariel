package com.gmail.seizans.util;


public final class Tuple<E, F> {
	private E e;
	private F f;
	public Tuple(E e, F f) {
		this.e = e;
		this.f = f;
	}
	public E fst() { return e; }
	public F snd() { return f; }
}
