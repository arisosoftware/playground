package prepare.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos {

	// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

	// Complete the minimumBribes function below.
	static void minimumBribes(int[] q) {

		// System.out.printf("===============================\n\n");

		int maxbrib = 2;
		int minbrib = 0;

		// sort

		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		int round = 0;
		boolean hasSwap = false;
		do {
			// System.out.printf("--\n");
			hasSwap = false;
			round++;
			int i = 0;
			do {

				int curr = q[i];

				int next = q[i + 1];

				// System.err.printf("check: %d, curr (%d) vs next(%d) \n", i, curr, next);

				if (curr > next) {
					int bribes = 0;
					if (hmap.containsKey(curr)) {
						bribes = hmap.get(curr);
					}

					bribes++;

					q[i] = next;
					q[i + 1] = curr;
					hmap.put(curr, bribes);
					// System.out.printf("%d::Swap. bribes++ %d for value:%d \n", round, bribes,
					// curr);
					hasSwap = true;
					if (bribes > maxbrib) {
						System.out.println("Too chaotic");
						return;
					}

					if (bribes > minbrib) {
						minbrib = bribes;
					}
				}
				i++;
			} while (i < q.length - 1);

		} while (hasSwap);

		int sumBrib = 0;
		for (int v : hmap.values()) {
			// System.out.printf(" %d , %d \n", sumBrib, v);
			sumBrib += v;
		}

		System.out.println(sumBrib);

	}

	public static void main(String[] args) {

		Scanner scanner = // new Scanner("2\n" + "5\n" + "2 1 5 3 4\n" + "5\n" + "2 5 1 3 4\n\n");

				new Scanner("2\n" + "8\n" + "5 1 2 3 7 8 6 4\n" + "8\n" + "1 2 5 3 7 8 6 4");

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] q = new int[n];

			String[] qItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				int qItem = Integer.parseInt(qItems[i]);
				q[i] = qItem;
			}

			minimumBribes(q);
		}

		scanner.close();
	}
}
