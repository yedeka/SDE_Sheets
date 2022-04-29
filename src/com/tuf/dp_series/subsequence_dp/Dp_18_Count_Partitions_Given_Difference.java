package com.tuf.dp_series.subsequence_dp;

import java.util.Arrays;

public class Dp_18_Count_Partitions_Given_Difference {
    private static int cntSubsetTargetSum_recursive(int[] nums, int target, int index){
        // base case to ensure that we always traverse deep till 0th index
        if(index == 0){
            if(target == 0 && nums[index] == 0) return 2;
            if(target ==0 || target == nums[index]) return 1;
            return 0;
        }
        int excludeSum = cntSubsetTargetSum_recursive(nums, target, index - 1);
        int includeSum = 0;
        if(nums[index] <= target){
            includeSum = cntSubsetTargetSum_recursive(nums, target - nums[index], index - 1);
        }
        return includeSum + excludeSum;
    }

    private static int cntSubsetTargetSum_memoization(int[] nums, int target, int index, int[][] dp){
        if(index == 0){
            if(target == 0 && nums[0] == 0) return dp[0][0] = 2;
            if(target == 0 || nums[0] == target) return dp[0][target] = 1;
            return dp[0][target] = 0;
        }
        if(dp[index][target] != -1){
            return dp[index][target];
        }
        int excludeSum = cntSubsetTargetSum_memoization(nums, target, index -1, dp);
        int includeSum = 0;
        if(nums[index] <= target){
            includeSum = cntSubsetTargetSum_memoization(nums, target - nums[index], index -1, dp);
        }
        return excludeSum + includeSum;
    }

    private static int partitionCnt(int[] nums, int diff, boolean recursiveFlag){
        int totalSum = 0, targetSum = 0, n = nums.length - 1;
        for(int elements: nums){
            totalSum += elements;
        }
        targetSum =(totalSum - diff) / 2;
        if(recursiveFlag){
            return cntSubsetTargetSum_recursive(nums, targetSum, n);
        } else {
            int[][] dp = new int[nums.length][targetSum + 1];
            for(int[] row: dp){
                Arrays.fill(row, -1);
            }
            return cntSubsetTargetSum_memoization(nums, targetSum, n, dp);
        }

    }
    public static void main(String[] args){
        int[] nums = new int[]{5, 2, 6, 4};
        int diff = 3;
        System.out.println("Count of paritions with given sum using recursion => "+partitionCnt(nums, diff, true));
        System.out.println("Count of paritions with given sum using memoization => "+partitionCnt(nums, diff, false));
    }
}
