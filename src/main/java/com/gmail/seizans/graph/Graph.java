package com.gmail.seizans.graph;

import java.util.ArrayList;
import java.util.List;

import com.gmail.seizans.util.Tuple3;

public final class Graph {
	final private List<Tuple3<String, String, Integer>> sides;

	public Graph(List<Tuple3<String, String, Integer>> sides) {
		this.sides = new ArrayList<Tuple3<String,String,Integer>>(sides);
	}

	public List<Tuple3<String, List<String>, Integer>> dijkstra() {
		return null;
	}

}
