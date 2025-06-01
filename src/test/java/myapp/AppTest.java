package test.java.myapp;

import main.java.myapp.model.Graph;
import main.java.myapp.service.BridgeWordFinder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void testOneBridgeWordExists() {
        Graph graph = new Graph();
        graph.addEdge("you", "love");
        graph.addEdge("love", "me");

        String result = BridgeWordFinder.queryBridgeWords(graph, "you", "me");
        assertTrue(result.contains("bridge word"));
    }

    @Test
    public void testWord2DoesNotExist() {
        Graph graph = new Graph();
        graph.addEdge("you", "love");

        String result = BridgeWordFinder.queryBridgeWords(graph, "you", "them");
        assertEquals("No \"them\" in the graph!", result);
    }

    @Test
    public void testWord1DoesNotExist() {
        Graph graph = new Graph();
        graph.addEdge("love", "me");

        String result = BridgeWordFinder.queryBridgeWords(graph, "zero", "me");
        assertEquals("No \"zero\" in the graph!", result);
    }

    @Test
    public void testNoBridgeWord() {
        Graph graph = new Graph();
        graph.addEdge("hello", "x");
        graph.addEdge("y", "world");

        String result = BridgeWordFinder.queryBridgeWords(graph, "hello", "world");
        assertEquals("No bridge words from \"hello\" to \"world\"!", result);
    }



}

