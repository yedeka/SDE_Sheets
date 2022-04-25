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

    private static int findFallingPathSumRecursive_tabulation(int[][]input){
        int[][] dp = new int[input.length][input[0].length];
        int n = input.length, m = input[0].length;
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        dp[0] = input[0];
        int rowMax = Integer.MIN_VALUE;
        for(int i=1; i<n; i++){
            int top = 0, ld = 0, rd = 0;
            for(int j=0; j<m; j++) {
                top = input[i][j] + dp[i - 1][j];
                if (j > 0) {
                    ld = input[i][j] + dp[i - 1][j - 1];
                }
                if (j < m - 1) {
                    rd = input[i][j] + dp[i - 1][j + 1];
                }
                int localMax = Math.max(top, Math.max(ld, rd));
                dp[i][j] = localMax;
                rowMax = Math.max(rowMax, dp[i][j]);
            }
        }
        return rowMax;
    }

    private static int findFallingPathSumRecursive_tabulation_optimized(int[][]input){
        int[] dp = new int[input[0].length];
        int n = input.length, m = input[0].length;
        dp = input[0];
        int rowMax = Integer.MIN_VALUE;
        for(int i=1; i<n; i++){
            int top = 0, ld = 0, rd = 0;
            int[] temp = new int[m];
            for(int j=0; j<m; j++) {
                top = input[i][j] + dp[j];
                if (j > 0) {
                    ld = input[i][j] + dp[j - 1];
                }
                if (j < m - 1) {
                    rd = input[i][j] + dp[j + 1];
                }
                int localMax = Math.max(top, Math.max(ld, rd));
                temp[j] = localMax;
                rowMax = Math.max(rowMax, temp[j]);
            }
            dp = temp;
        }
        return rowMax;
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
        System.out.println("Maximum Falling Path Sum tabulation => "+findFallingPathSumRecursive_tabulation(input));
        System.out.println("Maximum Falling Path Sum space optimized tabulation => "+findFallingPathSumRecursive_tabulation_optimized(input));
    }
}