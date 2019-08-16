package prepare.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MinimumAbsoluteDifference {

	// https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
	 // Complete the minimumAbsoluteDifference function below.
    static int minimumAbsoluteDifference(int[] arr) {
		Arrays.sort(arr);
		int minval = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			int absdiff = Math.abs(arr[i] - arr[i - 1]);

			if (minval < absdiff) {
				minval = absdiff;
			}
			;

		}
		return minval;
    }


	public static void main(String[] args) {

		Scanner scanner = // new Scanner("2\n" + "5\n" + "2 1 5 3 4\n" + "5\n" + "2 5 1 3 4\n\n");
				new Scanner("3\r\n" + 
						"3 -7 0");

		  int n = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        int[] arr = new int[n];

	        String[] arrItems = scanner.nextLine().split(" ");
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        for (int i = 0; i < n; i++) {
	            int arrItem = Integer.parseInt(arrItems[i]);
	            arr[i] = arrItem;
	        }

	        int result = minimumAbsoluteDifference(arr);
 
		scanner.close();
	}
}
