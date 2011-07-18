/**
 * 
 */
package unbbayes.util.dseparation.impl;

import java.util.HashSet;
import java.util.Set;

import unbbayes.prs.INode;

/**
 * @author Shou Matsumoto
 *
 */
public class MSeparationUtilityIncludingSeparatorsTest extends
		MSeparationUtilityTest {

	/**
	 * @param name
	 */
	public MSeparationUtilityIncludingSeparatorsTest(String name) {
		super(name);
	}
	
	
	
	/* (non-Javadoc)
	 * @see unbbayes.util.dseparation.impl.MSeparationUtilityTest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.classUnderTest = MSeparationUtilityIncludingSeparators.newInstance();
	}

	

	/* (non-Javadoc)
	 * @see unbbayes.util.dseparation.impl.MSeparationUtilityTest#testIsDSeparated()
	 */
	@Override
	public void testIsDSeparated() {
		super.testIsDSeparated();
		
		/*
		 * Testing if the separators themselves are d-separated
		 * 
		 * (sep 0)
		 *   |
		 *   V
		 * (sep 1)------>(Sep 2)--------
		 *   \                          \
		 *    \                          V
		 *     ------->(Sep 3)-------->(Node4)----->(Query5)
		 * 
		 */	
		
		// set up sets
		
		Set<INode> set5 = new HashSet<INode>();
		set5.add(this.nodesUnderTest.get(5));
		
		Set<INode> set23 = new HashSet<INode>();
		set23.add(this.nodesUnderTest.get(2));
		set23.add(this.nodesUnderTest.get(3));
		
		Set<INode> set01 = new HashSet<INode>();
		set01.add(this.nodesUnderTest.get(0));
		set01.add(this.nodesUnderTest.get(1));
		
		
		Set<INode> set0123 = new HashSet<INode>(set01);
		set0123.addAll(set23);
		
		assertTrue("The separators 0 and 1 shoud be d-separated from query as well.", this.classUnderTest.isDSeparated(new HashSet<INode>(this.nodesUnderTest), set01, set5, set0123));
	}



	/**
	 * Test method for {@link MSeparationUtility#getAllDSeparatedNodes(Set, Set, Set)}.
	 */
	public void testGetAllDSeparatedNodes() {
		
		super.testGetAllDSeparatedNodes();
		
		/*
		 * Testing if the separators themselves are d-separated
		 * 
		 * (sep 0)
		 *   |
		 *   V
		 * (sep 1)------>(Sep 2)--------
		 *   \                          \
		 *    \                          V
		 *     ------->(Sep 3)-------->(Node4)----->(Query5)
		 * 
		 */	
		
		// set up sets
		
		Set<INode> set5 = new HashSet<INode>();
		set5.add(this.nodesUnderTest.get(5));
		
		Set<INode> set23 = new HashSet<INode>();
		set23.add(this.nodesUnderTest.get(2));
		set23.add(this.nodesUnderTest.get(3));
		
		Set<INode> set01 = new HashSet<INode>();
		set01.add(this.nodesUnderTest.get(0));
		set01.add(this.nodesUnderTest.get(1));
		
		
		Set<INode> set0123 = new HashSet<INode>(set01);
		set0123.addAll(set23);
		
		assertTrue(this.classUnderTest.getAllDSeparatedNodes(set0123, set5, set0123).containsAll(set01));
		assertEquals("The d-separated nodes should be only 2", 2, this.classUnderTest.getAllDSeparatedNodes(set0123, set5, set0123).size());
		
	}

}
