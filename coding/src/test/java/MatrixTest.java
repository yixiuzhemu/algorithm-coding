import com.ly.algorithm.coding.MatrixCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/8/6 13:52
 * @desc
 **/
public class MatrixTest {

    @Test
    public void printMatrixZigZag(){
        int[][] matrix = new int[][]{{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18}};
        MatrixCoding.printMatrixZigZag(matrix);
    }

    @Test
    public void printCycle(){
        int[][] matrix = new int[][]{{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18}};
        MatrixCoding.printCycle(matrix);
    }

    @Test
    public void rotate(){
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        MatrixCoding.rotate(matrix,90);
    }
}
