package com.tuf.dp_series.grids.fix_starting_ending_point;

import java.util.Arrays;

public class Dp_8_Grid_Unique_Paths {
    private static int findUniquePaths(int i, int j){
        if(i == 0 && j == 0){
            return 1;
        }
        if(i <0 || j < 0){
            return 0;
        }
        return findUniquePaths(i, j-1) + findUniquePaths(i-1, j);
    }

    private static int findUniqueMemoized(int i, int j, int[][] dp){
        if(i == 0 && j == 0){
            return dp[i][j] = 1;
        }
        if(i < 0 || j < 0){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        } else {
            return findUniqueMemoized(i-1, j, dp) + findUniqueMemoized(i, j-1, dp);
        }
    }

    private static int findUniqueTabulation(int n){
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0, left = 0;
                if(j > 0){
                    left = dp[i][j-1];
                }
                if(i > 0){
                    up = dp[i-1][j];
                }
                dp[i][j] = up + left;
            }
        }
        return dp[n-1][n-1];
    }

    private static int findUniqueTabulation_Optimized(int n){
        int[] dp = new int[n];
        for(int i=0; i<n; i++){
            int[] temp = new int[n];
            for(int j=0; j< n; j++){
                if(i == 0 && j == 0){
                    temp[j] = 1;
                    continue;
                }
                int left = 0, up = 0;
                if(i > 0){
                    up = dp[j];
                }
                if(j > 0){
                   left = temp[j-1];
                }
                temp[j] = up + left;
            }
            dp = temp;
        }
        return dp[n-1];
    }


    private static int findUniquePathsStart(int i, int j, int n){
        if(i == n-1 && j == n-1){
            return 1;
        }
        if(i == n || j == n){
            return 0;
        }
        return findUniquePathsStart(i, j+1, n) + findUniquePathsStart(i+1, j, n);
    }

    private static int findUnique(int n, boolean endFlag){
        if(endFlag){
            return findUniquePaths(n-1, n-1);
        } else {
            return findUniquePathsStart(0, 0, n);
        }
    }

    private static int findUniqueMemoized(int n){
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return findUniqueMemoized(n-1, n-1, dp);
    }


    public static void main(String[] args){
        int n = 3;
        System.out.println("Unique paths starting from the end => "+findUnique(n, true));
        System.out.println("Unique paths starting from the start => "+findUnique(n, false));
        System.out.println("Unique paths starting from the end memoized => "+findUniqueMemoized(n));
        System.out.println("Unique paths using tabulation => "+findUniqueTabulation(n));
        System.out.println("Unique paths using optimized tabulation => "+findUniqueTabulation_Optimized(n));
    }

}
