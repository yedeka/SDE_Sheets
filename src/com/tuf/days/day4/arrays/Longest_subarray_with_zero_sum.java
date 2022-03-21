package com.tuf.days.day4.arrays;

import java.util.HashMap;
import java.util.HashSet;

public class Longest_subarray_with_zero_sum {
    // Time Complexity - O(N)
    // Space Complexity O(N)
    private static int findLongestSubarrayZeroSum(int[] nums){
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0, maxLength = 0;

        for(int i=0; i<nums.length; i++){
            prefixSum += nums[i];
            if(prefixSum == 0){
                maxLength = i+1;
            } else if(prefixSumMap.containsKey(prefixSum)){
                maxLength = Math.max(maxLength, (i - prefixSumMap.get(prefixSum)));
            }
             else {
                prefixSumMap.put(prefixSum, i);
            }

        }
        return maxLength;
    }

    private static int findMaxZeroSumSubArray(int[] a){
        int  max = 0;
        for(int i = 0; i < a.length; ++i){
            int sum = 0;
            for(int j = i; j < a.length; ++j){
                sum += a[j];
                if(sum == 0){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] nums = new int[]{9, -3, 3, -1, 6, -5};
        System.out.println(findLongestSubarrayZeroSum(nums));
        System.out.println(findMaxZeroSumSubArray(nums));

        nums = new int[]{6, -2, 2, -8, 1, 7, 4, -10};
        System.out.println(findLongestSubarrayZeroSum(nums));
    }
}
