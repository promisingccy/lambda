package stream;

import stream.Spliterator.CommonSpliterator;
import stream.entity.MyBook;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @ClassName E07StreamSpliterator
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 19:47
 * @Version 1.0
 **/
public class E07StreamSpliterator {
    public static void main(String[] args){
        //截断流-指定条件
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> lt5 = takeWhile(stream, x -> x < 5)
                .collect(Collectors.toList());
        //[1, 2, 3, 4]
        System.out.println(lt5);

        //demo1
        // spliteratorOne();
    }

    //stream 待截断的流
    //predicate 指定条件
    public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<T> predicate){
        CommonSpliterator<T> commonSpliterator = new CommonSpliterator<>(stream.spliterator(), predicate);
        return StreamSupport.stream(commonSpliterator, false);
    }

    private static void spliteratorOne() {
        //sp 1000
        Spliterator<MyBook> sp = generateElements().spliterator();
        //sp2 500
        Spliterator<MyBook> sp2 = sp.trySplit();
        System.out.println(String.format("sp.estimateSize() %s", sp.estimateSize()));
        System.out.println(String.format("sp.characteristics() %s", sp.characteristics()));
        System.out.println(String.format("call(sp) %s", call(sp)));
        System.out.println(String.format("call(sp2) %s", call(sp2)));
        System.out.println(String.format("sp.estimateSize() %s", sp.estimateSize()));
    }

    //生成list
    public static List<MyBook> generateElements(){
        return Stream.generate(() -> new MyBook("ccy's book"))
                .limit(1000)
                .collect(Collectors.toList());
    }

    //call
    public static String call(Spliterator<MyBook> spliterator){
        int current = 0;
        while(spliterator.tryAdvance(a->a.setName("test name".concat("- add new name")))){
            current++;
        }
        return Thread.currentThread().getName() + ":" + current;
    }
}
