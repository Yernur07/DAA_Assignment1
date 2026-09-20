package benchmark;
import algorithm.MergeSort;
import algorithm.Metrics;
import algorithm.QuickSelect;
import algorithm.QuickSort;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
public class BenchmarkRunner {
    private static final int[] SIZES = {1000, 10000, 100000, 1000000};
    private static final String[] TYPES = {"random", "sorted", "duplicates"};
    private static final int REPEATS = 5;
    private static final Random random = new Random(42);
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");
            for (int n : SIZES) {
                for (String type : TYPES) {
                    runBenchmark("MergeSort", type, n, writer);
                    runBenchmark("QuickSort", type, n, writer);
                    runBenchmark("QuickSelect", type, n, writer);
                }
            }
            System.out.println("Benchmark completed! Results saved to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void runBenchmark(String algorithm, String inputType, int n, PrintWriter writer) {
        long[] times = new long[REPEATS];
        long medianComparisons = 0;
        int medianDepth = 0;
        for (int i = 0; i < REPEATS; i++) {
            int[] data = generateData(inputType, n);
            Metrics metrics = new Metrics();
            if ("MergeSort".equals(algorithm)) {
                MergeSort.sort(data, metrics);
            } else if ("QuickSort".equals(algorithm)) {
                QuickSort.sort(data, metrics);
            } else if ("QuickSelect".equals(algorithm)) {
                int k = n / 2;
                QuickSelect.select(data, k, metrics);
            }
            times[i] = metrics.getExecutionTimeNs();
            if (i == REPEATS / 2) {
                medianComparisons = metrics.getComparisons();
                medianDepth = metrics.getMaxDepth();
            }
        }
        Arrays.sort(times);
        long medianTimeNs = times[REPEATS / 2];
        double medianTimeMs = medianTimeNs / 1_000_000.0;
        writer.printf("%s,%s,%d,%.4f,%d,%d\n", algorithm, inputType, n, medianTimeMs, medianComparisons, medianDepth);
        writer.flush();
    }
    private static int[] generateData(String type, int n) {
        int[] arr = new int[n];
        if ("random".equals(type)) {
            for (int i = 0; i < n; i++) arr[i] = random.nextInt();
        } else if ("sorted".equals(type)) {
            for (int i = 0; i < n; i++) arr[i] = i;
        } else if ("duplicates".equals(type)) {
            for (int i = 0; i < n; i++) arr[i] = random.nextInt(10);
        }
        return arr;
    }
}
