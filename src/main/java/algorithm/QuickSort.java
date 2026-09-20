package algorithm;
import java.util.Random;
public class QuickSort {
    private static final Random random = new Random();
    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        long startTime = System.nanoTime();
        sortRecursive(a, 0, a.length - 1, metrics);
        metrics.setExecutionTimeNs(System.nanoTime() - startTime);
    }
    private static void sortRecursive(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            metrics.enterRecursion();
            try {
                int pivotIdx = low + random.nextInt(high - low + 1);
                swap(a, low, pivotIdx);
                int[] p = partition3Way(a, low, high, metrics);
                int lt = p[0];
                int gt = p[1];
                if (lt - low < high - gt) {
                    sortRecursive(a, low, lt - 1, metrics);
                    low = gt + 1;
                } else {
                    sortRecursive(a, gt + 1, high, metrics);
                    high = lt - 1;
                }
            } finally {
                metrics.exitRecursion();
            }
        }
    }
    public static int[] partition3Way(int[] a, int low, int high, Metrics metrics) {
        int pivot = a[low];
        int lt = low;
        int i = low + 1;
        int gt = high;
        while (i <= gt) {
            metrics.incrementComparisons();
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                metrics.incrementComparisons();
                swap(a, i, gt--);
            } else {
                metrics.incrementComparisons();
                i++;
            }
        }
        return new int[]{lt, gt};
    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}