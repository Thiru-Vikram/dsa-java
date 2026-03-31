# 🌳 DSA Revision Notes — Trees

---

## 1. Tree

> **A Tree is a non-linear, hierarchical data structure consisting of nodes connected by edges, where there is one root node and every other node has exactly one parent. There are no cycles in a tree.**

### 🔑 One-Line Rule

> Tree → Each node can have **any number of children** (0, 1, 2, 3... unlimited)

### 📌 Key Terms

| Term    | Meaning                          |
| ------- | -------------------------------- |
| Root    | Topmost node of the tree         |
| Leaf    | Node with no children            |
| Height  | Longest path from root to leaf   |
| Depth   | Distance of a node from root     |
| Edge    | Connection between two nodes     |
| Subtree | Any node and all its descendants |

---

## 2. Binary Tree

> **A Binary Tree is a tree data structure in which each node has at most two children, referred to as the left child and the right child.**

### 🔑 One-Line Rule

> Binary Tree → Each node can have **at most 2 children** (0, 1, or 2 only)

> 💡 Binary Tree is a **restricted version** of a General Tree — the restriction is **maximum 2 children per node.**

---

## 3. Binary Tree Variants

### 1. Full Binary Tree

> Every node has either **0 or 2 children** — never 1.

```
        1
       / \
      2   3
     / \
    4   5
```

> 🧠 Remember: **"All or nothing — 0 or 2 children only"**

---

### 2. Complete Binary Tree

> All levels are **fully filled** except the last level, and the last level is filled **from left to right.**

```
        1
       / \
      2   3
     / \  /
    4   5 6
```

> 🧠 Remember: **"Fill left to right — no gaps allowed"**

---

### 3. Perfect Binary Tree

> All internal nodes have **2 children** and all leaves are at the **same level.**

```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```

> 🧠 Remember: **"Completely symmetric and full — the most perfect looking tree"**

---

### 4. Degenerate / Skewed Tree

> Every node has **only 1 child** — looks exactly like a linked list.

```
    1
     \
      2
       \
        3
         \
          4
```

> 🧠 Remember: **"A tree that forgot it's a tree — behaves like a linked list"**

---

### 5. Balanced Binary Tree

> The **height difference** between left and right subtree of any node is **at most 1.**

```
        1
       / \
      2   3
     /
    4

Height difference = 1 ✅ → Balanced
```

> 🧠 Remember: **"No side is too heavy — left and right are roughly equal"**

---

### ⚡ Quick Comparison Table

| Variant              | Rule                                       |
| -------------------- | ------------------------------------------ |
| Full Binary Tree     | 0 or 2 children only — never 1             |
| Complete Binary Tree | Filled left to right, no gaps              |
| Perfect Binary Tree  | All leaves at same level, fully symmetric  |
| Degenerate / Skewed  | Only 1 child per node — like a linked list |
| Balanced Binary Tree | Height difference ≤ 1 between subtrees     |

---

## 4. Binary Search Tree (BST)

> **A Binary Search Tree is a binary tree where for every node, all values in the left subtree are less than the node's value, and all values in the right subtree are greater than the node's value. This property holds for every single node in the tree.**

### 🔑 Golden Rule

> **Left < Root < Right** — applies to EVERY node, not just the root!

### 🖼️ Visual Example

```
        50
       /  \
      30   70
     / \   / \
    20  40 60  80

Node 50 → Left: 30,20,40 (all < 50) ✅  |  Right: 70,60,80 (all > 50) ✅
Node 30 → Left: 20 (< 30) ✅            |  Right: 40 (> 30) ✅
```

### 📌 Key Properties

| Property               | Value                                 |
| ---------------------- | ------------------------------------- |
| Left subtree           | Always smaller than root              |
| Right subtree          | Always greater than root              |
| Duplicate values       | Generally not allowed                 |
| Inorder traversal      | Gives sorted output (ascending order) |
| Search time (balanced) | O(log n)                              |
| Search time (skewed)   | O(n) — worst case                     |

### 💬 How to Say it in Interview

> _"A BST is a binary tree with a special ordering property — for any node, everything on the left is smaller and everything on the right is larger. This makes searching very efficient, similar to binary search, giving O(log n) time in a balanced tree. Also, inorder traversal of a BST always gives a sorted sequence."_

## 1. AVL Tree

> **An AVL Tree is a self-balancing Binary Search Tree where the height difference (Balance Factor) between the left and right subtree of any node is at most 1. If it becomes more than 1 after any insertion or deletion, rotations are performed to rebalance the tree.**

### 🔑 One-Line Rule

> AVL Tree → A BST that **automatically balances itself** using rotations. Balance Factor = **-1, 0, or 1** at every node.

### 🖼️ Visual Example

```
Before Insertion (Balanced ✅):
        30
       /  \
      20   40
     /
    10

After Inserting 5 (Unbalanced ❌ — Balance Factor = 2 at node 30):
        30
       /  \
      20   40
     /
    10
   /
  5

After Right Rotation (Balanced again ✅):
        20
       /  \
      10   30
     /       \
    5         40
```

### 📌 Key Properties

| Property               | Value                        |
| ---------------------- | ---------------------------- |
| Type                   | Self-balancing BST           |
| Balance Factor         | Height(Left) - Height(Right) |
| Allowed Balance Factor | -1, 0, or 1 only             |
| If Balance Factor > 1  | Left Heavy → Right Rotation  |
| If Balance Factor < -1 | Right Heavy → Left Rotation  |
| Search time            | Always O(log n) — guaranteed |
| Insertion / Deletion   | O(log n)                     |
| Used in                | Databases, file systems      |

### 🔄 4 Types of Rotations

| Case             | Rotation                          |
| ---------------- | --------------------------------- |
| Left-Left (LL)   | Single Right Rotation             |
| Right-Right (RR) | Single Left Rotation              |
| Left-Right (LR)  | Left Rotation then Right Rotation |
| Right-Left (RL)  | Right Rotation then Left Rotation |

### 💬 How to Say it in Interview

> _"An AVL Tree is a self-balancing BST where for every node, the height difference between the left and right subtree — called the Balance Factor — is always -1, 0, or 1. Whenever an insertion or deletion violates this, rotations are performed to restore balance. This guarantees O(log n) time for all operations unlike a regular BST which can degrade to O(n) in worst case."_

---

## 2. Segment Tree

> **A Segment Tree is a binary tree used for storing information about intervals or segments. It allows efficient range queries (like range sum, range minimum/maximum) and point updates, both in O(log n) time.**

### 🔑 One-Line Rule

> Segment Tree → Built for **range queries + updates** on an array in **O(log n)** time.

### 🖼️ Visual Example

```
Array: [1, 3, 5, 7, 9, 11]
Index:  0  1  2  3  4   5

Segment Tree (Range Sum):

                  [0-5] = 36
                 /            \
          [0-2] = 9        [3-5] = 27
          /     \           /       \
      [0-1]=4  [2-2]=5  [3-4]=16  [5-5]=11
      /    \            /     \
  [0-0]=1 [1-1]=3  [3-3]=7  [4-4]=9

Query: Sum of index 1 to 4 → 3+5+7+9 = 24 ✅
```

### 📌 Key Properties

| Property     | Value                                     |
| ------------ | ----------------------------------------- |
| Type         | Binary Tree on array segments             |
| Build time   | O(n)                                      |
| Range Query  | O(log n)                                  |
| Point Update | O(log n)                                  |
| Space        | O(4 \* n) — always allocate 4x array size |
| Common uses  | Range Sum, Range Min, Range Max           |
| Used in      | Competitive programming, databases        |

### 💬 How to Say it in Interview

> _"A Segment Tree is a binary tree built on top of an array where each node stores information about a range or segment of the array. It is mainly used when we need to perform repeated range queries — like sum, minimum, or maximum over a subarray — and also update elements, all in O(log n) time. Without a segment tree, range queries take O(n) time but with it we bring it down to O(log n)."_

---

## 3. Heap

> **A Heap is a complete binary tree that satisfies the Heap Property. In a Max Heap, every parent node is greater than or equal to its children. In a Min Heap, every parent node is less than or equal to its children.**

### 🔑 One-Line Rule

> Heap → A **complete binary tree** where **parent is always greater (Max Heap)** or **always smaller (Min Heap)** than its children.

### 🖼️ Visual Example

```
Max Heap ✅ (Parent always > Children):

            100
           /    \
          90     80
         /  \   /  \
        70  60 50   40

Min Heap ✅ (Parent always < Children):

             5
           /    \
          10     15
         /  \   /  \
        20  25 30   35
```

### 📌 Key Properties

| Property      | Value                                           |
| ------------- | ----------------------------------------------- |
| Type          | Complete Binary Tree                            |
| Max Heap Rule | Parent ≥ Children                               |
| Min Heap Rule | Parent ≤ Children                               |
| Insert        | O(log n) — heapify up                           |
| Delete (root) | O(log n) — heapify down                         |
| Get Max / Min | O(1) — always at root                           |
| Build Heap    | O(n)                                            |
| Storage       | Stored in array (no pointers needed)            |
| Used in       | Priority Queue, Heap Sort, Dijkstra's Algorithm |

### 🗂️ Array Representation

```
Heap stored as array: [100, 90, 80, 70, 60, 50, 40]
Index:                   0   1   2   3   4   5   6

For any node at index i:
  Left Child  → 2i + 1
  Right Child → 2i + 2
  Parent      → (i - 1) / 2
```

### ⚡ Max Heap vs Min Heap — When to Use

| Situation                 | Use                |
| ------------------------- | ------------------ |
| Find Kth Largest element  | Min Heap of size K |
| Find Kth Smallest element | Max Heap of size K |
| Always need the maximum   | Max Heap           |
| Always need the minimum   | Min Heap           |
| Dijkstra's shortest path  | Min Heap           |

### 💬 How to Say it in Interview

> _"A Heap is a complete binary tree that follows the heap property. In a Max Heap, every parent is greater than its children so the maximum element is always at the root. In a Min Heap, every parent is smaller so the minimum is at the root. The key advantage is that getting the max or min takes O(1) time and insertion or deletion takes O(log n). Heaps are internally used to implement Priority Queues in Java."_

---

## ⚡ Quick Comparison — All 3 Trees

| Tree         | Purpose                | Time Complexity           | Key Feature                |
| ------------ | ---------------------- | ------------------------- | -------------------------- |
| AVL Tree     | Self-balancing BST     | O(log n) always           | Balance Factor + Rotations |
| Segment Tree | Range queries on array | O(log n) query/update     | Stores segment info        |
| Heap         | Get min/max fast       | O(1) get, O(log n) insert | Root is always min or max  |

---
