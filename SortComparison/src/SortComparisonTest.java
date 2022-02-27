import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author
 * @version HT 2020
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

    @Test
    public void testInsertion() {
        double a[] = { 44.85819, 92.40928, 29.12171, 90.56594, 76.67673, 51.60359, 9.38018, 41.51448, 11.51681,
                10.46784 };
        double b[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819, 51.60359, 76.67673, 90.56594,
                92.40928 };
        // double c[] = { 9.38018, 10.46784, 11.51681, 29.12171, 41.51448, 44.85819,
        // 51.60359, 76.67673, 90.56594,
        // 92.40928 };

        assertArrayEquals(SortComparison.insertionSort(a), b, 0);
        a = new double[] { 44.85 };
        assertArrayEquals(SortComparison.insertionSort(a), a, 0);
    }

    // ----------------------------------------------------------
    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the
     * experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args) {
        // TODO: implement this method
    }

}
