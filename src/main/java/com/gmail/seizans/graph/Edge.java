package com.gmail.seizans.graph;

public final class Edge {
	private final int dist;
	private final String to;

	public Edge(int dist, String to) {
		this.dist = dist;
		this.to = to;
	}

	public int getDist() { return dist; }
	public String getTo() { return to; }
}
