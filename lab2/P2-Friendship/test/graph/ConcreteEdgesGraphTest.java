/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testConcreteEdgesGraphtoString() {
        Graph<String> graph =emptyInstance();
        graph.set("a", "b", 5);
        graph.set("a", "d", 4);
        graph.set("e", "f", 3);
        assertEquals("a到b的距离为5\n"+"a到d的距离为4\n"+"e到f的距离为3\n",graph.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
   @Test
    public void testgettarget()
   {
       Edge<String> edge1 = new Edge<String >("A","B",5);
       Edge<String> edge2 = new Edge<String >("A","C",5);
       String target1= edge1.getTarget();
       String target2= edge2.getTarget();
       assertEquals("B",target1);
       assertEquals("C",target2);
   }

   @Test
    public void testgetsource()
   {
       Edge<String> edge1 = new Edge<String >("A","B",5);
       Edge<String> edge2 = new Edge<String >("A","C",5);
       String source1= edge1.getSource();
       String source2= edge2.getSource();
       assertEquals("A",source1);
       assertEquals("A",source2);

   }

   @Test
    public void testgetweight()
   {
       Edge<String> edge1 = new Edge<String >("A","B",5);
       Edge<String> edge2 = new Edge<String >("A","C",3);
       int weight1 = edge1.getWeight();
       int weight2 = edge2.getWeight();
       assertEquals(5,weight1);
       assertEquals(3 ,weight2);
   }

}
