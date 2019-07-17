package prepare.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SockMerchant {

	// https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

	// Complete the sockMerchant function below.
	static int sockMerchant(int n, int[] ar) {

		int matchSock = 0;
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int color = ar[i];
			if (hmap.containsKey(color)) {
				int countOfColor = hmap.get(color);
				countOfColor++;
				if (countOfColor == 2) {
					matchSock++;
					hmap.remove(color);
				} else {
					hmap.put(color, countOfColor);
				}
			} else {
				hmap.put(color, 1);
			}
		}

		return matchSock;

	}

	public static void main(String[] args) {

		Scanner scanner = // new Scanner("2\n" + "5\n" + "2 1 5 3 4\n" + "5\n" + "2 5 1 3 4\n\n");
				new Scanner("9\n" + "10 20 20 10 10 30 50 10 20");
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] ar = new int[n];

		String[] arItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int arItem = Integer.parseInt(arItems[i]);
			ar[i] = arItem;
		}

		int result = sockMerchant(n, ar);
		System.out.print(String.valueOf(result));
		System.out.println();

		scanner.close();

	}
}
