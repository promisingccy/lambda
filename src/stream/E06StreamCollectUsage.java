package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName E06StreamCollectUsage
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 15:57
 * @Version 1.0
 **/
public class E06StreamCollectUsage {
    public static void main(String[] args){
        List<String> list = Arrays.asList("one", "two", "three", "four");
        List<String> listWithCouple = Arrays.asList("one", "one", "three", "four");

        System.out.println("====================");
        //根据条件返回两部分 key为bool值的map
        collectWithBoolGroupBy(list);

        //根据属性分组 返回map
        collectGroup(list);

        //统计类计算
        collectStat(list);

        //连接操作
        collectJoin(list);

        //再次处理  collectingAndThen
        collectAgain(list);

        //list转map(有相同key)
        // collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a))
        listToMapWithSameKey(listWithCouple);

        //list转map
        // collect(Collectors.toMap(Function.identity(), String::length))
        listToMap(list);

        //list转自定义结构
        // collect(Collectors.toCollection(TreeSet::new))
        listToSelfSet(listWithCouple);

        //list转set
        // collect(Collectors.toSet())
        listToSet(listWithCouple);

        //list转list
        // collect(Collectors.toList())
        listToList(list);

    }

    private static void collectWithBoolGroupBy(List<String> list) {
        Map<Boolean, List<String>> lengthWithGroupBy = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 3));
        //{false=[one, two], true=[three, four]}
        System.out.println(lengthWithGroupBy);
    }

    private static void collectGroup(List<String> list) {
        Map<Integer, Set<String>> collectGroup = list.stream().collect(Collectors.groupingBy(String::length,
                Collectors.toSet()));
        //{3=[one, two], 4=[four], 5=[three]}
        System.out.println(collectGroup);
    }

    private static void collectStat(List<String> list) {
        IntSummaryStatistics lengthRes = list.stream().collect(Collectors.summarizingInt(String::length));
        //IntSummaryStatistics{count=4, sum=15, min=3, average=3.750000, max=5}
        System.out.println(lengthRes);
    }

    private static void collectJoin(List<String> list) {
        String joinStr = list.stream().collect(Collectors.joining(" ","ccy_","_ccy"));
        //ccy_one two three four_ccy
        System.out.println(joinStr);
    }

    private static void collectAgain(List<String> list) {
        ArrayList<String> newCollectList = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
            return new ArrayList<>(l);
        }));
        System.out.println(newCollectList);
    }

    private static void listToMapWithSameKey(List<String> listWithCouple) {
        Map<String, Integer> collect = listWithCouple.stream().collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a));
        //{four=4, one=3, three=5}
        System.out.println(collect);
    }


    private static void listToMap(List<String> list) {
        Map<String, Integer> mapFromStream = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
        //{four=4, one=3, three=5, two=3}
        System.out.println(mapFromStream);
    }

    private static void listToSelfSet(List<String> listWithCouple) {
        Set<String> setFromStream =
                listWithCouple.stream().map(String::toUpperCase).collect(Collectors.toCollection(TreeSet::new));
        //[FOUR, ONE, THREE]
        System.out.println(setFromStream);
    }

    private static void listToSet(List<String> listWithCouple) {
        Set<String> setFromStream = listWithCouple.stream().map(String::toUpperCase).collect(Collectors.toSet());
        //[ONE, FOUR, THREE]
        System.out.println(setFromStream);
    }

    private static void listToList(List<String> list) {
        List<String> listFromStream = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        //[ONE, TWO, THREE, FOUR]
        System.out.println(listFromStream);
    }
}
