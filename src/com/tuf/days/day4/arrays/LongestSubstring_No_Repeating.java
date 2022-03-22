package com.tuf.days.day4.arrays;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstring_No_Repeating {
    // Better solution
    // Time Coimplexity - O(2*n) in case of repeating characters we will have left pointer traverse the same characters again until the duplicate character is removed.
    private static int findLongestNoRepeat(String strInput){
        int longestLegnth = 0, l=0 ;
        char[] chars = strInput.toCharArray();
        HashSet<Character> visitedSet = new HashSet<>();

        for(int r=0; r<chars.length; r++){
            char current = chars[r];
            while(visitedSet.contains(current)){
                l ++;
                visitedSet.remove(current);
            }
            longestLegnth = Math.max(longestLegnth, r - l+1);
            visitedSet.add(current);
        }
        return longestLegnth;
    }
    /*
    * Optimal Approach - Since we do not increment l one by one but instead we jump directly to desired location. This reduces time complexity
    * to O(N) and space complexity will be 26 considering the string has all 26 charactes. Hence we have constant space complexity.
    * */
    private static int optimalLongestNoRepeat(String strInput){
        char[] ch = strInput.toCharArray();
        int l =0, maxLength = 0;
        HashMap<Character, Integer> indexMap = new HashMap<>();

        for(int r=0; r<ch.length; r++){
            char current = ch[r];
            int lastIndex = indexMap.getOrDefault(current, -1);
            if(l<= lastIndex ){
               l =  lastIndex + 1;
            }
            indexMap.put(current, r);
            maxLength = Math.max(maxLength, r-l + 1);
        }
        return maxLength;
    }
    public static void main(String[] args){
        String strInput = "abcaabcdba";
        strInput = "ababcdede";
        System.out.println("Better Approach longest length => "+findLongestNoRepeat(strInput));
        System.out.println("Optimal Approach longest length => "+optimalLongestNoRepeat(strInput));
    }
}
