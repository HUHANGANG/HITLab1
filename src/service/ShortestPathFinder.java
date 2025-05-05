package service;
import java.util.*;
import model.Graph;
public class ShortestPathFinder {
    public static String calcShortestPath(Graph graph, String word1, String word2) {
        if (!graph.containsNode(word1) || !graph.containsNode(word2)) {
            return "No " + (graph.containsNode(word1) ? "" : "\"" + word1 + "\" ") +
                    (graph.containsNode(word2) ? "" : "\"" + word2 + "\" ") +
                    "in the graph!";
        }
        
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        for (String node : graph.getNodes()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(word1, 0);
        queue.offer(new Node(word1, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.word.equals(word2)) break;

            for (Map.Entry<String, Integer> neighborEntry : graph.getNeighbors(current.word).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int newDist = distance.get(current.word) + weight;

                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    previous.put(neighbor, current.word);
                    queue.offer(new Node(neighbor, newDist));
                }
            }
        }

        if (distance.get(word2) == Integer.MAX_VALUE) {
            return "No path from \"" + word1 + "\" to \"" + word2 + "\"!";
        }

        LinkedList<String> path = new LinkedList<>();
        String current = word2;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }

        StringJoiner sj = new StringJoiner(" -> ");
        for (String word : path) {
            sj.add(word);
        }
        return "Shortest path: " + sj.toString() + "\nTotal weight: " + distance.get(word2);
    }

    private static class Node {
        String word;
        int distance;

        Node(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }
}
