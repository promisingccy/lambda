package stream.Spliterator;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @ClassName CommonSpliterator
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/14 10:37
 * @Version 1.0
 **/
public class CommonSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
    private Spliterator<T> spliterator;
    private Predicate<T> predicate;
    private volatile boolean isMatched = true;

    public CommonSpliterator(Spliterator<T> split, Predicate<T> pred){
        super(split.estimateSize(), 0);
        spliterator = split;
        predicate = pred;
    }


    @Override
    public boolean tryAdvance(Consumer<? super T> consumer) {
        boolean hadNext = spliterator.tryAdvance(ele->{
            if(predicate.test(ele) && isMatched){
                consumer.accept(ele);
            } else {
              isMatched = false;
            }
        });
        return hadNext && isMatched;
    }

/*    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public long getExactSizeIfKnown() {
        return 0;
    }

    @Override
    public boolean hasCharacteristics(int characteristics) {
        return false;
    }

    @Override
    public Comparator<? super T> getComparator() {
        return null;
    }*/
}
