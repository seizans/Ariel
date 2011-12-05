package com.gmail.seizans.util;

public final class Tuple3<E, F, G> {
	private E e;
	private F f;
	private G g;
	public Tuple3(E e, F f, G g) {
		this.e = e;
		this.f = f;
		this.g = g;
	}
	public E fst() { return e; }
	public F snd() { return f; }
	public G thd() { return g; }
}
