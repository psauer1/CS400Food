package application;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TestBPTree {
	@Rule
	public Timeout timeLimit = new Timeout(2000, TimeUnit.MILLISECONDS);

	@Test
	public void test01IsEmpty() {
		BPTree<Integer,String> bpTree = new BPTree<Integer,String>(4);
		assertTrue(bpTree.isEmpty());
	}
	@Test
	public void test02insert() {
		BPTree<Integer,String> bpTree = new BPTree<Integer,String>(4);
		bpTree.insert(2, "spinach");
		bpTree.insert(1, "hi");
		bpTree.insert(20, "carrot");
		System.out.println("Size of the tree: " + bpTree.size());
		assertTrue(bpTree.size() == 3);
	}

	@Test
	public void test03checkRangeSearch() {
		BPTree<Integer, String> bpTree = new BPTree<Integer, String>(4);
		bpTree.insert(16, "spinach");
		bpTree.insert(20, "carrot");
		bpTree.insert(1, "pizza");
		System.out.println("rangeSearch List: " + bpTree.rangeSearch(15, "<=")); 
		assertTrue(bpTree.rangeSearch(15, ">=").size() == 2);
	}

	@Test
	public void test04firstSplit() {
		BPTree<Integer, String> bpTree = new BPTree<Integer, String>(4);
		bpTree.insert(1, "spinach");
		bpTree.insert(3, "carrot");
		bpTree.insert(16, "pizza");
		bpTree.insert(20, "Corona");
		assertTrue(bpTree.root.keys(0).size() == 1); 
	}

}
