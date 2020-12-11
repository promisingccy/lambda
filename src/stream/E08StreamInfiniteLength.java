package stream;

import stream.entity.IntegerWrapper;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName E08StreamInfiniteLength
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 19:54
 * @Version 1.0
 **/
public class E08StreamInfiniteLength {
    public static void main(String[] args){
        //创建指定长度的stream并转成list
        createListFromStream();

        //创建自定义类型的集合
        createSelfCollect();
    }

    private static void createSelfCollect() {
        Supplier<IntegerWrapper> custType = E08StreamInfiniteLength::generateCustType;
        Stream<IntegerWrapper> stream = Stream.generate(custType);
        List<Integer> collect = stream.skip(10).limit(10).map(IntegerWrapper::getInteger).collect(Collectors.toList());
        //[61, 52, 96, 89, 91, 94, 21, 22, 61, 68]
        System.out.println(collect);
    }


    public static IntegerWrapper generateCustType(){
        Random random = new Random();
        int i = random.nextInt(100);
        return new IntegerWrapper(i);
    }

    private static void createListFromStream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        List<Integer> list = stream.limit(10).collect(Collectors.toList());
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(list);
    }
}
