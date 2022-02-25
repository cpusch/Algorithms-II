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

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers
 * using different sort algorithms.
 *
 * @author Constantin Pusch
 * @version HT 2022
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double[] insertionSort(double a[]) {

        double temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        return a;
    }// end insertionsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] selectionSort(double a[]) {

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (a[j] < a[min])
                    min = j;
            double temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        return a;
    }

    private static int partition(double[] numbers, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        double pivot = numbers[lo];
        while (true) {
            while ((numbers[++i] == pivot)) {
                if (i == hi)
                    break;
            }
            while ((pivot == numbers[--j])) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            double temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        numbers[lo] = numbers[j];
        numbers[j] = pivot;
        return j;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] quickSort(double a[], int lo, int hi) {

        if (hi > lo) {
            int pivotPos = partition(a, lo, hi);
            quickSort(a, lo, pivotPos - 1);
            quickSort(a, pivotPos + 1, hi);
        } else {
            return a;
        }
        return a;
    }// end quicksort

    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted
     *         order.
     */

    static double[] mergeSortIterative(double a[]) {

        return new double[] {};

    }// end mergesortIterative

    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted
     *         order.
     */
    static double[] mergeSortRecursive(double a[]) {

        return new double[] {};

    }// end mergeSortRecursive

    public static void main(String[] args) {

        // todo: do experiments as per assignment instructions
    }

}// end class
