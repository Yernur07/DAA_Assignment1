package algorithm;
import java.util.Random;
public class QuickSelect {
    private static final Random random = new Random();
    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input array or k is out of bounds: k = " + k);
        }
        long startTime = System.nanoTime();
        int result = selectRecursive(a, 0, a.length - 1, k, metrics);
        metrics.setExecutionTimeNs(System.nanoTime() - startTime);
        return result;
    }
    private static int selectRecursive(int[] a, int low, int high, int k, Metrics metrics) {
        if (low == high) return a[low];
        metrics.enterRecursion();
        try {
            int pivotIdx = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIdx);
            int[] p = QuickSort.partition3Way(a, low, high, metrics);
            int lt = p[0];
            int gt = p[1];
            if (k >= lt && k <= gt) {
                return a[k];
            } else if (k < lt) {
                return selectRecursive(a, low, lt - 1, k, metrics);
            } else {
                return selectRecursive(a, gt + 1, high, k, metrics);
            }
        } finally {
            metrics.exitRecursion();
        }
    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
