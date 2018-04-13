package com.abner.playground.algorithm.sort;

public class SortingUtil {
	public static void bubbleSort(int[] array) {
		for(int i=0; i < array.length-1; i++){
			for(int j=0; j < array.length-i-1; j++){
				if(array[j] > array[j+1]) {
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	public static void selectionSort(int[] array) {
		for(int i=0; i < array.length - 1; i++){
			int k = i;
			for(int j = k + 1; j < array.length; j++){
				if(array[j] < array[k]){
					k = j;
				}
			}
			if( i != k){
				int temp = array[i];
				array[i] = array[k];
				array[k] = temp;
			}
		}
	}
	
	public static void quickSort(int[] array) {
		sort(array, 0, array.length - 1);
	}
	
	//返回key的位置（key的左侧都比key小，右侧都比key大）
	private static void sort(int[] array, int low, int high){
		if(low>=high) {
			return;
		}
		int index = partition(array, low, high);
		sort(array, low, index-1);
		sort(array, index+1, high);
	}
	
	private static int partition(int[] array, int low, int high){
		int key = array[low];
		while(low < high){
			while(low < high && key<=array[high]){
				high--;
			}
			array[low] = array[high];
			while(low < high && key>=array[low]){
				low++;
			}
			array[high] = array[low];
		}
		array[high] = key;
		/*for(int a:array)
			System.out.print(a + " ");*/
		return high;
	}
	
	public static void mergeSort(int[] array, int low, int high){
		int mid = (low + high) / 2;
		if(low < high){
			
		}
	}
	
	private static void merge(int[] a, int low, int mid, int high){
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid+1;
		int k=0;
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{5,7,8,10,2,3,9};
		partition(array, 0, 6);
	}
}
