
package unbbayes.util.dseparation.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import unbbayes.prs.INode;
import unbbayes.prs.exception.InvalidParentException;

/**
 * @author Shou Matsumoto
 * JUnit test class for MSeparationUtility
 * @see MSeparationUtility
 *
 */
public class MSeparationUtilityTest extends TestCase {
	
	protected MSeparationUtility classUnderTest = null;
	
	/**
	 * We do not use an instance of Graph here
	 * because MSeparationUtility does not use Graph, but only nodes.
	 * 
	 * The nodes are identified by its index at this list.
	 */
	protected List<INode> nodesUnderTest = null;
	
	/**
	 * Temporary representation of non-directed graph
	 */
	protected Map<INode, Set<INode>> adjacencyMap = null;
	

	/**
	 * @param name
	 */
	public MSeparationUtilityTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		this.classUnderTest = MSeparationUtility.newInstance();
		
		// Startup nodes
		int numberOfNodes = 6;	// how many nodes there are
		this.nodesUnderTest = new ArrayList<INode>(numberOfNodes);
		for (int i = 0; i < numberOfNodes; i++) {
			this.nodesUnderTest.add(new MSeparationTestNode("Node" + i));
		}
		
		/*
		 * Set up relationship as shown below:
		 * 
		 * (Node0)
		 *   |
		 *   V
		 * (Node1)------>(Node2)--------
		 *   \                          \
		 *    \                          V
		 *     ------->(Node3)-------->(Node4)----->(Node5)
		 * 
		 */		
		
		// Node0 -> Node1
		this.nodesUnderTest.get(0).addChildNode(this.nodesUnderTest.get(1));
		
		// Node1 -> (Node2,Node3)
		this.nodesUnderTest.get(1).addChildNode(this.nodesUnderTest.get(2));
		this.nodesUnderTest.get(1).addChildNode(this.nodesUnderTest.get(3));
		
		// Node2 -> Node4
		this.nodesUnderTest.get(2).addChildNode(this.nodesUnderTest.get(4));
		
		// Node3 -> Node4
		this.nodesUnderTest.get(3).addChildNode(this.nodesUnderTest.get(4));
		
		// Node 4 -> Node5
		this.nodesUnderTest.get(4).addChildNode(this.nodesUnderTest.get(5));
		
		// build adjacency map
		try{
			this.adjacencyMap = this.classUnderTest.buildClosedAdjacentNodeMap(new HashSet<INode>(this.nodesUnderTest));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to initialize adjacency map");
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.classUnderTest = null;
		this.nodesUnderTest = null;
		this.adjacencyMap = null;
		System.gc();
	}


	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#buildClosedAdjacentNodeMap(java.util.Set)}.
	 */
	public   void testBuildClosedAdjacentNodeMap() {
		Map<INode, Set<INode>> map = this.adjacencyMap;
		
		// test adjacency consistency
		
		assertEquals("0 should be adjacent to only 1 node", 
				map.get(this.nodesUnderTest.get(0)).size() , 1);
		assertTrue("0 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(0)).contains(this.nodesUnderTest.get(1)));
		
		assertEquals("1 should be adjacent to only 3 nodes", 
				map.get(this.nodesUnderTest.get(1)).size() , 3);
		assertTrue("1 should be adjacent to 2", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(2)));
		assertTrue("1 should be adjacent to 3", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(3)));
		assertTrue("1 should be adjacent to 0", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(0)));
		
		
		assertEquals("2 should be adjacent to only 2 nodes", 
				map.get(this.nodesUnderTest.get(2)).size() , 2);
		assertTrue("2 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(2)).contains(this.nodesUnderTest.get(1)));
		assertTrue("2 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(2)).contains(this.nodesUnderTest.get(4)));
		
		
		assertEquals("3 should be adjacent to only 2 nodes", 
				map.get(this.nodesUnderTest.get(3)).size() , 2);
		assertTrue("3 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(3)).contains(this.nodesUnderTest.get(1)));
		assertTrue("3 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(3)).contains(this.nodesUnderTest.get(4)));
		
		
		assertEquals("4 should be adjacent to only 3 nodes", 
				map.get(this.nodesUnderTest.get(4)).size() , 3);
		assertTrue("4 should be adjacent to 2", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(2)));
		assertTrue("4 should be adjacent to 3", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(3)));
		assertTrue("4 should be adjacent to 5", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(5)));
		
		
		assertEquals("5 should be adjacent to only 1 node", 
				map.get(this.nodesUnderTest.get(5)).size() , 1);
		assertTrue("5 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(5)).contains(this.nodesUnderTest.get(4)));

		
	}

	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#makeItMoral(java.util.Map)}.
	 */
	public   void testMakeItMoral() {
		Map<INode, Set<INode>> map = this.adjacencyMap;
		
		try {
			map = this.classUnderTest.makeItMoral(map);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to moralize adjacency map");
		}
		
		// test adjacency consistency with moral nodes
		
		assertEquals("0 should be adjacent to only 1 node", 
				map.get(this.nodesUnderTest.get(0)).size() , 1);
		assertTrue("0 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(0)).contains(this.nodesUnderTest.get(1)));
		
		assertEquals("1 should be adjacent to only 3 nodes", 
				map.get(this.nodesUnderTest.get(1)).size() , 3);
		assertTrue("1 should be adjacent to 2", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(2)));
		assertTrue("1 should be adjacent to 3", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(3)));
		assertTrue("1 should be adjacent to 0", 
				map.get(this.nodesUnderTest.get(1)).contains(this.nodesUnderTest.get(0)));
		
		
		assertEquals("2 should be adjacent to only 2 nodes and 1 became moral", 
				map.get(this.nodesUnderTest.get(2)).size() , 2 + 1);
		assertTrue("2 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(2)).contains(this.nodesUnderTest.get(1)));
		assertTrue("2 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(2)).contains(this.nodesUnderTest.get(4)));
		assertTrue("2 should be adjacent to 3 by becoming moral", 
				map.get(this.nodesUnderTest.get(2)).contains(this.nodesUnderTest.get(3)));
		
		
		assertEquals("3 should be adjacent to only 2 nodes and 1 became moral", 
				map.get(this.nodesUnderTest.get(3)).size() , 2 + 1);
		assertTrue("3 should be adjacent to 1", 
				map.get(this.nodesUnderTest.get(3)).contains(this.nodesUnderTest.get(1)));
		assertTrue("3 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(3)).contains(this.nodesUnderTest.get(4)));
		assertTrue("3 should be adjacent to 2 by becoming moral", 
				map.get(this.nodesUnderTest.get(3)).contains(this.nodesUnderTest.get(2)));
		
		
		assertEquals("4 should be adjacent to only 3 nodes", 
				map.get(this.nodesUnderTest.get(4)).size() , 3);
		assertTrue("4 should be adjacent to 2", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(2)));
		assertTrue("4 should be adjacent to 3", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(3)));
		assertTrue("4 should be adjacent to 5", 
				map.get(this.nodesUnderTest.get(4)).contains(this.nodesUnderTest.get(5)));
		
		
		assertEquals("5 should be adjacent to only 1 node", 
				map.get(this.nodesUnderTest.get(5)).size() , 1);
		assertTrue("5 should be adjacent to 4", 
				map.get(this.nodesUnderTest.get(5)).contains(this.nodesUnderTest.get(4)));

		
	}

	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#getRoutes(unbbayes.prs.INode, unbbayes.prs.INode, java.util.Map)}.
	 */
	public   void testGetRoutesINodeINodeMapOfINodeSetOfINode() {
		Set<List<INode>> routes = null;
		
		// routes from 0 to 5, not moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(0), this.nodesUnderTest.get(5), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 0 to 5");
		}		
		assertEquals("Expected number of routes is 2" , 2 , routes.size());
		for (List<INode> route : routes) {
			assertEquals("Route size is allways 5" , 5 , route.size() );
			assertEquals("Route must start from 0" , this.nodesUnderTest.get(0) , route.get(0));
			assertEquals("Route must end to 5" , this.nodesUnderTest.get(5) , route.get(route.size()-1));
			assertTrue("Route must contain at least node 1" , route.contains(this.nodesUnderTest.get(1)));
			assertTrue("Route must contain at least node 4" , route.contains(this.nodesUnderTest.get(4)));
			assertTrue("Route must contain node 2 or 3" , 
					route.contains(this.nodesUnderTest.get(2)) || route.contains(this.nodesUnderTest.get(3)));		
		}
		
		
		// routes from 5 to 0, not moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(5), this.nodesUnderTest.get(0), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 5 to 0");
		}		
		assertEquals("Expected number of routes is 2" , 2 , routes.size());
		for (List<INode> route : routes) {
			assertEquals("Route size is allways 5" , 5 , route.size() );
			assertEquals("Route must start from 5" , this.nodesUnderTest.get(5) , route.get(0));
			assertEquals("Route must end to 0" , this.nodesUnderTest.get(0) , route.get(route.size()-1));
			assertTrue("Route must contain at least node 1" , route.contains(this.nodesUnderTest.get(1)));
			assertTrue("Route must contain at least node 4" , route.contains(this.nodesUnderTest.get(4)));
			assertTrue("Route must contain node 2 or 3" , 
					route.contains(this.nodesUnderTest.get(2)) || route.contains(this.nodesUnderTest.get(3)));			
		}
		
		
		// routes from 2 to 3, not moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(2), this.nodesUnderTest.get(3), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 2 to 3");
		}		
		assertEquals("Expected number of routes is 2" , 2 , routes.size());
		for (List<INode> route : routes) {
			assertEquals("Route size is allways 3" , 3 , route.size() );
			assertEquals("Route must start from 2" , this.nodesUnderTest.get(2) , route.get(0));
			assertEquals("Route must end to 3" , this.nodesUnderTest.get(3) , route.get(route.size()-1));
			assertTrue("Route must contain node 1 or 4" , 
					route.contains(this.nodesUnderTest.get(1)) || route.contains(this.nodesUnderTest.get(4)));			
		}
		
		
		// routes from 3 to 2, not moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(3), this.nodesUnderTest.get(2), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 3 to 2");
		}		
		assertEquals("Expected number of routes is 2" , 2 , routes.size());
		for (List<INode> route : routes) {
			assertEquals("Route size is allways 3" , 3 , route.size() );
			assertEquals("Route must start from 3" , this.nodesUnderTest.get(3) , route.get(0));
			assertEquals("Route must end to 2" , this.nodesUnderTest.get(2) , route.get(route.size()-1));
			assertTrue("Route must contain node 1 or 4" , 
					route.contains(this.nodesUnderTest.get(1)) || route.contains(this.nodesUnderTest.get(4)));			
		}
		
		// make them moral
		this.adjacencyMap = this.classUnderTest.makeItMoral(this.adjacencyMap);
		
		// routes from 0 to 5, moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(0), this.nodesUnderTest.get(5), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 0 to 5");
		}		
		assertEquals("Expected number of routes is 4" , 4 , routes.size());
		for (List<INode> route : routes) {
			assertTrue("Route size is at least 5" , 5 <= route.size() );
			assertTrue("Route size is at most 6" , 6 >= route.size() );
			assertEquals("Route must start from 0" , this.nodesUnderTest.get(0) , route.get(0));
			assertEquals("Route must end to 5" , this.nodesUnderTest.get(5) , route.get(route.size()-1));
			assertTrue("Route must contain at least node 1" , route.contains(this.nodesUnderTest.get(1)));
			assertTrue("Route must contain at least node 4" , route.contains(this.nodesUnderTest.get(4)));
			assertTrue("Route must contain node 2 or 3" , 
					route.contains(this.nodesUnderTest.get(2)) || route.contains(this.nodesUnderTest.get(3)));		
		}
		
		
		// routes from 5 to 0, moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(5), this.nodesUnderTest.get(0), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 5 to 0");
		}		
		assertEquals("Expected number of routes is 4" , 4 , routes.size());
		for (List<INode> route : routes) {
			assertTrue("Route size is at least 5" , 5 <= route.size() );
			assertTrue("Route size is at most 6" , 6 >= route.size() );
			assertEquals("Route must start from 5" , this.nodesUnderTest.get(5) , route.get(0));
			assertEquals("Route must end to 0" , this.nodesUnderTest.get(0) , route.get(route.size()-1));
			assertTrue("Route must contain at least node 1" , route.contains(this.nodesUnderTest.get(1)));
			assertTrue("Route must contain at least node 4" , route.contains(this.nodesUnderTest.get(4)));
			assertTrue("Route must contain node 2 or 3" , 
					route.contains(this.nodesUnderTest.get(2)) || route.contains(this.nodesUnderTest.get(3)));			
		}
		
		
		// routes from 2 to 3, moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(2), this.nodesUnderTest.get(3), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 2 to 3");
		}		
		assertEquals("Expected number of routes is 3" , 3 , routes.size());
		for (List<INode> route : routes) {
			assertTrue("Route size is at least 2" , 2 <= route.size() );
			assertTrue("Route size is at most 3" , 3 >= route.size() );
			assertEquals("Route must start from 2" , this.nodesUnderTest.get(2) , route.get(0));
			assertEquals("Route must end to 3" , this.nodesUnderTest.get(3) , route.get(route.size()-1));
		}
		
		
		// routes from 3 to 2, not moral
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(3), this.nodesUnderTest.get(2), this.adjacencyMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 3 to 2");
		}		
		assertEquals("Expected number of routes is 3" , 3 , routes.size());
		for (List<INode> route : routes) {
			assertTrue("Route size is at least 2" , 2 <= route.size() );
			assertTrue("Route size is at most 3" , 3 >= route.size() );
			assertEquals("Route must start from 3" , this.nodesUnderTest.get(3) , route.get(0));
			assertEquals("Route must end to 2" , this.nodesUnderTest.get(2) , route.get(route.size()-1));
		}
		
	}

	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#getRoutes(unbbayes.prs.INode, unbbayes.prs.INode)}.
	 */
	public   void testGetRoutesINodeINode() {
		Set<List<INode>> routes = null;
		
		// routes from 0 to 5, directed
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(0), this.nodesUnderTest.get(5));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 0 to 5");
		}		
		assertEquals("Expected number of routes is 2" , 2 , routes.size());
		for (List<INode> route : routes) {
			assertEquals("Route size is allways 5" , 5 , route.size() );
			assertEquals("Route must start from 0" , this.nodesUnderTest.get(0) , route.get(0));
			assertEquals("Route must end to 5" , this.nodesUnderTest.get(5) , route.get(route.size()-1));
			assertTrue("Route must contain at least node 1" , route.contains(this.nodesUnderTest.get(1)));
			assertTrue("Route must contain at least node 4" , route.contains(this.nodesUnderTest.get(4)));
			assertTrue("Route must contain node 2 or 3" , 
					route.contains(this.nodesUnderTest.get(2)) || route.contains(this.nodesUnderTest.get(3)));		
		}
		
		
		// routes from 5 to 0, directed
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(5), this.nodesUnderTest.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 5 to 0");
		}		
		assertEquals("Expected number of routes is 0" , 0 , routes.size());
		
		
		// routes from 2 to 3, directed
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(2), this.nodesUnderTest.get(3));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 2 to 3");
		}		
		assertEquals("Expected number of routes is 0" , 0 , routes.size());
		
		// routes from 3 to 2, directed
		try {
			routes = this.classUnderTest.getRoutes(
					this.nodesUnderTest.get(3), this.nodesUnderTest.get(2));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed to obtain route from 3 to 2");
		}		
		assertEquals("Expected number of routes is 0" , 0 , routes.size());
		
	}

	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#getAllAncestors(java.util.Set)}.
	 */
	public   void testGetAllAncestors() {
		
		Set<INode> param = new HashSet<INode>();;
		
		// ancestors of node 0
		param.clear();
		param.add(this.nodesUnderTest.get(0));
		assertEquals("Number of ancestors of 0 must be 0", 0, this.classUnderTest.getAllAncestors(param).size());
		
		
		// ancestors of node 2
		param.clear();
		param.add(this.nodesUnderTest.get(2));
		Set<INode> ancestors2 = this.classUnderTest.getAllAncestors(param);		
		assertEquals("Number of ancestors of 2 must be 2", 2, ancestors2.size());
		assertFalse("Node should not be ancestor of itself", 
				ancestors2.contains(this.nodesUnderTest.get(2)));
		assertTrue("0 is ancestor of 2", 
				ancestors2.contains(this.nodesUnderTest.get(0)));
		assertTrue("1 is ancestor of 2", 
				ancestors2.contains(this.nodesUnderTest.get(1)));
		
		// ancestors of node 3
		param.clear();
		param.add(this.nodesUnderTest.get(3));
		Set<INode> ancestors3 = this.classUnderTest.getAllAncestors(param);		
		assertFalse("Node should not be ancestor of itself", 
				ancestors3.contains(this.nodesUnderTest.get(3)));
		
		assertTrue("ancestors of node 3 and 2 must be the same", ancestors2.equals(ancestors3));
		
		
		// ancestors of {2,3}
		param.clear();
		param.add(this.nodesUnderTest.get(2));
		param.add(this.nodesUnderTest.get(3));
		Set<INode> ancestors23 = this.classUnderTest.getAllAncestors(param);		
		
		assertTrue("ancestors of {2,3} and 2 must be the same", ancestors23.equals(ancestors2));
		
		
		
		// ancestors of node 5
		param.clear();
		param.add(this.nodesUnderTest.get(5));
		Set<INode> ancestors5 = this.classUnderTest.getAllAncestors(param);		
		assertEquals("Number of ancestors of 5 must be 5", 5, ancestors5.size());
		assertFalse("Node should not be ancestor of itself", ancestors5.contains(this.nodesUnderTest.get(5)));
		assertTrue("Ancestors of 5 must contain ancestors of {2,3}", 
				ancestors5.containsAll(ancestors23));
		assertTrue("Ancestors of 5 must contain 2", 
				ancestors5.contains(this.nodesUnderTest.get(2)));
		assertTrue("Ancestors of 5 must contain 3", 
				ancestors5.contains(this.nodesUnderTest.get(3)));
		assertTrue("Ancestors of 5 must contain 4", 
				ancestors5.contains(this.nodesUnderTest.get(4)));
		
		
		// ancestors of {0,2,3,5}
		param.clear();
		param.add(this.nodesUnderTest.get(0));
		param.add(this.nodesUnderTest.get(2));
		param.add(this.nodesUnderTest.get(3));
		param.add(this.nodesUnderTest.get(5));
		Set<INode> ancestors0235 = this.classUnderTest.getAllAncestors(param);		
		assertTrue("Ancestors of {0,2,3,5} should be equal to ancestors of 5", 
				ancestors0235.equals(ancestors5));
	
	}

	/**
	 * Test method for {@link unbbayes.util.dseparation.impl.MSeparationUtility#isDSeparated(unbbayes.prs.Graph, java.util.Set, java.util.Set, java.util.Set)}.
	 */
	public   void testIsDSeparated() {
		
		// set up sets
		
		Set<INode> set2 = new HashSet<INode>();
		set2.add(this.nodesUnderTest.get(2));
		
		Set<INode> set3 = new HashSet<INode>();
		set3.add(this.nodesUnderTest.get(3));
		
		Set<INode> set0 = new HashSet<INode>();
		set0.add(this.nodesUnderTest.get(0));
		
		Set<INode> set5 = new HashSet<INode>();
		set5.add(this.nodesUnderTest.get(5));
		
		Set<INode> set23 = new HashSet<INode>();
		set23.addAll(set2);
		set23.addAll(set3);
		
		Set<INode> set05 = new HashSet<INode>();
		set05 .addAll(set0);
		set05 .addAll(set5);
		
		Set<INode> set01 = new HashSet<INode>();
		set01.add(this.nodesUnderTest.get(0));
		set01.add(this.nodesUnderTest.get(1));
		
		Set<INode> set45 = new HashSet<INode>();
		set45.add(this.nodesUnderTest.get(4));
		set45.add(this.nodesUnderTest.get(5));
		
		
		// if "from" or "to" is empty, they are not m-separated...
		assertFalse(this.classUnderTest.isDSeparated(null, new HashSet<INode>(), new HashSet<INode>(), new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, set23, new HashSet<INode>(), new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, new HashSet<INode>(), set23, new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, new HashSet<INode>(), new HashSet<INode>(), set23));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, new HashSet<INode>(), set23));
		assertFalse(this.classUnderTest.isDSeparated(null, new HashSet<INode>(), set5, set23));
		
		// m-separation between 0 and 5
		assertTrue(this.classUnderTest.isDSeparated(null, set0, set5, set23));
		assertTrue(this.classUnderTest.isDSeparated(null, set5, set0, set23));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set5, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set0, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set5, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set0, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set5, new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set0, new HashSet<INode>()));
		
		
		// m-separation between {0,1} and 5
		assertTrue(this.classUnderTest.isDSeparated(null, set01, set5, set23));
		assertTrue(this.classUnderTest.isDSeparated(null, set5, set01, set23));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set5, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set01, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set5, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set01, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set5, new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, set5, set01, new HashSet<INode>()));
		
		// m-separation between 0 and {4,5}
		assertTrue(this.classUnderTest.isDSeparated(null, set0, set45, set23));
		assertTrue(this.classUnderTest.isDSeparated(null, set45, set0, set23));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set45, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set0, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set45, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set0, set3));	
		assertFalse(this.classUnderTest.isDSeparated(null, set0, set45, new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set0, new HashSet<INode>()));		
		
		// m-separation between {0,1} and {4,5}
		assertTrue(this.classUnderTest.isDSeparated(null, set01, set45, set23));
		assertTrue(this.classUnderTest.isDSeparated(null, set45, set01, set23));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set45, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set01, set2));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set45, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set01, set3));
		assertFalse(this.classUnderTest.isDSeparated(null, set01, set45, new HashSet<INode>()));
		assertFalse(this.classUnderTest.isDSeparated(null, set45, set01, new HashSet<INode>()));
		
		// m-separation between 2 and 3
		assertTrue(this.classUnderTest.isDSeparated(null, set2, set3, set01));
		assertTrue(this.classUnderTest.isDSeparated(null, set3, set2, set01));
		assertFalse(this.classUnderTest.isDSeparated(null, set2, set3, set45));
		assertFalse(this.classUnderTest.isDSeparated(null, set3, set2, set45));
		
	}

	
	/**
	 * Test method for {@link MSeparationUtility#getAllDSeparatedNodes(Set, Set, Set)}.
	 */
	public void testGetAllDSeparatedNodes() {
		
		// set up sets
		
		Set<INode> set2 = new HashSet<INode>();
		set2.add(this.nodesUnderTest.get(2));
		
		Set<INode> set3 = new HashSet<INode>();
		set3.add(this.nodesUnderTest.get(3));
		
		Set<INode> set0 = new HashSet<INode>();
		set0.add(this.nodesUnderTest.get(0));
		
		Set<INode> set5 = new HashSet<INode>();
		set5.add(this.nodesUnderTest.get(5));
		
		Set<INode> set23 = new HashSet<INode>();
		set23.addAll(set2);
		set23.addAll(set3);
		
		Set<INode> set05 = new HashSet<INode>();
		set05 .addAll(set0);
		set05 .addAll(set5);
		
		Set<INode> set01 = new HashSet<INode>();
		set01.add(this.nodesUnderTest.get(0));
		set01.add(this.nodesUnderTest.get(1));
		
		Set<INode> set45 = new HashSet<INode>();
		set45.add(this.nodesUnderTest.get(4));
		set45.add(this.nodesUnderTest.get(5));
		
		
		/*
		 * Set up relationship as shown below:
		 * 
		 * (Node0)
		 *   |
		 *   V
		 * (Node1)------>(Node2)--------
		 *   \                          \
		 *    \                          V
		 *     ------->(Node3)-------->(Node4)----->(Node5)
		 * 
		 */	
		
		// testing for empty sets - if consideredNodes or from is empty, no m-separated nodes should be detected
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( new HashSet<INode>(), new HashSet<INode>(), new HashSet<INode>()).size());
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( set23, new HashSet<INode>(), new HashSet<INode>()).size());
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( new HashSet<INode>(), set23, new HashSet<INode>()).size());
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( new HashSet<INode>(), new HashSet<INode>(), set23).size());
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( set0, new HashSet<INode>(), set23).size());
		assertEquals("No m-separated nodes should exist for empty set", 0, this.classUnderTest.getAllDSeparatedNodes( new HashSet<INode>(), set5, set23).size());
		
		// general use (consideredNodes are all nodes available)
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(new HashSet<INode>(this.nodesUnderTest),set5,set23).containsAll(set01));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(new HashSet<INode>(this.nodesUnderTest),set0,set23).containsAll(set45));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(new HashSet<INode>(this.nodesUnderTest),set05,set23).isEmpty());
		
		// m-separation between 0 and 5
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set0, set5, set23).containsAll(set0));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes( set5, set0, set23).containsAll(set5));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set0, set5, set2).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set5, set0, set2).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set0, set5, set3).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes( set5, set0, set3).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set0, set5, new HashSet<INode>()).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes( set5, set0, new HashSet<INode>()).isEmpty());
		
		
		// m-separation between {0,1} and 5
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set01, set5, set23).containsAll(set01));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set5, set01, set23).containsAll(set5));
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set01, set5, set2).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set5, set01, set2).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set01, set5, set3).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set5, set01, set3).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set01, set5, new HashSet<INode>()).isEmpty());
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set5, set01, new HashSet<INode>()).isEmpty());
		
		
		
		
		
		
	}
	
	// inner classes
	
	/**
	 * A sample implementation of INode,
	 * created just to test the MSeparationUtility.
	 * We are not doing the same to Graph, because
	 * MSeparationUtility does not use it 
	 * @author Shou Matsumoto
	 */
	private class MSeparationTestNode implements INode {
		
		private List<INode> children = null;
		private List<INode> parents = null;

		private List<String> states = null;
		
		private String name = "test";
		
		public MSeparationTestNode (String name) {
			super();
			this.setChildNodes(new ArrayList<INode>());
			this.setParentNodes(new ArrayList<INode>());
			this.setStates(new ArrayList<String>());
			this.name = name;
		}
		
		public void addChildNode(INode child) throws InvalidParentException {
			if (!this.getChildNodes().contains(child)) {
				this.getChildNodes().add(child);
			}
			if (!child.getParentNodes().contains(this)) {
				child.addParentNode(this);
			}
		}

		public void addParentNode(INode parent) throws InvalidParentException {
			if (!this.getParentNodes().contains(parent)) {
				this.getParentNodes().add(parent);
			}
			if (!parent.getChildNodes().contains(this)) {
				parent.addChildNode(this);
			}
		}

		public void appendState(String state) {
			this.states.add(state);
		}

		public List<INode> getAdjacentNodes() {
			List<INode> adjacents = new ArrayList<INode>();
			adjacents.addAll(this.getParentNodes());
			adjacents.addAll(this.getChildNodes());
			return adjacents;
		}

		public List<INode> getChildNodes() {
			return this.children;
		}

		public String getDescription() {
			return "Stub Node. Do not use it at production level";
		}

		public String getName() {
			return this.name;
		}

		public List<INode> getParentNodes() {
			return this.parents;
		}

		public String getStateAt(int index) {
			return this.states.get(index);
		}

		public int getStatesSize() {
			return this.states.size();
		}

		public int getType() {
			return 0;
		}

		public void removeChildNode(INode child) {
			this.getChildNodes().remove(child);
		}

		public void removeLastState() {
			if (states.size() > 1) {
				states.remove(states.size() - 1);
			}
		}

		public void removeParentNode(INode parent) {
			this.getParentNodes().remove(parent);
		}

		public void removeStateAt(int index) {
			this.states.remove(index);
		}

		public void setChildNodes(List<INode> children) {
			this.children = children;
		}

		public void setDescription(String text) {
			throw new java.lang.UnsupportedOperationException("Description is not supported");
		}

		public void setName(String name) {
			this.setName(name);
		}

		public void setParentNodes(List<INode> parents) {
			this.parents = parents;
		}

		public void setStateAt(String state, int index) {
			this.states.set(index , state);
		}

		public void setStates(List<String> states) {
			this.states = states;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (super.equals(obj)) {
				return true;
			}
			if (obj instanceof MSeparationTestNode) {
				MSeparationTestNode node = (MSeparationTestNode) obj;
				return this.getName().equals(node.getName());
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.getName();
		}		
		
		
	}
}
