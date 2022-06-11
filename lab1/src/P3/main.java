package P3;

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
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);

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

        System.out.println("c 和 b之间的距离为："+ graph.getDistance(c, b));
        System.out.println("rachel 和 c之间的距离为 ："+graph.getDistance(rachel, c));
    }
}
