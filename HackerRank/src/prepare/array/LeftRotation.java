package prepare.array;
//https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LeftRotation {

	// Complete the rotLeft function below.
	static int[] rotLeft(int[] a, int d) {
		int len = a.length;
		int newStartIdx = d % len;

		int newArray[] = new int[len];

		if (newStartIdx == 0)
			return a;

		int y = 0;
		for (int x = newStartIdx; x < len; x++) {
			newArray[y] = a[x];
			y++;
		}

		for (int x = 0; x < newStartIdx; x++) {
			newArray[y] = a[x];
			y++;
		}
		return newArray;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner("5 4\n" + "1 2 3 4 5");

		String[] nd = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nd[0]);

		int d = Integer.parseInt(nd[1]);

		int[] a = new int[n];

		String[] aItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int aItem = Integer.parseInt(aItems[i]);
			a[i] = aItem;
		}

		int[] result = rotLeft(a, d);

		for (int i = 0; i < result.length; i++) {
			System.out.print(String.valueOf(result[i]));

			if (i != result.length - 1) {
				System.out.print(" ");
			}
		}

		System.out.println();

		scanner.close();
	}
}
