/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public final class Route {
	/**
	 * 你需要完成功能的入口
	 * 
	 * @author XXX
	 * @since 2016-3-4
	 * @version V1
	 */
	public static String searchRoute(String graphContent, String condition) {
		Graph graph = new Graph(graphContent, condition);
		StringBuilder res = new StringBuilder("");
//		产生图的拓扑结构
//		for (Vector<Edge> row : graph.getTopo()) {
//			for (Edge edge : row) {
//				if(edge != null){
//					res.append(edge.getCost());
//				}else{
//					res.append(-1);
//				}
//				res.append(",");
//			}
//			res.setCharAt(res.length() - 1, '\n');
//		}
		
//		产生点v的Dijkstra的算法结果
		int v = 3;
		Path[] paths = graph.shortest(v);
		for (int i = 0; i < paths.length; i++) {
			res.append('(').append(paths[i].getLenth()).append(' ').append(paths[i].getPrevex()).append("),");
		}
		res.deleteCharAt(res.length() - 1);
		
		// 计算结果并生成结果字符串
//		Map<Integer, Path[]> paths = init(graph);
//		List<Integer> sres = search(paths, graph);
//		for (int i = 0; i < sres.size() - 1; i++) {
//			res.append(graph.getTopo().get(sres.get(i)).get(sres.get(i + 1)).getId());
//			res.append('|');
//		}
//		res.deleteCharAt(res.length() - 1);
		
		return res.toString();
	}
	
	/**
	 * 初始化操作，求出必需的最短路
	 * @param graph
	 * @return
	 */
	public static Map<Integer, Path[]> init(Graph graph){
		HashMap<Integer, Path[]> res = new HashMap<Integer, Path[]>();
		res.put(graph.getSrc(), graph.shortest(graph.getSrc()));
		for (Integer vex : graph.getDemands()) {
			res.put(vex, graph.shortest(vex));
		}
		return res;
	}
	
	public static List<Integer> search(Map<Integer, Path[]> paths, Graph graph){
		// 初始化递归迭代的参数们
		List<Integer> data = new LinkedList<Integer>(graph.getDemands());
		List<Integer> res = new LinkedList<Integer>();
		Integer[] realres = new Integer[graph.getDemands().size()];
		PermParam param = new PermParam(data, res, 0, Integer.MAX_VALUE, graph.getTopo().size());
		
		perm(graph.getSrc(), graph.getDest(), realres, paths, param);
		// 将求出的必需点序列转换为路径点序列
		res = new LinkedList<Integer>();
		res.add(graph.getSrc());
		for(int vex = realres[0]; vex != graph.getSrc(); ){
			int prevex = paths.get(graph.getSrc())[vex].getPrevex();
			res.add(1, vex);
			vex = prevex;
		}
		for (int i = 1; i < realres.length; i++) {
			int index = res.size();
			int pre = res.get(index - 1);
			for(int vex = realres[i]; vex != pre; ){
				int prevex = paths.get(realres[i-1])[vex].getPrevex();
				res.add(index, vex);
				vex = prevex;
			}
		}
		int index = res.size();
		int pre = res.get(index - 1);
		for(int vex = graph.getDest(); vex != pre; ){
			int prevex = paths.get(realres[realres.length - 1])[vex].getPrevex();
			res.add(index, vex);
			vex = prevex;
		}
		
		return res;
	}
	
	/**
	 * 用来遍历解空间的递归算法
	 * @param src 路径起点
	 * @param dest 路径终点
	 * @param realres 最终结果
	 * @param paths 存储最短路的map
	 * @param param 递归迭代过程中需要使用并修改的参数
	 */
	private static void perm(final int src, final int dest, Integer[] realres, final Map<Integer, Path[]> paths, PermParam param) {
		List<Integer> data = param.getData();
		List<Integer> res = param.getRes();
		if(res.size() > 0){//结果序列不为空时，进行以下判断
			// 获取起点到结果路径的起始点的距离
			int sdis = paths.get(src)[res.get(0)].getLenth();
			// 如果当前路径长度已经小于已有最短路径则返回
			if (param.getLength() + sdis >= param.getMinl()){
				return;
			}
			/**
			 * 如果原始数据已空
			 * 说明当前结果路径已经满足要求
			 */
			if (data.size() == 0) {
				int ddis = paths.get(res.get(res.size() - 1))[dest].getLenth();
				// 如果最终路径的长度仍小于已有最短路径则返回
				Integer totalLength = param.getLength() + sdis + ddis;
				if(totalLength >= param.getMinl()){
					return;
				}
				// 将满足要求且小于已有最短路径的长度替换最短路径
				param.setMinl(totalLength);
				realres = res.toArray(realres);
				return;
			}
		}
		int l = 0;	// 用来记录新增节点的路径长度增长
		// 递归过程
		for (int i = 0; i < data.size(); i++){
			Integer obj = data.remove(i);
			if(res.size() > 0){
				Path[] path = paths.get(res.get(res.size() - 1));
//				// 判断新增节点是否会产生环路
//				boolean hasCircle = false;
//				for(int vex = obj; vex != res.get(res.size() - 1); path[vex].getPrevex()){
//					if(param.getIsPassed()[vex]){
//						hasCircle = true;
//					}
//				}
//				if(hasCircle){
//					continue;
//				}
				// 新增节点的路径长度增量
				l = path[obj].getLenth();
			}
			res.add(obj);
			param.setLength(param.getLength() + l);
			perm(src, dest, realres, paths, param);
			data.add(i, obj);
			res.remove(res.size() - 1);
			param.setLength(param.getLength() - l);
		}
	}
	
	/**
	 * 用来封装递归迭代的参数
	 */
	private static final class PermParam{
		private List<Integer> data;
		private List<Integer> res;
		private int length;
		private int minl;
		private boolean[] isPassed;
		
		public PermParam(List<Integer> data, List<Integer> res, int length, int minl, int vexNum) {
			this.data = data;
			this.res = res;
			this.length = length;
			this.minl = minl;
			isPassed = new boolean[vexNum];
		}
		
		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public int getMinl() {
			return minl;
		}

		public void setMinl(int minl) {
			this.minl = minl;
		}

		public List<Integer> getData() {
			return data;
		}

		public List<Integer> getRes() {
			return res;
		}

		public boolean[] getIsPassed() {
			return isPassed;
		}
	}
}