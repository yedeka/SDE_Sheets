package com.tuf.dp_series.grids.fix_starting_variable_ending;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DP_11_Minimum_Triangle_Sum {
    private static int triangleSumRecursive(List<List<Integer>> triangle, int i, int j) {
        if(i == triangle.size()-1){
            return triangle.get(i).get(j);
        }
        return triangle.get(i).get(j)+ Math.min(triangleSumRecursive(triangle,i+1, j), triangleSumRecursive(triangle,i+1, j+1));
    }
    private static int triangleSumRecursive_Memoized(List<List<Integer>> triangle, int i, int j, List<List<Integer>> dp) {
        if(i == triangle.size()-1){
            dp.get(i).set(j, triangle.get(i).get(j));
            return dp.get(i).get(j);
        }
        if(dp.get(i).get(j) != -1){
            return dp.get(i).get(j);
        }
        int minValue = triangle.get(i).get(j)+ Math.min(triangleSumRecursive_Memoized(triangle,i+1, j, dp), triangleSumRecursive_Memoized(triangle,i+1, j+1, dp));
        dp.get(i).set(j, minValue);
        return dp.get(i).get(j);
    }

    private static int findMinTriangleSum(List<List<Integer>> triangle, boolean memoizationFlag){
        if(memoizationFlag){
            List<List<Integer>> dp = new ArrayList<>();
            for(List<Integer> row: triangle){
                ArrayList<Integer> copy = new ArrayList<>();
                for(int i=0; i<row.size(); i++){
                    copy.add(-1);
                }
                dp.add(copy);
            }
            return triangleSumRecursive_Memoized(triangle, 0, 0, dp);
        } else {
            return triangleSumRecursive(triangle, 0, 0);
        }
    }

    private static int findMinTriangleSum_Tabulation(List<List<Integer>> triangle){
        List<List<Integer>> dp = new ArrayList<>();
        for(List<Integer> row: triangle){
            ArrayList<Integer> copy = new ArrayList<>();
            for(int i=0; i<row.size(); i++){
                copy.add(-1);
            }
            dp.add(copy);
        }
        // Step 1 - Populate last row of DP array with the triangle list values itself.
        int n = triangle.get(triangle.size() - 1).size();
        dp.set(n-1,triangle.get(n-1));
        // Step 2 - Now run the DP in bottom up fashion from n-2 .... 0 to get the minimum triangle sum
        for(int i= n-2; i>=0; i--){
            for(int j=i; j>=0; j--){
                int existingValue = triangle.get(i).get(j);
                int minValue = Math.min(dp.get(i+1).get(j), dp.get(i+1).get(j+1));
                dp.get(i).set(j, existingValue + minValue);
            }
        }
        return dp.get(0).get(0);
    }

    private static int findMinTriangleSum_Tabulation_Optimized(List<List<Integer>> triangle){

        // Step 1 - Populate last row of DP array with the triangle list values itself.
        int n = triangle.get(triangle.size() - 1).size();
        List<Integer> dp = new ArrayList<>();
        for(Integer element: triangle.get(n-1)){
            dp.add(element);
        }

        // Step 2 - Now run the DP in bottom up fashion from n-2 .... 0 to get the minimum triangle sum
        for(int i= n-2; i>=0; i--){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j=0; j<dp.size()-1; j++){
                int existingValue = triangle.get(i).get(j);
                int minValue = Math.min(dp.get(j), dp.get(j+1));
                temp.add(existingValue + minValue);
            }
            dp = temp;
        }
        return dp.get(0);
    }

    public static void main(String[] args){
        List<List<Integer>> triangle = new ArrayList<>();
        int n = 4;
        for(int i=0; i<n; i++){
            triangle.add(new ArrayList<>());
        }
        triangle.get(0).add(1);
        triangle.get(1).add(2);
        triangle.get(1).add(3);
        triangle.get(2).add(3);
        triangle.get(2).add(6);
        triangle.get(2).add(7);
        triangle.get(3).add(8);
        triangle.get(3).add(9);
        triangle.get(3).add(6);
        triangle.get(3).add(10);

        System.out.println("Minimum triangle sum recursive => "+findMinTriangleSum(triangle, false));
        System.out.println("Minimum triangle sum memoization => "+findMinTriangleSum(triangle, true));
        System.out.println("Minimum triangle sum tabulation => "+findMinTriangleSum_Tabulation(triangle));
        System.out.println("Minimum triangle sum tabulation space optimization => "+findMinTriangleSum_Tabulation_Optimized(triangle));
    }
}
