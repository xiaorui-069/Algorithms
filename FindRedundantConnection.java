import java.util.*;

/**
 * 0 ----- 3
 * \  \   /
 *  \  \ /
 *   1 - 2
 */

class FindRedundantConnection {

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>(Arrays.asList(0,1)));
        graph.add(new ArrayList<>(Arrays.asList(2,0)));
        graph.add(new ArrayList<>(Arrays.asList(3,0)));
        graph.add(new ArrayList<>(Arrays.asList(3,2)));
        graph.add(new ArrayList<>(Arrays.asList(1,2)));
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(List<Integer> l : graph){
            int p1 = l.get(0), p2 = l.get(1);
            map.putIfAbsent(p1, new ArrayList<>());
            map.putIfAbsent(p2, new ArrayList<>());
            map.get(p1).add(p2);
            map.get(p2).add(p1);
        }
        int N = 4;
        dfs(map, 0, -1, new boolean[N], res);
        for(List<Integer> l : res){
            System.out.println(l.get(0) +" , "+ l.get(1));
        }
    }
    public static void dfs(Map<Integer, List<Integer>> graph, int k, int p, boolean[] v, List<List<Integer>> res){
        v[k] = true;
        for(int c : graph.get(k)){
            if(c == p) continue;
            if(!v[c]){
                dfs(graph, c, k, v, res);
            }else{
                res.add(new ArrayList<>(Arrays.asList(c, k)));
            }
        }
    }
}