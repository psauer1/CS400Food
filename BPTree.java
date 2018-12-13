package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of a B+ tree to allow efficient access to many different indexes of a large data
 * set. BPTree objects are created for each type of index needed by the program. BPTrees provide an
 * efficient range search as compared to other types of data structures due to the ability to
 * perform log_m N lookups and linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K> key - expect a string that is the type of id for each item
 * 
 * @param <V> value - expect a user-defined type that stores all data for a food item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

    private Node root; // reference to root node of tree


    private int branchingFactor; // Maximum # of children nodes per internal node

    private LinkedList<LeafNode> leaves; // List linking leaf nodes in order

    /**
     * Public constructor
     * 
     * @param branchingFactor
     */
    public BPTree(int branchingFactor) {

        if (branchingFactor <= 2) {
            throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
        leaves = new LinkedList<LeafNode>();
    }

    /**
     * This method reveals if the root node has been initialized. It returns true when the root is
     * null.
     * 
     * @return boolean
     */
    public boolean isEmpty() {

        if (root == null) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see BPTreeADT#insert(java.lang.Object, java.lang.Object)
     */
    @Override
    public void insert(K key, V value) {

        if (isEmpty()) {
            LeafNode newRoot = new LeafNode();
            newRoot.keys.add(key);
            newRoot.values.add(value);
            newRoot.kvPairs.put(key, newRoot.values);
            leaves.add(newRoot);
            this.root = newRoot;
            System.out
            .println("\nINSERTING: Key- [" + key + "], Value- " + newRoot.values.toString());
        } else {
            insert(key, value, root);
        }
    }

    /**
     * Private recursive helper method for public insert method above. This method finds the proper
     * leaf node to insert the new key and value into.
     * 
     * @param key to insert
     * @param value to insert
     * @param reference to the current node in tree traversal
     */
    @SuppressWarnings("unchecked")
    private void insert(K key, V value, Node index) {

        InternalNode node = new InternalNode();
        if (index.getClass().equals(node.getClass())) {
            for (int i = 0; i < index.keys.size(); i++) {
                if (key.compareTo(index.keys.get(i)) < 0) {
                    insert(key, value, ((InternalNode) index).children.get(i));
                    return;
                }
            }
            insert(key, value,
                ((InternalNode) index).children.get(((InternalNode) index).children.size() - 1));
        } else {
            for (LeafNode leaf : leaves) {
                if (leaves.size() == 1) {
                    leaf.insert(key, value);
                    return;
                }
                if (key.compareTo(leaf.keys.get(leaf.keys.size() - 1)) <= 0) {
                    leaf.insert(key, value);
                    return;
                }
                if (leaf.next == null || key.compareTo(leaf.next.getFirstLeafKey()) < 0) {
                    leaf.insert(key, value);
                    return;
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see BPTreeADT#rangeSearch(java.lang.Object, java.lang.String)
     */
    @Override
    public List<V> rangeSearch(K key, String comparator) {

        List<V> nodeVals = new ArrayList<V>();
        switch (comparator) {
            case "<=":
                for (LeafNode index : leaves) {
                    for (int i = 0; i < index.keys.size(); i++) {
                        K keyOfVals = index.keys.get(i);
                        if (key.compareTo(keyOfVals) >= 0) {
                            nodeVals.addAll(index.kvPairs.get(keyOfVals));
                        } else {
                            return nodeVals;
                        }
                    }
                }
                return nodeVals;
            case "==":
                return root.rangeSearch(key, comparator);
            case ">=":
                return root.rangeSearch(key, comparator);
            default:
                return new ArrayList<V>();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String toString() {

        Queue<List<Node>> queue = new LinkedList<List<Node>>();
        queue.add(Arrays.asList(root));
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
            while (!queue.isEmpty()) {
                List<Node> nodes = queue.remove();
                sb.append('{');
                Iterator<Node> it = nodes.iterator();
                while (it.hasNext()) {
                    Node node = it.next();
                    sb.append(node.toString());
                    if (it.hasNext())
                        sb.append(", ");
                    if (node instanceof BPTree.InternalNode)
                        nextQueue.add(((InternalNode) node).children);
                }
                sb.append('}');
                if (!queue.isEmpty())
                    sb.append(", ");
                else {
                    sb.append('\n');
                }
            }
            queue = nextQueue;
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private InternalNode getParent(InternalNode current, Node leaf) {

        for (int i = 0; i < current.children.size(); i++) {
            Node child = current.children.get(i);
            if (child.equals(leaf)) {
                return current;
            } else if (child.keys.get(0).compareTo(leaf.keys.get(0)) > 0) {
                InternalNode node = new InternalNode();
                if (node.getClass().equals(child.getClass())) {
                    return getParent((InternalNode) child, leaf);
                }
            } else if (i == current.children.size() - 1) {
                InternalNode node = new InternalNode();
                if (node.getClass().equals(child.getClass())) {
                    return getParent((InternalNode) child, leaf);
                }
            }
        }
        return null;
    }

    /**
     * This abstract class represents any type of node in the tree This class is a super class of
     * the LeafNode and InternalNode types.
     * 
     * @author sapan
     */
    private abstract class Node {

        // List of keys
        List<K> keys;

        /**
         * Package constructor
         */
        Node() {

            keys = new ArrayList<K>();
        }

        /**
         * Inserts key and value in the appropriate leaf node and balances the tree if required by
         * splitting
         * 
         * @param key
         * @param value
         */
        abstract void insert(K key, V value);

        /**
         * Gets the first leaf key of the tree
         * 
         * @return key
         */
        abstract K getFirstLeafKey();

        /**
         * Gets the new sibling created after splitting the node
         * 
         * @return Node
         */
        abstract Node split();

        /*
         * (non-Javadoc)
         * 
         * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
         */
        abstract List<V> rangeSearch(K key, String comparator);

        /**
         * Checks the branching factor
         * 
         * @return boolean
         */
        abstract boolean isOverflow();

        public String toString() {

            return keys.toString();
        }

    } // End of abstract class Node

    /**
     * This class represents an internal node of the tree. This class is a concrete sub class of the
     * abstract Node class and provides implementation of the operations required for internal
     * (non-leaf) nodes.
     * 
     * @author sapan
     */
    private class InternalNode extends Node {

        List<Node> children; // List of children nodes

        /**
         * Package constructor
         */
        InternalNode() {

            super();
            children = new ArrayList<Node>();
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {

            return leaves.element().keys.get(0);
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {

            return keys.size() >= branchingFactor;
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
         */
        void insert(K key, V value) {

            if (keys.size() == 0) {
                keys.add(key);
                return;
            }
            for (int i = 0; i < keys.size(); i++) {
                if (key.compareTo(keys.get(i)) < 0) {
                    keys.add(i, key);
                    break;
                } else if (i == keys.size() - 1 && key.compareTo(keys.get(i)) > 0) {
                    keys.add(key);
                    break;
                }
            }
            if (isOverflow()) {
                split();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#split()
         */
        @SuppressWarnings("unchecked")
        Node split() {

            int splitIndex = keys.size() / 2;
            K parentKey = keys.remove(splitIndex);
            InternalNode sibling = new InternalNode(); // split this node into two
            sibling.keys.addAll(keys.subList(splitIndex, keys.size()));
            keys.subList(splitIndex, keys.size()).clear();
            sibling.children.addAll(children.subList(keys.size() + 1, children.size()));
            children.subList(keys.size() + 1, children.size()).clear();

            InternalNode parent = getParent((InternalNode) root, this);
            if (parent != null) {
                parent.children.add(sibling);
                parent.insert(parentKey, null);
            } else {
                parent = new InternalNode();
                parent.keys.add(parentKey);
                parent.children.add(this);
                parent.children.add(sibling);
                root = parent;
            }
            return sibling;
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#rangeSearch(java.lang.Comparable, java.lang.String)
         */
        List<V> rangeSearch(K key, String comparator) {

            List<V> range = new ArrayList<V>();
            for (int i = 0; i < keys.size(); i++) {
                if ((key.compareTo(keys.get(i)) == 0)
                    || (i == keys.size() - 1 && key.compareTo(keys.get(i)) > 0)) {
                    return children.get(i + 1).rangeSearch(key, comparator);
                } else if (key.compareTo(keys.get(i)) < 0) {
                    return children.get(i).rangeSearch(key, comparator);
                }
            }
            return range;
        }
    } // End of class InternalNode

    /**
     * This class represents a leaf node of the tree. This class is a concrete sub class of the
     * abstract Node class and provides implementation of the operations that required for leaf
     * nodes.
     * 
     * @author sapan
     */
    private class LeafNode extends Node {


        List<V> values; // List of values

        LeafNode next; // Reference to the next leaf node

        @SuppressWarnings("unused")
        LeafNode previous; // Reference to the previous leaf node

        HashMap<K, List<V>> kvPairs; // Reference to value(s) associated with each key

        /**
         * Package constructor
         */
        LeafNode() {

            super();
            values = new ArrayList<V>();
            next = null;
            previous = null;
            kvPairs = new HashMap<K, List<V>>();
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {

            return keys.get(0);
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {

            return keys.size() >= branchingFactor;
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#insert(Comparable, Object)
         */
        void insert(K key, V value) {

            for (int i = 0; i < keys.size(); i++) {
                List<V> curVals = new ArrayList<V>();
                if (key.compareTo(keys.get(i)) == 0) {
                    curVals = kvPairs.get(key);
                    curVals.add(value);
                    kvPairs.replace(key, curVals);
                    break;
                } else if (key.compareTo(keys.get(i)) < 0) {
                    keys.add(i, key);
                    curVals.add(value);
                    kvPairs.put(key, curVals);
                    break;
                } else if (i == keys.size() - 1 && key.compareTo(keys.get(i)) > 0) {
                    keys.add(key);
                    curVals.add(value);
                    kvPairs.put(key, curVals);
                    break;
                }
            }
            if (isOverflow()) {
                split();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#split()
         */
        @SuppressWarnings("unchecked")
        Node split() {

            int splitIndex = keys.size() / 2;
            K splitKey = keys.get(splitIndex);
            LeafNode rightSplit = new LeafNode(); // split this node into two
            next = rightSplit;
            rightSplit.previous = this;
            for (int i = splitIndex; i < keys.size(); i++) {
                rightSplit.keys.add(keys.get(i));
                List<V> siblingVals = kvPairs.remove(keys.get(i));
                values.removeAll(siblingVals);
                rightSplit.kvPairs.put(keys.get(i), siblingVals);
                rightSplit.values.addAll(siblingVals);
            }
            keys.subList(splitIndex, keys.size()).clear();
            leaves.add(leaves.indexOf(this) + 1, rightSplit);

            InternalNode parent = new InternalNode();
            if (this.equals(root)) {
                parent.children.add(this);
                parent.children.add(rightSplit);
                root = parent;
            } else {
                parent = getParent((InternalNode) root, this);
                parent.children.add(parent.children.indexOf(this) + 1, rightSplit);
            }
            parent.insert(splitKey, null);
            return rightSplit;
        }

        /*
         * (non-Javadoc)
         * 
         * @see BPTree.Node#rangeSearch(Comparable, String)
         */
        List<V> rangeSearch(K key, String comparator) {

            List<V> nodeVals = new ArrayList<V>();
            switch (comparator) {
                case "==":
                    for (int i = 0; i < keys.size(); i++) {
                        if (keys.get(i).equals(key)) {
                            nodeVals.addAll(kvPairs.get(key));
                        }
                    }
                    break;
                case ">=":
                    for (int j = 0; j < keys.size(); j++) {
                        if (key.compareTo(keys.get(j)) <= 0) {
                            nodeVals.addAll(kvPairs.get(keys.get(j)));
                        }
                    }
                    if (next != null) {
                        nodeVals.addAll(next.rangeSearch(key, comparator));
                    }
                    break;
                default:
                    break;
            }
            return nodeVals;
        }
    } // End of class LeafNode

    /**
     * Contains a basic test scenario for a BPTree instance. It shows a simple example of the use of
     * this class and its related types.
     * 
     * @param args
     */
    public static void main(String[] args) {

        // create empty BPTree with branching factor of 3
        // BPTree<Double, Double> bpTree = new BPTree<>(3);
        BPTree<Integer, Integer> bpTree = new BPTree<>(4);
        // create a pseudo random number generator
        // Random rnd1 = new Random();

        // some value to add to the BPTree
        // Double[] dd = { 0.0d, 0.5d, 0.2d, 0.8d };

        // build an ArrayList of those value and add to BPTree also
        // allows for comparing the contents of the ArrayList
        // against the contents and functionality of the BPTree
        // does not ensure BPTree is implemented correctly
        // just that it functions as a data structure with
        // insert, rangeSearch, and toString() working.
        // List<Double> list = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            // Double j = dd[rnd1.nextInt(4)];
            // list.add(j);
            list.add(i);
            // bpTree.insert(j, j);
            bpTree.insert(i, i);
            System.out.println("\n\nTree structure:\n" + bpTree.toString());
        }
        // List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">=");
        List<Integer> filteredValues = bpTree.rangeSearch(20, ">=");
        System.out.println("Filtered values: " + filteredValues.toString());
    }

} // End of class BPTree
