import java.util.*;
import java.io.File;

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
        double[] temp = dijkstra(graph, 3);
        System.out.println(Arrays.toString(temp));
    }

    // class that hanldles the graph implementation using an
    // adjacency list. Also parses file to generate graph
    static class Graph {
        private List<List<Node>> adj_list = new ArrayList<>();
        private double[][] adj_matrix = new double[numNodes][numNodes];
        private static int numEdges;
        private static int numNodes;

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
            double temp[][] = new double[numNodes][numNodes];
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++)
                    temp[i][j] = Double.POSITIVE_INFINITY;
            }

            for (int i = 0; i < numNodes; i++) {
                for (Node n : adj_list.get(i)) {
                    temp[i][n.value] = n.weight;
                }
            }
            adj_matrix = temp;
        }

        int getNumEdges() {
            return numEdges;
        }

        int getNumNodes() {
            return numNodes;
        }

        List<List<Node>> getGraph() {
            return adj_list;
        }

        double[][] getMatrix() {
            return adj_matrix;
        }

        private static List<Edge> parseFile(String filename) {
            List<Edge> edges = new ArrayList<Edge>();
            try {
                File file = new File(filename);
                Scanner input = new Scanner(file);
                // skip the first two lines and set appropriat values
                numNodes = input.nextInt();
                numEdges = input.nextInt();
                // adds edges from file to edge list
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
    private static class Edge {
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

    static int getMinimumNode(boolean[] mst, double[] distance, Graph graph) {
        double minKey = Double.POSITIVE_INFINITY;
        int node = -1;
        for (int i = 0; i < graph.getNumNodes(); i++) {
            if (mst[i] == false && minKey > distance[i]) {
                minKey = distance[i];
                node = i;
            }
        }
        return node;
    }

    static double[] dijkstra(Graph graph, int source) {
        double[] distance = new double[graph.getNumNodes()];
        boolean[] spt = new boolean[graph.getNumNodes()];

        for (int i = 0; i < graph.getNumNodes(); i++)
            distance[i] = Double.POSITIVE_INFINITY;

        distance[source] = 0;

        for (int i = 0; i < graph.getNumNodes(); i++) {
            int nodeU = getMinimumNode(spt, distance, graph);

            spt[nodeU] = true;

            for (int nodeV = 0; nodeV < graph.getNumNodes(); nodeV++) {
                if (graph.getMatrix()[nodeU][nodeV] > 0) {
                    if (spt[nodeV] == false && graph.getMatrix()[nodeU][nodeV] != Double.POSITIVE_INFINITY) {
                        double newDistance = graph.getMatrix()[nodeU][nodeV] + distance[nodeU];
                        if (newDistance < distance[nodeV]) {
                            distance[nodeV] = newDistance;
                        }
                    }
                }
            }
        }
        return distance;
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
