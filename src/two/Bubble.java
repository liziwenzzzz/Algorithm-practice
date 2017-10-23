package two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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

	public static void main(String[] args) throws FileNotFoundException {
		int n=10;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(0,100);
		}
		show(a);
		sort(a);
		assert isSorted(a);
		show(a);
	}

}
