package prepare.array;
//https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node {
	public Node(int data2) {
		data = data2;
	}

	int data;
	Node left;
	Node right;
}

public class HeightofaBinaryTree {

	static class Track {
		Node x;
		int treeHeight;

		public Track(Node node, int height) {
			x = node;
			treeHeight = height;
		}
	}

	/*
	 * class Node
	 */
	public static int height(Node root) {
		// Write your code here.
		Stack<HeightofaBinaryTree.Track> stack = new Stack<HeightofaBinaryTree.Track>();
		HeightofaBinaryTree.Track rootTrack = new HeightofaBinaryTree.Track(root, 0);

		stack.add(rootTrack);
		int maxheight = 0;
		do {
			Track p = stack.pop();

			if (maxheight < p.treeHeight)
				maxheight = p.treeHeight;

			if (p.x.left != null) {
				stack.add(new Track(p.x.left, p.treeHeight + 1));
			}
			if (p.x.right != null) {
				stack.add(new Track(p.x.right, p.treeHeight + 1));
			}

		} while (!stack.empty());

		return maxheight;

	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String[] args) {

		Scanner scan = // new Scanner(System.in);
				new Scanner("2\n" + "8\n" + "5 1 2 3 7 8 6 4\n" + "8\n" + "1 2 5 3 7 8 6 4");

		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		scan.close();
		int height = height(root);
		System.out.println(height);
	}
}
