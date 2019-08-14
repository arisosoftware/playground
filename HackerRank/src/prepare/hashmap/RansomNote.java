package prepare.hashmap;

import java.util.HashSet;
import java.util.Scanner;
public class RansomNote {

	
	//https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
	// System.out.printf("===============================\n\n");
		HashSet<String> dict  = new HashSet<String>();
		
		for(String str:magazine)
		{
			if (!dict.contains(str))
				dict.add(str);
		}
		boolean Ok = true;
		for(String str:note)
		{
			if (!dict.contains(str))
			{
				Ok = false;
				break;
			}
			
		}
		
		if (Ok)
		{
			System.out.println("Yes");
		}
		else
		{
			System.out.println("No");
		}
    }
      
 	public static void main(String[] args) {
 		
 		Scanner scanner = // new Scanner("2\n" + "5\n" + "2 1 5 3 4\n" + "5\n" + "2 5 1 3 4\n\n");
				new Scanner("6 4\r\n" + 
						"give me one grand today night\r\n" + 
						"give one grand today");

 		
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
