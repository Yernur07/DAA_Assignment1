import pandas as pd
import matplotlib.pyplot as plt
import math
df = pd.read_csv('../results.csv')

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
    if alg == 'QuickSelect':
        ratio = group['comparisons'] / group['n']
    else:
        ratio = group['comparisons'] / (group['n'] * group['n'].apply(lambda x: math.log2(x)))
    plt.plot(group['n'], ratio, marker='o', label=f"{alg} ({inp})")
plt.xscale('log')
plt.xlabel('Array Size (n)')
plt.ylabel('Ratio (Comparisons / Complexity)')
plt.title('Performance Ratio vs Array Size (n)')
plt.legend()
plt.grid(True)
plt.savefig('ratio_vs_n.png')
plt.close()