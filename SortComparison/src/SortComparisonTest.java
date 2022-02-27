/*
                    |Insert  |  Selection |    Quick |    Merge Rec |    Merge It   |
1000 random         |_______ |____________|__________|______________|_______________|
                    |        |            |          |              |               |
1000 few unique     |________|____________|__________|______________|_______________|
                    |        |            |          |              |               |
1000 nearly ordered |________|____________|__________|______________|_______________|
                    |        |            |          |              |               |
1000 reverse order  |________|____________|__________|______________|_______________|
                    |        |            |          |              |               |
1000 sorted         |________|____________|__________|______________|_______________|
                    |        |            |          |              |               |
10000 random        |________|____________|__________|______________|_______________|

a.

b.

c.

d.

e.

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
        File thousandFile = new File("/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt");
        double[] array1000 = populateArray(thousandFile, 1000);

        File thousandDuplicates = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt/numbers1000Duplicates.txt");
        double[] array1000Duplicates = populateArray(thousandDuplicates, 1000);

        File thousandNearlyOrdered = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt/numbersNearlyOrdered1000.txt");
        double[] array1000NearlyOrdered = populateArray(thousandNearlyOrdered, 1000);

        File thousandReverse = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt/numbersReverse1000.txt");
        double[] array1000Reverse = populateArray(thousandReverse, 100);

        File thousandSorted = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt/numbersSorted1000.txt");
        double[] array1000Sorted = populateArray(thousandSorted, 100);

        File tenThousandsFile = new File(
                "/home/user/Semester2/Algorithms-II/SortComparison/numbers1000.txt/numbers10000.txt");
        double[] arrayTenThousand = populateArray(tenThousandsFile, 10000);

        // INSERTION SORT
        // 1000 random
        double[] temp = array1000.clone();
        long startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        long endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000: " + (startTime - endTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Duplicates: " + (startTime - endTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000NearlyOrdered: " + (startTime - endTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Reverse: " + (startTime - endTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort array1000Sorted: " + (startTime - endTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.insertionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort arrayTenThousand: " + (startTime - endTime));

        // SELECTION SORT
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000: " + (startTime - endTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Duplicates: " + (startTime - endTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000NearlyOrdered: " + (startTime - endTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Reverse: " + (startTime - endTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort array1000Sorted: " + (startTime - endTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.selectionSort(temp);
        endTime = System.nanoTime();
        System.out.println("Selection Sort arrayTenThousand: " + (startTime - endTime));

        // Quick SORT
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000: " + (startTime - endTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Duplicates: " + (startTime - endTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000NearlyOrdered: " + (startTime - endTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Reverse: " + (startTime - endTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort array1000Sorted: " + (startTime - endTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.quickSort(temp);
        endTime = System.nanoTime();
        System.out.println("Quick Sort arrayTenThousand: " + (startTime - endTime));

        // MERGE SORT ITERATIVE
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000: " + (startTime - endTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Duplicates: " + (startTime - endTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000NearlyOrdered: " + (startTime - endTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Reverse: " + (startTime - endTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative array1000Sorted: " + (startTime - endTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Iterative arrayTenThousand: " + (startTime - endTime));

        // MERGE SORT RECURSIVE
        // 1000 random
        temp = array1000.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000: " + (startTime - endTime));
        // 1000 duplicates
        temp = array1000Duplicates.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Duplicates: " + (startTime - endTime));
        // 1000 nearly ordered
        temp = array1000NearlyOrdered.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000NearlyOrdered: " + (startTime - endTime));
        // 1000 reverse
        temp = array1000Reverse.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Reverse: " + (startTime - endTime));
        // 1000 sorted
        temp = array1000Sorted.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive array1000Sorted: " + (startTime - endTime));
        // 10000 random
        temp = arrayTenThousand.clone();
        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(temp);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Recursive arrayTenThousand: " + (startTime - endTime));
    }

}
