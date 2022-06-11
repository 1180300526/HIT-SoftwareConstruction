/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy
    //   TODO


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    /**
     * testing strategy
     *以下构造了两个文件，一个空文件，一个多行文件，分别检验
     * 该函数形成的poem是否正确
     */
    @Test
    public  void testpoet() throws IOException
    {
        File file1 = new File("src/poet/passage.txt");
        String input1 = "how this I swallow will my";
        GraphPoet poet1 = new GraphPoet(file1);
        String output1 = poet1.poem(input1);
        assertEquals("how will this i will swallow will commence my",output1);

        File file2 = new File("src/poet/empty.txt");
        String input2 = "how this I swallow will my";
        GraphPoet poet2 = new GraphPoet(file2);
        String output2 = poet2.poem(input2);
        assertEquals("how this i swallow will my",output2);
    }
    /**
     * testing strategy
     *构造一个新的less文档，里面存两行字符串，用这文件
     * 调用tostring函数，检验输出是否与预期相同
     */
    @Test
    public void test_tostring() throws IOException
    {
        File file1 = new File("src/poet/less.txt");
        GraphPoet poet1 = new GraphPoet(file1);
        assertEquals("nullare->a\tam->a\ta->good\t\n" +
                "you->are\tare->a\t\n" +
                "i->am\t\n" +
                "i->am\tam->a\t\n" +
                "good->girl\t\n" +
                "a->good\tgood->girl\tgood->boy\t\n" +
                "good->boy\tboy->you\t\n" +
                "boy->you\tyou->are\t\n" , poet1.tostring());
    }
}
