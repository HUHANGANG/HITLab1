import java.util.Scanner;
import model.Graph;
import service.*;

public class Main {
    private static Graph graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path of the text file:");
        String filePath = scanner.nextLine();

        graph = GraphBuilder.buildGraph(filePath);
        System.out.println("Graph creation completed!");

        while (true) {

            System.out.println("\nSelect a menu option:");
            System.out.println("1. View directed graph");
            System.out.println("2. Query bridge words");
            System.out.println("3. Generate new text based on bridge words");
            System.out.println("4. Calculate shortest path between two words");
            System.out.println("5. Calculate PageRank of a word");
            System.out.println("6. Perform random walk");
            System.out.println("0. Exit");

            System.out.print("input: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {}

            switch (choice) {
                case 1:
                    showDirectedGraph(graph);
                    break;
                case 2:
                    System.out.print("input first word: ");
                    String word1 = scanner.nextLine().toLowerCase();
                    System.out.print("input second word: ");
                    String word2 = scanner.nextLine().toLowerCase();
                    System.out.println(queryBridgeWords(word1, word2));
                    break;
                case 3:
                    System.out.println("Enter a new text:");
                    String inputText = scanner.nextLine();
                    System.out.println(generateNewText(inputText));
                    break;
                case 4:
                    System.out.print("Enter the start word: ");
                    String from = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the end word: ");
                    String to = scanner.nextLine().toLowerCase();
                    System.out.println(calcShortestPath(from, to));
                    break;
                case 5:
                    System.out.print("Enter the word to calculate PageRank: ");
                    String word = scanner.nextLine().toLowerCase();
                    System.out.printf("PageRank of %s: %.5f\n", word, calPageRank(word));
                    break;
                case 6:
                    System.out.println("Random walk result:");
                    System.out.println(randomWalk());
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void showDirectedGraph(Graph g) {
        g.showDirectedGraph();
    }

    public static String queryBridgeWords(String word1, String word2) {
        return BridgeWordFinder.queryBridgeWords(graph, word1, word2);
    }

    public static String generateNewText(String inputText) {
        return TextGenerator.generateNewText(graph, inputText);
    }

    public static String calcShortestPath(String word1, String word2) {
        return ShortestPathFinder.calcShortestPath(graph, word1, word2);
    }

    public static Double calPageRank(String word) {
        return PageRankCalculator.calPageRank(graph, word);
    }

    public static String randomWalk() {
        return RandomWalker.randomWalk(graph);
    }
}
