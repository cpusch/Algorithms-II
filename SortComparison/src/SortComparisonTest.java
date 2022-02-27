/*
Times recorded in miliseconds averaged over 3 runs
                    |Insert  |  Selection |    Quick |    Merge Rec |    Merge It   |
                    | 5.73   |   3.39     |   .99    |         1.24 |    1          |
1000 random         |_______ |____________|__________|______________|_______________|
                    |   5.01 |  4.71      | 1.39     |   1.04       |  1.87         |
1000 few unique     |________|____________|__________|______________|_______________|
                    |  3.44  |   3.58     |   2.12   |   0.98       | 1.10          |
1000 nearly ordered |________|____________|__________|______________|_______________|
                    | 5.42   | 3.31       | 3.74     |  .66         | 0.95          |
1000 reverse order  |________|____________|__________|______________|_______________|
                    | 3.41   | 3.31       | 3.11     |   0.81       | 1.74          |
1000 sorted         |________|____________|__________|______________|_______________|
                    |  54.50 | 33.07      | 2.37     |  1.87        |  3.26         |
10000 random        |________|____________|__________|______________|_______________|

a. All algorithms are affected by the order of the input except selection sort which will
    always have the same number of comparisons no matter the order. All the other algorithms
    are impacted by the order becuase it changes the amount of comparisons they have to do
    and therfore impacts their run-time. 

b. Insertion sort has the largest difference between teh best and worst performance when looking
    at the different types of input for size 1000. This is because for insertion sorts worst case it
    has to n^2 comparisons but for its best case when the array is already sorted it only has to do N 
    comparisons and there heavily decrease its run time. 

c. Even though in this test that I have conducted insertion sort has had the worst scalability if done
    in a more controlled testing environment selection sort will most definitly have the worst scalability
    for a random input size since it will always be running at its worst case of n^2 where as insertion 
    sort may have scenarios where it will be able to run much more efficiently even with large data sets.

d. Yes it seems that accross the board the iterative implemention of merge sort preformed much slower
    than the recursive version of it self. This may just be up to the way the two methods are implemented
    but I am honestly not really sure since I have no idea to why this would be the case. Maybe it has 
    to do with how recursion has better instruction pre-fetching possibly. 

e. The recursive implementation of merge sort was fastest for all input files except the 1000 random
    which had quick sort run the fastest. 

*/

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.*;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Constantin Pusch
 * @version HT 2022
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new SortComparison();
    }

    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {

        double a[] = {};
        double sortedArray[] = new double[a.length];
        int length;

        sortedArray = SortComparison.insertionSort(a);
        length = sortedArray.length;
        assertEquals(length, 0);

        sortedArray = SortComparison.quickSort(a);
        length = sortedArray.length;
        assertEquals(length, 0);

        sortedArray = SortComparison.mergeSortIterative(a);
        length = sortedArray.length;
        assertEquals(length, 0);

        sortedArray = SortComparison.mergeSortRecursive(a);
        length = sortedArray.length;
        assertEquals(length, 0);

        sortedArray = SortComparison.selectionSort(a);
        length = sortedArray.length;
        assertEquals(length, 0);
    }

    // tests insertion sort
    @Test
    public void testInsertion() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };

        assertArrayEquals(SortComparison.insertionSort(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.insertionSort(a), new double[] { 44.85 }, 0);
    }

    @Test
    public void testQuick() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };

        assertArrayEquals(SortComparison.quickSort(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.quickSort(a), new double[] { 44.85 }, 0);
    }

    @Test
    public void testSelection() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };

        assertArrayEquals(SortComparison.selectionSort(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.selectionSort(a), new double[] { 44.85 }, 0);
    }

    @Test
    public void testMergeIter() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };

        assertArrayEquals(SortComparison.mergeSortIterative(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.mergeSortIterative(a), new double[] { 44.85 }, 0);
    }

    @Test
    public void testMergeRecur() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };

        assertArrayEquals(SortComparison.mergeSortRecursive(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.mergeSortRecursive(a), new double[] { 44.85 }, 0);
    }

    private static double[] populateArray(File file, int n) throws FileNotFoundException {
        double[] result = new double[n];
        Scanner in = new Scanner(file);

        for (int i = 0; i < result.length; i++) {
            result[i] = in.nextDouble();
        }

        in.close();
        return result;
    }

    // ----------------------------------------------------------
    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the
     * experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        File thousandFile = new File("/home/user/Semester2/Algorithms-II/SortComparison/src/numbers1000.txt");
        double[] array1000 = populateArray(thousandFile, 1000);

        File thousandDuplicates = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/src/numbers1000Duplicates.txt");
        double[] array1000Duplicates = populateArray(thousandDuplicates, 1000);

        File thousandNearlyOrdered = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/src/numbersNearlyOrdered1000.txt");
        double[] array1000NearlyOrdered = populateArray(thousandNearlyOrdered, 1000);

        File thousandReverse = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/src/numbersReverse1000.txt");
        double[] array1000Reverse = populateArray(thousandReverse, 100);

        File thousandSorted = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/src/numbersSorted1000.txt");
        double[] array1000Sorted = populateArray(thousandSorted, 100);

        File tenThousandsFile = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/src/numbers10000.txt");
        double[] arrayTenThousand = populateArray(tenThousandsFile, 10000);

        // INSERTION SORT
        // 1000 random
        double[] temp = array1000.clone();
        long startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        long endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000: " + (endTime - startTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Duplicates: " + (endTime - startTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000NearlyOrdered: " + (endTime - startTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Reverse: " + (endTime - startTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Sorted: " + (endTime - startTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort arrayTenThousand: " + (endTime - startTime));
        System.out.println("--------------------------------------\n\n");

        // SELECTION SORT
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000: " + (endTime - startTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Duplicates: " + (endTime - startTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000NearlyOrdered: " + (endTime - startTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Reverse: " + (endTime - startTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Sorted: " + (endTime - startTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort arrayTenThousand: " + (endTime - startTime));
        System.out.println("--------------------------------------\n\n");

        // Quick SORT
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000: " + (endTime - startTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Duplicates: " + (endTime - startTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000NearlyOrdered: " + (endTime - startTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Reverse: " + (endTime - startTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Sorted: " + (endTime - startTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort arrayTenThousand: " + (endTime - startTime));
        System.out.println("--------------------------------------\n\n");

        // MERGE SORT ITERATIVE
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000: " + (endTime - startTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Duplicates: " + (endTime - startTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000NearlyOrdered: " +
                (endTime - startTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Reverse: " + (endTime - startTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Sorted: " + (endTime - startTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative arrayTenThousand: " + (endTime - startTime));
        System.out.println("--------------------------------------\n\n");

        // MERGE SORT RECURSIVE
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000: " + (endTime - startTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Duplicates: " + (endTime - startTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000NearlyOrdered: " +
                (endTime - startTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Reverse: " + (endTime - startTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Sorted: " + (endTime - startTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive arrayTenThousand: " + (endTime - startTime));
    }
}
