# DSA Algorithm Roadmap — Must Know Algorithms

# Foundation (Linear)
1. Array
2. String
3. Linked List (Singly, Doubly, Circular)
4. Stack
5. Queue (Simple, Circular, Deque, Priority Queue)


# Intermediate (Non-Linear)
6. Hash Table / HashMap / HashSet
7. Tree
   -> Binary Tree
   -> Binary Search Tree (BST)
   -> AVL Tree
   -> Segment Tree
   -> Fenwick Tree (BIT)
8. Heap (Min Heap, Max Heap)
9. Trie (Prefix Tree)
10. Graph (Directed, Undirected, Weighted)


# Advanced
11. Disjoint Set / Union-Find
12. Monotonic Stack / Queue
13. Sparse Table
14. Suffix Array / Suffix Tree
15. LRU / LFU Cache (design-based)

## 🚀 Priority Order to Learn

### Phase 1 — Basics (Start Here)

| Topic             | Importance |
| ----------------- | ---------- |
| Arrays & Strings  | ⭐⭐       |
| HashMap & HashSet | ⭐⭐       |
| Stack & Queue     | ⭐⭐       |
| Binary Search     | ⭐⭐       |
| Two Pointers      | ⭐⭐       |
| Sliding Window    | ⭐⭐       |

### Phase 2 — Important

| Topic              | Importance |
| ------------------ | ---------- |
| BFS & DFS          | ⭐⭐       |
| Tree Traversals    | ⭐⭐       |
| Basic DP (1D)      | ⭐⭐       |
| Sorting Algorithms | ⭐⭐       |
| Union Find         | ⭐⭐       |

### Phase 3 — Advanced

| Topic                       | Importance |
| --------------------------- | ---------- |
| Graph Algorithms (Dijkstra) | ⭐⭐       |
| 2D DP / Knapsack            | ⭐⭐       |
| Backtracking                | ⭐         |
| Trie                        | ⭐         |
| Segment Tree                | ⭐         |

---

## ⚡ Cheat Sheet

| Problem Says         | Use                  |
| -------------------- | -------------------- |
| Shortest path        | BFS / Dijkstra       |
| Min/Max subarray     | Sliding Window       |
| Sorted array search  | Binary Search        |
| Connected components | Union Find / BFS     |
| All combinations     | Backtracking         |
| Optimal choice       | DP / Greedy          |
| Spreading problems   | BFS                  |
| Path exists          | DFS                  |
| Frequency count      | HashMap              |
| Top K elements       | PriorityQueue (Heap) |
| Level by level       | BFS with levels      |
| Cycle detection      | DFS                  |

---

## 1. Sorting

### Must Know

| Algorithm      | Time Complexity | Notes                  |
| -------------- | --------------- | ---------------------- |
| Bubble Sort    | O(n²)           | Simplest               |
| Selection Sort | O(n²)           |                        |
| Insertion Sort | O(n²)           | Good for small arrays  |
| **Merge Sort** | **O(n log n)**  | ⭐ Stable sort         |
| **Quick Sort** | **O(n log n)**  | ⭐ Most used           |
| Counting Sort  | O(n)            | For small numbers only |

### Just Know Concept

- Heap Sort

---

## 2. Searching

### Must Know

| Algorithm         | Time Complexity | Notes                |
| ----------------- | --------------- | -------------------- |
| Linear Search     | O(n)            | Simple               |
| **Binary Search** | **O(log n)**    | ⭐⭐ Very important  |
| Two Pointers      | O(n)            | ⭐⭐ Used everywhere |

### Binary Search Variants

- On sorted array (basic)
- On answer (binary search on range)
- Lower bound / Upper bound

---

## 3. Traversal — BFS & DFS

> BFS and DFS are **traversal techniques**, not tied to any one data structure.
> They work on **Trees, Graphs, Grids** — anything with nodes and connections.

|                | BFS                     | DFS                          |
| -------------- | ----------------------- | ---------------------------- |
| Explores       | Level by level (wide)   | Depth first (deep)           |
| Data Structure | Queue                   | Stack / Recursion            |
| Use for        | Shortest path, min time | Path exists, cycle detection |

### Where they work

| Data Structure | BFS | DFS |
| -------------- | --- | --- |
| Tree           | ✅  | ✅  |
| Graph          | ✅  | ✅  |
| Grid / Matrix  | ✅  | ✅  |
| BST            | ✅  | ✅  |

---

## 4. Graph Algorithms

### Must Know

| Algorithm            | Use Case                         | Importance |
| -------------------- | -------------------------------- | ---------- |
| BFS / DFS            | Traversal                        | ⭐⭐       |
| **Dijkstra**         | Shortest path (positive weights) | ⭐⭐       |
| **Union Find (DSU)** | Connected components             | ⭐⭐       |
| **Topological Sort** | Ordering of tasks                | ⭐         |

### Good to Know

| Algorithm      | Use Case                          |
| -------------- | --------------------------------- |
| Bellman Ford   | Shortest path with negative edges |
| Floyd Warshall | All pairs shortest path           |
| Prim's         | Minimum spanning tree             |
| Kruskal's      | Minimum spanning tree             |

---

## 5. Dynamic Programming

### Must Know Patterns

#### 1D DP

- Fibonacci
- Climbing Stairs
- House Robber

#### 2D DP

- Grid paths
- Longest Common Subsequence

#### Knapsack ⭐⭐

- 0/1 Knapsack
- Unbounded Knapsack

#### String DP

- Longest Common Subsequence (LCS)
- Edit Distance

#### Interval DP

- Matrix Chain Multiplication

---

## 6. Tree Algorithms

### Must Know

| Algorithm                                | Importance |
| ---------------------------------------- | ---------- |
| Inorder / Preorder / Postorder traversal | ⭐⭐       |
| Height of tree                           | ⭐⭐       |
| Diameter of tree                         | ⭐⭐       |
| Lowest Common Ancestor (LCA)             | ⭐         |
| BST operations (insert, delete, search)  | ⭐⭐       |

---

## 7. Sliding Window & Two Pointers

### Must Know

| Pattern              | Example Problem                  | Importance |
| -------------------- | -------------------------------- | ---------- |
| Fixed size window    | Max sum of K elements            | ⭐⭐       |
| Variable size window | Longest subarray with K distinct | ⭐⭐       |
| Two pointers         | Pair sum, remove duplicates      | ⭐⭐       |

---

## 8. Greedy

### Must Know

- Activity Selection Problem
- Fractional Knapsack
- Job Sequencing
- Huffman Coding

---

## 9. Backtracking

### Must Know

- N Queens Problem
- Sudoku Solver
- Rat in a Maze
- Generate all subsets / permutations ⭐

---

## 10. Data Structures to Know

| Data Structure       | Importance |
| -------------------- | ---------- |
| Array / String       | ⭐⭐       |
| LinkedList           | ⭐⭐       |
| Stack                | ⭐⭐       |
| Queue                | ⭐⭐       |
| HashMap / HashSet    | ⭐⭐       |
| PriorityQueue (Heap) | ⭐⭐       |
| Tree / BST           | ⭐⭐       |
| Graph                | ⭐⭐       |
| Trie                 | ⭐         |
| Segment Tree         | ⭐         |

---

## 💡 How to Revise

Every time you are free, pick ONE algorithm and:

```
1. Understand the concept
2. Learn the template code
3. Solve 3-5 problems on it
4. Revise after 1 week
```

> ⭐⭐ = Most frequently asked in coding interviews
> Focus on these first before moving to advanced topics!

---
