/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    //  vertices,edges 都将映射到有向图
    // Representation invariant:
    //   TODO
    //  每条边的权重大于 0
    //   不存在两条边同一个source 和 target
    // Safety from rep exposure:
    //   TODO
    // 使用 private final 修饰， 并且clone copies ，防止表示泄露
    /**
     *
     */

    // TODO constructor
    public ConcreteEdgesGraph() {
    }
    // TODO checkRep

    /**
     * 检查RI
     * 1.判断边的顶点都在集合vertices 内，边权重大于0
     * 2.不存在两边 同时指向同一个source 和 target
     */
    private void checkRep() {
        for (Edge<L> bian : edges) {
            assert vertices.contains(bian.getSource());
            assert vertices.contains(bian.getTarget());
            assert bian.getWeight()>0;
        }

        for(int i =0 ; i<edges.size() ;i++)
        {
            for(int j =i+1;  j<edges.size() ;j++)
            {
                assert  !(edges.get(i).getSource().equals(edges.get(j).getSource())
                        && edges.get(i).getTarget().equals(edges.get(j).getTarget()));

            }
        }

    }
    /**
     * Add a vertex to this graph.
     *
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertices.contains(vertex)) {
            //System.out.println( vertex + "点已经存在");
           return false;
        }
        checkRep();
        return vertices.add(vertex);
    }
    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     *
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
        if(weight > 0 )
        {
             for(Edge<L> bian : edges)
             {
                 if(bian.getSource().equals(source) && bian.getTarget().equals(target))
                 {
                     int oldweight = bian.getWeight();
                     edges.remove(bian);
                     Edge<L> newedge = new Edge<>(source , target ,weight);
                     edges.add(newedge);
                     checkRep();
                     return oldweight;               // 存在则返回旧值
                 }
             }
             add(source);
             add(target);
            Edge<L> newedge = new Edge<>(source , target ,weight);
            edges.add(newedge);
            checkRep();
        }
        else if(weight ==0 )
        {

            for(Edge<L> bian : edges)
            {
                if(bian.getSource().equals(source) && bian.getTarget().equals(target))
                {
                    int oldweight =bian.getWeight();
                    edges.remove(bian);
                    checkRep();
                    return  oldweight;  //存在则返回旧数据
                }
            }
        }
        else return -1;      //   权重为负数则返回 -1
        return  0;   // 不存在都返回 0
    }
    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     *
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertices.contains(vertex)) {
            vertices.remove(vertex);              // 移除以该顶点为顶点的边
            edges.removeIf(bian -> Objects.equals(bian.getSource(), vertex)
                    || Objects.equals(bian.getTarget(), vertex));
        }
        else
            return false;
        checkRep();
        return true;
    }
    /**
     * Get all the vertices in this graph.
     *
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {  //clone 保持类的不可变性
        //throw new RuntimeException("not implemented");
        Set<L> ver = new HashSet<>(vertices);
        checkRep();
        return  ver;
    }
    /**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 获取指向target的边权重以及顶点
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        Map<L , Integer> map = new HashMap<>();
        for(Edge<L> bian : edges)
        {
            if(bian.getTarget().equals(target) )
            {
                 map.put(bian.getSource(),bian.getWeight());
                 checkRep();
            }
        }
        return  map;
    }
    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 获取从source出发的边权重以及顶点
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
        Map<L , Integer> map = new HashMap<>();
        for(Edge<L> bian : edges)
        {
            if(bian.getSource().equals(source) )
            {
                map.put(bian.getTarget(),bian.getWeight());
                checkRep();
            }
        }
        return  map;
    }

    // TODO toString()

    /**
     *
     * @return 一幅图的信息字符串
     */
    @Override public String toString() {
        String mystring = "";
        for (Edge<L> edge : edges) {
            mystring = mystring + edge.toString();
        }
        return mystring;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {  //Edge 是immutable类型

    // TODO fields
    private  final L source,target;
    private  final int weight;

    // Abstraction function:
    //   TODO
    //  source ，target 可以映射到任意L，并无要求，但不能为空
    //  weight 映射到int 范围内任意数字
    // Representation invariant:
    //   TODO
    // 每条边都有且只有一个 source 和 target
    // 权重大于 0，为0 则不存在该边
    // Safety from rep exposure:
    //   TODO
    //使用private final 修饰，
    // TODO constructor
    public Edge(L source,L target , int weight ) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    // TODO checkRep
    public  void checkRep()
    {
        assert  source!=null;
        assert  target !=null;
        assert  weight >0;
    }
    // TODO methods
    //private
    public  L getTarget()
    {
        return target;
    }
    public L getSource()
    {
        return  source;
    }
    public int getWeight() {
        return weight;
    }
    // TODO toString()


    @Override
    public String toString()
    {
        return source.toString() +"到" +target.toString() +"的距离为"+weight +"\n";
    }
}
