import os
import math
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

csv_path = os.path.join(os.path.dirname(__file__), '..', 'results.csv')
df = pd.read_csv(csv_path)

plt.figure(figsize=(10, 6))
for (alg, inp), group in df.groupby(['algorithm', 'input']):
    plt.plot(group['n'], group['time_ms'], marker='o', label=f"{alg} ({inp})")
plt.xscale('log')
plt.yscale('log')
plt.xlabel('Array Size (n)')
plt.ylabel('Time (ms)')
plt.title('Execution Time vs Array Size (n)')
plt.legend()
plt.grid(True)
plt.savefig('time_vs_n.png')
plt.close()

plt.figure(figsize=(10, 6))
for (alg, inp), group in df.groupby(['algorithm', 'input']):
    plt.plot(group['n'], group['max_depth'], marker='o', label=f"{alg} ({inp})")
plt.xscale('log')
plt.xlabel('Array Size (n)')
plt.ylabel('Max Recursion Depth')
plt.title('Max Recursion Depth vs Array Size (n)')
plt.legend()
plt.grid(True)
plt.savefig('depth_vs_n.png')
plt.close()

plt.figure(figsize=(10, 6))
for (alg, inp), group in df.groupby(['algorithm', 'input']):
    n_vals = group['n'].astype(float)
    if alg == 'QuickSelect':
        ratio = group['comparisons'] / n_vals
    else:
        ratio = group['comparisons'] / (n_vals * np.log2(n_vals))
    plt.plot(group['n'], ratio, marker='o', label=f"{alg} ({inp})")
plt.xscale('log')
plt.xlabel('Array Size (n)')
plt.ylabel('Ratio (Comparisons / Complexity)')
plt.title('Performance Ratio vs Array Size (n)')
plt.legend()
plt.grid(True)
plt.savefig('ratio_vs_n.png')
plt.close()
print("Plots successfully generated and saved in plots/ directory!")