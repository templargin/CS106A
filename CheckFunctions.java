

import acm.program.*;
import java.util.*;

public class CheckFunctions extends ConsoleProgram {
	public void run() {
		
	   int arr[] = {3,1,2,4,5};
       int arr_size = arr.length;
	   
       int x = consecutiveCount(arr);
       printArray(arr);
       println(" ");
       println(x);
      
	}

	private int consecutiveCount(int[] arr)
	{
		sort(arr);
		int max = 0;
		int count = 1;
		for (int i = 1; i < arr.length; i++)
		{
			if ((arr[i] - arr[i-1]) == 1) 
			{
				count++;
				if (count > max) max = count;
			}
			else
			{
				count = 1;
			}
		}
		return max;
	}
	
	private int maxDuplicates(int[] arr)
	{
		HashMap<Integer, Integer> duplicatesCount = new HashMap<Integer, Integer>();
		
		int max = 0;
		
		for (int i = 0; i < arr.length; i++)
		{
			Integer count = duplicatesCount.get(arr[i]);
			
			if (count == null)
			{
				duplicatesCount.put(arr[i], 1);
			}
			else 
			{	
				duplicatesCount.put(arr[i], ++count);
				if (count > max)
				{
					max = count; 
				}
			}			
		}		
		return max;
	}
	
	private void sort(int a[])
	{
		int c = 0;
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a.length - i - 1; j++)
			{
				if (a[j] > a[j+1]) 
				{
					c = a[j];
					a[j] = a[j+1];
					a[j+1] = c;
				}
			}
		}
		
	}
	
	private void printArray(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			println(arr[i]);
		}
	}


}