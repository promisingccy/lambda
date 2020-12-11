package stream;

import stream.entity.CustBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
     * stream的三个组成：数据源/中间操作终止操作。
     *
     * 中间操作是对数据的加工，是lazy操作，
     * 并不会立马启动，而是等待终止操作才会执行!
     *
     * @数据源
     * @中间操作方法：
     * distinct 去重->new Stream
     * filter 过滤->new Stream
     * map 再加工->new Stream
     * flatMap 平铺层级关系->new Stream
     * ----------------
     * @终止操作方法：
     * count 计数->int
     * anyMatch 只要有一个为真 则为真->bool
     * allMatch 全部为真 则为真->bool
     * noneMatch 都不为真 则为真->bool
     * reduce  初始值，遍历算法->int
     * collect 将stream转为集合
     */
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        //collect 将stream转为集合
        collectStream(list);

        System.out.println("=================");

        //reduce  初始值，遍历算法
        reduceItem(list);
        //flatMap 平铺层级关系
        flatMap();
        //map 再加工
        mapItem(list);
        //filter 过滤，避免使用 if/else
        filterItem(list);
        //match 匹配
        matchItem(list);
        //去重后计数
        distCount(list);
    }

    //collect 将stream转为集合
    private static void collectStream(List<Integer> list) {
        List<Integer> res = list.stream().map(e -> e + 3).collect(Collectors.toList());
        //[4, 5, 6, 7]
        System.out.println(res);
    }

    //reduce  初始值，遍历算法
    private static void reduceItem(List<Integer> list) {
        Integer addAll = list.stream().reduce(100, (a, b) -> a + b);
        //110
        System.out.println(addAll);
        Integer removeAll = list.stream().reduce(100, (a, b) -> a - b);
        //90
        System.out.println(removeAll);
    }

    private static void flatMap() {
        ArrayList<CustBook> users = new ArrayList<>();
        users.add(new CustBook(Arrays.asList("ccy","ddy")));
        Stream<Stream<String>> streamMap = users.stream().map(user -> user.getBookName().stream());
        //java.util.stream.ReferencePipeline$Head@7ba4f24f
        streamMap.forEach(e-> System.out.println(e));

        Stream<String> streamFlatMap = users.stream().flatMap(user -> user.getBookName().stream());
        //ccy ddy
        streamFlatMap.forEach(e-> System.out.println(e));
    }

    //map 再加工
    private static void mapItem(List<Integer> list) {
        Stream<Integer> newStream = list.stream().map(e -> e + 10);
        //11 12 13 14
        newStream.forEach(e-> System.out.println(e));
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
        //4
        System.out.println(count);
    }



}
