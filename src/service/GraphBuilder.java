package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import model.Graph;
public class GraphBuilder {
    public static Graph buildGraph(String filePath) {
        Graph graph = new Graph();
        List<String> words = new ArrayList<>();

        try {
            // 파일 읽기 (줄 단위)
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                // 1. 모든 문자를 소문자로 변환
                line = line.toLowerCase();
                // 2. 특수문자와 숫자 제거, 알파벳과 공백만 남기기
                line = line.replaceAll("[^a-z\\s]", " ");
                // 3. 여러 공백을 하나로 통합
                line = line.replaceAll("\\s+", " ").trim();

                if (!line.isEmpty()) {
                    // 단어 분리
                    String[] splitWords = line.split(" ");
                    Collections.addAll(words, splitWords);
                }
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 실패: " + e.getMessage());
            System.exit(1);
        }

        // 단어 리스트를 이용해 인접 단어끼리 간선 생성
        for (int i = 0; i < words.size() - 1; i++) {
            String from = words.get(i);
            String to = words.get(i + 1);
            graph.addEdge(from, to);
        }

        return graph;
    }
}
