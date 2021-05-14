package practical3;

import java.util.Scanner;

public class Fibonacci
{
    //iterative method to calculate fibonacci numbers
    static long fibonnaciItereative(long num){
        if (num<=1)
            return 1 ;

        long fib = 1;
        long prevFib =  1;

        for (int i = 2; i < num; i++)
        {
            long temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    //recursive method to calculate
    static long fibonacciRecursive(long num)
    {
        //this is the base case
        if (num <= 1)
            return num;

        //the number is decremented until it reaches the base case
        return fibonacciRecursive(num-1) + fibonacciRecursive(num-2);
    }

    public static void main (String args[])
    {
        Scanner in = new Scanner(System.in);

        //reading the number from the user
        long num = 0;
        System.out.println("Enter the number\n");
        num = in.nextInt();

        //System.currentTimeMillis() is used to calculate the time taken by the algorithm
        //timing both the algorithms to see their performance

        final long startTime1 = System.currentTimeMillis();
        System.out.println("Result from iterative method: " + fibonnaciItereative(num));
        final long elapsedTime1 = System.currentTimeMillis() - startTime1;
        System.out.println("The time taken: " + elapsedTime1);

        final long startTime2 = System.currentTimeMillis();
        System.out.println("Result from recursive method: "+ fibonacciRecursive(num));
        final long elapsedTime2 = System.currentTimeMillis() - startTime2;
        System.out.println("The time taken: " + elapsedTime2);
    }
}
