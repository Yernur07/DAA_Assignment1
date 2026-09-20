package algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class QuickSortTest {
    private Metrics metrics;
    private Random random;
    @BeforeEach
    void setUp() {
        metrics = new Metrics();
        random = new Random(42);
    }
    @Test
    void testCorrectnessOn100RandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = random.nextInt(500) + 1;
            int[] actual = random.ints(n, -1000, 1000).toArray();
            int[] expected = actual.clone();
            Arrays.sort(expected);
            QuickSort.sort(actual, metrics);
            assertArrayEquals(expected, actual);
        }
    }
    @Test
    void testEdgeCases() {
        int[] empty = new int[0];
        QuickSort.sort(empty, metrics);
        assertArrayEquals(new int[0], empty);
        int[] single = new int[]{42};
        QuickSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);
        int[] allEqual = new int[]{7, 7, 7, 7, 7, 7};
        QuickSort.sort(allEqual, metrics);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 7}, allEqual);
        int[] sorted = new int[]{10, 20, 30, 40, 50};
        QuickSort.sort(sorted, metrics);
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, sorted);
    }
    @Test
    void testRecursionDepthOnSortedArray() {
        int n = 100_000;
        int[] sortedArray = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArray[i] = i;
        }
        QuickSort.sort(sortedArray, metrics);
        double limit = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= limit);
    }
}