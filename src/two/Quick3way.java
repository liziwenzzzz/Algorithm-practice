package two;

import java.util.concurrent.ThreadLocalRandom;

public class Quick3way {
	private Quick3way() {
	}

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi)
			return;

		exch(a, lo, ThreadLocalRandom.current().nextInt(lo, hi + 1));

		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp > 0)
				exch(a, gt--, i);
			else if (cmp < 0)
				exch(a, i++, lt++);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
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

	public static void main(String[] args) {
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
