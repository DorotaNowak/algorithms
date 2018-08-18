/*
Recursive based solution for modified backpack problem
Given weights of k items, put some of them in a backpack of determined capacity,
so that the sum of the weights was equal to this capacity and print the first solution.
 */

import java.util.Scanner;

public class BackpackProblem {
    public static boolean printArray = false;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
            int capacity = in.nextInt(); // Capacity of a backpack
            int k = in.nextInt(); // Maximum number of items we can put in the backpack
            int weights[] = new int[k]; // Weights of available items
            int tmp[] = new int[k];
            for (int i = 0; i < k; ++i) {
                weights[i] = in.nextInt();
            }

            // Recursive function that finds the first solution if there exists one
            find(weights,0,0, capacity, tmp);

            // If any solution was printed, then solution doesn't exist
            if(printArray == false) {
                System.out.println("NO SOLUTION");
            }
    }

    public static void find(int[] A, int curr, int index, int capacity, int[] solution) {
        // If solution have been already printed then return
        if(printArray == true){
            return;
        }
        if (curr == capacity) {
            System.out.print(capacity + " =");
            printArray(A, solution, solution.length);
            System.out.println();
        }
        else if (index == A.length) {
            return;
        }
        /*
        There are two options
        1) take the nth item
        2) don't take the nth item
         */
        else {
            solution[index] = 1;
            curr += A[index];
            find(A, curr, index + 1, capacity, solution);
            curr -= A[index];
            solution[index] = 0;
            find(A, curr, index + 1, capacity, solution);
        }
        return;
    }

    // Recursive function that prints the solution
    public static void printArray(int a[], int arr[], int size)
    {
        printArray = true;
        if(size == 1) {
            if(arr[0] == 1) {
                System.out.print(" " + a[0]);
            }
        }
        else
        {
            printArray(a, arr,size-1);
            if(arr[size-1] == 1) {
                System.out.print(" " + a[size - 1]);
            }
        }
    }
}

/*
Example:
Input:
20
5
11 8 7 6 5
Output:
20 = 8 7 5

Input:
21
3
5 6 7
Output:
NO SOLUTION
 */
