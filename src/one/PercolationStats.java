package one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PercolationStats {

	List<Double> list = new ArrayList<>();

	public PercolationStats(int n, int t) {
		while (t-- > 0) {
			int count = 0;
			Percolation pp = new Percolation(n);
			while (!pp.percolates()) {
				int x = ThreadLocalRandom.current().nextInt(1, n + 1);
				int y = ThreadLocalRandom.current().nextInt(1, n + 1);
				if (pp.isOpen(x, y)) // ±‹√‚÷ÿ∏¥µƒµ„
					continue;
				pp.open(x, y);
				count++;
			}
			list.add(count * 1.0 / (n * n));
		}
	}

	public double mean() {
		double sum = 0.0;
		for (Double x : list) {
			sum += x;
		}
		return sum / list.size();
	}

	public double stddev() {
		double mean = mean();
		double sum = 0.0;
		for (Double x : list) {
			sum += (x - mean) * (x - mean);
		}
		return Math.sqrt(sum / (list.size() - 1));
	}

	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(list.size());
	}

	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(list.size());
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int t = input.nextInt();

		long start = System.currentTimeMillis();

		PercolationStats ps = new PercolationStats(n, t);
		System.out.println(ps.mean());
		System.out.println(ps.stddev());
		System.out.println(ps.confidenceLo());
		System.out.println(ps.confidenceHi());

		long end = System.currentTimeMillis();
		System.out.println("use time:" + (end - start) / 1000.0 + "s");
	}
}
