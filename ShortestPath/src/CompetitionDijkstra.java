import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
    public static void main(String args[]) {
        String file = "/home/user/Semester2/Algorithms-II/ShortestPath/src/inputAssignment2/tinyEWD.txt";
        Graph graph = new Graph(file);
        System.out.println(graph.getGraph().get(0));
    }

    // class that hanldles the graph implementation using an
    // adjacency list. Also parses file to generate graph
    static class Graph {
        private List<List<Node>> adj_list = new ArrayList<>();
        static int numEdges;
        static int numNodes;

        /**
         * 
         * @param edges list of edges to then create the graph
         */
        Graph(String filename) {
            List<Edge> edges = parseFile(filename);
            // adjacency list memory allocation
            for (int i = 0; i < numNodes; i++)
                adj_list.add(i, new ArrayList<>());

            // add edges to the graph
            for (Edge e : edges) {
                // allocate new node in adjacency List from src to dest
                adj_list.get(e.src).add(new Node(e.dest, e.weight));
            }
        }

        List<List<Node>> getGraph() {
            return adj_list;
        }

        static private List<Edge> parseFile(String filename) {
            List<Edge> edges = new ArrayList<Edge>();
            try {
                File file = new File(filename);
                Scanner input = new Scanner(file);
                // skip the first two lines
                numNodes = input.nextInt();
                numEdges = input.nextInt();
                while (input.hasNextLine()) {
                    edges.add(new Edge(input.nextInt(), input.nextInt(), input.nextDouble()));
                }
                input.close();
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            return edges;
        }
    }

    // represents edges in graph
    static private class Edge {
        int src, dest;
        double weight;

        /**
         * 
         * @param src    source node
         * @param dest   destination node
         * @param weight weight of edge
         */
        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

    }

    // represents nodes in grpah
    private static class Node {
        int value;
        double weight;

        Node(int value, double weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    List<Edge> Dijkstra() {
        return new ArrayList<Edge>();
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        double minTime = 0;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can
     *         meet
     */
    public int timeRequiredforCompetition() {

        // TO DO
        return -1;
    }

}
