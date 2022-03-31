import java.util.*;
import java.io.File;

public class CompetitionFloydWarshall {
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

    static double[][] floydWarshall(Graph graph) {
        double dist[][] = new double[graph.getNumNodes()][graph.getNumNodes()];

        dist = graph.getMatrix().clone();

        for (int k = 0; k < graph.getNumNodes(); k++) {
            for (int i = 0; i < graph.getNumNodes(); i++) {
                for (int j = 0; j < graph.getNumNodes(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        return dist;
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
        Graph graph = new Graph(filename);
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        slowestWalker = Math.min(Math.min(sA, sB), sC);
        double[][] temp = floydWarshall(graph);

        for (int i = 0; i < graph.getNumNodes(); i++) {
            for (int j = 0; j < graph.getNumNodes(); j++) {
                if (temp[i][j] > longestPath) {
                    longestPath = temp[i][j];
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