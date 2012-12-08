package chap1;

public class Sorter {
	public void mergeSort(int[] xs, int s, int e) {
		if (s < 0 || xs.length <= e) {
			throw new IllegalArgumentException();
		}
		if (s < e) {
			int m = (s + e) / 2;
			mergeSort(xs, s, m);
			mergeSort(xs, m + 1, e);
			merge(xs, s, m, e);
		}
	}
	
	public void merge(int[] xs, int s, int m, int e) {
		if (s < 0 || xs.length <= e || s > m || m >= e) {
			throw new IllegalArgumentException();
		}
		int[] lhs = new int[m - s + 1];
		int[] rhs = new int[e - m];
		for (int i = 0; i < m - s + 1; i++) {
			lhs[i] = xs[i + s];
		}
		for (int j = 0; j < e - m; j++) {
			rhs[j] = xs[j + m + 1];
		}
		int i = 0;
		int j = 0;
		while (i <= m - s && j <= e - m - 1) {
			if (lhs[i] < rhs[j]) {
				xs[i + j + s] = lhs[i];
				i++;
			} else {
				xs[i + j + s] = rhs[j];
				j++;
			}
		}
		while (i <= m - s) {
			xs[i + j + s] = lhs[i];
			i++;
		}
		while (j <= e - m - 1) {
			xs[i + j + s] = rhs[j];
			j++;
		}
	}
	
	public void rotate(int[] xs) {
		int x = xs[xs.length - 1];
		for (int i = xs.length - 1; i > 0; i--) {
			xs[i] = xs[i - 1];
		}
		xs[0] = x;
	}
	
}
