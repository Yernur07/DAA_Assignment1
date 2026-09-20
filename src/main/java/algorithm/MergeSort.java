package algorithm;
public class MergeSort {
    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] buffer = new int[a.length];
        long startTime = System.nanoTime();
        sortRecursive(a, buffer, 0, a.length - 1, metrics);
        metrics.setExecutionTimeNs(System.nanoTime() - startTime);
    }
    private static void sortRecursive(int[] a, int[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        try {
            if (right - left + 1 <= 15) {
                InsertionSort.sort(a, left, right, metrics);
                return;
            }
            int mid = left + (right - left) / 2;
            sortRecursive(a, buffer, left, mid, metrics);
            sortRecursive(a, buffer, mid + 1, right, metrics);
            merge(a, buffer, left, mid, right, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }
    private static void merge(int[] a, int[] buffer, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(a, left, buffer, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                a[k++] = buffer[i++];
            } else {
                a[k++] = buffer[j++];
            }
        }
        while (i <= mid) {
            a[k++] = buffer[i++];
        }
        while (j <= right) {
            a[k++] = buffer[j++];
        }
    }
}