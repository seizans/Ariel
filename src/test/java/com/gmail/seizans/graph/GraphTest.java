package com.gmail.seizans.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gmail.seizans.util.Tuple3;

public class GraphTest {

	@Test
	public void totalTest1() {
		List<Tuple3<String, String, Integer>> sides = new ArrayList<Tuple3<String,String,Integer>>();
		sides.add(new Tuple3<String, String, Integer>("s", "t", 10));
		sides.add(new Tuple3<String, String, Integer>("s", "y", 5));
		sides.add(new Tuple3<String, String, Integer>("t", "x", 1));
		sides.add(new Tuple3<String, String, Integer>("t", "y", 2));
		sides.add(new Tuple3<String, String, Integer>("x", "z", 4));
		sides.add(new Tuple3<String, String, Integer>("y", "t", 3));
		sides.add(new Tuple3<String, String, Integer>("y", "x", 9));
		sides.add(new Tuple3<String, String, Integer>("y", "z", 2));
		sides.add(new Tuple3<String, String, Integer>("z", "s", 7));
		sides.add(new Tuple3<String, String, Integer>("z", "x", 6));
		Graph graph = new Graph(sides);
		List<Tuple3<String, List<String>, Integer>> dijkstra = graph.dijkstra();
		for (Tuple3<String, List<String>, Integer> tuple: dijkstra) {
			if (tuple.fst().equals("s")) {
				assertThat(tuple.thd(), is(0));
			} else if (tuple.fst().equals("t")) {
				assertThat(tuple.thd(), is(8));
			} else if (tuple.fst().equals("x")) {
				assertThat(tuple.thd(), is(9));
			} else if (tuple.fst().equals("y")) {
				assertThat(tuple.thd(), is(5));
			} else if (tuple.fst().equals("z")) {
				assertThat(tuple.thd(), is(7));
			}
		}
	}

}
