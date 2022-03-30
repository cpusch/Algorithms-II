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
    private double longestPath = 0;
    private int sA, sB, sC;
    private int slowestWalker;

    public static void main(String args[]) {
        String file = "/home/user/Semester2/Algorithms-II/ShortestPath/src/inputAssignment2/tinyEWD.txt";
        CompetitionDijkstra test = new CompetitionDijkstra(file, 50, 80, 70);
        System.out.println(test.timeRequiredforCompetition());
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
        double tempDistance = Double.POSITIVE_INFINITY;
        int node = -1;
        for (int i = 0; i < graph.getNumNodes(); i++) {
            if (mst[i] == false && tempDistance > distance[i]) {
                tempDistance = distance[i];
                node = i;
            }
        }
        return node;
    }

    double[] dijkstra(Graph graph, int source) {
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
        Graph graph = new Graph(filename);
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        slowestWalker = Math.min(Math.min(sA, sB), sC);
        List<double[]> nodePaths = new ArrayList<double[]>();

        for (int i = 0; i < graph.getNumNodes(); i++) {
            nodePaths.add(dijkstra(graph, i));
        }

        for (double[] paths : nodePaths) {
            for (int i = 0; i < graph.getNumNodes(); i++) {
                if (paths[i] > longestPath) {
                    longestPath = paths[i];
                }
            }
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can
     *         meet
     */
    public int timeRequiredforCompetition() {
        if (!((sA >= 50 && sA <= 100) && (sB >= 50 && sB <= 100) && (sC >= 50 && sC <= 100)))
            return -1;
        else if (longestPath == Double.POSITIVE_INFINITY || longestPath == 0)
            return -1;

        else
            return (int) Math.ceil((longestPath * 1000.0) / slowestWalker);
    }

}
