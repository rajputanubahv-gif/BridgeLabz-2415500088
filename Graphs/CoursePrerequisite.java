import java.util.*;
class CoursePrerequisite {
    static boolean hasCycle(Map<String, List<String>> graph) {
        Set<String> visiting = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (dfs(node, graph, visiting, visited)) return true;
        }
        return false;
    }
    static boolean dfs(String node, Map<String,List<String>> graph,
                       Set<String> visiting, Set<String> visited) {
        if (visiting.contains(node)) return true;
        if (visited.contains(node)) return false;
        visiting.add(node);
        for (String nei : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs(nei, graph, visiting, visited)) return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
    static List<String> topoSort(Map<String,List<String>> graph) {
        Map<String,Integer> indegree = new HashMap<>();
        for (String node : graph.keySet()) indegree.put(node, 0);
        for (String node : graph.keySet()) {
            for (String nei : graph.get(node)) {
                indegree.put(nei, indegree.getOrDefault(nei,0)+1);
            }
        }
        Queue<String> q = new LinkedList<>();
        for (String node : indegree.keySet()) {
            if (indegree.get(node)==0) q.add(node);
        }
        List<String> order = new ArrayList<>();
        while (!q.isEmpty()) {
            String curr = q.poll();
            order.add(curr);
            for (String nei : graph.getOrDefault(curr,new ArrayList<>())) {
                indegree.put(nei, indegree.get(nei)-1);
                if (indegree.get(nei)==0) q.add(nei);
            }
        }
        return order;
    }
    public static void main(String[] args) {
        Map<String,List<String>> graph = new HashMap<>();
        graph.put("CS101", Arrays.asList("CS102","CS201"));
        graph.put("CS102", Arrays.asList("CS202"));
        graph.put("MATH101", Arrays.asList("CS201"));
        System.out.println(hasCycle(graph));
        System.out.println(topoSort(graph));
    }
}