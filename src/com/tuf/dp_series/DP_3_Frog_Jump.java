package com.tuf.dp_series;

// There is a frog standing at 0th step of a stair case the frog can jump either 1 or 2 steps above the energy exhausted in reaching one step is 1 and
// in jumping 2 steps is 2. Figure out the minimum energy needed for the frog to reach nth stair
// Another variation of fibonacci series
public class DP_3_Frog_Jump {
    private static int findMinEnergy_memoized(int n, int[] energy, int[] dp){
        if(n == 0){
            return 0;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        int total = findMinEnergy_memoized(n-1, energy, dp) + Math.abs(energy[n] - energy[n-1]);
        if(n > 1){
           total = Math.min(total, findMinEnergy_memoized(n-2, energy, dp) + Math.abs(energy[n] - energy[n-2]));
        }
        return dp[n] = total;
    }

    private static int findMinEnergy_tabulation(int n, int[] energy){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = Math.abs(energy[1] - energy[0]);

        for(int i=2; i<=n; i++){
            dp[i] = Math.min(dp[i-1] + Math.abs(energy[i] - energy[i-1]),  dp[i-2] + Math.abs(energy[i] - energy[i-2]));
        }
        return dp[n];
    }

    private static int findMinEnergy_tabulation_optimized(int n, int[] energy){
        int secondPrev = 0;
        int prev = Math.abs(energy[1] - energy[0]);

        for(int i=2; i<=n; i++){
            int current = Math.min(prev + Math.abs(energy[i-1] - energy[i]), secondPrev + Math.abs(energy[i-2] - energy[i]));
            secondPrev = prev;
            prev = current;
        }
        return prev;
    }


    public static void main(String[] args){
        int n = 3;
        int[] energy = new int[]{10, 20, 30, 10};
        System.out.println("Minimum energy to climb "+n+" stairs memoization => "+findMinEnergy_memoized(n, energy, new int[n+1]));
        System.out.println("Minimum energy to climb "+n+" stairs tabulation => "+findMinEnergy_tabulation(n, energy));
        System.out.println("Minimum energy to climb "+n+" stairs tabulation optimized => "+findMinEnergy_tabulation_optimized(n, energy));
    }
}
