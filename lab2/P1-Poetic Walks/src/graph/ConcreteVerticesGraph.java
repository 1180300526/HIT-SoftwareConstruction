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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // vertices 是到有向图顶点的映射
    // Representation invariant:
    //   TODO
    // 不存在相同的顶点，顶点都存在name
    // Safety from rep exposure:
    //   TODO
    // 使用private final 以及 防御拷贝
    // TODO constructor
    public  void ConcreteEdgesGraph(){}
    // TODO checkRep

    /**
     * 1.不存在相同的顶点，顶点都存在name
     *
     */
    public  void checkRep()
    {
        Set<Vertex<L>> mp = new HashSet<>();
        for(Vertex<L> v : vertices)
        {
            assert v.getName() !=null;  // 每一个顶点name都存在
            mp.add(v);
        }
        assert mp.size()==vertices.size();  // 判断没有重复顶点
    }
    /**
     * Add a vertex to this graph.
     *
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {  // 加顶点
       // throw new RuntimeException("not implemented");
        for(Vertex<L> v : vertices)  // 遍历 list ，如果已经存在顶点，返回false
        {
            if(v.getName().equals(vertex))
                return false;
        }
        vertices.add(new Vertex<L>(vertex));  // 新建顶点返回true
        checkRep();
        return  true;
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
        int oldweight=0;
        this.add(source);
        this.add(target);
        for(Vertex<L> v : vertices)
        {
            if(v.getName().equals(source))  // 如果v == source ，就更新他的target
            {
                if(oldweight==0)   //仅更新一次
                 oldweight = v.totarget(target);
                v.addtarget(target,weight);  // 更新weight，即使weight==0
            }
            if(v.getName().equals(target))  //如果v == target ，就更新他的source
            {
                if(oldweight==0)  //仅更新一次
                    oldweight = v.tosource(source);
                v.addsource(source,weight);
            }
        }
        return oldweight;
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
        for(Vertex<L> v : vertices)
        {
            if(v.tosource(vertex)!=0) // 把每一个以vertex为source的顶点都与其断联
            {
                v.removeSource(vertex);
            }
            if(v.totarget(vertex)!=0)  // 把每一个以vertex为target的顶点都与其断联
            {
                v.removeTarget(vertex);
            }
        }
        for(Vertex<L> v : vertices) //遍历，如果存在name 为 vertex 顶点 就返回true
        {
            if(v.getName().equals(vertex))
            {
                vertices.remove(v);
                return true;
            }
        }
        return  false;

    }
    /**
     * Get all the vertices in this graph.
     *
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
        Set<L> vertexall = new HashSet<>();// 新建一个set，防止表达泄露
        for(Vertex<L> v : vertices)
        {
            vertexall.add(v.getName());
        }
        return vertexall;
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
       // throw new RuntimeException("not implemented");
        Map<L,Integer> mp = new HashMap<>();
        for(Vertex<L> v: vertices)
        {
            if(v.totarget(target)!=0)
                mp.put(v.getName(),v.totarget(target));
        }
        return  mp;
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
        Map<L,Integer> mp = new HashMap<>();
        for(Vertex<L> v: vertices)
        {
            if(v.tosource(source)!=0)
                mp.put(v.getName(),v.tosource(source));
        }
        return  mp;
    }
    
    // TODO toString()
    @Override public String toString() {
        String mystring = "";
        for (Vertex<L> ver : vertices) {
            mystring = mystring + ver.toString();
        }
        return mystring;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {            //Vertex 是 mutable 类型
    // TODO fields
    private  final L name;  // 自己，无所谓不使用final
    private  final Map<L , Integer > sourcemap =new HashMap<>();  //所有的以name为 target的顶点，以及边长
    private final Map<L , Integer > targetmap  =new HashMap<>();  // 以name为source 的顶点，以及边长
    // Abstraction function:
    //   TODO
    // name 是到有向图顶点的映射，sourcemap 和 targetmap是到有向图边的映射
    // Representation invariant:
    //   TODO
    // name 不能为 null，source 和target 不能存在 Sting=null 或者Integer =0 的情况
    // Safety from rep exposure:
    //   TODO
    // 使用防御拷贝保护,以及使用private final 保护数据
    // TODO constructor
    public Vertex(L vertex) {
        this.name = vertex;
    }

    public L getName() {
        return name;
    }

    // TODO checkRep

    /**
     * 每一条边的权重都大于零，并且每一个顶点都存在另外一个顶点与之对应
     */
    public  void checkRep()  //检查rep
    {
        assert name !=null;
        Set<L> sourceweight = sourcemap.keySet();
        Set<L> targetweight = targetmap.keySet();
        for(L sk : sourceweight )
        {
            int sw = sourcemap.get(sk);
            assert  sw>0;
        }
        for (L tk : targetweight)
        {
            int tw = targetmap.get(tk);
            assert tw>0;
        }
    }

    // 判断节点中是否存在source 以及 target

    /**
     *
     * @param source 一个出发顶点顶点
     * @return 返回所有的由该出发顶点到达的终结顶点，不存在返回0
     */
    public int tosource(L source)
    {
         if(sourcemap.containsKey(source))
        {
            return sourcemap.get(source);
        }
         else
             return 0;
    }

    /**
     *
     * @param target 一个终结顶点
     * @return 返回所有的到该节点的出发顶点，不存在返回0
     */
    public int totarget(L target)
    {
        if(targetmap.containsKey(target))
        {
            return targetmap.get(target);
        }
        else
            return 0;
    }
    // TODO methods

    /**
     * 用于添加一条出发顶点到该name的边
     * @param source 一个出发顶点
     * @param weight 边权重
     */
    public void addsource(L source , int weight) //加源节点
    {
        if(weight > 0) {
            sourcemap.put(source, weight);
            checkRep();
        }
        else if(weight ==0)
        {
            sourcemap.remove(source);
        }

    }

    /**
     * 用于添加一条该name顶点到target的边
     * @param target 终结顶点
     * @param weight 边权重
     */
    public  void addtarget(L target , int weight)  // 加目标节点
    {
        if(weight > 0) {
            targetmap.put(target, weight);
            checkRep();
        }
        else if(weight ==0)
        {
            targetmap.remove(target);
        }

    }

    /**
     * 删除某个以ziji为target的顶点和边权重
     * @param source 一个出发顶点
     */
    public void removeSource(L source )
    {
                sourcemap.remove(source);
    }

    /**
     * 删除某个以ziji为source的顶点和边权重
     * @param target 终结顶点
     */
    public void removeTarget(L target )
    {
                targetmap.remove(target);
    }
    // TODO toString()
    @Override
   public String toString()
    {
        return "以"+ name.toString()+"为终点的点以及边权重为"+sourcemap.toString() +"\t"+"以"+ name.toString()+"为起始点到达的点以及" +
                "边的权重为："+ targetmap.toString() + "\n";
    }
}
