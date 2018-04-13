package com.abner.playground.algorithm.sort;

public class Midian {
	
	public static void main(String[] args) {
		
	}
	
	
	public static int getMedian(int[] array) {
		//偶数时， 取平均值
		if(array.length % 2 == 0){
			return (getMedian(array,array.length/2,0,array.length-1) + getMedian(array,array.length/2 + 1,0,array.length-1))/2;
		}else{
			return getMedian(array, array.length/2 + 1, 0, array.length-1);
		}
	}
	
	private static int getMedian(int[] array, int k, int low, int high){
		if(k > high - low + 1){
			return -1;
		}
		int index = partition(array, low, high);
		if(index - low < k-1) {
			return getMedian(array, k-index-1, index+1, high);
		}else if(index - low == k-1){
			return array[index];
		}else{
			return getMedian(array, k, low, index-1);
		}
	}
	
	private static int partition(int[] array, int low, int high){
		int key = array[low];
		while(low < high) {
			while(low < high && key <= array[high]){
				high--;
			}
			array[low] = array[high];
			while(low < high && key >= array[low]){
				low++;
			}
			array[high] = array[low];
		}
		array[high] = key;
		return high;
	}
}
