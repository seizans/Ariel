package com.gmail.seizans.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.gmail.seizans.util.Tuple;
import com.gmail.seizans.util.Tuple3;

public final class Graph {
	final private List<Tuple3<String, String, Integer>> sides;

	public Graph(List<Tuple3<String, String, Integer>> sides) {
		this.sides = new ArrayList<Tuple3<String,String,Integer>>(sides);
	}

	public List<Tuple3<String, List<String>, Integer>> dijkstra() {
		List<String> vertexes = vertexes();
		Map<String, Tuple3<List<String>, Integer, Color>> map = init(vertexes);
		
		PriorityQueue<Tuple<String, Integer>> pq = new PriorityQueue<Tuple<String,Integer>>(11, new TempComparator());
		for (Iterator<Entry<String, Tuple3<List<String>, Integer, Color>>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, Tuple3<List<String>, Integer, Color>> entry = it.next();
			Tuple<String, Integer> e = new Tuple<String, Integer>(entry.getKey(), entry.getValue().snd());
			pq.add(e);
		}

		while (! pq.isEmpty()) {
			
		}
		List<Tuple3<String, List<String>, Integer>> dijkstra = new ArrayList<Tuple3<String,List<String>,Integer>>();
		return dijkstra;
	}

	private List<String> vertexes() {
		List<String> list = new ArrayList<String>();
		for (Tuple3<String, String, Integer> tuple: sides) {
			if (! list.contains(tuple.fst())) {
				list.add(tuple.fst());
			}
			if (! list.contains(tuple.snd())) {
				list.add(tuple.snd());
			}
		}
		return list;
	}

	private Map<String, Tuple3<List<String>, Integer, Color>> init(List<String> vertexes) {
		Map<String, Tuple3<List<String>, Integer, Color>> map = new HashMap<String, Tuple3<List<String>,Integer,Color>>();
		for (String str: vertexes) {
			Tuple3<List<String>, Integer, Color> value = new Tuple3<List<String>, Integer, Graph.Color>(null, Integer.MAX_VALUE, Color.WHITE);
			map.put(str, value);
		}
		return map;
	}

	private void relax(Map<String, Tuple3<List<String>, Integer, Color>> map, String u, String v) {
		for (Tuple3<String, String, Integer> tuple: sides) {
			if (tuple.fst().equals(u) && tuple.snd().equals(v)) {
				if (map.get(v).snd() > map.get(u).snd() + tuple.thd()) {
					List<String> e = new ArrayList<String>(map.get(u).fst());
					e.add(u);
					Tuple3<List<String>, Integer, Color> value = new Tuple3<List<String>, Integer, Graph.Color>(e, map.get(u).snd() + tuple.thd(), Color.GRAY);
					map.put(v, value);
				}
			}
		}
	}

	private static class TempComparator implements Comparator<Tuple<String, Integer>> {
		public int compare(Tuple<String, Integer> o1, Tuple<String, Integer> o2) {
			return o2.snd() - o1.snd();
		}
	}

	private static enum Color {
		WHITE, GRAY, BLACK;
	}
}
