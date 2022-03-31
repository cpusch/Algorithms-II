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
    private int numNodes;
    private double[][] matrix;

    void parseFile(String filename) {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            // skip the first two lines and set appropriat values
            numNodes = input.nextInt();
            input.next();
            matrix = new double[numNodes][numNodes];
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++)
                    matrix[i][j] = Double.POSITIVE_INFINITY;
            }
            // adds edges from file to edge list
            while (input.hasNextLine()) {
                matrix[input.nextInt()][input.nextInt()] = input.nextDouble();
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    // helper class to locate next shortest node
    private int getMinimumNode(boolean[] mst, double[] distance) {
        double tempDistance = Double.POSITIVE_INFINITY;
        int node = -1;
        for (int i = 0; i < numNodes; i++) {
            if (mst[i] == false && tempDistance > distance[i]) {
                tempDistance = distance[i];
                node = i;
            }
        }
        return node;
    }

    double[] dijkstra(int source) {
        double[] distance = new double[numNodes];
        boolean[] spt = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            spt[i] = false;
        }

        distance[source] = 0;

        for (int i = 0; i < numNodes; i++) {
            int nodeU = getMinimumNode(spt, distance);
            if (nodeU == -1)
                return distance;
            spt[nodeU] = true;

            for (int nodeV = 0; nodeV < numNodes; nodeV++) {
                if (matrix[nodeU][nodeV] >= 0) {
                    if (spt[nodeV] == false && matrix[nodeU][nodeV] != Double.POSITIVE_INFINITY) {
                        double newDistance = matrix[nodeU][nodeV] + distance[nodeU];
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
        parseFile(filename);
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        slowestWalker = Math.min(Math.min(sA, sB), sC);
        List<double[]> nodePaths = new ArrayList<double[]>();

        for (int i = 0; i < numNodes; i++) {
            nodePaths.add(dijkstra(i));
        }

        // extracts longest path
        for (double[] paths : nodePaths) {
            for (int i = 0; i < numNodes; i++) {
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
