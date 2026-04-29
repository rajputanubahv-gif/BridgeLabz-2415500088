import java.util.*;
class CityRoadNetwork {
    static class Pair {
        String node; int dist;
        Pair(String n, int d){ node=n; dist=d; }
    }
    Map<String,List<Pair>> graph = new HashMap<>();
    void addEdge(String u, String v, int w, boolean bidir) {
        graph.putIfAbsent(u,new ArrayList<>());
        graph.putIfAbsent(v,new ArrayList<>());
        graph.get(u).add(new Pair(v,w));
        if (bidir) graph.get(v).add(new Pair(u,w));
    }
    void bfs(String src) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(src);
        visited.add(src);
        while (!q.isEmpty()) {
            String curr = q.poll();
            System.out.print(curr + " ");
            for (Pair p : graph.get(curr)) {
                if (!visited.contains(p.node)) {
                    visited.add(p.node);
                    q.add(p.node);
                }
            }
        }
    }
    public static void main(String[] args) {
        CityRoadNetwork g = new CityRoadNetwork();
        g.addEdge("A","B",5,false);
        g.addEdge("B","C",3,true);
        g.addEdge("A","D",7,true);
        g.addEdge("D","E",2,false);
        g.addEdge("C","E",4,false);
        g.bfs("A");
    }
}