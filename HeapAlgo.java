import java.util.*;

public class HeapAlgo {
    public int n;
    HeapAlgo(int n){
        this.n = n;
    }
    public static void main(String[] args){
        int[] arr = new int[]{5,3,6,7,1,7,8};
        HeapAlgo obj = new HeapAlgo(arr.length);
        System.out.println(Arrays.toString(obj.sort(arr)));
    }
    public int[] sort(int[] arr){
        for(int i=n/2-1;i>=0;i--){
            helper(arr, n, i);
        }
        for(int i=n-1;i>0;i--){
            swap(arr, 0, i);
            helper(arr, i, 0);
        }
        return arr;
    }
    public void helper(int[] arr, int n, int i){
        int lchild = i * 2 + 1, rchild = i * 2 + 2, t = i;
        if(lchild < n && arr[lchild] > arr[t]){
            t = lchild;
        }
        if(rchild < n && arr[rchild] > arr[t]){
            t = rchild;
        }
        if(t != i){
            swap(arr, t, i);
            helper(arr, n, t);
        }
    }
    public void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}