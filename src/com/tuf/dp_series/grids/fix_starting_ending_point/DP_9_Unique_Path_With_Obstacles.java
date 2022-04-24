package com.tuf.dp_series.grids.fix_starting_ending_point;

import java.util.Arrays;

public class DP_9_Unique_Path_With_Obstacles {
    private static int findUniquepathsRecursive(int n, int m, int[][] grid){
        if(n == 0 && m ==0){
            return 1;
        }
        if(n < 0 || m < 0 || grid[n][m] == -1){
            return 0;
        }
        int left = findUniquepathsRecursive(n, m-1, grid);
        int top = findUniquepathsRecursive(n-1, m, grid);
        return left + top;
    }

    private static int findUniquePathsMemoized(int n, int m, int[][] grid, int[][] dp){
        if(n == 0 && m ==0){
            return dp[n][m] = 1;
        }
        if(grid[n][m] == -1){
            return dp[n][m] = 0;
        }
        if(n < 0 || m < 0){
            return 0;
        }
        if(dp[n][m] != -5){
            return dp[n][m];
        }
        int left = findUniquepathsRecursive(n, m-1, grid);
        int top = findUniquepathsRecursive(n-1, m, grid);
        return dp[n][m] = left + top;
    }

    private static int findUniquePathsTabulation(int[][] grid){
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i ==0 && j ==0){
                    dp[i][j] = 1;
                    continue;
                }
                if(grid[i][j] == -1){
                    dp[i][j] = 0;
                    continue;
                }
                int top = 0, left = 0;
                if(i > 0){
                    top = dp[i-1][j];
                }
                if(j > 0){
                    left = dp[i][j-1];
                }
                dp[i][j] = top + left;
            }
        }
        return dp[n-1][m-1];
    }

    private static int findUniquePathsTabulation_space_optimized(int[][] grid){
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];

        for(int i = 0; i<n; i++){
            int[] temp = new int[m];
            for(int j=0; j<m; j++){
                if(i ==0 && j ==0){
                    temp[j] = 1;
                    continue;
                }
                if(grid[i][j] == -1){
                    temp[j] = 0;
                    continue;
                }
                int top = 0, left = 0;
                if(i > 0){
                    top = dp[j];
                }
                if(j > 0){
                    left = temp[j-1];
                }
                temp[j] = top + left;
            }
            dp = temp;
        }
        return dp[m-1];
    }



    private static int findUniquePaths(int[][] grid, boolean recursiveFlag){
        int n= grid.length;
        int m = grid[0].length;
        if(recursiveFlag){
            return findUniquepathsRecursive(n-1, m-1, grid);
        } else {
            int[][] dp = new int[n][m];
            for(int[] rows: dp){
                Arrays.fill(rows, -5);
            }
            return findUniquePathsMemoized(n-1, m-1, grid, dp);
        }
    }
    public static void main(String[] args){
        int[][] grid = new int[][]{{0, 0, 0}, {0, -1, 0}, {0, 0, 0}};
        System.out.println("Unique paths with obstacles recursive => "+findUniquePaths(grid, true));
        System.out.println("Unique paths with obstacles memoized => "+findUniquePaths(grid, false));
        System.out.println("Unique paths with tabulation => "+findUniquePathsTabulation(grid));
        System.out.println("Unique paths with space optimized tabulation => "+findUniquePathsTabulation_space_optimized(grid));
    }
}
