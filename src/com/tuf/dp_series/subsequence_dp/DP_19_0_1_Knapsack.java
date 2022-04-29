package com.tuf.dp_series.subsequence_dp;

import java.util.Arrays;

public class DP_19_0_1_Knapsack {
    private static int findMaxValKP(int index, int wt, int[][] itemInventory){
        // Base Case
        if(index == 0){
            if(itemInventory[index][0] <= wt){
                return itemInventory[index][1];
            } else {
                return 0;
            }
        }
        int excludeItem = findMaxValKP(index - 1, wt, itemInventory);
        int includeItem = Integer.MIN_VALUE;
        if(itemInventory[index][0] <= wt){
            includeItem = itemInventory[index][1] + findMaxValKP(index - 1, wt-itemInventory[index][0], itemInventory);
        }
        return Math.max(excludeItem, includeItem);
    }

    private static int findMaxValKp_Memoization(int index, int wt, int[][] itemInventory, int[][] dp){
        if(index == 0){
            if(itemInventory[0][0] <= wt){
                return dp[0][0] = itemInventory[0][1];
            } else {
                return dp[0][0] = 0;
            }
        }
        if(dp[index][wt] != -1){
            return dp[index][wt];
        }
        int excludeValue = findMaxValKp_Memoization(index - 1, wt, itemInventory, dp);
        int includeValue = Integer.MIN_VALUE;
        if(itemInventory[index][0] <= wt){
            includeValue = itemInventory[index][1] + findMaxValKp_Memoization(index - 1, wt - itemInventory[index][0] , itemInventory, dp);
        }
        return Math.max(excludeValue, includeValue);
    }

    private static int findMaxVal_0_1_Knapsack(int n, int wt, int[][] itemInventory, boolean recursionFlag){
        if(recursionFlag){
            return findMaxValKP(n-1, wt, itemInventory);
        }
        int[][] dp = new int[n][wt+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        return findMaxValKp_Memoization(n-1, wt, itemInventory, dp);
    }
    public static void main(String[] args){
        int n = 4;
        int[][] wtValues = new int[][]{
                {1, 5}, {2, 4}, {4, 8}, {5, 6}
        };
        int weightLimit = 5;
        System.out.println("Maximum value of given sack with weight "+weightLimit+" using recursion => "+findMaxVal_0_1_Knapsack(n, weightLimit, wtValues, true));
        System.out.println("Maximum value of given sack with weight "+weightLimit+" using memoization  => "+findMaxVal_0_1_Knapsack(n, weightLimit, wtValues, false));
    }
}
