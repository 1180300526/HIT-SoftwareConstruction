package graph;

    public class main {
        public static void main(String[] args) {
            FriendshipGraph graph = new FriendshipGraph();
            Person rachel = new Person("Rachel");
            Person ross = new Person("Ross");
            Person ben = new Person("Ben");
            Person kramer = new Person("Kramer");
            Person a = new Person("a");
            Person b = new Person("b");
            Person c = new Person("c");
            Person d = new Person("d");

            graph.addVertex(a);
            graph.addVertex(b);
            graph.addVertex(c);
            graph.addVertex(d);
            graph.addVertex(rachel);
            graph.addVertex(ross);
            graph.addVertex(ben);
            graph.addVertex(kramer);

            graph.addEdge(rachel, ross);
            graph.addEdge(rachel, ben);
            graph.addEdge(ross, rachel);
            graph.addEdge(ross, ben);
            graph.addEdge(ben, ross);
            graph.addEdge(ross, c);
            graph.addEdge(ben, a);
            graph.addEdge(a, b);
            graph.addEdge(a, kramer);
            graph.addEdge(c, b);
            graph.addEdge(kramer, d);
            graph.addEdge(d, ben);

            int cb = graph.getDistance(c, b);
            System.out.println("原版例子(但是增加了很多节点)：");
            System.out.println("(rachel , ross)之间的距离为" +graph.getDistance(rachel, ross));
            //should print 1
            System.out.println("(rachel , ben)之间的距离为" +graph.getDistance(rachel, ben));
            //should print 1
            System.out.println("(rachel , rachel)之间的距离为" +graph.getDistance(rachel, rachel));
            //should print 0
            System.out.println("(rachel , kram)之间的距离为" +graph.getDistance(rachel, kramer));
            //shoule print 3

            System.out.println("下面是自己写的测试例子：");
            System.out.println("c      和 b之间的距离为："+ graph.getDistance(c, b));
            System.out.println("rachel 和 c之间的距离为 ："+graph.getDistance(rachel, c));
            System.out.println("a 和 rachel之间的距离为 ："+graph.getDistance(a, rachel));
        }
    }


