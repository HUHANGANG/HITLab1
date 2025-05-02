import java.util.Scanner;

public class Main {
    private static Graph graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 텍스트 파일 경로 입력
        System.out.println("텍스트 파일 경로를 입력하세요:");
        String filePath = scanner.nextLine();

        // 2. 그래프 생성
        graph = GraphBuilder.buildGraph(filePath);
        System.out.println("그래프 생성 완료!");

        while (true) {
            // 3. 메뉴 출력
            System.out.println("\n메뉴 선택:");
            System.out.println("1. 유향 그래프 보기");
            System.out.println("2. Bridge Words 조회");
            System.out.println("3. Bridge Word 기반 새 텍스트 생성");
            System.out.println("4. 두 단어 간 최단 경로 계산");
            System.out.println("5. 단어의 PageRank 계산");
            System.out.println("6. 랜덤 워크 실행");
            System.out.println("0. 종료");

            System.out.print("입력: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {}

            switch (choice) {
                case 1:
                    showDirectedGraph(graph);
                    break;
                case 2:
                    System.out.print("첫 번째 단어 입력: ");
                    String word1 = scanner.nextLine().toLowerCase();
                    System.out.print("두 번째 단어 입력: ");
                    String word2 = scanner.nextLine().toLowerCase();
                    System.out.println(queryBridgeWords(word1, word2));
                    break;
                case 3:
                    System.out.println("새로운 텍스트를 입력하세요:");
                    String inputText = scanner.nextLine();
                    System.out.println(generateNewText(inputText));
                    break;
                case 4:
                    System.out.print("출발 단어 입력: ");
                    String from = scanner.nextLine().toLowerCase();
                    System.out.print("도착 단어 입력: ");
                    String to = scanner.nextLine().toLowerCase();
                    System.out.println(calcShortestPath(from, to));
                    break;
                case 5:
                    System.out.print("PageRank를 계산할 단어 입력: ");
                    String word = scanner.nextLine().toLowerCase();
                    System.out.printf("%s의 PageRank: %.5f\n", word, calPageRank(word));
                    break;
                case 6:
                    System.out.println("랜덤 워크 결과:");
                    System.out.println(randomWalk());
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
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
