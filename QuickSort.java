/**
 * 双指针法(Hoare Partition Scheme) 变体
 * 
 * DRYRUN
 */

import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4,2,6,1,1,4};
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public void quickSort(int[] arr, int lo, int hi){
        if(lo >= hi) return;
        int pivot = partition(arr, lo, hi);
        quickSort(arr, lo, pivot-1);
        quickSort(arr, pivot+1, hi);
    }
    public int partition(int[] arr, int lo, int hi){
        Random r = new Random();
        int random = lo + r.nextInt(hi-lo+1);
        int pivot = lo, left = lo + 1, right = hi;
        swap(arr, pivot, random);
        while(true){
            while(left <= right && arr[left] < arr[pivot]){
                left++;
            }
            while(left <= right && arr[right] > arr[pivot]){
                right--;
            }
            if(left >= right){
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        swap(arr, pivot, right);
        return right;
    }
    public void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}