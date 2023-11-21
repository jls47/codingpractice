import java.util.*;
import java.io.*;

/*
Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

*/


public class working {

	public static void main(String[] args) {
		int[] x = {-1, 2, 1, -4};
		int[] y = {0, 0, 0};
		int[] z = {3, -9, 2, 5, 7, 2, 8, -2, -3, -5};
		int[] zeta = {3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3, -9, 2, 5, 7, 2, 8, -2, -3, -5, 2, 4, 5, 0, 2, 6, 3};

		System.out.println(findSum(x, 1));
		System.out.println(findSum(y, 2));
		System.out.println(findSum(z, 0));
		System.out.println(findSum(zeta, 0));
		System.out.println(findSum(zeta, 1));
		System.out.println(findSum(zeta, 2));
		System.out.println(findSum(zeta, 3));
		System.out.println(findSum(zeta, 4));
		System.out.println(findSum(zeta, 5));
		System.out.println(findSum(zeta, 6));
		System.out.println(findSum(zeta, 7));
		System.out.println(findSum(zeta, 8));
		System.out.println(findSum(zeta, 9));
		
	}


	private static int findSum(int[] nums, int n) {

		if(nums.length == 3) {
			return nums[0] + nums[1] + nums[2];
		}
		List<Integer> trio = new ArrayList<Integer>();
		int[] x = {Integer.MAX_VALUE};
		for(int i = 0; i < nums.length - 2; i++) {
			gatherSums(nums, n, 0, 0, i, x);
		}
		System.out.println();

		return x[0];
	}

	private static void gatherSums(int[] nums, int n, int num, int curSum, int index, int[] trackedMin) {
		if(index < nums.length) {
			curSum += nums[index];
			num ++;
			if(num == 3) {
				if(Math.abs(curSum - n) < Math.abs(trackedMin[0] - n)) {
					trackedMin[0] = curSum;
				}
			} else {
				for(int i = index + 1; i <= nums.length - num; i++) {
					gatherSums(nums, n, num, curSum, i, trackedMin);
				}
			}
		}
	}

}