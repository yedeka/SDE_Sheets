package com.tuf.days.day4.arrays;

import java.util.HashSet;

public class Longest_Consecutive_Subsequence {
    // Better than O(N^2) but not the best approach.
    // TC - approximately O(n^2)
    private static int twoLoopsApproach(int[] nums){
        int length = nums.length, maxLength = 0, cnt = 0;
        for(int i=0; i<length - 1; i++){
            cnt ++;
            int max = nums[i];
            int min = nums[i];
            HashSet<Integer> freqSet = new HashSet<>();
            freqSet.add(nums[i]);
            for(int j = i+1; j<nums.length; j++){
                cnt ++;
                if(freqSet.contains(nums[j])) break;
                freqSet.add(nums[j]);
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                if(max - min == j - i){
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        System.out.println(cnt);
        return maxLength;
    }

    //Best approach.
    // TC - O(N) - Only go over same elements for longest subsequence
    // SC - o(N) - Hashset is needed to store all the elements.
    private static int hashSetBasedApproach(int[] nums){
        HashSet<Integer> elementSet = new HashSet<>();
        int maxCount = 0, cnt = 0;
        // Step 1 - First populate the Element Set completely
        for(int element: nums){
            elementSet.add(element);
        }
        // Step 2 - Run a single for loop
        for(int element: nums){
            cnt += 1;
            if(!elementSet.contains(element - 1)){
                int current = element;
                int currentCnt = 1;
                while(elementSet.contains(current+1)){
                    current = current+1;
                    currentCnt += 1;
                }
                maxCount = Math.max(maxCount, currentCnt);
                cnt += 1;
            }
        }
        System.out.println(cnt);
        return maxCount;
    }

    public static void main(String[] args){
        int[] nums = new int[]{100, 200, 1, 3, 1, 2, 4};
        System.out.println("Length of longest consecutive subsequence => "+twoLoopsApproach(nums));
        System.out.println("Length of longest consecutive subsequence => "+hashSetBasedApproach(nums));
    }
}
