package com.abner.playground.algorithm.sort;

public class SortClient {
	public static void main(String[] args) {
		int[] array = new int[]{6,3,8,2,9,1};
		
		//bubble sort
		//SortingUtil.bubbleSort(array);
		//quick sort
		SortingUtil.quickSort(array);
		//selection sort
		
		printArray(array);
	}
	
	private static void printArray(int[] array){
		for(int a : array) {
			System.out.print(a + " ");
		}
	}
}
