package com.ly.algorithm.coding;

/**
 *
 * 1、zigzag打印矩阵
 *
 * 2、转圈打印矩阵
 *
 * 3、原地旋转正方形
 *
 * 核心技巧：找到coding上的宏观调度
 * @author Ly
 * @create 2023/8/6 13:39
 * @desc
 **/
public class MatrixCoding {


    /**
     * zigzag打印矩阵
     * a指针：从左往右走，直到不能往右开始往下走
     *
     * b指针：从上往下走，直到不能往下开始往右走
     *
     * a,b指针每移动一次，会形成一条斜线，移动次数为奇数时，从从上往下打印，移动次数为偶数时，从下往上打印
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix){
        if(matrix == null){
            return;
        }
        Position a = new Position();
        Position b = new Position();
        int count = matrix.length-1+matrix[0].length-1;
        System.out.println(matrix[0][0]);
        for(int i = 0;i<count;i++){
            if(a.y+1 == matrix[0].length){
                a.x++;
            } else {
                a.y++;
            }
            if(b.x+1 == matrix.length){
                b.y++;
            }else{
                b.x++;
            }
            if(i%2 == 0){
                //从上往下打印
                print(matrix,a,b,true);
                continue;
            }
            //从下往上打印
            print(matrix,a,b,false);
        }
    }

    public static void print(int[][] matrix,Position a,Position b,boolean top2Bottom){
        if(top2Bottom){
            //从上往下打印
            int x = a.x;
            int y = a.y;
            int endX = b.x;
            int endY = b.y;
            while(x != endX && y != endY){
                System.out.println(matrix[x++][y--]);
            }
            System.out.println(matrix[endX][endY]);
            return;
        }
        int x = b.x;
        int y = b.y;
        int endX = a.x;
        int endY = a.y;
        while(x != endX && y != endY){
            System.out.println(matrix[x--][y++]);
        }
        System.out.println(matrix[endX][endY]);
    }

    static class Position{
        public int x;
        public int y;

        public Position(){

        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void printCycle(int[][] matrix){
        if(matrix == null){
            return;
        }
        //第一层
        Position a = new Position(0,0);
        Position b = new Position(0,matrix[0].length-1);
        Position c = new Position(matrix.length-1,matrix[0].length-1);
        Position d = new Position(matrix.length-1,0);
        while(true){
            //当左上角的行等于右下角的行时，此时代表只有一行了
           if(a.x == c.x){
               for (int i = a.y;i<=c.y;i++){
                   System.out.println(matrix[a.x][i]);
               }
               return;
           }
           //当右上角的列等于左下角的列时，此时代表只有一列了
            if(b.y == d.y){
                for(int i = b.x;i<=d.x;i++){
                    System.out.println(matrix[i][b.y]);
                }
                return;
            }
            for (int i = a.y;i<b.y;i++){
                System.out.println(matrix[a.x][i]);
            }
            for(int i = b.x;i<c.x;i++){
                System.out.println(matrix[i][b.y]);
            }
            for(int i = c.y;i>d.y;i--){
                System.out.println(matrix[c.x][i]);
            }
            for(int i = d.x;i>a.x;i--){
                System.out.println(matrix[i][d.y]);
            }
          //进入下一层
          a.x++;
          a.y++;
          b.x++;
          b.y--;
          c.x--;
          c.y--;
          d.x--;
          d.y++;
        }
    }

    /**
     * 原地旋转正方形矩阵
     * 必须是正方形矩阵
     * @param matrix
     * @param angle
     */
    public static void rotate(int[][] matrix,int angle){
        if(angle % 90 != 0 || matrix.length != matrix[0].length){
            return;
        }
        int count = angle / 90;
        while(count-- > 0){
            //每90°旋转一次
            rotateEdge(matrix);
        }
    }

    private static void rotateEdge(int[][] matrix){
        //定义4个顶点
        Position a = new Position(0,0);
        Position b = new Position(0,matrix[0].length-1);
        Position c = new Position(matrix.length-1,matrix[0].length-1);
        Position d = new Position(matrix.length-1,0);
        //因为是正方形，所以必然不可能出现内部剩一条直线的情况
        while(a.x < c.x){
            int tmp = 0;
            //完成当前层的旋转
            for(int i = 0;i<c.x - a.x;i++){
                tmp = matrix[a.x][a.y+i];
                matrix[a.x][a.y+i]=matrix[d.x-i][d.y];
                matrix[d.x-i][d.y] = matrix[c.x][c.y-i];
                matrix[c.x][c.y-i]=matrix[b.x+i][b.y];
                matrix[b.x+i][b.y]=tmp;
            }
            //去下一层进行旋转
            a.x++;
            a.y++;
            b.x++;
            b.y--;
            c.x--;
            c.y--;
            d.x--;
            d.y++;
        }
    }
}
