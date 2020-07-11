package common.functions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaFluentConsumer
        extends ConditionFluentConsumer<LambdaFluentConsumer>
        implements FluentConsumer<LambdaFluentConsumer>{
    @Override
    public LambdaFluentConsumer chain() {
        return this;
    }

    public  <T> LambdaFluentConsumer onObject(@Nonnull Supplier<? extends T> t,
                           @Nonnull Consumer<? super T> consumer,
                           @Nonnull Predicate<? super T> predicate){
        return onObject(t.get(),consumer,predicate);
    }

    public <T> LambdaFluentConsumer onObjectNotNull(
            @Nonnull Supplier<? extends T> t, @Nonnull Consumer<? super T> consumer){
        return onObjectNotNull(t.get(),consumer);
    }

    public LambdaFluentConsumer onStringNotNullOrBlank(
            @Nullable Supplier<String> t,@Nonnull Consumer<String> consumer){
        return onStringNotNullOrBlank(t.get(),consumer);
    }
}
