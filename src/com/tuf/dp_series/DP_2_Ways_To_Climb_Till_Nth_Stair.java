package com.tuf.dp_series;

// You are starnding at 0th floor and you have to find number of unique ways to reach till nth stair.
// You can take either 1 or 2 steps.
// This problem eventually reduces to fibonacci.
public class DP_2_Ways_To_Climb_Till_Nth_Stair {
    private static int fib_memoization(int n, int[] dp){
        // You cant go any further from 0 also you cant jump -2 from 1
        if(n == 0 || n == 1){
            return 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        return dp[n] = fib_memoization(n-1, dp) + fib_memoization(n-2, dp);
    }

    private static int fib_tabulation(int n){
        // You cant go any further from 0 also you cant jump -2 from 1
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    private static int fib_tabulation_optimized(int n){

        int secondPrev = 1;
        int prev = 1;
        for(int i=2; i<=n; i++){
            int current = secondPrev + prev;
            secondPrev = prev;
            prev = current;
        }
        return prev;
    }




    public static void main(String[] args){
        int n = 3;
        System.out.println("Number of ways to reach 9th floor using memoization => "+fib_memoization(n, new int[n+1]));
        System.out.println("Number of ways to reach 9th floor using tabulation => "+fib_tabulation(n));
        System.out.println("Number of ways to reach 9th floor using optimized tabulation => "+fib_tabulation_optimized(n));

    }
}
