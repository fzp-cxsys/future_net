/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

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
//		StringBuilder res = new StringBuilder("");
//		for (Vector<Edge> row : graph.getTopo()) {
//			for (Edge edge : row) {
//				if(edge != null){
//					res.append(edge.getCost());
//				}
//				res.append(",");
//			}
//			res.setCharAt(res.length() - 1, '\n');
//		}
//		return res.toString();
		Path[] shortest = graph.shortest(5);
//		StringBuffer buffer = new StringBuffer();
		int[] p = new int[shortest.length];
//		Path path = shortest[0];
		for(Path pa:shortest){
			p[pa.getStart()] = pa.getStart();
		}
		return null;
	}
}