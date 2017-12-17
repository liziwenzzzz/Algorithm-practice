package three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

class City {
	int x;
	int y;

	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Road {
	int another;
	double dist;

	public Road(int another, double dist) {
		this.another = another;
		this.dist = dist;
	}

	public String toString() {
		return another + " " + dist;
	}
}

class Transit implements Comparable<Transit> {
	int city;
	double dist;

	public Transit(int city, double d) {
		this.city = city;
		this.dist = d;
	}

	@Override
	public int compareTo(Transit o) {
		if (this.dist > o.dist)
			return 1;
		else if (this.dist < o.dist)
			return -1;
		return 0;
	}
}

public class MapRouting {
	static final int MAXNUM = 100000;
	int V, E;
	City[] city;
	ArrayList<Road>[] road;
	double distTo[];
	int edgeTo[];

	@SuppressWarnings("unchecked")
	public MapRouting(String path) throws FileNotFoundException {
		Scanner in = new Scanner(new File(path));
		this.V = in.nextInt();
		this.E = in.nextInt();
		city = new City[this.V];
		road = (ArrayList<Road>[]) new ArrayList[this.E];
		for (int i = 0; i < this.V; i++) {
			int t = in.nextInt();
			city[t] = new City(in.nextInt(), in.nextInt());
		}
		for (int i = 0; i < this.E && in.hasNextInt(); i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			Road t = new Road(b, distance(a, b));
			Road s = new Road(a, distance(a, b));
			if (road[a] == null)
				road[a] = new ArrayList<>();
			road[a].add(t);
			if (road[b] == null)
				road[b] = new ArrayList<>();
			road[b].add(s);
		}
		in.close();
	}

	private double distance(int x, int y) {
		int xx = city[x].x - city[y].x;
		int yy = city[x].y - city[y].y;
		return Math.sqrt(xx * xx + yy * yy);
	}

	public void printPath(int start, int a) {
		System.out.println("the distance of path is:" + distTo[a]);
		Stack<Integer> path = new Stack<>();
		int v = a;
		for (; v != start; v = edgeTo[v]) {
			path.push(v);
		}
		path.push(start);
		System.out.print("the path is:");
		while (!path.isEmpty()) {
			System.out.print(path.pop() + " ");
		}
		System.out.println();
	}

	public void Dijkstra(int src, int dest) {
		distTo = new double[this.V];
		edgeTo = new int[this.V];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		Arrays.fill(edgeTo, -1);
		distTo[src] = 0.0;
		PriorityQueue<Transit> pq = new PriorityQueue<>();
		pq.offer(new Transit(src, 0.0));
		while (!pq.isEmpty()) {
			Transit u = pq.poll();
			if (u.city == dest)
				break;
			// if (u.dist > distTo[u.city]) {
			// continue;
			// }
			for (Road adj : road[u.city]) {
				// System.out.println(adj.another + " " + adj.dist);
				if (distTo[u.city] + adj.dist < distTo[adj.another]) {
					distTo[adj.another] = distTo[u.city] + adj.dist;
					edgeTo[adj.another] = u.city;
					pq.offer(new Transit(adj.another, distTo[adj.another]));
				}
			}
		}
		return;
	}

	public static void main(String[] args) throws FileNotFoundException {
		MapRouting test = new MapRouting("D:/Eclipse/Algorithm-practice/src/three/usa.txt");
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("input the src and dest:");
			int a = in.nextInt(), b = in.nextInt();
			test.Dijkstra(a, b);
			test.printPath(a, b);
			System.out.println();
			// System.out.println("0-1-2-5:" + (test.distance(0, 1) +
			// test.distance(1, 2) + test.distance(2, 5)));
			// System.out.println("0-3-5:" + (test.distance(0, 3) +
			// test.distance(3, 5)));
			// System.out.println("0-1-4-5:" + (test.distance(0, 1) +
			// test.distance(1, 4) + test.distance(4, 5)));
			// System.out.println("0-1-2-3-5:"
			// + (test.distance(0, 1) + test.distance(1, 2) + test.distance(2,
			// 3) + test.distance(3, 5)));
		}
	}
}
