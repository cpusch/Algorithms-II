import java.util.*;
import java.io.File;

public class CompetitionFloydWarshall {
    public static void main(String args[]) {
        String file = "/home/user/Semester2/Algorithms-II/ShortestPath/src/inputAssignment2/tinyEWD.txt";
        Graph graph = new Graph(file);
        double temp[][] = graph.getMatrix();
        for (int i = 0; i < graph.getNumNodes(); i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
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

    static double[][] floydWarshall(Graph graph) {
        double matrix[][] = new double[graph.getNumNodes()][graph.getNumNodes()];

        return matrix;
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

        // TODO
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