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

    /**
     * testing strategy
     * 任意加入边，顶点，最后产看tostring是否返回的string和我们
     * 输入的边，顶点构成的图相等
     */
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

    /**
     * Testing strategy
     * 任意传入一个顶点，查看返回的顶点是否和输入的顶点相同
     */
    @Test
    public void testgetname()
    {
        Vertex<String> vertex = new Vertex<>("a");
        assertEquals("a",vertex.getName());
    }

    /**
     * Testing strategy
     * 先构建一个源顶点也即是name节点，然后任意增加
     * 该节点的source节点，最后查看tosource返回的节点
     * 是否和我们输如的节点一样
     */
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
    /**
     * Testing strategy
     * 先构建一个源顶点也即是name节点，然后任意增加
     * 该节点的target节点，最后查看totarget返回的节点
     * 是否和我们输入的节点一样
     */
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

    /**
     * Testing strategy
     * 先定义一个name节点为a ，然后随意输入几个
     * 以该点为起始点的target顶点，之后使用removetarget函数
     * 查看该两点之间的距离是否变成0
     */
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

    /**
     * Testing strategy
     * 先定义一个name节点为a ，然后随意输入几个
     * 以该点为终结点的source顶点，之后使用removesource函数
     * 查看该两点之间的距离是否变成0
     */
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
