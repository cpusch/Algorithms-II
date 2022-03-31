import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@RunWith(JUnit4.class)
public class CompetitionTest {
    String file = "/home/user/Semester2/Algorithms-II/ShortestPath/src/input-K.txt";
    String file2 = "/home/user/Semester2/Algorithms-II/ShortestPath/src/inputAssignment2/input-J.txt";
    String file3 = "/home/user/Semester2/Algorithms-II/ShortestPath/src/inputAssignment2/input-F.txt";

    @Test
    public void testDijkstraConstructor() {

        new CompetitionDijkstra(file, 50, 60, 70);
    }

    @Test
    public void testFWConstructor() {
        new CompetitionFloydWarshall(file, 50, 60, 70);
    }

    @Test
    public void testDijkstraTime() {
        CompetitionDijkstra test = new CompetitionDijkstra(file, 50, 60, 70);
        int speed = test.timeRequiredforCompetition();
        assertEquals(320, speed);

        test = new CompetitionDijkstra(file2, 50, 60, 70);
        speed = test.timeRequiredforCompetition();
        assertEquals(-1, -1);
    }

    @Test
    public void testWarshallTime() {
        CompetitionFloydWarshall test = new CompetitionFloydWarshall(file, 50, 60, 70);
        int speed = test.timeRequiredforCompetition();
        assertEquals(320, speed);

        test = new CompetitionFloydWarshall(file2, 50, 60, 70);
        speed = test.timeRequiredforCompetition();
        assertEquals(-1, -1);
    }

    @Test
    public void testFalseParameters() {
        CompetitionDijkstra dijk = new CompetitionDijkstra(file, 40, 50, 60);
        int speed = dijk.timeRequiredforCompetition();
        assertEquals(-1, speed);

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall(file, 40, 60, 70);
        speed = floyd.timeRequiredforCompetition();
        assertEquals(-1, speed);
    }

    @Test
    public void testFalseGraph() {
        CompetitionDijkstra dijk = new CompetitionDijkstra(file3, 50, 80, 60);
        int speed = dijk.timeRequiredforCompetition();
        assertEquals(-1, speed);

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall(file3, 50, 60, 70);
        speed = floyd.timeRequiredforCompetition();
        assertEquals(-1, speed);
    }

}
