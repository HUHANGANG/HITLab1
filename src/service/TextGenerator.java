import java.util.*;

public class TextGenerator {
    public static String generateNewText(Graph graph, String inputText) {
        // 입력 텍스트 전처리
        inputText = inputText.toLowerCase();
        inputText = inputText.replaceAll("[^a-z\\s]", " ");
        inputText = inputText.replaceAll("\\s+", " ").trim();

        String[] words = inputText.split(" ");
        if (words.length == 0) return "";

        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < words.length - 1; i++) {
            String current = words[i];
            String next = words[i + 1];
            result.append(current).append(" ");

            // Bridge Word 찾기
            Set<String> bridges = new HashSet<>();
            Map<String, Integer> neighbors = graph.getNeighbors(current);
            for (String bridgeCandidate : neighbors.keySet()) {
                if (graph.getNeighbors(bridgeCandidate).containsKey(next)) {
                    bridges.add(bridgeCandidate);
                }
            }

            if (!bridges.isEmpty()) {
                List<String> bridgeList = new ArrayList<>(bridges);
                String bridgeWord = bridgeList.get(random.nextInt(bridgeList.size()));
                result.append(bridgeWord).append(" ");
            }
        }

        // 마지막 단어 추가
        result.append(words[words.length - 1]);

        return result.toString();
    }
}
