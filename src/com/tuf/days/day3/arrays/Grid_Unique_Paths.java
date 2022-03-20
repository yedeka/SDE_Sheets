package com.tuf.days.day3.arrays;

import java.util.Arrays;

public class Grid_Unique_Paths {
    // Brute force approach of recursion
    // Time Complexity - O(m*n)
    // Space complexity - O(m*n) - Stack frames of all the recursive calls
    private static int findUniquePaths(int srci, int srcj, int dsti, int dstj){
        if(srci == dsti || srcj==dstj){
            return 0;
        } else if(srci == dsti - 1 && srcj == dstj - 1){
            return 1;
        }
        return findUniquePaths(srci+1,srcj, dsti, dstj) + findUniquePaths(srci,srcj+1, dsti, dstj);
    }

    // Appraoch using DP
    // Reduces time complexity from exponential to quadratic
    // Space complexity - O(M * n)
    private static int findUniquePathsDP(int srci, int srcj, int dsti, int dstj, int[][] dp){
        if(srci == dsti || srcj == dstj) {
            return 0;
        }
        if(srci == dsti - 1 && srcj == dstj - 1) {
            return 1;
        }
        int result = -1;
        if(dp[srci][srcj] != -1){
            result = dp[srci][srcj];
        } else {
            result = findUniquePathsDP(srci+1, srcj, dsti, dstj, dp) + findUniquePathsDP(srci, srcj + 1, dsti, dstj, dp);
        }
        return result;
    }

    public static void main(String[] args){
        int m = 2, n = 3;
        System.out.println("Unique paths => "+findUniquePaths(0, 0, m, n));
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));
        System.out.println("DP unique paths => "+findUniquePathsDP(0, 0, m, n, dp));
    }
}