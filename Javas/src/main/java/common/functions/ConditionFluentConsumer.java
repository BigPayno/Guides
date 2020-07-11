package common.functions;

import com.google.common.base.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ConditionFluentConsumer<F extends ConditionFluentConsumer<F>> implements FluentConsumer<F>{
    public <T> F onObjectNotNull(
            @Nullable T t, @Nonnull Consumer<? super T> consumer){
       onObject(t,consumer,var->null!=var);
        return chain();
    }

    public F onStringNotNullOrBlank(
            @Nullable String t,@Nonnull Consumer<String> consumer){
        onObject(t,consumer,var->!Strings.isNullOrEmpty(var));
        return chain();
    }

    public <T> F onCollectionNotNullOrEmpty(   @Nullable Collection<T> collection,
                                               @Nonnull Consumer<? super T> consumer,
                                               @Nullable Predicate<? super T> itemCondition){
        onCollection(collection,consumer,null,itemCondition);
        return chain();
    }

    public <T> F onCollectionNotNullOrEmpty(   @Nullable Collection<T> collection,
                                               @Nonnull Consumer<? super T> consumer){
        onCollection(collection,consumer,null,null);
        return chain();
    }
}
