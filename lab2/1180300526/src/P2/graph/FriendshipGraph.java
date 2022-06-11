package graph;

import java.util.*;

public class FriendshipGraph {

    Graph<Person> shipgraph = Graph.empty();
    // 把每个person转化为一个数字
    static  int[][] distance = new int[5000][5000];
    Hashtable<Person ,Integer> hash =new Hashtable<>();
    int num;   // 人数

    /**
     * 用于访问该类里面的shipgraph函数
     * @return 返回shipgraph
     */
    public Graph<Person> getShipgraph()
    {
        return shipgraph;
    }

    /**
     * 给任务关系增加节点
     * @param p1 人物
     */
    public void addVertex(Person p1)
    {
        shipgraph.add(p1);
    }

    /**
     * 给人物增加关系
     * @param p1 人物1
     * @param p2 人物2
     */
    public  void addEdge(Person p1,Person p2)
    {
        shipgraph.set(p1,p2,1);
    }

    /**
     * 求 p1 和 p2 之间的距离
     * @param p1 人物 1
     * @param p2 人物 2
     * @return  返回一个整数
     */
    public  int getDistance(Person p1 , Person p2 )
    {
        if(shipgraph.vertices().contains(p1) && shipgraph.vertices().contains(p2))
        {
            if(p1.getName().equals(p2.getName()))
                return 0;
            // 每一个人对有一个编号
            for(Person p : shipgraph.vertices())
            {
                hash.put(p,num++);
            }
            // 把每一个souce 到 target 都赋值为1 ，表示有路
            for(Person source : hash.keySet())
            {
                for(Person target  : hash.keySet())
                {
                    if(shipgraph.targets(source).containsKey(target))
                    {
                        int m = hash.get(source);
                        int n = hash.get(target);
                        distance[m][n] =1;
                    }
                }
            }


        }
        int start = hash.get(p1);
        int end = hash.get(p2);
        return  bfs(start ,end);

    }

    /**
     * bfs搜索
     * @param begin 出发点
     * @param end  终结点
     * @return 返回一个整数距离
     */
    public  int bfs(int begin ,int end )
    {
        if(begin == end )
            return 0;
        Set<Integer> set =new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        int sign1=0,sign2=0;
        for(int i = 0;i<num;i++)
        {
            if(distance[begin][i] ==1) {
                que.add(i);
                sign1 = i;
            }
        }
        int dis=1;
        while(!que.isEmpty())
        {
            int start = que.poll();
            if(start == end) {
                return dis;
            }
            for(int i = 0;i<num;i++)
            {

                if(distance[start][i] ==1 && !set.contains(i)) {
                    set.add(i);
                    que.add(i);
                    sign2 = i;
                }
            }
            if(sign1==start)
            {
                sign1 =sign2;
                dis++;
            }
        }

        return -1;
    }
}
