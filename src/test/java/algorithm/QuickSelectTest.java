package algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class QuickSelectTest {
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
            int[] array = random.ints(n, -1000, 1000).toArray();
            int[] sorted = array.clone();
            Arrays.sort(sorted);
            int k = random.nextInt(n);
            int expected = sorted[k];
            int actual = QuickSelect.select(array, k, metrics);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testInvalidInputsThrowException() {
        int[] arr = new int[]{1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(arr, -1, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(arr, 3, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[0], 0, metrics));
    }
}
