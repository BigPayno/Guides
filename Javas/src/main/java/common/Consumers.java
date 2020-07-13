package common;

import com.google.common.base.Strings;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.*;

/**
 * configers
 *  为了偷懒
 * @author payno
 * @date 2020/07/11
 */
public enum Consumers {
    Fluent(new FluentConsumer());

    FluentConsumer instance;
    Consumers(FluentConsumer instance){
        this.instance = instance;
    }

    public static FluentConsumer fluent(){
        return Fluent.instance;
    }

    static final class FluentConsumer{
        public <T> FluentConsumer  on(  @Nullable T supplier,
                                        Consumer<? super T> consumer,
                                        Predicate<? super T> condition  ){
            if(condition.test(supplier)){
                consumer.accept(supplier);
            }
            return this;
        }

        public <T> FluentConsumer  on(  Supplier<? extends T> supplier,
                                        Consumer<? super T> consumer,
                                        Predicate<? super T> condition  ){
            on(supplier.get(),consumer,condition);
            return this;
        }

        public FluentConsumer onStringNotNullOrBlank(@Nullable String supplier, Consumer<? super String> consumer){
            on(supplier,consumer,t->!Strings.isNullOrEmpty(t));
            return this;
        }

        public FluentConsumer onStringNotNullOrBlank(
                Supplier<? extends String> supplier, Consumer<? super String> consumer){
            onStringNotNullOrBlank(supplier.get(),consumer);
            return this;
        }

        public <T> FluentConsumer onObjectNotNull(@Nullable T t, Consumer<? super T> consumer){
           on(t,consumer,val->null!=val);
            return this;
        }

        public <T> FluentConsumer onObjectNotNull(Supplier<? extends T> supplier,Consumer<? super T> consumer){
            onObjectNotNull(supplier.get(),consumer);
            return this;
        }

        public <T> FluentConsumer onCollection( Collection<? extends T> supplier,
                                                 Consumer<? super T> consumer,
                                                 Predicate<? super Collection> condition,
                                                 Predicate<? super T> itemCondition){
            if(condition.test(supplier)){
                supplier.stream().filter(itemCondition).forEach(consumer);
            }
            return this;
        }

        public <T> FluentConsumer onCollectionNotNullOrEmply(
                Collection<? extends T> supplier, Consumer<? super T> consumer){
            if(!CollectionUtils.isEmpty(supplier)){
                supplier.forEach(consumer);
            }
            return this;
        }

        public <T> FluentConsumer onCollectionNotNullOrEmply(
                Supplier<? extends Collection<? extends T>> supplier, Consumer<? super T> consumer){
            onCollectionNotNullOrEmply(supplier.get(),consumer);
            return this;
        }

        public FluentConsumer on(int t,IntConsumer consumer){
            consumer.accept(t);
            return this;
        }

        public FluentConsumer on(long t, LongConsumer consumer){
            consumer.accept(t);
            return this;
        }

        public FluentConsumer on(double t, DoubleConsumer consumer){
            consumer.accept(t);
            return this;
        }

        public <T> FluentConsumer on(@Nonnull T t,Consumer<? super T> consumer){
            consumer.accept(t);
            return this;
        }
    }
}
