package com.tuf.days.day2.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Merge_Intervals {
    private static ArrayList<Integer[]> mergeIntervals(int[][] intervals){
        ArrayList<Integer[]> result = new ArrayList<>();
        // Sort the array based on start times
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int prevBegin = intervals[0][0];
        int prevEnd = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            int[] current = intervals[i];
            if(current[0] <= prevEnd){//Current interval starts before the previous ends indicating they can be merged
                prevEnd = Math.max(prevEnd, current[1]);
            } else {
                result.add(new Integer[]{prevBegin, prevEnd});
                prevBegin = current[0];
                prevEnd = current[1];
            }
        }
        result.add(new Integer[]{prevBegin, prevEnd});
        return result;
    }
    public static void main(String[] args){
        int[][] intervals = new int[][]{{1,3}, {2,6}, {8,10}, {15,18}};
        /*ArrayList<Integer[]> merged = mergeIntervals(intervals);
        for(Integer[] mergedIntr: merged){
            System.out.println("[ "+mergedIntr[0]+", "+mergedIntr[1]+" ]");
        }*/

        intervals = new int[][]{{1,3}, {2,4}, {2,6}, {8,9},{8,10}, {9,11},{15,18}, {16, 17}};
        ArrayList<Integer[]> merged = mergeIntervals(intervals);
        for(Integer[] mergedIntr: merged){
            System.out.println("[ "+mergedIntr[0]+", "+mergedIntr[1]+" ]");
        }

    }
}
