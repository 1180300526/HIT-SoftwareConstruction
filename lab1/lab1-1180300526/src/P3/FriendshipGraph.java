package P3;

import java.util.*;
public class FriendshipGraph {

    int num=0;
    static  int[][] distance = new int[5000][5000];
     Hashtable<String ,Integer> hash =new Hashtable<>();
    public void addVertex(Person name) {

        if(hash.containsKey(name.getname()))
        {
            System.out.println("社交网已经包含了这个人,程序错误即将退出程序");
            System.exit(0);
        }
        else
        {
            hash.put(name.getname(),num++);
            for(int i=0;i<num ;i++ ) {
                if (num - 1 == i)
                    distance[i][i] = 0;
                else {
                    distance[num - 1][i] = -1;
                    distance[i][num - 1] = -1;
                }
            }
        }
    }

    public void addEdge(Person name1, Person name2) {

        search(name1,name2);
        int i = hash.get(name1.getname());
        int j = hash.get(name2.getname());
        distance[i][j]=1;
    }

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
    public int getDistance(Person name1,Person name2)
    {
        search(name1,name2);
        int i = hash.get(name1.getname());
        int j = hash.get(name2.getname());
        int dis = bfs(i,j);
        return  dis;
    }
    public  boolean containEdge(Person name1,Person name2)
    {
        if(distance[hash.get(name1.getname())][hash.get(name2.getname())]==1)
            return true;
        return false;
    }
    public void search(Person name1,Person name2)
    {
        if(hash.containsKey(name1.getname()) == false)
        {

            System.out.println("社交网中没有"+ name1.getname()+"即将退出程序");
            System.exit(0);
        }
        if(hash.containsKey(name2.getname()) == false)
        {
            System.out.println("社交网中没有"+ name2.getname()+"即将退出程序");
            System.exit(0);
        }

    }



}
