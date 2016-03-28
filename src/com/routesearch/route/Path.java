package com.routesearch.route;

import java.util.LinkedList;
import java.util.List;

public class Path {
	private Graph graph;
	private int start;
	private int end;
	private int lenth;
	private List<Integer> route;
	
	public Path(Graph graph, int start, int end) {
		this.graph = graph;
		this.start = start;
		this.end = end;
		this.lenth = 0;
		this.route = new LinkedList<Integer>();
	}
	
	public Graph getGraph() {
		return graph;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getLenth() {
		return lenth;
	}
	public void setLenth(int lenth) {
		this.lenth = lenth;
	}
	public List<Integer> getRoute() {
		return route;
	}
	public void setRoute(List<Integer> route) {
		this.route = route;
	}
}
