package two;

import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;

public class Quick {
	private Quick() {
	}

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int p = pritition(a, lo, hi);
		sort(a, lo, p - 1);
		sort(a, p + 1, hi);
	}

	private static int pritition(Comparable[] a, int lo, int hi) {
		// random between lo and hi,can't be between 0 and length
		exch(a, lo, ThreadLocalRandom.current().nextInt(lo, hi + 1));
		int i = lo, j = hi + 1;
		while (true) {
			while (i < hi && less(a[++i], a[lo]))
				;
			while (j > lo && less(a[lo], a[--j]))
				;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		int n = 10;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(0, 100);
		}
		show(a);
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
