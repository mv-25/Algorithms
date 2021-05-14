package practical1;

//importing necessary classes
import java.util.Scanner;
import java.lang.*;

//class to implement Russian Peasant Algorithm to multiply numbers
class RussianPeasant
{
    public static long RussianMultiply(long n, long m)
    {
        //instance to store the result
        int accumulator = 0;

        // Loop to implement the algorithm
        // The left number is progressively halved (discarding any remainder) and the right one doubled, until the left number is 1.
        // Then you add all of the number2s together in rows where the number 1 is odd
        while (n!= 0)
        {
            if( n%2 == 1 )
                accumulator +=  m;
            n /= 2;
            m *= 2;
        }
        return accumulator;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //reading the numbers to be multiplied
        long num1 = 0, num2=0;
        System.out.println("Enter the numbers\n");
        num1 = in.nextLong();
        num2 = in.nextLong();

        //System.nanoTime() is used to calculate the time taken by the algorithm to multiply the numbers
        //the elapsed time progressively increases as the numbers are increased
        final long startTime = System.nanoTime();
        System.out.println(num1 + " x " + num2 + RussianMultiply(num1, num2));
        final long elapsedTime = System.nanoTime() - startTime;
        System.out.println("The time taken: " + elapsedTime);
    }

}