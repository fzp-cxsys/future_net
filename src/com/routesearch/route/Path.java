package com.routesearch.route;

public class Path {
	private Graph graph;
	private int prevex;
	private int end;
	private int lenth;

	
	public Path(Graph graph, int prevex, int end,int length) {
		this.graph = graph;
		this.prevex = prevex;
		this.end = end;
		this.lenth = length;
	}
	
	public Graph getGraph() {
		return graph;
	}
	public int getPrevex() {
		return prevex;
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
	public void setPrevex(int prevex){
		this.prevex = prevex;
	}
	public void setEnd(int end){
		this.end = end;
	}


}
