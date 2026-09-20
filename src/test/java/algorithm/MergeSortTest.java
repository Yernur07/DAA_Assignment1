package algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class MergeSortTest {
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
            MergeSort.sort(actual, metrics);
            assertArrayEquals(expected, actual);
        }
    }
    @Test
    void testEdgeCases() {
        int[] empty = new int[0];
        MergeSort.sort(empty, metrics);
        assertArrayEquals(new int[0], empty);
        int[] single = new int[]{42};
        MergeSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);
        int[] allEqual = new int[]{5, 5, 5, 5, 5};
        MergeSort.sort(allEqual, metrics);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, allEqual);
        int[] sorted = new int[]{1, 2, 3, 4, 5, 6, 7};
        MergeSort.sort(sorted, metrics);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, sorted);
    }
}
