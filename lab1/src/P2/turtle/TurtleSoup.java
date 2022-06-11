/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context*
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        /*throw new RuntimeException("implement me!");*/
        int i;
        for(i=0;i<=3;i++) {
            turtle.forward(sideLength);
            turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
        if(sides<=2) {
            return 0;
        }
        else {
            double angle=((double)(sides-2)*(double)(180))/(double)(sides);
            return angle;
        }
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");


            int sides =  (int)Math.round(360/(180-angle));
            return sides;

    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
        int i=0;
        double angle= 180- calculateRegularPolygonAngle(sides);
        for( ;i<sides;i++)
        {
            turtle.forward(sideLength);
            turtle.turn(angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        //throw new RuntimeException("implement me!");
        int gapx=targetX-currentX;
        int gapy = targetY - currentY;
        double angle = Math.toDegrees(Math.atan2( gapy, gapx));
        double targetdegree;
        /* 先减去currentBeearing 再判断是否小于零，
          小于零则说明初始角度已经超过了两点连线与y轴的夹角度数
          所以需要再转一圈才能到达。
         */
       targetdegree = (90-currentBearing) -angle;
       if(targetdegree <0 )
           targetdegree+=360;
       return targetdegree ;

    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        //throw new RuntimeException("implement me!");


        double angle=0;
        List<Double> ans = new ArrayList<>();
        if(xCoords.size()==0)
            return ans;
        for(int i=0; i<xCoords.size()-1 ;i++)
        {
            double d= calculateBearingToPoint(angle,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
            ans.add(d);
            angle = angle +d;
            if(angle>360)
                angle-=360;
        }

        return ans;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */

    public static Set<Point> convexHull(Set<Point> points) {
        // throw new RuntimeException("implement me!");
        Set<Point> ans = new HashSet<>();//返回的点集合
        List<Point> temp_p=new ArrayList<Point>(); // 暂时存储点
        temp_p.addAll(points);
        if(temp_p.size()<=3)
            return points;

            //首先找到最左边点里面最下面的点
        Point firstpoint=temp_p.get(0);
        for(Point P:points) {
                if(P.x()<firstpoint.x())
                    firstpoint=P;
                else if(P.x()==firstpoint.x()&&P.y()<firstpoint.y())
                    firstpoint=P;

        }

            List<Point> convexHill=new ArrayList<Point>();
            convexHill.add(firstpoint);
            int i=0;
            temp_p.remove(firstpoint);
            Point startpoint =firstpoint;

            //循环用来将找到的点放进convewHill 里面
            do {
                // 当走过firstpoint点之后再将其加入点集合，防止走回该点。并且用来标记终止状态
                if(i==1) {
                    temp_p.add(firstpoint);
                }
                //每次循环都恢复到最大角度360，最短边长 0
                double nextdegree=360,nextdistance=0;
                Point nextpoint =null;
                for(Point P:temp_p) {
                    //  calculateBearingToPoint 只会返回一个正数  0< = degree <360
                    double degreegap=calculateBearingToPoint(0,(int) startpoint.x(),
                            (int) startpoint.y(),(int)P.x(),(int)P.y());
                    double distance=Math.pow(startpoint.x() - P.x(), 2) + Math.pow(startpoint.y() - P.y(), 2);
                    //以该点为中心点，边以顺时针转，找到连线与y轴夹角最小点
                    if(nextdegree>degreegap) {
                        nextdegree   = degreegap;
                        nextdistance = distance;
                        nextpoint = P ;
                    }
                    else if(degreegap==nextdegree&&distance>nextdistance) {
                        //如果度数一样就选离该 点 远的那一个点,每次转身之后不会再回头，所以不需要删除
                        nextpoint =P;
                        nextdistance=distance;
                    }
                }
                //更新起始点
                startpoint = nextpoint;
                //找到了下一个点放进凸包列表里面
                convexHill.add(nextpoint);
                // 去除用过的点
                temp_p.remove(nextpoint);
                i++;
            }while(convexHill.get(i)!=firstpoint);//运行回原点的时候退出循环


            ans.addAll(convexHill);
            return ans;

    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
       // throw new RuntimeException("implement me!");
        PenColor[] color = new PenColor[7];
        color[0] = PenColor.RED;
        color[1] = PenColor.ORANGE;
        color[2] = PenColor.YELLOW;
        color[3] = PenColor.GREEN;
        color[4] = PenColor.PINK;
        color[5] = PenColor.BLUE;
        color[6] = PenColor.CYAN;
        int i=0,yanse;
       while(i<42) {
            drawRegularPolygon(turtle, (i + 3), (i * 2));
           yanse = i % 7;
            turtle.color(color[yanse]);
            turtle.turn(120);
            i++;
        }


    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
      //  DrawableTurtle turtle = new DrawableTurtle();
        Turtle turtle = new DrawableTurtle();
         //draw the window
        turtle.draw();
      //  drawSquare(turtle, 40);
      //  drawRegularPolygon(turtle,8,80);

      //  凸包测试代码
     /*   Set<Point> point = new HashSet<>();
        Point p1 = new Point(10,10);
        point.add(p1);
        Point p2 = new Point(1,10);
        point.add(p2);
        Point p3 = new Point(1,1);
        point.add(p3);
        Point p4 = new Point(1,2);
        point.add(p4);
        Point p5 = new Point(2,3);
        point.add(p5);
        Point p6 = new Point(3,2);
        point.add(p6);
        Point p7 = new Point(0.5,0.5);
        point.add(p7);
        point.add(new Point(10,1));

      Set<Point> ans = new HashSet<>();

        ans = convexHull(point);
        for(Point p : ans)
        {
            System.out.print(p.x() +"   ");
            System.out.println(p.y());
        }*/

        drawPersonalArt( turtle);

    }

}
