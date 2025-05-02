import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        adjacencyList.putIfAbsent(from, new HashMap<>());
        Map<String, Integer> neighbors = adjacencyList.get(from);
        neighbors.put(to, neighbors.getOrDefault(to, 0) + 1);
    }

    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }

    public Map<String, Integer> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyMap());
    }

    public boolean containsNode(String node) {
        return adjacencyList.containsKey(node);
    }

    public void showDirectedGraph() {
        System.out.println("유향 그래프:");
        for (String from : adjacencyList.keySet()) {
            for (Map.Entry<String, Integer> entry : adjacencyList.get(from).entrySet()) {
                String to = entry.getKey();
                int weight = entry.getValue();
                System.out.printf("%s -> %s (w = %d)\n", from, to, weight);
            }
        }
    }

    public Map<String, Map<String, Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
