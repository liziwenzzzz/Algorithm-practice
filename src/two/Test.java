package two;

import java.util.concurrent.ThreadLocalRandom;

public class Test {

	public static void randomTest(String type) {
		int n = 100000;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(0, n);
		}
		// show(a);

		long start = System.currentTimeMillis();

		if (type.compareTo("Insertion") == 0)
			Insertion.sort(a);
		else if (type.compareTo("Merge") == 0)
			Merge.sort(a);
		else if (type.compareTo("MergeBU") == 0)
			MergeBU.sort(a);
		else if (type.compareTo("Quick") == 0)
			Quick.sort(a);
		else if (type.compareTo("Quick3way") == 0)
			Quick3way.sort(a);
		long end = System.currentTimeMillis();

		assert Insertion.isSorted(a);
		// show(a);
		System.out.println("use time:" + (end - start) * 1000.0 + " us");
		System.out.println("use memory:"
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 + " kilo bytes");
	}

	public static void ascTest(String type) {
		int n = 100000;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
		// show(a);

		long start = System.currentTimeMillis();

		if (type.compareTo("Insertion") == 0)
			Insertion.sort(a);
		else if (type.compareTo("Merge") == 0)
			Merge.sort(a);
		else if (type.compareTo("MergeBU") == 0)
			MergeBU.sort(a);
		else if (type.compareTo("Quick") == 0)
			Quick.sort(a);
		else if (type.compareTo("Quick3way") == 0)
			Quick3way.sort(a);
		long end = System.currentTimeMillis();

		assert Insertion.isSorted(a);
		// show(a);
		System.out.println("use time:" + (end - start) * 1000.0 + " us");
		System.out.println("use memory:"
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 + " kilo bytes");
	}

	public static void desTest(String type) {
		int n = 100000;
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = n - i;
		}
		// show(a);

		long start = System.currentTimeMillis();

		if (type.compareTo("Insertion") == 0)
			Insertion.sort(a);
		else if (type.compareTo("Merge") == 0)
			Merge.sort(a);
		else if (type.compareTo("MergeBU") == 0)
			MergeBU.sort(a);
		else if (type.compareTo("Quick") == 0)
			Quick.sort(a);
		else if (type.compareTo("Quick3way") == 0)
			Quick3way.sort(a);
		long end = System.currentTimeMillis();

		assert Insertion.isSorted(a);
		// show(a);
		System.out.println("use time:" + (end - start) * 1000.0 + " us");
		System.out.println("use memory:"
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 + " kilo bytes");
	}

	public static void main(String[] args) {
		System.out.println("sort 100000 random number:");
		System.out.println("Insersion result:");
		randomTest("Insertion");
		System.out.println("\nMerge result:");
		randomTest("Merge");
		System.out.println("\nMergeBU result:");
		randomTest("MergeBU");
		System.out.println("\nQuick result:");
		randomTest("Quick");
		System.out.println("\nQuick3way result:");
		randomTest("Quick3way");

		System.out.println("\nsort 100000 asc number:");
		System.out.println("Insersion result:");
		ascTest("Insertion");
		System.out.println("\nMerge result:");
		ascTest("Merge");
		System.out.println("\nMergeBU result:");
		ascTest("MergeBU");
		System.out.println("\nQuick result:");
		ascTest("Quick");
		System.out.println("\nQuick3way result:");
		ascTest("Quick3way");

		System.out.println("\nsort 100000 des number:");
		System.out.println("Insersion result:");
		desTest("Insertion");
		System.out.println("\nMerge result:");
		desTest("Merge");
		System.out.println("\nMergeBU result:");
		desTest("MergeBU");
		System.out.println("\nQuick result:");
		desTest("Quick");
		System.out.println("\nQuick3way result:");
		desTest("Quick3way");
	}

}
