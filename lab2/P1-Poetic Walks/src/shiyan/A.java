package shiyan;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

public abstract class A {
    public A(int k)
    {
        System.out.println("执行父类" + k);
    }
    public void fun()
    {
        System.out.println("执行A");
    }
    public abstract List<Integer> printt();
    List<Integer> kk = printt();
}

class B extends A
{
    @Override
    public List<Integer> printt() {
        List<Integer> m =new ArrayList<>();
        m.add(20);
        return m;
    }
    public B()
    {
        super(20);
        System.out.println("执行子类");
    }

}
 class shiyan {
     public static void main(String[] args) {
         B a =new B();
         System.out.println(a.kk);
         a.printt();
     }
}
