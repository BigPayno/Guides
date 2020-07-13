package pattern.fluent;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Predicate;

@AllArgsConstructor
public class IsNotNull implements Fluent<If>{
    If anIf;

    @Override
    public If root() {
        return anIf;
    }

    public <T> If thenConsume(T t, Consumer<? super T> consumer){
        if(null!=t){
            consumer.accept(t);
        }
        return root();
    }
}
