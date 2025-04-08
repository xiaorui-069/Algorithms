/**
 * Kth Largest Element in an Array

    Given an integer array nums and an integer k, return the kth largest element in the array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 */

import java.util.*;

public class QuickSelect {
    public static void main(String[] args) {
        int[] arr = new int[]{2,5,5,5,7,2,4,6};
        int k = 6;
        QuickSelect qs = new QuickSelect();
        System.out.println(qs.quickSelect(arr, 0, arr.length-1, k-1));
    }
    public int quickSelect(int[] arr, int lo, int hi, int k) {
        if(lo > hi) return arr[k];
        int pivot = partition(arr, lo, hi);
        if(pivot < k){
            return quickSelect(arr, pivot+1, hi, k);
        }else if(pivot > k){
            return quickSelect(arr, lo, pivot-1, k);
        }
        return arr[pivot];

    }
    public int partition(int[] arr, int lo, int hi) {
        Random r = new Random();
        int random = lo + r.nextInt(hi-lo+1);
        int pivot = lo, left = lo + 1, right = hi;
        swap(arr, pivot, random);
        while(true){
            while(left <= right && arr[left] > arr[pivot]){
                left++;
            }
            while(left <= right && arr[right] < arr[pivot]){
                right--;
            }
            if(left >= right) break;
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