package service;
import java.util.*;
import model.Graph;
public class RandomWalker {
    public static String randomWalk(Graph graph) {
        Set<String> visitedEdges = new HashSet<>();
        List<String> path = new ArrayList<>();
        Random random = new Random();

        List<String> nodes = new ArrayList<>(graph.getNodes());
        if (nodes.isEmpty()) return "";

        String current = nodes.get(random.nextInt(nodes.size()));
        path.add(current);

        while (true) {
            Map<String, Integer> neighbors = graph.getNeighbors(current);
            if (neighbors.isEmpty()) break; // 더 이상 갈 곳 없음

            List<String> neighborList = new ArrayList<>(neighbors.keySet());
            String next = neighborList.get(random.nextInt(neighborList.size()));

            String edge = current + "->" + next;
            if (visitedEdges.contains(edge)) {
                break;
            }

            visitedEdges.add(edge);
            path.add(next);
            current = next;
        }

        return String.join(" ", path);
    }
}
