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
        File tenFile = new File("../numbers10.txt");
        double[] array10 = populateArray(tenFile, 10);

        File hundredFile = new File("../numbers100.txt");
        double[] array100 = populateArray(hundredFile, 100);

        File thousandFile = new File("../numbers1000.txt");
        double[] array1000 = populateArray(thousandFile, 1000);

        File thousandDuplicates = new File("../numbers1000Duplicates.txt");
        double[] array1000Duplicates = populateArray(thousandDuplicates, 1000);

        File thousandNearlyOrdered = new File("../numbersNearlyOrdered1000.txt");
        double[] array1000NearlyOrdered = populateArray(thousandNearlyOrdered, 1000);

        File thousandReverse = new File("../numbersReverse1000.txt");
        double[] array1000Reverse = populateArray(thousandReverse, 100);

        File thousandSorted = new File("../numbersSorted1000.txt");
        double[] array1000Sorted = populateArray(thousandSorted, 100);

    }

}
