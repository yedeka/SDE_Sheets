package com.tuf.days.day2.arrays;

public class Count_Inversions {
    private static int[] mergeParts(int[] left, int[] right, int[] result){ // Since variable are passed by reference we pass in an array so that result is preserved.
        // take 3 pointers 1 for keeping track of left array, 1 for keeping trakc of right array and 1 for result array
        int[] fin = new int[left.length + right.length];
        int i=0, j =0, k = 0;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                fin[k++] = left[i++];
            } else {
                fin[k++] = right[j++];
                result[0] += left.length - i;
            }
        }
        while(i <left.length) {
            fin[k++] = left[i++];
        }
        while(j <right.length) {
            fin[k++] = right[j++];
        }
        return fin;
    }
    // regular merge sort
    // TC - Nlog(n)
    private static int[] mergeSort(int[] nums, int lo, int hi, int[] result){
        if(lo == hi){
            return new int[]{nums[lo]};
        }
        int mid = (hi + lo) / 2;
        int[] left = mergeSort(nums, lo, mid, result);
        int[] right = mergeSort(nums, mid+1, hi, result);
        return mergeParts(left, right, result);
    }
    public static void main(String[] args){
        int[] nums = new int[]{5, 3, 2, 1, 4};
        int[] result = new int[1];
        mergeSort(nums,0, nums.length - 1, result);
        System.out.println("Total inversions => "+result[0]);
    }
}
