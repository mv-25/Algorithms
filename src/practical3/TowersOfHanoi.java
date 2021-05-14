package practical3;

import java.util.Scanner;

public class TowersOfHanoi
{
    static void towersOfHanoi(int numOfDisks, String source, String destination, String auxiliary)
    {
        //this is the base case.
        // when there is just one disk it is directly moved fromthe first tower to the last.
        if (numOfDisks == 1)
        {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        //the disk is moved to the auxiliary tower and then it's moved to the destination.
        //recursion is used to call the function repeatedly until it reaches the base case
        towersOfHanoi(numOfDisks-1, source, auxiliary, destination);
        System.out.println("Move disk " + numOfDisks + " " + source + " to " + destination);
        towersOfHanoi(numOfDisks-1, auxiliary, destination, source);

    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //reading the number of disks
        int numOfDisks = 0;
        System.out.println("Enter the number of disks\n");
        numOfDisks = in.nextInt();

        //calling the function
        towersOfHanoi(numOfDisks, "A", "C", "B");

        System.out.println("Disks moved from source to destination");
    }
}
