/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void tese_ConcreteVerticesGraphtoString()
    {
        Graph<String> graph =emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.set("a", "b", 5);
        graph.set("b", "c", 4);
        graph.set("c", "a", 3);
        assertEquals("以a为终点的点以及边权重为{c=3}\t以a为起始点到达的点以及边的权重为：{b=5}" +"\n"+
                "以b为终点的点以及边权重为{a=5}\t以b为起始点到达的点以及边的权重为：{c=4}" +"\n"+
                "以c为终点的点以及边权重为{b=4}\t以c为起始点到达的点以及边的权重为：{a=3}"+"\n",graph.toString());
    }

    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void testgetname()
    {
        Vertex<String> vertex = new Vertex<>("a");
        assertEquals("a",vertex.getName());
    }

    @Test
    public void test_tosource_addsource()
    {
        Vertex<String> vertex = new Vertex<>("a");
        vertex.addsource("b",5);
        vertex.addsource("c",6);
        int ba = vertex.tosource("b");
        int ca = vertex.tosource("c");
        assertEquals(5,ba);
        assertEquals(6,ca);

    }
    @Test
    public void test_totarget_addsource()
    {
        Vertex<String> vertex = new Vertex<>("a");
        vertex.addtarget("b",5);
        vertex.addtarget("c",6);
        int ba = vertex.totarget("b");
        int ca = vertex.totarget("c");
        assertEquals(5,ba);
        assertEquals(6,ca);

    }

    @Test
    public void test_removetarget()
    {
        Vertex<String> vertex = new Vertex<>("a");
        vertex.addtarget("b",5);
        vertex.addtarget("c",6);
        vertex.removeTarget("b");
        int ba = vertex.totarget("b");
        int ca = vertex.totarget("c");
        assertEquals(0,ba);
        assertEquals(6,ca);
    }

    @Test
    public void test_removesource()
    {
        Vertex<String> vertex = new Vertex<>("a");
        vertex.addsource("b",5);
        vertex.addsource("c",6);
        vertex.removeSource("b");
        int ba = vertex.tosource("b");
        int ca = vertex.tosource("c");
        assertEquals(0,ba);
        assertEquals(6,ca);
    }
}
