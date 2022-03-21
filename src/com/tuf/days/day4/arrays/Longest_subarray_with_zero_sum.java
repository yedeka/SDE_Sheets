package com.tuf.days.day4.arrays;

import java.util.HashMap;
import java.util.HashSet;

public class Longest_subarray_with_zero_sum {
    // Time Complexity - O(N)
    // Space Complexity O(N)
    private static int findLongestSubarrayZeroSum(int[] nums){
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 0);
        int prefixSum = 0, maxLength = 0;
        for(int i=0; i<nums.length; i++){
            prefixSum += nums[i];
            if(prefixSumMap.containsKey(prefixSum)){
                maxLength = Math.max(maxLength, (i - prefixSumMap.get(prefixSum) + 1));
            }
        }
        return maxLength;
    }
    public static void main(String[] args){
        int[] nums = new int[]{9, -3, 3, -1, 6, -5};
        System.out.println(findLongestSubarrayZeroSum(nums));
        nums = new int[]{6, -2, 2, -8, 1, 7, 4, -10};
        System.out.println(findLongestSubarrayZeroSum(nums));
    }
}
