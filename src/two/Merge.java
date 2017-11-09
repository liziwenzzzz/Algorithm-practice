package two;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
public class Merge {

	private Merge() {
	}

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int left, int right) {

		if (right <= left)
			return;
		int mid = left + (right - left) / 2;
		sort(a, left, mid);
		sort(a, mid + 1, right);
		merge(a, left, mid, right);
	}

	// first put into aux,then to a
	private static void merge(Comparable[] a, int left, int mid, int right) {
		int n = right - left + 1;
		Comparable[] aux = new Comparable[n];
		int i = left, j = mid + 1, k = 0;
		while (k < n) { // only one was executed,if else if,
			if (i > mid)
				aux[k++] = a[j++];
			else if (j > right)
				aux[k++] = a[i++];
			else if (less(a[i], a[j]))
				aux[k++] = a[i++];
			else
				aux[k++] = a[j++];
		}
		for (int t = left; t <= right; t++) {
			a[t] = aux[t - left];
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
