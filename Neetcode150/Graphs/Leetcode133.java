package Neetcode150.Graphs;

import java.util.*;

class Node {
    int val;
    List<Node> neighbours;

    public Node() {
        this.val = 0;
        this.neighbours = new ArrayList<Node>();
    }

    public Node(int val) {
        this.val = val;
        this.neighbours = new ArrayList<Node>();
    }

    public Node(int val, List<Node> neighbours) {
        this.val = val;
        this.neighbours = neighbours;
    }
}

// q is we need to clone the given graph and return them.
// a is use hashmap and store the oldnode , newnode return them.
// why hashmap?
// Without map → infinite loop!
// 1 → 2 → 1 → 2 → 1 → 2 ...StackOverflow
// With map → "already cloned? just reuse it!"

// 133. Clone Graph
public class Leetcode133 {

    // TimeO(V + E)Visit every node (V) + every edge (E) once
    // SpaceO(V)HashMap stores all V nodes + recursion call stack
    private Node cloneUntil(Node node, HashMap<Node, Node> map) {

        // created new node of old node with same val
        Node newNode = new Node(node.val);
        map.put(node, newNode); // put in map

        // checking neighbors of old node
        for (Node neighbor : node.neighbours) {
            // if doesnt have recur and find for nodes nodes eighbor
            if (!map.containsKey(neighbor)) {
                newNode.neighbours.add(cloneUntil(neighbor, map));
                // if persent connect old neighbor in new neighbor
            } else {
                newNode.neighbours.add(map.get(neighbor));
            }
        }
        return newNode;
    }

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        // map stores {node, newNode};
        HashMap<Node, Node> map = new HashMap<>();
        return cloneUntil(node, map);
    }

    public static void main(String[] args) {

    }

}