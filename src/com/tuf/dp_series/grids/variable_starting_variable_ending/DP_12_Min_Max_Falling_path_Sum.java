package com.tuf.dp_series.grids.variable_starting_variable_ending;

import java.util.Arrays;

public class DP_12_Min_Max_Falling_path_Sum {
    private static int findFallingPathSumRecursive(int i, int j, int[][]input){
        if(j < 0 || j >= input[0].length){
            return (int)Math.pow(10, -9);
        }
        if(i == 0){
            return input[0][j];
        }
        int up = input[i][j] + findFallingPathSumRecursive(i-1, j, input);
        int ld = input[i][j] + findFallingPathSumRecursive(i-1, j-1, input);
        int rd = input[i][j] + findFallingPathSumRecursive(i-1, j+1, input);

        return Math.max(rd, Math.max(up, ld));
    }

    private static int findFallingPathSumRecursive_memoized(int i, int j, int[][]input, int[][] dp){
        if(j < 0 || j >= input[0].length){
            return dp[i][j] = (int)Math.pow(10, -9);
        }
        if(i == 0){
            return dp[i][j] =  input[0][j];
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int up = input[i][j] + findFallingPathSumRecursive(i-1, j, input);
        int ld = input[i][j] + findFallingPathSumRecursive(i-1, j-1, input);
        int rd = input[i][j] + findFallingPathSumRecursive(i-1, j+1, input);

        return dp[i][j] = Math.max(rd, Math.max(up, ld));
    }

    private static int findMaxFallingSum(int[][] input, boolean recursiveFlag){
        int n= input.length, m = input[0].length;
        if(recursiveFlag){
            int maxSum = Integer.MIN_VALUE;
            for(int j=0; j<m; j++){
                maxSum = Math.max(maxSum, findFallingPathSumRecursive(n-1, j, input));
            }
            return maxSum;
        } else {
            int[][] dp = new int[n][m];
            for(int[] rows: dp){
                Arrays.fill(rows, -1);
            }
            int maxSum = Integer.MIN_VALUE;
            for(int j=0; j<m; j++){
                maxSum = Math.max(maxSum, findFallingPathSumRecursive_memoized(n-1, j, input, dp));
            }
            return maxSum;
        }
    }


    public static void main(String[] args){
        int[][] input = new int[][]{
                {1, 2, 10, 4},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1}
        };
        System.out.println("Maximum Falling Path Sum recursive => "+findMaxFallingSum(input, true));
        System.out.println("Maximum Falling Path Sum memoized => "+findMaxFallingSum(input, false));
    }
}
