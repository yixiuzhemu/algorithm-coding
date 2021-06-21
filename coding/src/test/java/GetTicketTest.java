import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ly
 * @create 2021/6/21 14:41
 * @desc
 **/
public class GetTicketTest implements Runnable{

    private AtomicLong atomicLong = new AtomicLong();

    private List<String> initNums = Lists.newArrayList();

    private String terminalString;

    private volatile Long count = 0L;

    private volatile Boolean execute;


    public GetTicketTest() {
        execute = true;
        for(int i = 1;i<36;i++){
            if(i<10){
                initNums.add("0"+i);
            }else{
                initNums.add(String.valueOf(i));
            }
        }
    }

    public List<String> getInitNums() {
        return initNums;
    }

    public void setTerminalString(String terminalString) {
        this.terminalString = terminalString;
    }

    @Override
    public void run() {
        String terminalS = "";
        while(!terminalString.equals(terminalS)){
            if(!execute){
                return;
            }
            terminalS = GetTicketTest.getS(initNums);
            atomicLong.incrementAndGet();
        }
        if(execute){
            synchronized (this){
                if(execute){
                    count = atomicLong.get();
                }
            }
        }
        execute = false;
    }

    public Long getCount() {
        return count;
    }

    public Boolean getExecute() {
        return execute;
    }

    public static String getS( List<String> initNums){
        Map<Integer, Set<String>> prefixNumberMap = Maps.newHashMap();
        Map<Integer,Set<String>> suffixNumberMap = Maps.newHashMap();
        Integer index = 0;
        Random random = new Random();
        while(index < 5){
            Set<String> prefixNumbers = Sets.newTreeSet();
            Set<String> suffixNumbers = Sets.newTreeSet();
            while(prefixNumbers.size()<6){
                int r =random.nextInt(32)+1;
                prefixNumbers.add(initNums.get(r));
            }
            while(suffixNumbers.size()<1){
                int r =random.nextInt(15)+1;
                suffixNumbers.add(initNums.get(r));
            }
            prefixNumberMap.put(index,prefixNumbers);
            suffixNumberMap.put(index,suffixNumbers);
            index++;
        }
        Set<String> prefixString = prefixNumberMap.get(random.nextInt(5));
        Set<String> suffixString = suffixNumberMap.get(random.nextInt(5));
        return JSON.toJSONString(prefixString) + " "+ JSON.toJSONString(suffixString);
    }

    @Test
    public void getTicketNumber(){
        Map<String,Long> maps = Maps.newHashMap();
        for(int m = 0;m<50;m++){
            GetTicketTest testGet = new GetTicketTest();
            String s = GetTicketTest.getS(testGet.getInitNums());
            testGet.setTerminalString(s);

            for(int i = 0;i< 8;i++){
                new Thread(testGet).start();
            }
            while(testGet.getExecute()){
            }
            Long aLong = maps.get(s);
            if(aLong==null || aLong > testGet.getCount()){
                maps.put(s,testGet.getCount());
            }
        }
        Long min = null;
        Iterator<Map.Entry<String, Long>> iterator = maps.entrySet().iterator();
        String result = null;
        while(iterator.hasNext()){
            Map.Entry<String, Long> next = iterator.next();
            Long value = next.getValue();
            String key = next.getKey();
            if(min == null){
                min = value;
                result = key;
            }
            if(min>value){
                min = value;
                result = key;
            }
        }
        System.out.println("buy："+result);
        System.out.println("Bingo,foreach："+min);
    }
}
