package one;

import java.util.HashSet;
import java.util.Set;

public class Percolation {

	int[][] tables;
	int count = 0;

	public Percolation(int N) {
		if (N <= 0)
			throw new IllegalArgumentException();
		tables = new int[N + 1][N + 1];
	}

	public void open(int i, int j) {
		if (i < 1 || i >= tables.length)
			throw new IndexOutOfBoundsException();
		if (j < 1 || i >= tables.length)
			throw new IndexOutOfBoundsException();
		if (isOpen(i, j))
			return;
		tables[i][j] = ++count;
	}

	public boolean isOpen(int i, int j) {
		return tables[i][j] != 0;
	}

	public boolean isFull(int i, int j) {
		return tables[i][j] == 0;
	}

	//这样太慢了，采用递归？
	public boolean percolates() {
		for (int i = 1; i < tables.length - 1; i++) {
			for (int j = 1; j < tables.length - 1; j++) {
				if (isOpen(i, j) && isOpen(i + 1, j))
					union(i, j, i + 1, j);
				if (isOpen(i, j) && isOpen(i, j + 1))
					union(i, j, i, j + 1);
			}
		}
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for (int i = 1; i < tables.length; i++) {
			if (isOpen(1, i)) {
				set1.add(tables[1][i]);
			}
			if (isOpen(tables.length - 1, i)) {
				set2.add(tables[tables.length - 1][i]);
			}
		}
		/*
		 * for(int i=1;i<tables.length;i++){ for(int j=1;j<tables.length;j++){
		 * System.out.print(find(i, j)); } System.out.println(); }
		 * System.out.println(count);
		 */
		set1.retainAll(set2);
		return set1.size() > 0;
	}

	private void union(int i, int j, int x, int y) {
		if (i < 1 || i >= tables.length)
			throw new IndexOutOfBoundsException();
		if (j < 1 || i >= tables.length)
			throw new IndexOutOfBoundsException();
		if (connected(i, j, x, y))
			return;
		if (i < x)
			tables[x][y] = find(i, j);
		else
			tables[i][j] = find(x, y);

		if (j < y)
			tables[x][y] = find(i, j);
		else
			tables[i][j] = find(x, y);
	}

	private int find(int i, int j) {
		return tables[i][j];
	}

	private boolean connected(int i, int j, int x, int y) {
		return find(i, j) == find(x, y);
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		Percolation test = new Percolation(10);
		System.out.println(test.percolates());
	}
}
