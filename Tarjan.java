import java.util.*;

public class Tarjan {
    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('B', 'C', 'G'));
        graph.put('B', List.of('A', 'C'));
        graph.put('C', List.of('A', 'B', 'D'));
        graph.put('D', List.of('C', 'E'));
        graph.put('E', List.of('D', 'F', 'G'));
        graph.put('F', List.of('G', 'E'));
        graph.put('G', List.of('E', 'F', 'A'));
        System.out.println(getAllArticulations(graph));
    }

    public static List<Character> getAllArticulations(Map<Character, List<Character>> graph) {
        List<Character> result = new ArrayList<>();
        dfs(graph, new HashSet<>(), new HashMap<Character, Integer>(), 0, 'A', new HashMap<Character, Integer>(), new HashMap<Character, Character>(), result);
        return result;
    }

    public static int dfs(Map<Character, List<Character>> graph, Set<Character> visited, Map<Character, Integer> discoveredTime, int t, char cur,  Map<Character, Integer> lowestTime, Map<Character,Character> parent, List<Character> res) {
        int childTreeCount = 0;
        int childLowestTime = Integer.MAX_VALUE;
        int curlowestTime = Integer.MAX_VALUE;
        visited.add(cur);
        discoveredTime.put(cur, t);
        for(char v: graph.get(cur)) {
            if(!visited.contains(v)) {
                childTreeCount++;
                parent.put(v, cur);
                childLowestTime = Math.min(dfs(graph, visited, discoveredTime,t + 1, v, lowestTime, parent, res), childLowestTime);
                curlowestTime = Math.min(curlowestTime, childLowestTime);
            }else if(parent.containsKey(cur) && parent.get(cur) != v) {
                curlowestTime = Math.min(curlowestTime, discoveredTime.get(v));
            }
        }
        lowestTime.put(cur, curlowestTime);
        if(cur == 'A'){
            if(childTreeCount > 1){
                res.add(cur);
            }
        }else if(childLowestTime != Integer.MAX_VALUE && childLowestTime >= t){
            res.add(cur);
        }
        return curlowestTime;
    }
}