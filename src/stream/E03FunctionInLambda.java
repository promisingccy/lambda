package stream;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName E03FunctionInLambda
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 13:50
 * @Version 1.0
 **/
public class E03FunctionInLambda {
    public static void main(String[] args){
        //计算map中的条目
        computeIfAbsentItem();
        //线程池demo
        threadDemo();
    }

    private static void computeIfAbsentItem() {
        HashMap<String, Integer> nameMap = new HashMap<>();
        Integer length1 = nameMap.computeIfAbsent("name", String::length);
        //4 由于name这个key不存在 则统计了 name.length = 4
        System.out.println(length1);
        nameMap.put("name",3);
        Integer length2 = nameMap.computeIfAbsent("name", String::length);
        //3 指定了name的值为3
        System.out.println(length2);
    }


    private static void threadDemo() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //原生写法
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //new runnable
                System.out.println("new runnable");
            }
        });
        //lambda 写法 省略匿名类的构造
        //new runnable
        executorService.submit(()->System.out.println("new runnable"));
    }
}
