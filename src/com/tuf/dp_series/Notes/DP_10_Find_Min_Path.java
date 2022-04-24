package com.tuf.dp_series.Notes;

import java.util.Arrays;

public class DP_10_Find_Min_Path {
    private static int minPathRecursive(int n, int m, int[][] grid){
        if(n == 0 && m == 0){
            return grid[n][m];
        }
        if(n < 0 || m < 0){
            return Integer.MAX_VALUE;
        }
        int topRecursiveSum = minPathRecursive(n-1, m, grid);
        int topSum = topRecursiveSum == Integer.MAX_VALUE ? Integer.MAX_VALUE :  grid[n][m] + topRecursiveSum;
        int leftSumRecursive = minPathRecursive(n, m-1, grid);
        int leftSum = leftSumRecursive == Integer.MAX_VALUE ? Integer.MAX_VALUE :  grid[n][m] + leftSumRecursive;
        return Math.min(leftSum, topSum);
    }

    private static int minPathRecursive_Memoized(int n, int m, int[][] grid, int[][] dp){
        if(n == 0 && m == 0){
            return dp[n][m] = grid[n][m];
        }
        if(n < 0 || m < 0){
            return Integer.MAX_VALUE;
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        int topRecursiveSum = minPathRecursive_Memoized(n-1, m, grid, dp);
        int topSum = topRecursiveSum == Integer.MAX_VALUE ? Integer.MAX_VALUE :  grid[n][m] + topRecursiveSum;
        int leftSumRecursive = minPathRecursive_Memoized(n, m-1, grid, dp);
        int leftSum = leftSumRecursive == Integer.MAX_VALUE ? Integer.MAX_VALUE :  grid[n][m] + leftSumRecursive;
        return dp[n][m] = Math.min(leftSum, topSum);
    }



    private static int minPathSumRecursive(int[][] grid, boolean recursiveFlag){
        int n = grid.length;
        int m = grid[0].length;
        if(recursiveFlag){
            return minPathRecursive(n-1, m-1, grid);
        } else {
            int[][] dp = new int[n][m];
            for(int[] row: dp){
                Arrays.fill(row, -1);
            }
            return minPathRecursive_Memoized(n-1, m-1, grid, dp);
        }
    }

    private static int minPathSum_Tabulation(int[][] grid){
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                int leftSum = grid[i][j], topSum = grid[i][j];
                if(i > 0){
                    topSum += dp[i-1][j];
                } else {
                    topSum += (int)Math.pow(10,9);
                }
                if(j > 0){
                    leftSum += dp[i][j-1];
                }else {
                    leftSum += (int)Math.pow(10,9);
                }
                dp[i][j] = Math.min(leftSum, topSum);
            }
        }
        return dp[n-1][m-1];
    }

    private static int minPathSum_Tabulation_spaceOptmized(int[][] grid){
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];
        for(int i=0; i<n; i++){
            int[] temp = new int[m];
            for(int j=0; j<m; j++){
                if(i == 0 && j == 0){
                    temp[j] = grid[i][j];
                    continue;
                }
                int leftSum = grid[i][j], topSum = grid[i][j];
                if(i > 0){
                    topSum += dp[j];
                } else {
                    topSum += (int)Math.pow(10,9);
                }
                if(j > 0){
                    leftSum += temp[j-1];
                }else {
                    leftSum += (int)Math.pow(10,9);
                }
                temp[j] = Math.min(leftSum, topSum);
            }
            dp = temp;
        }
        return dp[m-1];
    }

    public static void main(String[] args){
        int[][] maze = new int[][]{
                {5, 9, 6}, {11, 5, 2}
        };
        System.out.println("Minimum Path Sum recursive => "+minPathSumRecursive(maze, true));
        System.out.println("Minimum Path Sum recursive memoized => "+minPathSumRecursive(maze, false));
        System.out.println("Minimum Path Sum Tabulation => "+minPathSum_Tabulation(maze));
        System.out.println("Minimum Path Sum Tabulation space optimized => "+minPathSum_Tabulation_spaceOptmized(maze));
    }
}
