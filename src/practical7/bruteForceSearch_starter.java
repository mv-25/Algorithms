package practical7;
// Java program for Naive Pattern Searching 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class bruteForceSearch
{
	//brute force algorithm to find given pattern in string
	public static int search(String txt, String pat)
	{
		int p = pat.length();
		int t = txt.length();
		for (int i = 0; i <= t - p; i++)
		{
			int j;
			for (j = 0; j < p; j++)
				if (txt.charAt(i+j) != pat.charAt(j))
					break;
			if (j == p)
				//pattern found, position returned
				return i;
		}
		//returned if pattern not found
		return -1;
	}
	  
	    public static void main(String[] args) throws IOException
		{
			//reading a text file as string
			String data = "";
			data = new String(Files.readAllBytes(Paths.get("C:\\Users\\Mudita Verma\\IdeaProjects\\ algorithm-portfolio-20290-mv-25\\src\\practical7\\medTale.txt")));
			String pat = "king";

			//sample text
			String txt = "ABABDABACDABABCABAB";
			String pat1 = "ABABCABAB";

			//displaying the search result
	        int a = search(data, pat);
	        if(a==-1)
	        	System.out.println("Pattern not found");
	        else
	        	System.out.println("Pattern found at " + a);
	    } 

}
