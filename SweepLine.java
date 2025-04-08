/**
 * 给三个数组 start[1, 2, 1, 4]、end[3, 5, 8, 9] 和 value[2, 4, 2, 10]； 在此示例中，第一个任务从第 1 天到第 3 天可用，值为 2，第二个任务从第 2 天到第 5 天可用，值为 4。您还会获得一个 int K，它表示每天可以执行的最大任务数。 返回某一天可以取得的最大值； 在此示例中，如果 K 为 3，则应返回 16，因为在第 4 天或第 5 天，您可以执行 3 个任务，其值为 4、2 和 10；
 * 03/11/2025 11:50  -  12:06
 */

/**
 * dry run : node(1,3,2) node(1,8,2) node(2,5,4) node(4,9,10)
 * node(2,5,4) node(2,3,2) node(2,8,2) node(4,9,10)  ans = 4
 * node(4,9,10) node(4,5,4)  node(4,8,2)   ans = 8
 * node(4,9,10) node(4,5,4)  node(4,8,2)   ans = 16
 */


import java.util.*;

public class SweepLine {
    public static void main(String[] args) {
        int[] start = new int[]{1,2,1,4}, end = new int[]{3,5,8,9}, value = new int[]{2,4,2,10};
        int k = 3;
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
            if(a.left == b.left){
                return b.value - a.value;
            }
            return a.left - b.left;
        });
        int ans = 0;
        for(int i=0;i<start.length;i++){
            pq.offer(new Node(start[i], end[i], value[i]));
        }
        while(!pq.isEmpty()){
            int cnt = 0, t = pq.peek().left, p = 0;
            List<Node> l = new ArrayList<>();
            while(!pq.isEmpty() && pq.peek().left == t){
                Node node = pq.poll();
                if(++cnt <= k){
                    p += node.value;
                }
                l.add(node);
            }
            ans = Math.max(ans, p);
            if(pq.isEmpty()) break;
            int q = pq.peek().left;
            for(Node node : l){
                if(node.right >= q){
                    node.left = q;
                    pq.offer(node);
                }
            }
        }
        System.out.println(ans);
    }
}
class Node {
    int left, right, value;
    Node(int a, int b, int c){
        this.left = a;
        this.right = b;
        this.value = c;
    }
}