import java.util.*;
import java.io.File;

public class CompetitionFloydWarshall {
    double longestPath = 0;
    int sA, sB, sC;
    int slowestWalker;
    double[][] matrix;
    int numNodes;

    void parseFile(String filename) {
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            // skip the first two lines and set appropriat values
            numNodes = input.nextInt();
            input.next();
            matrix = new double[numNodes][numNodes];
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (i == j)
                        matrix[i][j] = 0;
                    else
                        matrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
            // adds edges from file to matrix
            while (input.hasNextLine()) {
                matrix[input.nextInt()][input.nextInt()] = input.nextDouble();
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    void floydWarshall() {
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
        for (double[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
        parseFile(filename);
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        slowestWalker = Math.min(Math.min(sA, sB), sC);
        floydWarshall();

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (matrix[i][j] > longestPath) {
                    longestPath = matrix[i][j];
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