package turtle;



public class souptest {
    public static void main(String[] args) {
        Point one = new Point(1,1);
        Point two =one;
        Point three =new Point(2,2) ;
        two.setX(5);
        System.out.println(three.x());
        System.out.println(one.x());
    }

}
