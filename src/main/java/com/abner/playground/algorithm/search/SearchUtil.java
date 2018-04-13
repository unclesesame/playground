package com.abner.playground.algorithm.search;

public class SearchUtil {
	
	public static int binarySearch(int[] array, int a){
		int low = 0;
		int high = array.length -1;
		int mid;
		while(low < high){
			mid = (low + high) /2;
			if(array[mid] == a){
				return mid;
			}else if(array[mid] < a){
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		return -1;
	}
}
