package pattern.fluent;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Predicate;

@AllArgsConstructor
public class IsNotNullOrBlank implements Fluent<If>{

    If anIf;

    @Override
    public If root() {
        return anIf;
    }

    public If thenConsume(String t, Consumer<String> consumer, Predicate<String> condition){
        if(condition.test(t)){
            consumer.accept(t);
        }
        return root();
    }

    public If thenConsume(String t, Consumer<String> consumer){
        return thenConsume(t,consumer,val->!Strings.isNullOrEmpty(val));
    }
}
