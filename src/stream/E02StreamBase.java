package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName E02StreamBase
 * @Description //分为中间操作和终止操作
 * @Author ccy
 * @Date 2020/12/10 19:53
 * @Version 1.0
 **/
public class E02StreamBase {
    /**
     * 中间操作方法：
     * distinct 去重
     * filter 过滤
     * ----------------
     * 终止操作方法：
     * count 计数
     * anyMatch 只要有一个为真 则为真
     * allMatch 全部为真 则为真
     * noneMatch 都不为真 则为真
     */
    public static void main(String[] args){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        //去重后计数
        distCount(integers);
        //match 匹配
        matchItem(integers);
        //filter 过滤
        filterItem(integers);


    }

    //过滤偶数
    private static void filterItem(List<Integer> integers) {
        Stream<Integer> couples = integers.stream().filter(e -> e % 2 == 0);
        //2 4
        couples.forEach(e-> System.out.println(e));
    }


    //match
    private static void matchItem(List<Integer> integers) {
        //只要有一个为真 则为真
        boolean isCouple = integers.stream().anyMatch(e -> e % 2 == 0);
        //全部为真 则为真
        boolean allMatch = integers.stream().allMatch(e -> e % 2 == 0);
        //都不为真 则为真
        boolean noneMatch = integers.stream().noneMatch(e -> e % 2 == 0);
        //isCouple : true / allMatch : false / noneMatch : false
        System.out.println(String.format("isCouple : %b / allMatch : %b / noneMatch : %b",isCouple, allMatch, noneMatch));
    }

    //去重后计数
    private static void distCount(List<Integer> integers) {
        long count = integers.stream().distinct().count();
        System.out.println(count);
    }



}
