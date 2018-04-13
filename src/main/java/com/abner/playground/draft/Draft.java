package com.abner.playground.draft;

public class Draft {
	
	//返回key的位置（key的左侧都比key小，右侧都比key大）
	private int partition(int array[], int left, int right) {
		int key = array[left];
		while(left < right){
			while(left < right && key <= array[right]){
				right--;
			}
			array[left] = array[right];
			while(left < right && key >= array[left]){
				left++;
			}
			array[right] = array[left];
		}
		array[right] = key;
		return right;
	}
	
	private void recursion(int array[], int left, int right) {
		if(left>=right){
			return;
		}
		int index = partition(array, left, right);
		recursion(array, left, index - 1);
		recursion(array, index + 1, right);
	}
	
	public void quickSort(int array[]){
		recursion(array, 0, array.length - 1);
	}
	
}
