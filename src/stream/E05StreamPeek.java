package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName E05StreamPeek
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 15:29
 * @Version 1.0
 **/
public class E05StreamPeek {
    public static void main(String[] args){
        /**
         * peek主要用于debug 输出遍历流程中的数据状态；
         * 如果是基本类型，peek不会改变返回结果；
         * 如果是对象，peek改变了其中属性则会影响返回结果
         */

        // filter:three
        // map:THREE
        // filter:four
        // map:FOUR
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("filter:" + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("map:" + e))
                .collect(Collectors.toList());
        //[THREE, FOUR]
        System.out.println(list);
    }



}
