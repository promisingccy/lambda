package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName GetStream
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/10 16:54
 * @Version 1.0
 **/
public class E01GetStream {
    public static void main(String[] args){
        /************* 通过 .stream 获取 *************/
        String[] strings = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(strings);

        /************* 通过 .stream 获取 *************/
        Stream<String> stream1 = Stream.of("a", "b", "c");

        /************* stream 多线程 *************/
        List<String> list = Arrays.asList("a", "b", "c");
        //多线程输出 顺序不保证  b c a
        list.parallelStream().forEach(e-> System.out.println(e));

    }

}
