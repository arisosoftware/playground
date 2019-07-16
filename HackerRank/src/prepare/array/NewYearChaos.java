package prepare.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos {

	//https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
	
	// Complete the minimumBribes function below.
	static void minimumBribes(int[] q) {
		int bribes = 0;

		int maxbrib = 2;

		for (int i = 0; i < q.length; i++) {
			boolean seekNext = true;

			for (int l = 1; l <= maxbrib && seekNext; l++) {

				int k = i + l;

				if (k >= q.length)
					break;

				System.err.printf("check: %d, qi[%d](%d)  >= qk[%d](%d)  \n", l, i, q[i], k, q[k]);

				if (k < q.length && q[i] < q[k]) {
					System.err.printf("Found q[%d]:%d < q[%d]:%d \n", i, k, q[i], q[k]);

				}

				if (k < q.length && q[i] >= (q[k] + 1)) {

					if (q[i] == q[k] + 1) {
						seekNext = false;
						continue;
					}
					{
						bribes++;
					}
				}

			}

			if (seekNext) {
				System.out.println("Too chaotic");
				return;
			}

		}
		System.out.println(bribes);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner("2\n" + "5\n" + "2 1 5 3 4\n" + "5\n" + "2 5 1 3 4\n\n");

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
