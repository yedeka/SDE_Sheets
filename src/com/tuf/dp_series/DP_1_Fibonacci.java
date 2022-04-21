package com.tuf.dp_series;

public class DP_1_Fibonacci {
    // TC -> exponential
    // SC - O(n) stack space + O(n) - DP array Space
    private static int memoization_fib(int n, int[] dp){
        if(n == 1 || n == 0){
            return n;
        }
        if(dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = memoization_fib(n-1, dp) + memoization_fib(n-2, dp);
    }

    // TC - O(N)
    // SC - O(N)
    private static int tabulation_fib(int n){
        int[] dp = new int[n+1];
        dp[0] = 0; // Base case in memoization
        dp[1] = 1; // Base case in memoization
        for(int i=2; i<=n; i++){// Resembles the recursive call
            dp[i] = dp[i-1] + dp[i-2]; // recurrance relation
        }
        return dp[n];
    }

    public static void main(String[] args){
        int n = 5;
        System.out.println("Fibonacci by memoization => "+memoization_fib(n, new int[n+1]));
        System.out.println("Fibonacci by tabulation => "+tabulation_fib(n));
    }
}
