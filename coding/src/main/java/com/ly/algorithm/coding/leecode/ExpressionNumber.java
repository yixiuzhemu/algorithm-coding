package com.ly.algorithm.coding.leecode;

/**
 * 给定一个只由0（假）、1（真）、&（逻辑与）、|（逻辑或）和^（异或）五种字符组成的字符串express，再给定一个布尔值desired。返回express能由多少种组合方式，可以达到desired的效果
 *
 * 	举例：
 *
 * 	express="1^0|0|1" . desired = false;
 *
 * 	只有1^（（0|0）|1）和1^（0|（0|1））的组合可以得到false，返回2.express=”1“,desired=false,没有组合可以得到false，返回0
 * @author Ly
 * @create 2023/10/21 10:48
 * @desc
 **/
public class ExpressionNumber {

    public static boolean isValid(char[] exp){
        if(exp.length % 2 == 0){
            return false;
        }
        for(int i = 0;i < exp.length;i++){
            if(i % 2 == 0 && ( exp[i]  == '&' || exp[i] == '|' || exp[i] == '^')){
               return false;
            }
            if(i % 2 != 0 && (exp[i] == '0' || exp[i] == 1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 第一步，检查字符串的合法性，即偶数位必须位0或1，奇数位必须位| & ^ 符号其中之一，并且字符串的长度必须为奇数，否则直接返回0
     *
     * 	第二步，定义一个方法f，传入参数L，R，以及期待的结果，返回符合条件的种数。
     *
     * 	第三步，假设在L~R范围上期待结果为1，假设最后以i位置的字符最为i最终运算符
     *
     * 	i = & ： L~I-1为1的数量为a，I+1 ~ R为1的数量为b，所以总的结果为a * b
     *
     * 	i = | ： L~i-1为1的数量为a，为0的数量为b，i+1~R 为1的数量为c，为0的数量为d，那么总的结果为a * c + a * d  + c * b
     *
     * 	i = ^ ： L~i-1为1数量为a，为0的数量为b，i+1~R 为1的数量为c，为0的数量为d，那么总的结果为 a * d + c * b
     *
     * 	如果期待的结果为0
     *
     * 	i = & ：L~i-1为1的数量为a，为0的数量为b，i+1~R 为1的数量为c，为0的数量为d，所以总的结果为a * d + b * c + b * d
     *
     * 	 i = | ： L~i-1为1的数量为a，为0的数量为b，i+1~R 为1的数量为c，为0的数量为d，那么总的结果为b * d
     *
     * 	i = ^ ： L~i-1为1数量为a，为0的数量为b，i+1~R 为1的数量为c，为0的数量为d，那么总的结果为
     *
     * a * c + b * d
     *
     * 	第四步：计算出L~R范围上每个符号位置的答案，累加起来就是L~R范围上的总答案
     *
     * 	隐藏条件，L~R的边界必然在0或者1上，不可能在符号位上，所以L~R上的符号位就是L+1, L+3 .... R-1
     * @param express
     * @param desired
     * @return
     */
    public static int expressionNum_v1(String express,boolean desired){
        char[] chars = express.toCharArray();
        if(!isValid(chars)){
            return 0;
        }
        return process(chars,0,chars.length-1,desired);
    }

    public static int process(char[] chars,int L,int R,boolean desired){
        int ans = 0;
        if(L == R){
            boolean a  = chars[L] == '1' ? true : false;
            return a == desired ? 1 : 0;
        }
        if(L == 2 && R == 8){
            L = L;
        }
        if(desired){
            for(int i = L+1; i < R ; i = i+2){
                switch (chars[i]){
                    case '&' :
                        ans += process(chars,L,i-1,true) * process(chars,i+1,R,true);
                        break;
                    case '|':
                        ans += process(chars,L,i-1,true) * (process(chars,i+1,R,true) +  process(chars,i+1,R,false)) + process(chars,L,i-1,false) * process(chars,i+1,R,true);
                        break;
                    case '^':
                        ans+= process(chars,L,i-1,true) * process(chars,i+1,R,false) + process(chars,L,i-1,false) * process(chars,i+1,R,true);
                        break;
                }
            }
        }else{
            for(int i = L+1; i < R ; i = i+2){
                switch (chars[i]){
                    case '&' :
                        ans += process(chars,L,i-1,false) * (process(chars,i+1,R,true) +  process(chars,i+1,R,false)) + process(chars,L,i-1,true) *  process(chars,i+1,R,false);
                        break;
                    case '|':
                        ans +=  process(chars,L,i-1,false) * process(chars,i+1,R,false);
                        break;
                    case '^':
                        ans +=  process(chars,L,i-1,true) * process(chars,i+1,R,true) + process(chars,L,i-1,false) * process(chars,i+1,R,false);
                        break;
                }
            }

        }
        return ans;
    }

    /**
     * 存在三个变量,L,R,期待值
     *
     * 	准备两个dp数组，trueDp[N] [N]  记录期待值1 ， falseDp[N] [N] 记录期待值0
     *
     * 	哪些位置不用填
     *
     * 	L > R 的位置，符号位，即奇数位
     *
     * 	初始化dp数组的对角线，即L==R时，根据当前的字符值填出trueDp和falseDp的对角线
     *
     * 	填符号位的状态，对于任意i,j位置
     *
     * 	已经填好了对角线，那么row从N-3位置开始（N-1位置已经填好，N-2位置是符号位，所以从N-3位置开始），col从row+2位置开始（row+1是符号位，row+2是下一个字符）。也就是row代表L，col代表R。再遍历L~R中的字符位i，从row+1开始，步长为2，根据暴力递归的逻辑开始填值
     *
     * 	i == & :
     *
     * 			 trueDp[row] [col] += trueDp[row] [i-1] * trueDp[i+1] [col];
     *
     * 			falseDp[row] [col] += falseDp[row] [i-1] * (trueDp[i+1] [col] + falseDp[i+1] [col]) + trueDp[row] [i-1]*falseDp[i+1] [col];
     *
     * 	i == | :
     *
     * 			trueDp[row] [col] += trueDp[row] [i-1] * (trueDp[i+1] [col] + falseDp[i+1] [col]) + falseDp[row] [i-1] * trueDp[i+1] [col];
     *
     * 			falseDp[row] [col] += falseDp[row] [i-1] * falseDp[i+1] [col];
     *
     * 	i == ^ ：
     *
     * 			trueDp[row] [col] += trueDp[row] [i-1] * falseDp[i+1] [col] + falseDp[row] [i-1] * trueDp[i+1] [col];
     *
     * 		falseDp[row] [col] += trueDp[row] [i-1] * trueDp[i+1] [col] + falseDp[row] [i-1] * falseDp[i+1] [col];
     * @param expression
     * @param desired
     * @return
     */
    public static int expressionNum_V2(String expression,boolean desired){
        char[] chars = expression.toCharArray();
        if(!isValid(chars)){
            return 0;
        }
        int N = chars.length;
        int[][] trueDp = new int[N][N];
        int[][] falseDp = new int[N][N];
        for(int i = 0; i < chars.length;i+=2){
            trueDp[i][i] = chars[i] == '1'? 1 : 0;
            falseDp[i][i] = chars[i] == '0' ? 1 : 0;
        }
        //row代表L
        for(int row = N - 3; row >= 0;row-=2){
            //j代表R
            for(int col = row+2;col<N;col+=2){
                //i代表L~R中的符号位
                for(int i = row+1;i<col;i+=2){
                    switch (chars[i]){
                        case '&' :
                            trueDp[row][col] += trueDp[row][i-1] * trueDp[i+1][col];
                            falseDp[row][col] += falseDp[row][i-1] * (trueDp[i+1][col] + falseDp[i+1][col]) + trueDp[row][i-1]*falseDp[i+1][col];
                            break;
                        case '|':
                            trueDp[row][col] += trueDp[row][i-1] * (trueDp[i+1][col] + falseDp[i+1][col]) + falseDp[row][i-1] * trueDp[i+1][col];
                            falseDp[row][col] += falseDp[row][i-1] * falseDp[i+1][col];
                            break;
                        case '^':
                            trueDp[row][col] += trueDp[row][i-1] * falseDp[i+1][col] + falseDp[row][i-1] * trueDp[i+1][col];
                            falseDp[row][col] += trueDp[row][i-1] * trueDp[i+1][col] + falseDp[row][i-1] * falseDp[i+1][col];
                    }
                }
            }
        }
        if(desired){
            return trueDp[0][N-1];
        }
        return falseDp[0][N-1];
    }

}
