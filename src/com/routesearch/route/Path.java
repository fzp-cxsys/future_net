package com.routesearch.route;

import java.util.LinkedList;
import java.util.List;

public class Path {
	private Graph graph;
	private int start;
	private int end;
	private int lenth;

	
	public Path(Graph graph, int start, int end,int length) {
		this.graph = graph;
		this.start = start;
		this.end = end;
		this.lenth = length;
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
	public void setStart(int start){
		this.start = start;
	}
	public void setEnd(int end){
		this.end = end;
	}


}
