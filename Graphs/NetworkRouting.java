import java.util.*;
class NetworkRouting {
    static Map<String,List<String>> graph = new HashMap<>();
    static void addEdge(String u, String v) {
        graph.putIfAbsent(u,new ArrayList<>());
        graph.putIfAbsent(v,new ArrayList<>());
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    static boolean isConnected(String start) {
        Set<String> visited = new HashSet<>();
        dfs(start, visited);
        return visited.size() == graph.size();
    }
    static void dfs(String node, Set<String> visited) {
        visited.add(node);
        for (String nei : graph.get(node)) {
            if (!visited.contains(nei)) dfs(nei, visited);
        }
    }
    static int minHops(String src, String dest) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(src);
        visited.add(src);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String curr = q.poll();
                if (curr.equals(dest)) return level;
                for (String nei : graph.get(curr)) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        q.add(nei);
                    }
                }
            }
            level++;
        }
        return -1;
    }
    public static void main(String[] args) {
        addEdge("R1","R2");
        addEdge("R1","R3");
        addEdge("R2","R4");
        addEdge("R3","R4");
        addEdge("R4","R5");
        addEdge("R5","R6");
        System.out.println(isConnected("R1"));
        System.out.println(minHops("R1","R6"));
    }
}