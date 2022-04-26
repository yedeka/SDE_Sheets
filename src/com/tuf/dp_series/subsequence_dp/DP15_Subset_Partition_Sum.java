package com.tuf.dp_series.subsequence_dp;

public class DP15_Subset_Partition_Sum {
    private static boolean targetSumSubSet_Recursive(int[]nums, int target, int index){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[0] == target;
        }
        boolean excludeSum = targetSumSubSet_Recursive(nums, target, index-1);
        boolean includeSum = false;
        if(target >= nums[index]){
            includeSum = targetSumSubSet_Recursive(nums, target - nums[index], index-1);
        }
        return excludeSum || includeSum;
    }

    private static boolean targetSumSubSet_memoized(int[]nums, int target, int index, int[][] dp){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[0] == target;
        }
        if(dp[index][target] != -1){
            return dp[index][target] == 0;
        }
        boolean excludeSum = targetSumSubSet_Recursive(nums, target, index-1);
        boolean includeSum = false;
        if(target >= nums[index]){
            includeSum = targetSumSubSet_Recursive(nums, target - nums[index], index-1);
        }
        boolean result = excludeSum || includeSum;
        if(result){
            dp[index][target] = 0;
        } else {
            dp[index][target] = 1;
        }
        return result;
    }

    private static boolean subsetPartitionSum(int[] nums, int target, boolean recursionFlag){
        int modifiedTarget = target/2;
        if(recursionFlag){
            return targetSumSubSet_Recursive(nums, modifiedTarget, nums.length-1);
        } else {
            int[][] dp = new int[nums.length-1][target+1];
            return targetSumSubSet_memoized(nums, modifiedTarget, nums.length-1, dp);
        }

    }
    public static void main(String[] args){
        int[] nums = new int[]{2, 3, 3, 4, 5};
        int target = 10;
        System.out.println("Can we divide the array into 2 parts of equal sum "+target+" => "+subsetPartitionSum(nums, target, true));
        System.out.println("Can we divide the array into 2 parts of equal sum with Memoization "+target+" => "+subsetPartitionSum(nums, target, true));
    }
}
