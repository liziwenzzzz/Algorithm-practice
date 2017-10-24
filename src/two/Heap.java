package two;

import java.util.concurrent.ThreadLocalRandom;

public class Heap {
	// 先建立最大堆，然后堆排序，升序
	private Heap() {
	}

	public static void sort(Comparable[] a) {
		int m = a.length;
		Comparable[] aux = new Comparable[m + 1];
		int n = aux.length - 1; // [1 - n]
		for (int i = 0; i < m; i++) {
			aux[i + 1] = a[i];
		}

		for (int i = n / 2; i >= 1; i--) {
			sink(aux, i, n);
		}
		while (n >1) {
			exch(aux, 1, n--);
			sink(aux, 1, n);
		}

		for (int i = 0; i < m; i++) {
			a[i] = aux[i + 1];
		}
	}

	// sink operation.i in [1-n]
	private static void sink(Comparable[] a, int i, int n) {
		while (2 * i <= n) {
			int j = i * 2;
			if (j < n && less(a[j], a[j + 1]))
				j++;
			if (less(a[j], a[i]))
				break;
			exch(a, i, j);
			i =j;          //attention:j has change
		}
	} 

	// swin i in [1-i]
	private static void swim(Comparable[] a, int i) {
		while (i > 1 && less(a[i], a[i / 2])) {
			exch(a, i, i / 2);
			i /= 2;
		}
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) {
		for (Comparable x : a) {
			System.out.print(x + "  ");
		}
		System.out.println();
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
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
