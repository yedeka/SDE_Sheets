package com.tuf.days.day3.arrays;

public class Count_Pairs {
    private static int[] merge(int[] left, int[] right){
        int[] res = new int[left.length + right.length];
        int k=0, i=0, j=0;
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }
        while(i<left.length){
            res[k++] = left[i++];
        }
        while(j<right.length){
            res[k++] = right[j++];
        }
        return res;
    }
    private static int[] mergeSort(int lo, int hi, int[] nums, int[] result){
        if(lo == hi){
            return new int[]{nums[lo]};
        }
        int mid = (hi + lo)/2;
        int[] left = mergeSort(lo, mid, nums, result);
        int[] right = mergeSort(mid+1, hi, nums, result);
        // Reverse pairs logic
        // We apply it here because we get the previous level left and right subarrays which are sorted so we apply the reverse pair counting
        // here and then merge.
        int j = 0;
        for(int i=0; i<left.length; i++){
            while(j < right.length && left[i] >= 2 * right[j]){
                j++;
            }
            result[0] += j;
        }

        return merge(left, right);
    }
    private static int countPairs(int[] nums){
        int[] result = new int[1];
        int lo =0, hi = nums.length - 1;
        mergeSort(lo, hi, nums, result);
        return result[0];
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,3,2,3,1};
        System.out.println("Pair Count => "+countPairs(nums));
    }
}
