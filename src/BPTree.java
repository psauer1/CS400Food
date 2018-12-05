import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Implementation of a B+ tree to allow efficient access to
 * many different indexes of a large data set. 
 * BPTree objects are created for each type of index
 * needed by the program.  BPTrees provide an efficient
 * range search as compared to other types of data structures
 * due to the ability to perform log_m N lookups and
 * linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K> key - expect a string that is the type of id for each item
 * @param <V> value - expect a user-defined type that stores all data for a food item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

    // Root of the tree
	private Node root;

	// Branching factor is the number of children nodes
	// for internal nodes of the tree
	private int branchingFactor;

	private LinkedList<LeafNode> leaves;

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

	public boolean isEmpty() {

		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see BPTreeADT#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {

		if (isEmpty()) {
			LeafNode newRoot = new LeafNode();
			newRoot.insert(key, value);
			leaves.add(newRoot);
			this.root = newRoot;
		} else {
			for (LeafNode leaf : leaves) {
				if (leaf.getFirstLeafKey().compareTo(key) >= 0) {
					if (leaf.keys.get(leaf.keys.size() - 1).compareTo(key) <= 0) {
						if (leaf.isOverflow()) {
							leaf.split();
						} else {
							leaf.insert(key, value);
						}
						return;
					} 
				}
			}
		}
	}
    
    /**
     * (non-Javadoc)
     * @see BPTreeADT#rangeSearch(java.lang.Object, java.lang.String)
     */
    @Override
    public List<V> rangeSearch(K key, String comparator) {
        @Override
	public List<V> rangeSearch(K key, String comparator) {
		List<V> searched = new ArrayList<V>();
		List<K> keyList = root.keys;
		if (key == null) {
			return searched; 
		}
		if (comparator.equals("==")) {
			for (int i = 0; i < leaves.size(); i++) {
				for (K thing : keyList) {
					if (thing.equals(key)) {
						searched.add(leaves.get(i).values.get(i));
					}
				}
			}
		}
		if (comparator.equals("<=")) {
			for (int i = 0; i < leaves.size(); i++) {
				if ((leaves.get(i).keys.get(i)).compareTo(key) <= 0)  {
					searched.add(leaves.get(i).values.get(i));
				}			
			}
		}
		if (comparator.equals(">=")) {
			for (int i = 0; i < leaves.size(); i++) {
				if (leaves.get(i).keys.get(i).compareTo(key) >= 0 )  {
					searched.add(leaves.get(i).values.get(i));

				}
			}
		}
		return searched; 
	}
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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
	private InternalNode getParent(InternalNode current, LeafNode leaf) {
		
		for (Node child : current.children) {
			if (child.keys.get(0).compareTo(leaf.getFirstLeafKey()) < 0) {
				return getParent((InternalNode)child, leaf);
			}
			if (child.equals(leaf)) {
				return current;
			}
		}
		return null;
	}
    
    /**
     * This abstract class represents any type of node in the tree
     * This class is a super class of the LeafNode and InternalNode types.
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
            // TODO : Complete
        	keys = new ArrayList<K>();
        }
        
        /**
         * Inserts key and value in the appropriate leaf node 
         * and balances the tree if required by splitting
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
         * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
         */
        abstract List<V> rangeSearch(K key, String comparator);

        /**
         * Checks the branching factor
         * @return boolean
         */
        abstract boolean isOverflow();
        
        public String toString() {
            return keys.toString();
        }
    
    } // End of abstract class Node
    
    /**
     * This class represents an internal node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations
     * required for internal (non-leaf) nodes.
     * 
     * @author sapan
     */
    private class InternalNode extends Node {

        // List of children nodes
        List<Node> children;
        
        /**
         * Package constructor
         */
        InternalNode() {
            super();
            // TODO : Complete
            children = new ArrayList<Node>();
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
            // TODO : Complete
            return keys.get(0);
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
            // TODO : Complete
        	int maxSize = branchingFactor - 1;
        	if (keys.size() == maxSize)
        		return true;
            return false;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
         */
	void insert(K key, V value) {
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i).compareTo(key) >= 0) {
				keys.add(i, key);
			}
		}
		if (isOverflow()) {
			split();
		}
	}
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
		Node split() {
			int splitIndex = (keys.size()) / 2;
			K splitKey = keys.get(splitIndex);

			// right split
			InternalNode sibling = new InternalNode();
			sibling.keys = keys.subList(splitIndex, keys.size());
			sibling.children = children.subList(splitIndex, children.size());

			// fix this node
			children = children.subList(0, splitIndex);
			keys = keys.subList(splitIndex, keys.size());

			// promote splitKey to parent node

			return sibling;
		}
        
        /**
	 * (non-Javadoc)
	 * 
	 * @see BPTree.Node#rangeSearch(java.lang.Comparable, java.lang.String)
	 */
	List<V> rangeSearch(K key, String comparator) {
		List<V> range = new ArrayList<V>();
		for (int i = 0; i < keys.size(); i++) {
			if ((comparator.equals("==") && keys.get(i).compareTo(key) == 0) 
				|| (comparator.equals(">=") && keys.get(i).compareTo(key) >= 0) 
				|| (comparator.equals("<=") && keys.get(i).compareTo(key) <= 0)) {
				for (Node child : children) {
					range = child.rangeSearch(key, comparator);
				}
			}
		}
		return range;
	}
    
    } // End of class InternalNode
    
    
    /**
     * This class represents a leaf node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations that
     * required for leaf nodes.
     * 
     * @author sapan
     */
    private class LeafNode extends Node {
        
        // List of values
        List<V> values;
        
        // Reference to the next leaf node
        LeafNode next;
        
        // Reference to the previous leaf node
        LeafNode previous;
        
	HashMap<K, List<V>> kvPairs;
	    
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
        
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
            return keys.get(0);
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
            if (keys.size() >= branchingFactor - 1) {
            	return true;
            }
            return false;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(Comparable, Object)
         */
        void insert(K key, V value) {
		
		if (!isOverflow()) {
			for (int i = 0; i < keys.size(); i++) {
				if (key.compareTo(keys.get(i)) == 0) {
					List<V> curVals = kvPairs.get(key);
					kvPairs.remove(key);
					curVals.add(value);
					kvPairs.put(key, curVals);
					return;
				} else if (key.compareTo(keys.get(i)) < 0) {
					keys.add(i, key);
					values.add(i, value);
					List<V> pairVals = new ArrayList<V>();
					pairVals.add(value);
					kvPairs.put(key, pairVals);
					return;
				}
			}
		} else {
			keys.add(key);
			values.add(value);
			split();
		}
        }
        

        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
        Node split() {
		
        	int splitIndex = keys.size() / 2;
    		
    		// split this node into right
    		LeafNode rightSplit = new LeafNode();
    		rightSplit.keys = keys.subList(splitIndex, keys.size());
    		rightSplit.values = values.subList(splitIndex, keys.size());
    		keys = keys.subList(0, keys.size());
    		values = values.subList(0, splitIndex);
    		InternalNode parent = getParent((InternalNode)root, this);
    		parent.children.add(rightSplit);
    		
    		// connect this node to new right node
    		next = rightSplit;
    		rightSplit.previous = this;
    		
    		// promote key.get(splitIndex) to internalNode
    		return rightSplit;
        		
            return rightSplit;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#rangeSearch(Comparable, String)
         */
        List<V> rangeSearch(K key, String comparator) {
			List<V> nodeVals = new ArrayList<V>();
			if (comparator.equals("==")) {
				for (int i = 0; i < keys.size(); i++) {
					if (keys.get(i).equals(key)) {
						nodeVals.add(values.get(i));
					} 
				}
			}
			if (comparator.equals("<=")) {
				for (int i = 0; i < keys.size(); i++) {
					if (keys.get(i).compareTo(key) <= 0) {
						nodeVals.add(values.get(i));
					} 
				}
			}
			if (comparator.equals(">=")) {
				for (int i = 0; i < keys.size(); i++) {
					if (keys.get(i).compareTo(key) >= 0) {
						nodeVals.add(values.get(i));
					} 
				}
			}
			return nodeVals; 
		}
        
    } // End of class LeafNode
    
    
    /**
     * Contains a basic test scenario for a BPTree instance.
     * It shows a simple example of the use of this class
     * and its related types.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // create empty BPTree with branching factor of 3
        BPTree<Double, Double> bpTree = new BPTree<>(3);

        // create a pseudo random number generator
        Random rnd1 = new Random();

        // some value to add to the BPTree
        Double[] dd = {0.0d, 0.5d, 0.2d, 0.8d};

        // build an ArrayList of those value and add to BPTree also
        // allows for comparing the contents of the ArrayList 
        // against the contents and functionality of the BPTree
        // does not ensure BPTree is implemented correctly
        // just that it functions as a data structure with
        // insert, rangeSearch, and toString() working.
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            Double j = dd[rnd1.nextInt(4)];
            list.add(j);
            bpTree.insert(j, j);
            System.out.println("\n\nTree structure:\n" + bpTree.toString());
        }
        List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">=");
        System.out.println("Filtered values: " + filteredValues.toString());
    }

} // End of class BPTree
