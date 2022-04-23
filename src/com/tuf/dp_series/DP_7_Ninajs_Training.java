package com.tuf.dp_series;

public class DP_7_Ninajs_Training {
    private static int findMaxRewards_recursive(int[][] tasks, int dayNum, int prevTask){
        int[] rewards = tasks[dayNum];
        if(dayNum == 0){// Base case
            int max = Integer.MIN_VALUE;
            for(int i=0; i<rewards.length; i++){
                if(i != prevTask){
                    max = Math.max(max, rewards[i]);
                }
            }
            return max;
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<rewards.length; i++){
            if(i != prevTask){
                max = Math.max(max, rewards[i] + findMaxRewards_recursive(tasks, dayNum - 1, i));
            }
        }
        return max;
    }

    private static int findMaxRewards_memoized(int[][] tasks, int dayNum, int prevTask, int[][] dp){
        int[] rewards = tasks[dayNum];
        if(dp[dayNum][prevTask] != 0) {
            return dp[dayNum][prevTask];
        }
        if(dayNum == 0){// Base case
            int max = Integer.MIN_VALUE;
            for(int i=0; i<rewards.length; i++){
                if(i != prevTask){
                    max = Math.max(max, rewards[i]);
                }
            }
            return dp[dayNum][prevTask] = max;
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<rewards.length; i++){
            if(i != prevTask){
                max = Math.max(max, rewards[i] + findMaxRewards_recursive(tasks, dayNum - 1, i));
            }
        }
        return dp[dayNum][prevTask] = max;
    }

    private static int findMaxRewards_tabulation(int[][] tasks){
        int[][] dp = new int[tasks.length][tasks[0].length + 1];
        // Write base case
        dp[0][0] = Math.max(tasks[0][1], tasks[0][2]);
        dp[0][1] = Math.max(tasks[0][0], tasks[0][2]);
        dp[0][2] = Math.max(tasks[0][0], tasks[0][1]);
        dp[0][3] = Math.max(Math.max(tasks[0][0], tasks[0][1]), tasks[0][2]);

        for(int i=1; i<tasks.length; i++){
            for(int prev=0; prev < tasks[0].length + 1;prev++){
                dp[i][prev] = 0;
                for(int task = 0; task<tasks[0].length; task++){
                    if(task != prev){
                        int max = tasks[i][task] + dp[i-1][task];
                        dp[i][prev] = Math.max(dp[i][prev], max);
                    }
                }
            }
        }
        return dp[tasks.length-1][tasks[0].length];
    }

    private static int findMaxRewards_tabulation_space_optimization(int[][] tasks){
        int[] dp = new int[tasks[0].length + 1];
        // Write base case
        dp[0] = Math.max(tasks[0][1], tasks[0][2]);
        dp[1] = Math.max(tasks[0][0], tasks[0][2]);
        dp[2] = Math.max(tasks[0][0], tasks[0][1]);
        dp[3] = Math.max(Math.max(tasks[0][0], tasks[0][1]), tasks[0][2]);

        for(int i=1; i<tasks.length; i++){
            int[] current = new int[tasks[0].length + 1];
            for(int prev=0; prev < 4; prev++){

                current[prev] = 0;
                for(int task = 0; task<=2; task++){
                    if(task != prev){
                        current[prev] = Math.max(current[prev], tasks[i][task] + dp[task]);
                    }
                }
            }
            dp = current;
        }
        return dp[3];
    }



    private static int findMaxRewards(int[][] tasks){
        return findMaxRewards_recursive(tasks, tasks.length - 1, 3);
    }

    private static int findMaxRewards_memoized(int[][] tasks){
        int[][] dp = new int[tasks.length][4];
        return findMaxRewards_memoized(tasks, tasks.length - 1, 3, dp);
    }

    public static void main(String[] args){
        int[][] training_tasks = new int [][]{
            {2, 1, 3}, {3, 4, 6}, {10, 1, 6}, {8, 3, 7}
        };
        System.out.println("Maximum reward from action => "+findMaxRewards(training_tasks));
        System.out.println("Maximum reward from action memoized => "+findMaxRewards_memoized(training_tasks));
        System.out.println("Maximum reward from action tabulated => "+ findMaxRewards_tabulation(training_tasks));
        System.out.println("Maximum reward from action tabulated space optimized => "+ findMaxRewards_tabulation_space_optimization(training_tasks));
    }
}
