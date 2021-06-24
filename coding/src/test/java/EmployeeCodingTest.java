import com.alibaba.fastjson.JSON;
import com.ly.algorithm.EmployeeInfo;
import com.ly.algorithm.coding.EmployeeCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/6/24 16:50
 * @desc
 **/
public class EmployeeCodingTest {

    @Test
    public void testMaxHappy(){
        EmployeeInfo employeeInfo = TestData.getEmployeeInfo();
        System.out.println("公司信息："+ JSON.toJSONString(employeeInfo));
        Integer maxHappyInfo = EmployeeCoding.getMaxHappyInfo(employeeInfo);
        System.out.println("最大快乐值："+maxHappyInfo);
    }

}
