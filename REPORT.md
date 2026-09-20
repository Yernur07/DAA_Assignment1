# Report: Assignment 1 - Divide and Conquer & Asymptotic Notations
**Author:** Inkarbek Yernur

---

## 1. Asymptotic Complexity Table

| Algorithm | Best Case | Average Case | Worst Case | Reason / Input Condition |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Always splits in half and merges lineally regardless of array order. |
| **QuickSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ | Best/Average: balanced partition. Worst: highly unbalanced partitions (e.g., sorted without random pivot). |
| **QuickSelect** | $\Theta(n)$ | $\Theta(n)$ | $O(n^2)$ | Best/Average: reduces problem size by constant fraction. Worst: extreme pivot selections. |
| **Insertion Sort** | $\Omega(n)$ | $\Theta(n^2)$ | $O(n^2)$ | Best: already sorted input. Worst/Average: reverse sorted or random array. |

---

## 2. Recurrence Relations & Master Theorem Analysis

### 1. MergeSort
* **Recurrence:** $T(n) = 2 T(n/2) + \Theta(n)$
* **Parameters:** $a = 2, b = 2, f(n) = \Theta(n)$
* **Critical Exponent:** $\log_b a = \log_2 2 = 1 \implies n^{\log_b a} = n^1 = n$
* **Master Theorem Case:** Case 2, since $f(n) = \Theta(n^{\log_b a}) = \Theta(n)$.
* **Result:** $T(n) = \Theta(n \log n)$

### 2. QuickSort (Assuming Balanced Split)
* **Recurrence:** $T(n) = 2 T(n/2) + \Theta(n)$
* **Parameters:** $a = 2, b = 2, f(n) = \Theta(n)$
* **Master Theorem Case:** Case 2.
* **Result:** $T(n) = \Theta(n \log n)$
* **Average Case Explanation:** A random pivot guarantees that bad splits (e.g., 99:1 ratio) occur rarely. On average, the partition split is bounded by a constant ratio, yielding an $O(n \log n)$ tree depth and linear work per level.

### 3. QuickSelect (Assuming Balanced Split)
* **Recurrence:** $T(n) = 1 T(n/2) + \Theta(n)$
* **Parameters:** $a = 1, b = 2, f(n) = \Theta(n)$
* **Critical Exponent:** $\log_b a = \log_2 1 = 0 \implies n^{\log_b a} = n^0 = 1$
* **Master Theorem Case:** Case 3, since $f(n) = \Omega(n^{0 + \epsilon})$ for $\epsilon = 1$, and regularity condition $a f(n/b) \le c f(n)$ holds for $c = 1/2 < 1$.
* **Result:** $T(n) = \Theta(n)$
---
## 3. Performance Plots
*Plots are generated and stored in the `plots/` folder:*
1. **Time vs n:** `plots/time_vs_n.png`
2. **Max Depth vs n:** `plots/depth_vs_n.png`
3. **Ratio vs n:** `plots/ratio_vs_n.png`
---
## 4. Empirical Ratio Analysis ($\Theta$ Bound Verification)
The plot of `comparisons / (n log2 n)` for MergeSort and QuickSort levels off into a horizontal asymptote as $n$ grows. Similarly, `comparisons / n` for QuickSelect stabilizes around a constant value (~2 to 3).
This constant ratio confirms that measured operational growth matches theoretical expectations:
$$c_1 \cdot g(n) \le f(n) \le c_2 \cdot g(n) \quad \text{for all } n \ge n_0$$
* **QuickSort Ratio Constant:** $c \approx 1.38$
* **MergeSort Ratio Constant:** $c \approx 1.0$
* **QuickSelect Ratio Constant:** $c \approx 2.5$
* **Threshold Value ($n_0$):** $n_0 = 10\,000$
---

## 5. Discussion
The experimental measurements tightly correspond to theoretical asymptotic bounds. Minor deviations on smaller arrays ($n = 1\,000$) are attributed to JVM initial warm-up overhead and JIT compiler compilation costs. As array sizes reach $n = 1\,000\,000$, cache line utilization and Garbage Collector (GC) pauses introduce small non-linear execution spikes, though exact comparison counts remain perfectly stable. The insertion sort cutoff ($\le 15$) significantly lowers overhead in MergeSort by eliminating small recursive branch allocations.