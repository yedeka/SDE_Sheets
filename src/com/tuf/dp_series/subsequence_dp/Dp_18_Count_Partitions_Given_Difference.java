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

    private static int partitionCnt_Tabulation(int[] nums, int diff){
        int n = nums.length, totalSum = 0, targetSum = 0;
        for(int element: nums){
            totalSum += element;
        }
        targetSum = (totalSum - diff)/2;
        int[][] dp = new int[n][targetSum + 1];
        // Handle Base cases
        // Base case 1 - If sum is 0 and index 0  then answer is 2
        if(nums[0] == 0){
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;// Since target is already 0 if the number at index 0 is not zero there is only one way not take and hence we mark it as 1
            if(nums[0] <= targetSum){
                dp[0][nums[0]] = 1; // here only way to achieve target is to include the element in the sum.
            }
        }
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                int excludeSum = dp[i-1][j];
                int includeSum = 0;
                if(nums[i] <= j){
                    includeSum += dp[i][j-nums[i]];
                }
                dp[i][j] = excludeSum + includeSum;
            }
        }
        return dp[n-1][targetSum];
    }

    public static void main(String[] args){
        int[] nums = new int[]{5, 2, 6, 4};
        int diff = 3;
        System.out.println("Count of paritions with given sum using recursion => "+partitionCnt(nums, diff, true));
        System.out.println("Count of partitions with given sum using memoization => "+partitionCnt(nums, diff, false));
        System.out.println("Count of partitions with given sum using tabulation => "+partitionCnt_Tabulation(nums, diff));
    }
}
