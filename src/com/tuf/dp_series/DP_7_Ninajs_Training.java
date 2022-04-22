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

    private static int findMaxRewards(int[][] tasks){
        return findMaxRewards_recursive(tasks, tasks.length - 1, -1);
    }

    public static void main(String[] args){
        int[][] training_tasks = new int [][]{
            {2, 1, 3}, {3, 4, 6}, {10, 1, 6}, {8, 3, 7}
        };
        System.out.println("Maximum rewqrd from action => "+findMaxRewards(training_tasks));
    }
}
