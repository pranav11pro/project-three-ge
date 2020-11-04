// --== CS400 File Header Information ==--
// Name: Pranav Agrawal
// Email: pragrawal@wisc.edu
// Team: GE
// TA: Daniel K
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<Integer> graph;
    @BeforeEach
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled 0 to 8
     * (should be 15), and from the vertex labelled 9 to 8 (should be 17).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost(0,8) == 15);    
        assertTrue(graph.getPathCost(9,8) == 17);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled 0 to 8, and from the vertex labelled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath(0, 8).toString().equals(
            "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
            "[9, 4, 5, 1, 8]"
        ));
    }
    
    /**
     * Checks the distance/total weight cost from the vertex labeled 4 to 8
     * (should be 12), according to the value reported in the activity in the previous week.
     */
    @Test
    public void checkLongestPathCost_activity() {
        assertTrue(graph.getPathCost(4, 8) == 12);    
    }
    
    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled 4 to 8, according to nodes from the previous test.
     */
    @Test
    public void checkLongestPathContent_activity() {
        assertTrue(graph.shortestPath(4, 8).toString().equals(
            "[4, 5, 1, 8]"
        ));
    }
    
    /**
     * Checks the sequence and weight of a path from a vertex to itself
     */
    @Test
    public void checkZeroPath() {
      assertTrue(graph.getPathCost(0,0) == 0);    
      assertTrue(graph.shortestPath(0, 0).toString().equals(
          "[0]"
      ));
    }
    
    /**
     * Checks the sequence and weight after passing a null vertex
     */
    @Test
    public void checkNullVertex() {
      try {
        graph.getPathCost(null, null);
        graph.shortestPath(null, null);
      }
      catch(NullPointerException e) {};
    }
}