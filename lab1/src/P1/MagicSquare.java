package P1;
import java.io.*;
import java.util.Scanner;

public class MagicSquare {
    public static void main(String[] args) {
        System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/1.txt"));
        System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/2.txt"));
        System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/3.txt"));
        System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/4.txt"));
        System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/5.txt"));


        Scanner input=new Scanner(System.in);
        int a = input.nextInt();
            if (a % 2 == 0) {
                System.out.println(a + "是偶数不符合输入规则" + MagicSquare.generateMagicSquare(a));
            } else if (a < 0) {
                System.out.println(a + "是负数，不符合输入规则" + MagicSquare.generateMagicSquare(a));
            } else {
                MagicSquare.generateMagicSquare(a);
                System.out.println(isLegalMagicSquare("src/p1/txt/6.txt"));
            }
        }



    public static boolean isLegalMagicSquare(String fileName)
    {
        File myfile = new File(fileName);

        try
        {
            int rol=0;       // 计算文件中的行数
            String f1=null;// f1 用来读取每一行的字符串

           // 先计算出行数
           BufferedReader error1 = new BufferedReader(new FileReader(myfile));
            while((f1=error1.readLine())!=null)
            {
                rol++;
            }
             error1.close();

            f1=null;
            int colnum;
            String[] rolstr;
            //第一种错误：每一行的数字个数不等于列数col，也就是不是正方形
            BufferedReader error2 = new BufferedReader(new FileReader(myfile));
            while((f1=error2.readLine())!=null)
            {
                rolstr=f1.split("\t");
                colnum=rolstr.length;
                if(colnum !=rol) {

                    System.out.println(fileName+"行列数不相等，不能构成矩阵");
                    return false;
                }

            }
            error2.close();


            //第二种错误：存在数字含有小数点或者负数
            f1=null;
            BufferedReader error3 = new BufferedReader(new FileReader(myfile));
            while((f1=error3.readLine())!=null)
            {
                rolstr = f1.split("\t");
                for(String str : rolstr)
                {
                    if(str.contains(".") || str.contains("-")) {
                        System.out.println(fileName+"存在数字含有小数或者负数");
                        return false;
                    }
                }
            }



            //接下来将数据存入一个多维矩阵
            f1=null;
            int[][] squre = new int[rol][rol];
            int i=0,j=0;
            BufferedReader Readnum = new BufferedReader( new FileReader(myfile));
            try { // 防止valueof 方法溢出
                while ((f1 = Readnum.readLine()) != null) {
                    rolstr = f1.split("\t");
                    for (String str : rolstr) {

                        squre[i][j++] = Integer.valueOf(str);
                    }
                    j = 0;
                    i++;

                }
            }
            catch (Exception e)
            {
               return false;
            }


            //计算每一行每一列以及对角线的和
            int[] sum=new int[2*rol +2];//保存和
            for(i=0 ; i<rol ;i++)
            {
                for(j=0 ; j<rol ;j++)
                {
                    sum[i]=sum[i] + squre[i][j];        //每一行和
                    sum[rol+j]=sum[rol+j]+squre[i][j];  // 每一列和
                    if(i==j)
                    {
                        sum[2*rol+1]+=squre[i][j];  //i==j 对角线和
                    }
                    if( i+j ==rol-1)
                    {
                        sum[2*rol] += squre[i][j];  // i+j =rol-1 对角线和
                    }
                }
            }
            int allsum =sum[0];  //allsum  和
            for(int k :sum)
            {
             //如果有不相等的就返回false
               // System.out.println(k);
                if(k!=allsum)
                    return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
        System.out.println(fileName+"是一个magicsquare");
        return true;
    }
    public static boolean generateMagicSquare (int n)
    {
        if(n%2==0 || n<0)
        {
            return false;
        }
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }

        FileWriter red = null;
        FileWriter fw =null;
        BufferedWriter bfw =null;
        try
        {
            fw =new FileWriter("src/P1/txt/6.txt");
            bfw=new BufferedWriter(fw);
            for(i=0; i<n ;i++)
            {
                for(j=0;j<n ;j++)
                {
                    bfw.write(magic[i][j]+"\t");
                }
                bfw.write("\n");
            }
            try {
                if(bfw!=null)
                    bfw.close();
            } catch (Exception e) {
                return false;
            }


        }catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
