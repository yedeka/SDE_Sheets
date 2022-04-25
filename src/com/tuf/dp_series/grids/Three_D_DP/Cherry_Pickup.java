package com.tuf.dp_series.grids.Three_D_DP;

import java.util.Arrays;

public class Cherry_Pickup {
    private static int findMaxCherry_Recursive(int i, int j1, int j2, int[][] grid){
        // Negative base case where we are going outside the board
        if(j1 < 0 || j1 == grid[0].length || j2 < 0 || j2 == grid[0].length){
            return (int)Math.pow(10, -9);
        }
        // Positive base case when we reach the last row
        if(i == grid.length - 1){
            if(j1 == j2){
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }
        // Now iterate over all the columns, we simply iterate over the delta which is -1..1 that allows us to iterate over all the columns of subsequent row.
        // a1 signifies column for alice
        int maxPath = Integer.MIN_VALUE;
        for(int a1=-1; a1<=1; a1++){
            // b1 signifies column for bob
            for(int b1=-1; b1<=1; b1++){
                int value = 0;
                if(j1 == j2){
                    value = grid[i][j1];
                } else {
                    value = grid[i][j1] + grid[i][j2];
                }
                value += findMaxCherry_Recursive(i+1, j1 + a1, j2+b1, grid);
                maxPath = Math.max(maxPath, value);
            }
        }
        return maxPath;
    }

    private static int findMaxCherry_Recursive_memoized(int i, int j1, int j2, int[][] grid, int[][][] dp){
        // handle negative base case
        if(j1 < 0 || j1 >= grid[0].length || j2 < 0 || j2 >= grid[0].length ){
            return (int)Math.pow(10, -9);
        }
        if(i == grid.length - 1){
            if(j1 == j2){
                return grid[i][j1];
            }
            return grid[i][j1] + grid[i][j2];
        }
        if(dp[i][j1][j2] != -1){
            return dp[i][j1][j2];
        }
        // Now after handling all the trivial cases simply iterate over the 3D array to return maximum
        int maxSum = Integer.MIN_VALUE;

        for(int a=-1; a<=1; a++){
            for(int b=-1; b<=1; b++){
                int value = 0;
                if(j1 == j2){
                    value = grid[i][j1];
                } else {
                    value = grid[i][j1] + grid[i][j2];
                }
                value += findMaxCherry_Recursive_memoized(i+1, j1+a, j2+b,grid, dp);
                maxSum = Math.max(maxSum, value);
            }
        }
        return dp[i][j1][j2] = maxSum;
    }

    private static int findMaxCherry(int[][] grid, boolean recursiveFlag){
        if(recursiveFlag){
            return findMaxCherry_Recursive(0, 0, grid[0].length - 1, grid);
        } else {
            int n = grid.length, m = grid[0].length;
            int[][][] dp = new int[n][m][m];
            for (int row1[][]: dp) {
                for (int row2[]: row1) {
                    Arrays.fill(row2, -1);
                }
            }
            return findMaxCherry_Recursive_memoized(0, 0, m-1, grid, dp);
        }

    }
    public static void main(String[] args){
        int[][] grid = new int[][]{
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        System.out.println("Maximum cherry pickup recursive => "+findMaxCherry(grid, true));
        System.out.println("Maximum cherry pickup recursive memoized => "+findMaxCherry(grid, false));
    }
}
