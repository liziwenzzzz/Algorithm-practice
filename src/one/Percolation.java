package one;

import java.util.Arrays;

public class Percolation {

	private int n;
	private boolean[][] tables;
	private UnionFind uf;

	private int change(int i, int j) {
		return i * n + j;
	}

	public Percolation(int n) {
		this.n = n;
		tables = new boolean[n + 2][n + 2];
		uf = new UnionFind((n + 2) * (n + 2));
		Arrays.fill(tables[0], true);
		Arrays.fill(tables[n + 1], true);
		for (int j = 0; j < n + 2; j++) {
			uf.union(change(0, j), change(0, j + 1));
			uf.union(change(n + 1, j), change(n + 1, j + 1));
		}
	}

	public boolean isOpen(int i, int j) {
		if (i < 1 || i > n || j < 1 || j > n)
			throw new IndexOutOfBoundsException("i or j out of bound!");
		return tables[i][j];
	}

	// is the (i,j) full?
	public boolean isFull(int i, int j) {
		if (i < 1 || i > n || j < 1 || j > n)
			throw new IndexOutOfBoundsException("i or j out of bound!");
		return uf.connected(change(0, 0), change(i, j));
	}

	public void open(int i, int j) {
		if (i < 1 || i > n || j < 1 || j > n)
			throw new IndexOutOfBoundsException("i or j out of bound!");
		tables[i][j] = true;
		if (tables[i + 1][j])
			uf.union(change(i, j), change(i + 1, j));
		if (tables[i][j + 1])
			uf.union(change(i, j + 1), change(i, j));
		if (tables[i][j - 1])
			uf.union(change(i, j), change(i, j - 1));
		if (tables[i - 1][j])
			uf.union(change(i, j), change(i - 1, j));
	}

	public boolean percolates() {
		return uf.connected(change(0, 1), change(n + 1, 1));
	}

	public static void main(String[] args) {
		Percolation test = new Percolation(5);
		int i = 1;
		while (!test.percolates()) {
			test.open(i++, 2);
		}
		System.out.println("yes");
	}

}

class UnionFind {
	int[] father;

	public UnionFind(int n) {
		father = new int[n];
		for (int i = 0; i < n; i++) {
			father[i] = i;
		}
	}

	public void union(int i, int j) {
		int xi = find(i);
		int xj = find(j);
		father[xi] = xj;
	}

	public int find(int i) {
		while (i != father[i])
			i = father[i];
		return i;
	}

	public boolean connected(int i, int j) {
		return find(i) == find(j);
	}

}