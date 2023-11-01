package leecode;

import com.google.common.collect.Lists;
import com.ly.algorithm.coding.leecode.FindJob;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2023/9/24 13:48
 * @desc
 **/
public class FindJobTest {

    @Test
    public void findJob(){
        List<FindJob.Job> jobs = Lists.newArrayList();
        jobs.add(FindJob.Job.builder().hard(1).money(3).build());
        jobs.add(FindJob.Job.builder().hard(1).money(2).build());
        jobs.add(FindJob.Job.builder().hard(2).money(5).build());
        jobs.add(FindJob.Job.builder().hard(3).money(6).build());
        jobs.add(FindJob.Job.builder().hard(4).money(7).build());
        jobs.add(FindJob.Job.builder().hard(4).money(5).build());
        jobs.add(FindJob.Job.builder().hard(5).money(8).build());
        jobs.add(FindJob.Job.builder().hard(6).money(12).build());
        jobs.add(FindJob.Job.builder().hard(7).money(7).build());
        int[] arr = new int[]{2,5,7,4,3,2};
        int[] job = FindJob.findJob(jobs, arr);
        for (int i : job) {
            System.out.println(i);
        }
    }

}
