package service;
import java.util.*;
import model.Graph;

public class BridgeWordFinder {
    public static String queryBridgeWords(Graph graph, String word1, String word2) {
        if (!graph.containsNode(word1) || !graph.containsNode(word2)) {
            return "No " + (graph.containsNode(word1) ? "" : "\"" + word1 + "\" ") +
                    (graph.containsNode(word2) ? "" : "\"" + word2 + "\" ") +
                    "in the graph!";
        }

        Set<String> bridges = new HashSet<>();
        Map<String, Integer> neighborsOfWord1 = graph.getNeighbors(word1);
        for (String bridgeCandidate : neighborsOfWord1.keySet()) {
            Map<String, Integer> neighborsOfCandidate = graph.getNeighbors(bridgeCandidate);
            if (neighborsOfCandidate.containsKey(word2)) {
                bridges.add(bridgeCandidate);
            }
        }

        if (bridges.isEmpty()) {
            return "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
        } else if (bridges.size() == 1) {
            return "The bridge word from \"" + word1 + "\" to \"" + word2 + "\" is: \"" +
                    bridges.iterator().next() + "\".";
        } else {
            StringJoiner sj = new StringJoiner(", ", "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ", ".");
            for (String bridge : bridges) {
                sj.add("\"" + bridge + "\"");
            }
            return sj.toString();
        }
    }
}
