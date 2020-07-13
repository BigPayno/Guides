package common.functions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.*;

public interface FluentConsumer<F extends FluentConsumer<F>> {
    default <T> F onObject(@Nullable T t,
                          @Nonnull Consumer<? super T> consumer,
                          @Nonnull Predicate<? super T> predicate){
        if(predicate.test(t)){
            consumer.accept(t);
        }
        return chain();
    }

    default <T> F onCollection(@Nullable Collection<T> collection,
                                @Nonnull Consumer<? super T> consumer,
                                @Nullable Predicate<? super Collection> condition,
                                @Nullable Predicate<? super T> itemCondition){
        if(condition==null||condition.test(collection)){
            if(collection!=null){
                collection.stream().filter(itemCondition).forEach(consumer);
            }
        }
        return chain();
    }
    default F on(int t, IntConsumer consumer){
        consumer.accept(t);
        return chain();
    }

    default F on(long t, LongConsumer consumer){
        consumer.accept(t);
        return chain();
    }

    default F on(double t, DoubleConsumer consumer){
        consumer.accept(t);
        return chain();
    }

    default  <T> F on(@Nonnull T t, Consumer<? super T> consumer){
        consumer.accept(t);
        return chain();
    }

    F chain();
}
