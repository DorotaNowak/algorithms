/*
Given n sorted linked lists each of maximum size m, merge them and print the sorted output.
Time complexity: O(m*n*log(n)), memory: O(n).
 */

import java.util.Scanner;

// HeaoNode class that stores the data and the number of array/set/list from which this data comes
class HeapNode{
    int data;
    int arrNumber;

    public HeapNode(int data, int arr){
        this.data = data;
        this.arrNumber = arr;
    }
}

// ArrayContainer class that represents array and current index
class ArrayContainer{
    private int[] arr;
    private int currIndex;

    public ArrayContainer(int[] arr){
        this.arr = arr;
        this.currIndex = 0;
    }

    public int pop(){
        return arr[currIndex++];
    }

    public boolean isEmpty(){
        if(currIndex >= arr.length){
            return true;
        }
        else{
            return false;
        }
    }
}

public class MergeSortedLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt(); // number of tests
        while(tests > 0){
            int n = scanner.nextInt(); // number of sets
            int[] sizes = new int[n]; // array with the sizes of sets
            ArrayContainer[] arrays = new ArrayContainer[n]; // array of ArrayContainer objects (array + current index)
            HeapNode[] heap = new HeapNode[n]; // heap of size n
            for(int i = 0; i < n; ++i){
                sizes[i] = scanner.nextInt();
            }

            for(int i = 0; i < n; ++i){
                int s = sizes[i];
                int[] a = new int[s];
                for(int j = 0; j < s; ++j){
                    a[j] = scanner.nextInt();
                }
                arrays[i] = new ArrayContainer(a);
                heap[i] = new HeapNode(arrays[i].pop(), i); // put the first elements to the heap
                if(i >= 1) {
                    up(heap, i);
                }
            }

            while(true){
                System.out.print(heap[0].data + " "); // min value is in heap[0]
                if(arrays[heap[0].arrNumber].isEmpty()){ // if array is empty
                    int next = 0;
                    for(int i=0; i<arrays.length; ++i){
                        if(!arrays[next].isEmpty()){
                            break;
                        }
                        next++;
                    }
                    if(next==arrays.length){
                        break;
                    }
                    heap[0] = new HeapNode(arrays[next].pop(), next);
                    down(heap, 0, n);
                }
                else{ // if array is not empty, take next element
                    heap[0].data = arrays[heap[0].arrNumber].pop();
                    down(heap, 0, n);
                }
            }

            // All arrays are empty, we have to print out only the element from heap
            int l = heap.length;
            swap(heap,0, l-1); // put the printed element under the last index
            l--;
            down(heap,0,l);

            while(l > 0){
                System.out.print(heap[0].data + " ");
                swap(heap,0, l-1);
                l--;
                down(heap,0, l);
            }
            System.out.println();
            tests--;
        }
    }

    public static void swap(HeapNode[] heap, int a, int b) {
        HeapNode tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    public static void down(HeapNode[] a, int k, int n) {
        int j;
        HeapNode tmp = a[k];
        while ( k < n / 2 ) {
            j = 2*k+1;
            if ( j<n-1 && a[j].data>=a[j+1].data ) j++;
            if (tmp.data < a[j].data ) break ;
            a[k] = a[j] ;
            k = j ;
        }
        a[k] = tmp;
    }

    public static void up(HeapNode[] a, int k) {
        int i = (k-1) / 2;
        HeapNode tmp = a[k];
        while (k > 0 && a[i].data >= tmp.data) {
            a[k] = a[i];
            k = i;
            i = (i-1) / 2;
        }
        a[k] = tmp;
    }
}

