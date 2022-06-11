/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import javax.swing.*;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    //   TODO

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());

    }

    // TODO other tests for instance methods of Graph
    @Test
    public void testadd() {
        Graph<String> graph = emptyInstance();
        Graph<String> graph1;
        String A = "a";
        String B = "b";
        String C = "c";
        graph.add(B);
        graph.add(A);
        assertEquals(true, graph.vertices().contains(A));
        assertEquals(true, graph.vertices().contains(B));
        assertEquals(false, graph.vertices().contains(C));
    }

    @Test
    public void testset() {
        Graph<String> graph = emptyInstance();
        String A = "a";
        String B = "b";
        String C = "c";
        graph.set(A, B, 2);
        graph.set(B, C, 3);
        assertEquals(2, graph.set(A, B, 3));
        assertEquals(3, graph.set(B, C, 4));
    }
    @Test
    public void testremove()
    {
        Graph<String> graph = emptyInstance();
        String A = "a";
        String B = "b";
        graph.add(A);
        graph.add(B);
        graph.remove(A);
        assertEquals(false,graph.vertices().contains(A));
        assertEquals(true,graph.vertices().contains(B));
    }


    @Test
    public void testVertics()
    {
        Graph<String> graph = emptyInstance();
        String A = "a";
        String B = "b";
        String C ="c";
        graph.add(A);
        graph.add(B);
        assertEquals(true,graph.vertices().contains(A));
        assertEquals(true,graph.vertices().contains(B));
        assertEquals(false,graph.vertices().contains(C));
    }

    @Test
    public void testsource()
    {
        Graph<String> graph = emptyInstance();
        String A = "a";
        String B = "b";
        String C ="c";
        graph.set(A,B,5);
        graph.set(B,C,2);
        graph.set(C,A,5);
        int AB =graph.sources(B).get(A);
        int BC =graph.sources(C).get(B);
        assertEquals(5,AB);
        assertEquals(2,BC);

    }

    @Test
    public void testtarget()
    {
        Graph<String> graph = emptyInstance();
        String A = "a";
        String B = "b";
        String C ="c";
        graph.set(A,B,5);
        graph.set(B,C,2);
        graph.set(C,A,5);
        int AB = graph.targets(A).get(B);
        int BC = graph.targets(B).get(C);
        assertEquals(5,AB);
        assertEquals(2,BC);
    }
}
