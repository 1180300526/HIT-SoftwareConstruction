/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import graph.ConcreteVerticesGraph;
import graph.Graph;

/**
 * A graph-based poetry generator.
 *
 *
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
       // throw new RuntimeException("not implemented");
        FileReader FR = new FileReader(corpus);
        BufferedReader BR = new BufferedReader(FR);
        List<String> mylist = new ArrayList<>();
        String mystring;
        String[] word;
        Map<String,Integer> mp = new HashMap<>();
        while((mystring = BR.readLine())!=null)
        {
            word = mystring.split(" ");
            mylist.addAll(Arrays.asList(word));
        }
        BR.close();
        // w1 - w2 的次数是 w1w2 的边权重
        for(int i =0 ;i<mylist.size()-1 ;i++)
        {
            int times =0;
            String start = mylist.get(i).toLowerCase();
            String end   = mylist.get(i+1).toLowerCase();
            String all=start+end;
            if(mp.containsKey(all))
            {
                times=mp.get(all);
            }
            mp.put(all,times+1);
            graph.set(start , end ,times+1);
        }

    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
        StringBuilder str=new StringBuilder();
        String mypoem;
        String[] mystring = input.split(" ");
        Map<String, Integer> allsource = new HashMap<>();
        Map<String, Integer> alltarget = new HashMap<>();
        int min = 0x3fff;
        int i;
        for( i=0 ; i<mystring.length-1;i++)
        {
            min = 0x3fff;
            String source = mystring[i].toLowerCase();
            String target = mystring[i+1].toLowerCase();
            alltarget = graph.targets(source);
            allsource = graph.sources(target);
              str.append(source);
              str.append(" ");
              String mid ="";
              for(String key : allsource.keySet())
              {
                  if(alltarget.containsKey(key) && allsource.get(key)+ alltarget.get(key)<=min)
                  {
                      mid = key;
                      min = allsource.get(key)+ alltarget.get(key);
                  }
              }
              if(min!=0x3fff) {
                  str.append(mid);
                  str.append(" ");
              }
        }
        str.append(mystring[i]);
        mypoem=str.toString();
        return mypoem;
    }
    
    // TODO toString()

    /**
     * 将图转化为字符串输出
     * @return 输出图的字符串表示
     */
    public String tostring()
    {
        String  str = null;
        Map<String ,Integer> mp =null;
        for(String v : graph.vertices())
        {
            mp = graph.sources(v);
            for(String sucs : mp.keySet())
            {
                if(!Objects.equals(sucs, ""))
                str = str + sucs + "->" + v+"\t";
            }
            mp = graph.targets(v);
            for(String tar : mp.keySet())
            {
                if(!Objects.equals(tar, ""))
                str = str + v + "->" + tar +"\t";
            }
           str = str +"\n";
        }
        return str;
    }
}
