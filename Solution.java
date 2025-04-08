
import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(dp(new int[]{1,2,1,3,3}));
        /**
         * prefix : [0,1,3,4,7,10]
         * dp :     [0,1,2,2,3,4]
         * t :      [0,1,2,3,3,3]
         */
    }

    /** O (n * n) */
    public static int dp(int[] arr){
        int n = arr.length;
        int[] prefix = new int[n + 1], dp = new int[n+1], t = new int[n + 1];
        Arrays.fill(t, Integer.MAX_VALUE);
        t[0] = 0;
        for(int i=1;i<prefix.length;i++){
            prefix[i] = prefix[i-1] + arr[i-1];
        }
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                /** The bug is right here, should be prefix[i] - prefix[j] not prefix[i] - prefix[j-1] */
                if((prefix[i] - prefix[j] >= t[j]) && dp[j] + 1 >= dp[i]){
                    dp[i] = dp[j] + 1;
                    t[i] = Math.min(t[i], prefix[i] - prefix[j]);
                }
            }
        }
        return dp[n];
    }
    /**
     * 首先要降低复杂度 我门需要知道哪里是冗余的, 在针对每个外层循环 我们都要做一遍内层循环判断 prefix[i] >= t[j] + prefix[j]
     * 
     * 也就是说 我们怎么能减少内层的循环次数 ————
     * 
     * 同时我们也发现 [1,6,3,8] t = 11  [1,6,3,8,1] t = 9  所以 t[j] 和 prefix[j] + t[j] 都不一定是递增的
     * 
     * 那么 j > k 且 prefix[j] + t[j] <= prefix[k] + t[k] 时, k可以被舍弃
     * 
     * 那针对 k < j 且 prefix[k] + t[k] <= prefix[j] + t[j] 时, 因为 我门知道 dp[k] <= dp[j], t[i] 和 prefix[i] - prefix[k or j] 有关, 那么我们可以知道 prefix[i] - prefix[k] 一定 大于 prefix[i] - prefix[j] (k < j) 所以 如果 k和j都行, 那么 最小的 t[i] 一定是被prefix[i] - prefix[j] 贡献的, 所以我们可得 dp[k] <= dp[j] 同时 最小的 t[i]（也就是递增序列最大的数）来自于 j, 那么 k 同样就可以被舍弃
     * 
     *  但是 如果 k < j k符合条件, j不符合条件, 那么 我们需要找到一个 _j (k<=_j) 使得 _j 可行, 由此 我门想到 使用 二分法找到那么最大的可行 _j.        -----时间复杂度是O(nlogn)------
     * 
     * 
     * 那么可以将时间复杂度降到O(n)吗, 也就是说 在随着i增大, 上述 小于 _j 的 部分 永远不会被使用了。
     * 答案是可行的, 因为我们需要判断 prefix[i] >= t[j] + prefix[j]. 随着i增大, prefix[i] 是一定增大的, 那么 如果 t[_j] + prefix[_j] 满足 <= prefix[i] 那么 它也满足 <= prefix[i+1], 之前证明了 k 和 j （k < j）都行时，最小的 t[i] 只和 j 有关, 那么说明随着i增大，k也无影响。所以我们甚至不需要一直保留着 从 0 - i-1 的 所有 prefix[k] + t[k] 的结果, 而是 将 _j 之前的都丢弃。 ------时间复杂度 O(n) ---------
     * 
     */

}

