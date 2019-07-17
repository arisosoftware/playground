package prepare.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumSwaps2 {

	// https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

	// Complete the minimumSwaps function below.
	static int minimumSwapsA1(int[] arr) {
		int num = 0;
		int minIndex;
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			if (i != minIndex) {
				int t = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = t;
				num++;
			}
		}

		return num;
	}

	static int minimumSwaps(int[] ar) {
		int n = ar.length;
		Map<Integer, Integer> hashmap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			hashmap.put(ar[i], i);
		}
		Arrays.sort(ar);
		for (int i = 0; i < n; i++) {
			ar[i] = hashmap.get(ar[i]);
		}
		hashmap = null;
		int swap_num = 0;
		for (int i = 0; i < n; i++) {
			int val = ar[i];
			if (val < 0)
				continue;
			while (val != i) {
				int new_val = ar[val];
				ar[val] = -1;
				val = new_val;
				swap_num++;
			}
			ar[i] = -1;
		}
		return swap_num;
	}

//	// Complete the solve function below.
//	static void solve(double meal_cost, int tip_percent, int tax_percent) {
//		double tip = meal_cost * tip_percent / 100;
//		double tax = meal_cost * tax_percent / 100;
//
//		double total = Math.round(meal_cost + tip + tax);
//		System.out.format("%.0f%n", total);
//	}

	// Complete the plusMinus function below.
//	static void plusMinus(int[] arr) {
//		int plus = 0;
//		int minus = 0;
//		int zero = 0;
//		int len = arr.length;
//		for (int i = 0; i < arr.length; i++) {
//			if (arr[i]>0)
//			{
//				plus++;
//			}
//			else if (arr[i]<0)
//			{
//				minus ++;
//			}
//			else
//			{
//				zero++;
//			}			
//		}
//
//		System.out.format("%.6f%n%.6f%n%.6f%n", (plus*1.0/len),(minus*1.0/len),(zero*1.0/len));
//				
//		
//	}

	public static void main(String[] args) {

		Scanner scanner =

				new Scanner("4\n" + "4 3 1 2");

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] arr = new int[n];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		int res = minimumSwaps(arr);
		System.out.println(res);
		scanner.close();
	}
}
