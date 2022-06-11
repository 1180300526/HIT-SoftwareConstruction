package FriendshipGraph;

import graph.FriendshipGraph;
import graph.Person;
import org.junit.Test;
import static org.junit.Assert.*;

public class FriendshipGraphtest {

    /**
     * testing strategy
     * 将person a ，b加入图，然后调用图自带的函数
     * getShipgraph().vertices().contains(A)判断
     * 图里面是否存在A，和 B
     */
    @Test
    public void addVertex_tast()
    {
        FriendshipGraph graph = new FriendshipGraph();
        Person A= new Person("a");
        Person B= new Person("b");
        graph.addVertex(A);
        assertTrue(graph.getShipgraph().vertices().contains(A));
        assertFalse(graph.getShipgraph().vertices().contains(B));
    }

    /**
     * testing strategy
     * 在图里面添加几个边，记录起始点和初始点，然后
     * 调用函数getShipgraph().sources(p1).containsKey(p2)判断
     * 以p1为终结点的起始点是否存在p2，这样就可以判断是否加入 边 p1p2
     */
    @Test
    public void addEdge_test()
    {
        FriendshipGraph graph = new FriendshipGraph();
        Person A= new Person("a");
        Person B= new Person("b");
        Person C= new Person("c");
        Person D= new Person("d");
        graph.addEdge(A,B);
        graph.addEdge(B,C);
        graph.addEdge(C,D);
        assertTrue(graph.getShipgraph().sources(B).containsKey(A));
        assertTrue(graph.getShipgraph().sources(C).containsKey(B));
        assertTrue(graph.getShipgraph().sources(D).containsKey(C));
        assertFalse(graph.getShipgraph().sources(D).containsKey(A));

    }

    /**
     * testing strategy
     * 同样是加入边，构成图，然后任取两点顶点调用getdistance函数
     * 查看输出结果是否和预期一致
     */
    @Test
    public void getdistance_test()
    {
        FriendshipGraph graph = new FriendshipGraph();
        Person A= new Person("a");
        Person B= new Person("b");
        Person C= new Person("c");
        Person D= new Person("d");
        graph.addEdge(A,B);
        graph.addEdge(B,C);
        graph.addEdge(C,D);
        assertEquals(3,graph.getDistance(A,D));
        assertEquals(0,graph.getDistance(A,A));
        assertEquals(-1,graph.getDistance(D,A));

    }
}
