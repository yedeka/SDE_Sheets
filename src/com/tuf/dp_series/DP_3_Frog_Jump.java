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
    public static void main(String[] args){
        int n = 3;
        int[] energy = new int[]{10, 20, 30, 10};
        System.out.println("Minimum energy to climb "+n+" stairs => "+findMinEnergy_memoized(n, energy, new int[n+1]));
    }
}
