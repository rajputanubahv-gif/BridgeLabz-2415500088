import java.util.*;
class SocialNetwork {
    Map<String, List<String>> graph = new HashMap<>();
    void addFriendship(String u, String v) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    List<String> getFriends(String user) {
        return graph.getOrDefault(user, new ArrayList<>());
    }
    boolean isConnected(String u, String v) {
        return graph.getOrDefault(u, new ArrayList<>()).contains(v);
    }
    int degreeOfSeparation(String src, String dest) {
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
        SocialNetwork g = new SocialNetwork();
        g.addFriendship("Alice","Bob");
        g.addFriendship("Alice","Charlie");
        g.addFriendship("Bob","David");
        g.addFriendship("Charlie","Eve");
        g.addFriendship("David","Eve");
        System.out.println(g.getFriends("Alice"));
        System.out.println(g.isConnected("Bob","Eve"));
        System.out.println(g.degreeOfSeparation("Alice","Eve"));
    }
}