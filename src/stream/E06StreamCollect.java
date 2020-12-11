package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName E06StreamCollect
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 15:57
 * @Version 1.0
 **/
public class E06StreamCollect {
    public static void main(String[] args){
        List<String> list = Arrays.asList("one", "two", "three", "four");
        List<String> listWithCouple = Arrays.asList("one", "one", "three", "four");

        //list转list
        // collect(Collectors.toList())
        listToList(list);

        //list转set
        // collect(Collectors.toSet())
        listToSet(listWithCouple);

        //list转自定义结构
        // collect(Collectors.toCollection(TreeSet::new))
        listToSelfSet(listWithCouple);

        //list转map
        // collect(Collectors.toMap(Function.identity(), String::length))
        listToMap(list);

        //list转map(有相同key)
        // collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a))
        listToMapWithSameKey(listWithCouple);

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
