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

    // class that hanldles the graph implementation using an
    // adjacency list. Also parses file to generate graph
    static class Graph {
        private double[][] adjMatrix;
        private static int numEdges;
        private static int numNodes;

        /**
         * 
         * @param edges list of edges to then create the graph
         */
        Graph(String filename) {
            adjMatrix = parseFile(filename);
        }

        int getNumEdges() {
            return numEdges;
        }

        int getNumNodes() {
            return numNodes;
        }

        double[][] getMatrix() {
            return adjMatrix;
        }

        private static double[][] parseFile(String filename) {
            double temp[][] = null;
            try {
                File file = new File(filename);
                Scanner input = new Scanner(file);
                // skip the first two lines and set appropriat values
                numNodes = input.nextInt();
                numEdges = input.nextInt();
                temp = new double[numNodes][numNodes];
                for (int i = 0; i < numNodes; i++) {
                    for (int j = 0; j < numNodes; j++)
                        temp[i][j] = Double.POSITIVE_INFINITY;
                }
                // adds edges from file to edge list
                while (input.hasNextLine()) {
                    temp[input.nextInt()][input.nextInt()] = input.nextDouble();
                }
                input.close();
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            return temp;
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

        for (int i = 0; i < graph.getNumNodes(); i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            spt[i] = false;
        }

        distance[source] = 0;

        for (int i = 0; i < graph.getNumNodes(); i++) {
            int nodeU = getMinimumNode(spt, distance, graph);
            if (nodeU == -1)
                return distance;
            spt[nodeU] = true;

            for (int nodeV = 0; nodeV < graph.getNumNodes(); nodeV++) {
                if (graph.getMatrix()[nodeU][nodeV] >= 0) {
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

        // extracts longest path
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
