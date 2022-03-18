package com.tuf.days.day2.arrays;

import java.util.Arrays;

public class Merge_Sorted_Arrays {
    // Time Complexity - O(n1+n2) (First iteration to populate merged array) + O(nlogn) (sort merged array) + O(n1+n2) (second iteration to split merged array)
    private static void mergeSorted_Brute_Force(int[] arr1, int[] arr2){
        int[] merged = new int[arr1.length + arr2.length];
        int cnt = 0;
        // Step 1 - Add elements of both arr1 and arr2 into merged array
        for(int i=0; i<arr1.length; i++){
            merged[cnt++] = arr1[i];
        }
        for(int j=0; j<arr2.length; j++){
            merged[cnt++] = arr2[j];
        }
        //Step 2 - Sort the merged array
        Arrays.sort(merged);
        //Step 3 - Transfer the merged array back into original arrays
        cnt = 0;
        for(int i=0; i<arr1.length; i++){
            arr1[i] = merged[cnt++];
        }
        for(int j=0; j<arr2.length; j++){
            arr2[j] = merged[cnt++];
        }
    }
    // Time complexity - O(n1 * n2)
    // Space complexity - O(1)
    private static void mergeSorted_better(int[] arr1, int[] arr2){
        if(arr2.length > arr1.length){
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        for(int i=0; i<arr1.length; i++){
            int element = arr1[i];
            if(element > arr2[0]){ // We found unsorted merged element
                // Step 1 - Swap with arr2[0]
                arr1[i] = arr2[0];
                arr2[0] = element;
                // Sort arr2 to ensure we always get the smallest element at 0th index since we always compare 0th index
                Arrays.sort(arr2);
            }
        }
    }

    // Range technique.
    // Time complexity - o(logn) * O(n) = O(nlogn)
    // Space complexity O(1)
    private static void mergeSorted_optimal(int[] ar1, int[] ar2){
        int n = ar1.length, m = ar2.length;
        int gap =(int) Math.ceil((double)(n + m) / 2.0);

        while (gap > 0) {
            int i = 0;
            int j = gap;
            while (j < (n + m)) {
                if (j < n && ar1[i] > ar1[j]) {
                    swap(i, j, ar1, ar1);
                } else if (j >= n && i < n && ar1[i] > ar2[j - n]) {
                    swap(i, j - n, ar1, ar2);
                } else if (j >= n && i >= n && ar2[i - n] > ar2[j - n]) {
                    swap(i - n, j - n, ar2, ar2);
                }
                j++;
                i++;
            }
            if (gap == 1) {
                gap = 0;
            } else {
                gap =(int) Math.ceil((double) gap / 2.0);
            }
        }
    }

    private static void swap(int a,int b, int[] arr1, int[] arr2)
    {
        int temp=arr1[a];
        arr1[a]=arr2[b];
        arr2[b]=temp;
    }

    private static void printMerged(int[] arr1, int[] arr2){
        System.out.println("Merge sorted arr1");
        for(int i=0; i<arr1.length; i++){
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.println("Merge sorted arr2");
        for(int i=0; i<arr2.length; i++){
            System.out.print(arr2[i]+" ");
        }
    }

    public static void main(String[] args){
        int[] arr1 = new int[]{3, 27, 38, 43};
        int[] arr2 = new int[]{9, 10, 82};
        /*mergeSorted_Brute_Force(arr1, arr2);
        printMerged(arr1, arr2);
        mergeSorted_better(arr1, arr2);
        printMerged(arr1, arr2);*/
        mergeSorted_optimal(arr1, arr2);
        printMerged(arr1, arr2);
    }
}
