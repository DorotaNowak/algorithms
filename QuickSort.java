/*
Iterative Quick-Sort with time complexity O(nlogn) and memory complexity O(1).
A dataset contains only positive integers.
 */

import java.util.Scanner;

public class QuickSort{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] tab = new int[size];
        for(int i = 0; i < size; ++i){
            tab[i] = scanner.nextInt();
        }
        quickSort(tab);
        display(tab);
    }

    /*
    Method that prints out the solution.
     */
    public static void display(int[] a){
        for(int i=0; i<a.length; ++i){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    /*
    Swaps two element in given array.
     */
    public static void swap(int i, int j, int[] a){
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    public static void quickSort(int tab[]){
        int L = 0;
        int R = tab.length-1;
        int n = 0;
        int q = 0;
        while(n > 0 || L < R){
            if(L < R){
                n++;
                q = partition(L, R, tab);
                tab[R] = -tab[R]; // The array contains only positive integers, so we can mark right end with opposite value.
                R = q-1;
            }
            else{
                L = R + 2; // The order is: R,q,L
                q = R + 1;
                q = findNextR(tab,q);
                tab[q] = -tab[q];
                R = q;
                n--;
            }
        }
    }

    static int findNextR(int[] tab, int p){
        while(tab[p]>0){
            ++p;
        }
        return p;
    }

    static int partition(int L, int R, int[] a){ // Hoare partition
        int i = L-1, j = R;
        swap(L,R,a); // It helps in pesimistic case not to choose min/max value.
        int x = a[R]; // The last element divide the array.
        while(true){
            while (a[++i] < x);
            while (j > L && a[--j] > x);

            if (i >= j){
                break;
            }
            else{
                swap(i, j, a);
            }
        }
        swap(i, R, a);
        return i; // Return an index of partition.
    }
}
