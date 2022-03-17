package com.tuf.days.day1.arrays;

public class Kadanes_Algorithm {
    private static int findMaxSubArraySum(int[] nums){
        int maxSumSubArray = nums[0], currentSum = nums[0];
        for(int i=1; i<nums.length; i++){
            currentSum = Math.max( nums[i], currentSum + nums[i]);
            maxSumSubArray = Math.max(maxSumSubArray, currentSum);
        }
        return maxSumSubArray;
    }
    public static void main(String[] args){
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        nums = new int[]{1, 2, 7, -4, 3, 2, -10, 9,1};
        nums = new int[]{-7, -8, -16, -4, -8, -5, -7, -11, -10, -12, -4, -6, -4, -16, -10};
        System.out.println("Maximum subarray sum => "+findMaxSubArraySum(nums));
    }
}
