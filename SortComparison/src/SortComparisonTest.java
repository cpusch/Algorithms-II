import static org.junit.Assert.assertEquals;

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
        assertEquals("Checking insertion Empty", length, 0);

        sortedArray = SortComparison.quickSort(a);
        length = sortedArray.length;
        assertEquals("CHecking Quick Empty", length, 0);

        sortedArray = SortComparison.mergeSortIterative(a);
        length = sortedArray.length;
        assertEquals("Checking merge Iterative empty", length, 0);

        sortedArray = SortComparison.mergeSortRecursive(a);
        length = sortedArray.length;
        assertEquals("CHecking merge Recusive empty", length, 0);

        sortedArray = SortComparison.selectionSort(a);
        length = sortedArray.length;
        assertEquals("Checking Selection Empty", length, 0);
    }

    // TODO: add more tests here. Each line of code and ech decision in
    // Collinear.java should
    // be executed at least once from at least one test.

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
