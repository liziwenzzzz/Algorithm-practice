package two;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
public class Bubble {

	private Bubble() {
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		boolean flag = false; // order?
		while (!flag) {
			flag = true;
			for (int i = 0; i < n - 1; i++) {
				if (less(a[i + 1], a[i])) {
					exch(a, i, i + 1);
					flag = false;
				}
			}
		}
	}

	public static void exch(Comparable[] a, int i, int j) {
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
		int n = 100000;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(0, 100000);
		}
		// show(a);

		long start = System.currentTimeMillis();
		sort(a);
		long end = System.currentTimeMillis();

		assert isSorted(a);
		// show(a);
		System.out.println("use time:" + (end - start) * 1000.0 + " us");
		System.out.println("use memory:"
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 + " kilo bytes");
	}

}
