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
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                line = line.toLowerCase();
                line = line.replaceAll("[^a-z\\s]", " ");
                line = line.replaceAll("\\s+", " ").trim();

                if (!line.isEmpty()) {
                    String[] splitWords = line.split(" ");
                    Collections.addAll(words, splitWords);
                }
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 실패: " + e.getMessage());
            System.exit(1);
        }

        for (int i = 0; i < words.size() - 1; i++) {
            String from = words.get(i);
            String to = words.get(i + 1);
            graph.addEdge(from, to);
        }

        return graph;
    }
}
