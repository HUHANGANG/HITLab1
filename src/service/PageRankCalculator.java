package service;
import java.util.*;
import model.Graph;
public class PageRankCalculator {
    private static final double DAMPING_FACTOR = 0.85; // d 값은 0.85로 고정
    private static final int ITERATIONS = 100;         // 반복 횟수
    private static final double EPSILON = 1e-6;        // 수렴 조건

    public static Double calPageRank(Graph graph, String word) {
        if (!graph.containsNode(word)) {
            System.out.println("The word \"" + word + "\"is not in the graph!");
            return 0.0;
        }

        Set<String> nodes = graph.getNodes();
        int n = nodes.size();
        Map<String, Double> pr = new HashMap<>();

        // 초기 PR 값 설정
        for (String node : nodes) {
            pr.put(node, 1.0 / n);
        }

        // Dead-end(출력이 없는 노드)도 처리해야 함
        for (int iter = 0; iter < ITERATIONS; iter++) {
            Map<String, Double> newPr = new HashMap<>();
            double danglingSum = 0.0;

            for (String node : nodes) {
                if (graph.getNeighbors(node).isEmpty()) {
                    danglingSum += pr.get(node);
                }
            }

            for (String node : nodes) {
                double rank = (1.0 - DAMPING_FACTOR) / n;
                rank += DAMPING_FACTOR * danglingSum / n;
                for (String other : nodes) {
                    if (graph.getNeighbors(other).containsKey(node)) {
                        int outDegree = graph.getNeighbors(other).size();
                        rank += DAMPING_FACTOR * pr.get(other) / outDegree;
                    }
                }
                newPr.put(node, rank);
            }

            // 수렴 확인
            double diff = 0.0;
            for (String node : nodes) {
                diff += Math.abs(newPr.get(node) - pr.get(node));
            }
            pr = newPr;
            if (diff < EPSILON) break;
        }

        return pr.getOrDefault(word, 0.0);
    }
}
