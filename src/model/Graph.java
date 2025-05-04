package model;

import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;
    private Set<String> nodes; // 모든 노드를 저장하는 집합 추가

    public Graph() {
        adjacencyList = new HashMap<>();
        nodes = new HashSet<>();
    }

    public void addEdge(String from, String to) {
        adjacencyList.putIfAbsent(from, new HashMap<>());
        Map<String, Integer> neighbors = adjacencyList.get(from);
        neighbors.put(to, neighbors.getOrDefault(to, 0) + 1);

        // 모든 노드 등록 (출발 노드와 도착 노드 모두)
        nodes.add(from);
        nodes.add(to);
    }

    // 모든 노드를 반환
    public Set<String> getNodes() {
        return nodes;
    }

    // 이웃 노드들 반환
    public Map<String, Integer> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyMap());
    }

    // 특정 노드 존재 여부
    public boolean containsNode(String node) {
        return nodes.contains(node);
    }

    // 그래프 출력
    public void showDirectedGraph() {
        System.out.println("Directed Graph:");
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
