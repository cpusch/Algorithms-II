
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

    // implemented from https://www.geeksforgeeks.org/java-program-for-quicksort/
    private static int partition(double arr[], int low, int high) {
        double pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    public static double[] quickSort(double[] a) {
        recurQuick(a, 0, a.length - 1);
        return a;
    }

    private static double[] recurQuick(double a[], int lo, int hi) {

        if (hi > lo) {
            int pivotPos = partition(a, lo, hi);
            recurQuick(a, lo, pivotPos - 1);
            recurQuick(a, pivotPos + 1, hi);
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

        double[] temp = new double[a.length];
        for (int sz = 1; sz < a.length; sz = sz + sz)
            for (int lo = 0; lo < a.length - sz; lo += sz + sz)
                merge(a, temp, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, a.length - 1));

        return a;

    }// end mergesortIterative

    private static void mergeSort(double[] a, double[] temp, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, temp, lo, mid);
        mergeSort(a, temp, mid + 1, hi);
        merge(a, temp, lo, mid, hi);
    }

    private static void merge(double[] a, double[] temp, int lo, int mid, int hi) {
        temp = a.clone();

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = temp[j++];
            else if (j > hi)
                a[k] = temp[i++];
            else if (temp[j] < temp[i])
                a[k] = temp[j++];
            else
                a[k] = temp[i++];

        }
    }

    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted
     *         order.
     */
    static double[] mergeSortRecursive(double a[]) {

        double[] temp = new double[a.length];
        mergeSort(a, temp, 0, a.length - 1);
        return a;

    }// end mergeSortRecursive

}// end class
