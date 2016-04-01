package com.routesearch.route;

public class Edge {
	private int cost;
	private int id;
	private int src;
	private int dest;
	
	public Edge(int id, int src, int dest, int cost) {
		this.id = id;
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public int getId() {
		return id;
	}

	public int getSrc() {
		return src;
	}

	public int getDest() {
		return dest;
	}
	
}
