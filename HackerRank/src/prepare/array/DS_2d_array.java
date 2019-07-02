package prepare.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class DS_2d_array {

	/*
	 * Tips:  
	 * If the sums of hourglasses are all negative , and if u have taken your max variable initilized to zero and calculate the max with refrence to 0 obviously you will be returning 0 as the answer which is wrong . 
	 * So its better to initilize with Integer.MIN_VALUE to a variable to which will be storing a max value .
	 * 
	 */
 
	// Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
    		int [][] matrix = new int [][] {
    			new int []{1,1,1},
    			new int []{0,1,0},
    			new int []{1,1,1}
    		};
    		
    	    int lenX = arr.length;
    	    int lenY = lenX;
    	
    	    int maxsum=Integer.MIN_VALUE;
    	    for(int x =0;x< lenX -2 ; x++)
    	    {
    	    	
    	    	for (int y=0;y<lenY-2; y++)
    	    	{
    	    		int sum =0;
    	    		
    	    		for(int mx =0;mx<3;mx++)
    	    		{
    	    			for (int my=0;my<3;my++)
    	    			{
    	    				
    	    				if ((y+my )<arr[x+mx].length)
    	    				{
    	    					//System.out.printf("(%d %d)", y+my, x+mx);
    	    					
    	    					System.out.printf("%d ", arr[x+mx][y+my]);
    	    					sum =sum +  (arr[x+mx][y+my]) * (matrix[mx][my]);
    	    				}
    	    			}
    	    			System.out.printf("\n" );
    	    		}
    	    		System.out.printf("sum = %d\n",sum );
        	    	if (maxsum<sum)
    	    		{
    	    			maxsum=sum;
    	    		}  
        	    	System.out.printf("\n" );
        	    	System.out.printf("\n" );
    	    	}

	    			
    	    }
    	    
    		return maxsum;
    }

    //private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	Scanner   scanner = new Scanner(
    			"0 -4 -6 0 -7 -6\n" + 
    			"-1 -2 -6 -8 -3 -1\n" + 
    			"-8 -4 -2 -8 -8 -6\n" + 
    			"-3 -1 -2 -5 -7 -4\n" + 
    			"-3 -5 -3 -6 -6 -6\n" + 
    			"-3 -6 0 -8 -6 -7");
        
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);
        System.out.println(result);

        scanner.close();
    }
}
 