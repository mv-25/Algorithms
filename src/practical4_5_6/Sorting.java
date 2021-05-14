package practical4_5_6;

import java.util.Random;
import java.util.Scanner;

public class Sorting
{

     /************************************************ SELECTION SORT ************************************/
    //smallest is swapped with the next element.
    //process is repeated until the array is sorted
    public static void selectionSort (int array[])
    {
        for (int i = 0; i < (array.length -1); i++)
        {
            for (int j = i + 1; j < (array.length); j++)
            {
                if(array[i] > array[j])
                    //the new found smallest element is swapped
                     swap(array, j, i);
            }
        }
    }

    /**************************** Implementation of INSERTION SORT **************************************/
    //any element greater than the key element is shifted towards right
    //and the element is inserted into its place
    public static void insertSort (int array[])
    {
        for (int i = 1; i < array.length; ++i)
        {
            int key = array[i];
            int j = i -1;

            while(j >= 0 && array[j] > key)
            {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j+1] = key;
        }
    }

    /******************************************** Implementation of BOGO SORT *********************************/
    //array is shuffled until it is sorted
    public static void bogoSort(int[] array)
    {
        while (isSorted(array) == false)
            shuffle(array);
    }

    // implementing swapping of two elements
    public static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // To generate a permutation of the array
    // Math.random() returns a double positive value, greater than or equal to 0.0 and less than 1.0.
    public static void shuffle(int[] array)
    {
        int n = array.length;
        for (int i = 0; i < n; i++)
        {
            // choose index uniformly in [0, i]
            //explicit conversion to int
            int r = (int) (Math.random() * (i + 1));
            swap(array,r,i);
        }
    }

    // To check if array is sorted or not
    public static boolean isSorted(int[] array)
    {
        for (int i=1; i<array.length; i++)
            if (array[i] < array[i-1])
                return false;
        return true;
    }

    /******************************************** Implementation of MERGE SORT ***************************/

    //used in merge sort and quick sort
    private static final int CUTOFF = 10;

    //array is divided and the halves are recursively sorted
    // the final sorted array is returned.
    public static void mergeSort(int[] array, int size)
    {
        //base case
        if (size < 2)
            return;

        //dividing the array from the middle
        int mid = size / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[size - mid];
        for (int i = 0; i < mid; i++)
        {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < size; i++)
        {
            rightArray[i - mid] = array[i];
        }
        //recursion
        mergeSort(leftArray, mid);
        mergeSort(rightArray, size - mid);
        //merging the sorted halves
        merge(array, leftArray, rightArray, mid, size - mid);
    }

    //implementing an enhanced merge sort
    public static void enhancedMergeSort(int[] array, int size)
    {
        //base case
        if (size < 2)
            return;

        //sub arrays are sorted using insertion sort as
        //Mergesort has too much overhead for tiny subarrays.

        if ((size-1) < CUTOFF)
        {
            insertSort(array);
            return;
        }

        //dividing the array from the middle
        int mid = size / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[size - mid];
        for (int i = 0; i < mid; i++)
        {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < size; i++)
        {
            rightArray[i - mid] = array[i];
        }
        //recursion
        mergeSort(leftArray, mid);
        mergeSort(rightArray, size - mid);

        // if the last element of left half is lesser or equal than first element of second half of the array
        // merge() call is skipped as it is not required.
        if ((array[mid] <= array[mid+1]))
            return;

        //merging the sorted halves
        merge(array, leftArray, rightArray, mid, size - mid);
    }

    //method to merge the sorted sub arrays
    public static void merge(int[] a, int[] leftArray, int[] rightArray, int left, int right)
    {
        int i = 0, j = 0, k = 0;
        //checks ensure that the merged array stays sorted.
        while (i < left && j < right)
        {
            if (leftArray[i] <= rightArray[j])
                a[k++] = leftArray[i++];
            else
                a[k++] = rightArray[j++];
        }

        while (i < left)
            a[k++] = leftArray[i++];
        while (j < right)
            a[k++] = rightArray[j++];
    }

    /****************************************** Implementation of QUICK SORT *****************************/

     public static void quickSort(int[] array)
    {
        quickSort(array, 0, (array.length - 1));
    }

    public static void quickSort(int[] array, int lo, int hi)
    {
        //base case
        if (hi <= lo)
            return;
        int j = partition(array, lo, hi);

        //recursion
        quickSort(array, lo, j-1);
        quickSort(array, j+1, hi);
    }

     public static void enhancedQuickSort(int[] array)
    {
        //array is shuffled to improve performance
        shuffle(array);
        enhancedQuickSort(array, 0, (array.length - 1));
    }

    public static void enhancedQuickSort(int[] array, int lo, int hi)
    {
        //base case
        if (hi <= lo)
            return;

        //sub arrays are sorted using insertion sort
        if( hi< CUTOFF)
        {
            insertSort( array);
            return;
        }

        //finding the median
		int mid = (lo + hi)/2;

		if(array[mid]<array[lo])
			swap(array,lo,mid);
		if(array[hi]<array[lo])
			swap(array,lo,hi);
		if(array[hi] < array[mid])
			swap(array,mid,hi);

		swap(array,mid,hi-1);

        int pivot = partition(array, lo, hi);

        //Recursive calls to sort before and after partition
        enhancedQuickSort(array, lo, pivot-1);
        enhancedQuickSort(array, pivot+1, hi);
    }


    public static int partition(int[] array, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while ((array[++i]< array[lo]))
                if (i == hi)
                    break;
            while ((array[lo]< array[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            swap(array, i, j);
        }
        swap(array, lo, j);
        return j;
    }

    public static int enhancedPartition(int[] array, int lo, int hi)
    {
        //using median
        int pivot = array[hi - 1];
        int i = (lo-1);
        for (int j = lo; j < hi; j++)
        {
            while ((array[++i]< pivot))
                if (i == hi)
                    break;
            while ((pivot< array[--j])) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            swap(array, i, j);
        }
        swap(array, i+1, hi);

        return i+1;
    }

    /*****************************************************************************************************/

    // method to print the arrays
    public static void printArray(int[] array)
    {
        for (int value : array)
            System.out.print(value + " ");
        System.out.println();
    }

    /***************************************** MAIN FUCNTION : Testing framework for the algorithms ******/

    public static void main(String[] args)
    {
        //reading the size of array
        Scanner in = new Scanner(System.in);
        int size = 0;
        System.out.println("Enter the size of the array\n");
        size = in.nextInt();

        // creating a random array of given size
        Random rd = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++)
            array[i] = rd.nextInt(size);

        //System.nanoTime() is used to calculate the time taken by the algorithm to multiply the numbers
        //implementing selection sort and timing the performance
        final long startTimeS = System.nanoTime();
        selectionSort(array);
        System.out.print("Sorted array via selection sort: ");
        printArray(array);
        final long elapsedTimeS = System.nanoTime() - startTimeS;
        System.out.println("The time taken: " + elapsedTimeS);

        //implementing bogo sort and timing the performance
        final long startTime = System.nanoTime();
        bogoSort(array);
        System.out.print("Sorted array via bogo sort: ");
        printArray(array);
        final long elapsedTime = System.nanoTime() - startTime;
        System.out.println("The time taken: " + elapsedTime);

        //implementing insertion sort and timing the performance
        final long startTimeI = System.nanoTime();
        insertSort(array);
        System.out.print("Sorted array via insertion sort: ");
        printArray(array);
        final long elapsedTimeI = System.nanoTime() - startTimeI;
        System.out.println("The time taken: " + elapsedTimeI);

        //implementing merge sort and timing the performance
        final long startTimeM = System.nanoTime();
        mergeSort(array, size);
        System.out.print("Sorted array via merge sort: ");
        printArray(array);
        final long elapsedTimeM = System.nanoTime() - startTimeM;
        System.out.println("The time taken: " + elapsedTimeM);

        //implementing enhanced merge sort and timing the performance
        final long startTimeEm = System.nanoTime();
        enhancedMergeSort(array, size);
        System.out.print("Sorted array via enhanced merge sort: ");
        printArray(array);
        final long elapsedTimeEm = System.nanoTime() - startTimeEm;
        System.out.println("The time taken: " + elapsedTimeEm);

        //implementing quick sort and timing the performance
        final long startTimeQ= System.nanoTime();
        quickSort(array);
        System.out.print("Sorted array via quick sort: ");
        printArray(array);
        final long elapsedTimeQ = System.nanoTime() - startTimeQ;
        System.out.println("The time taken: " + elapsedTimeQ);

        //implementing enhanced quick sort and timing the performance
        final long startTimeEq = System.nanoTime();
        enhancedQuickSort(array);
        System.out.print("Sorted array via enhanced quick sort: ");
        printArray(array);
        final long elapsedTimeEq= System.nanoTime() - startTimeEq;
        System.out.println("The time taken: " + elapsedTimeEq);

    }

}