package P3;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendshipGraphTest {

    FriendshipGraph graph =new FriendshipGraph();

    @Test
    public void addVertextest() {
        Person wu = new Person("wu");
        Person jia = new Person( "jia");
        Person hao = new Person("hao");
        Person zui = new Person("zui");
        Person shuai = new Person( "shuai");
        graph.addVertex(wu);
        graph.addVertex(jia);
        graph.addVertex(hao);
        graph.addVertex(zui);
        graph.addVertex(shuai);
        // assertTrue(graph.contain(wu));
        assertEquals(5,graph.num);

    }
    @Test
    public void addEdgeTest()
    {
        Person wu = new Person("wu");
        Person jia = new Person( "jia");
        Person hao = new Person("hao");
        Person zui = new Person("zui");
        Person shuai = new Person( "shuai");
        graph.addVertex(wu);
        graph.addVertex(jia);
        graph.addVertex(hao);
        graph.addVertex(zui);
        graph.addVertex(shuai);

        graph.addEdge(wu,jia);
        graph.addEdge(hao,jia);
        graph.addEdge(wu,zui);
        graph.addEdge(shuai,jia);
        graph.addEdge(hao,zui);
        assertTrue(graph.containEdge(wu,jia));
        assertTrue(graph.containEdge(hao,jia));
        assertTrue(graph.containEdge(wu,zui));
        assertTrue(graph.containEdge(shuai,jia));
        assertTrue(graph.containEdge(hao,zui));
    }



    @Test
    public void getDistancetest()
    {
        Person wu = new Person("wu");
        Person jia = new Person( "jia");
        Person hao = new Person("hao");
        Person zui = new Person("zui");
        Person shuai = new Person( "shuai");
        graph.addVertex(wu);
        graph.addVertex(jia);
        graph.addVertex(hao);
        graph.addVertex(zui);
        graph.addVertex(shuai);

        graph.addEdge(wu,jia);
        graph.addEdge(hao,jia);
        graph.addEdge(zui,wu);
        graph.addEdge(shuai,jia);
        graph.addEdge(hao,zui);
        graph.addEdge(jia,zui);

        assertEquals(2,graph.getDistance(hao,wu));
        assertEquals(3,graph.getDistance(shuai,wu));
        assertEquals(1,graph.getDistance(shuai,jia));
        assertEquals(-1,graph.getDistance(zui,hao));

    }

}
