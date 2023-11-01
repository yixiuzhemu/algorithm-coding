package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.*;

/**
 *
 * 每种工作有难度和报酬，规定如下
 *
 * 	class Job{
 *
 * 		//该工作的报酬

 * 		public int money;
 *
 * 		//该工作的难度
 *
 * 		public int hard;
 *
 * }
 *
 * 给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作，选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的高位。
 *
 * 给定一个int类型数组arr，代表所有人的能力，返回int类型的数组，表示每个人按照标准选择工作后能获得的最高报酬。
 * @author Ly
 * @create 2023/9/24 12:19
 * @desc
 **/
public class FindJob {

    @Data
    @Builder
    public static class Job{
        /**
         * 该工作的报酬
         */
        private int money;

        /**
         * 该工作的难度
         */
        private int hard;
    }

    /**
     * 对工作jobarr进行排序，按照难度从小到大，报酬从大到小进行排序。然后难度相等的工作，只保留报酬最高的工作。筛选出组长数组后，再对组长进行筛选，如果难度上升，报酬没有上升，那么就删除这个工作。最后保证，难度上升、报酬也上升的工作，保持单调性
     * @param jobArr
     * @param scores
     * @return
     */
    public static int[] findJob(List<Job> jobArr,int[] scores){
        //对jobArr，按照hard 从小到大排序，money 从大到小排序
        Collections.sort(jobArr, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if(o1.hard < o2.hard){
                    return -1;
                }else if(o1.hard > o2.hard){
                    return 1;
                }
                if(o1.money < o2.money){
                    return 1;
                }else if(o1.money > o2.money){
                    return -1;
                }
                return 0;
            }
        });
        //同等难度下只保留头
        List<Job> leaderJob = Lists.newArrayList();
        Job curJob = null;
        for (Job job : jobArr) {
            if(curJob == null || curJob.hard != job.hard){
                leaderJob.add(job);
            }
        }
        //遍历leaderJob，只保留难度上升，报酬也上升的工作
        Iterator<Job> iterator = leaderJob.iterator();
        Job preJob = null;
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(preJob == null || preJob.money < job.money){
                preJob = job;
                continue;
            }
            iterator.remove();
        }
        //将当前leaderJob
        Collections.sort(leaderJob,Comparator.comparingInt(a->a.getHard()));
        int[] ans = new int[scores.length];
        for (int i = 0;i<scores.length;i++) {
            ans[i] = findJob(leaderJob,0,leaderJob.size()-1,scores[i]);
        }
        return ans;
    }

    private static int findJob(List<Job> jobs,int L,int R,int score){
        Job leftJob = jobs.get(L);
        if(leftJob.getHard() > score){
            return 0;
        }
        Job rightJob = jobs.get(R);
        if(rightJob.getHard() <= score){
            return rightJob.getMoney();
        }
        int mid = L+ (R-L)/2;
        Job midJob = jobs.get(mid);
        if(midJob.getHard() <= score){
            //右边如果找不到工作，那么返回当前工作
            return Math.max(findJob(jobs,mid+1,R,score),midJob.getMoney());
        }else if(midJob.getHard() > score){
            //去左边找工作
            return findJob(jobs,L,mid-1,score);
        }
        return 0;
    }

}
