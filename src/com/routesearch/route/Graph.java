package com.routesearch.route;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Graph {
	private Vector<Vector<Edge>> topo;
	private Set<Integer> demands;
	private int src;
	private int dest;
	
	public Graph(String topo, String demand) {
		this.topo = new Vector<Vector<Edge>>();
		this.demands = new HashSet<Integer>();
		dealTopo(topo);
		dealDemand(demand);
	}

	/**
	 * 处理拓扑结构字符串
	 * @param topo
	 */
	private void dealTopo(String topo) {
		// 按行切分
		String[] edges = topo.split("\n");
		for (String string : edges) {
			// 按行处理，每行对应一条边
			String[] splits = string.split(",");
			Edge edge = new Edge(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]),
					Integer.parseInt(splits[2]), Integer.parseInt(splits[3]));
			int newSize = 0;
			if (edge.getSrc() <= edge.getDest()) {
				newSize = edge.getDest() + 1;
			} else {
				newSize = edge.getSrc() + 1;
			}
			// 当顶点个数超出向量大小时要扩大向量
			if(this.topo.size() < newSize){
				// 1.扩大行数
				this.topo.setSize(newSize);
				for (int i = 0; i < this.topo.size(); i++) {
					Vector<Edge> row = this.topo.get(i);
					if(row == null){
						// 2.对于空行进行初始化
						row = new Vector<Edge>();
						this.topo.set(i, row);
					}
					// 3.扩大列数
					row.setSize(newSize);
				}
			}
			// 行号表示起始点
			Vector<Edge> row = this.topo.get(edge.getSrc());
			// 一行中的某列号示目的点
			row.set(edge.getDest(), edge);
		}
	}

	/**
	 * 处理问题需求字符串
	 * @param demand
	 */
	private void dealDemand(String demand) {
		String[] splits = demand.split(",");
		this.src = Integer.parseInt(splits[0]);
		this.dest = Integer.parseInt(splits[1]);
		for (String string : splits[2].substring(0, splits[2].length() - 1).split("\\|")) {
			this.demands.add(Integer.parseInt(string));
		}
	}

	public Vector<Vector<Edge>> getTopo() {
		return topo;
	}

	public Set<Integer> getDemands() {
		return demands;
	}

	public int getSrc() {
		return src;
	}

	public int getDest() {
		return dest;
	}
	
	public Path[] shortest(int startVertx){
		return null;
	}
}
