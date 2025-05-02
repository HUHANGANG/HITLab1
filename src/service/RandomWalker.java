import java.util.*;

public class RandomWalker {
    public static String randomWalk(Graph graph) {
        Set<String> visitedEdges = new HashSet<>();
        List<String> path = new ArrayList<>();
        Random random = new Random();

        List<String> nodes = new ArrayList<>(graph.getNodes());
        if (nodes.isEmpty()) return "";

        // 1. 랜덤한 시작 노드 선택
        String current = nodes.get(random.nextInt(nodes.size()));
        path.add(current);

        while (true) {
            Map<String, Integer> neighbors = graph.getNeighbors(current);
            if (neighbors.isEmpty()) break; // 더 이상 갈 곳 없음

            List<String> neighborList = new ArrayList<>(neighbors.keySet());
            String next = neighborList.get(random.nextInt(neighborList.size()));

            String edge = current + "->" + next;
            if (visitedEdges.contains(edge)) {
                break; // 이미 방문한 간선이면 멈춤
            }

            visitedEdges.add(edge);
            path.add(next);
            current = next;
        }

        // 결과 반환 (노드들을 공백으로 연결)
        return String.join(" ", path);
    }
}
