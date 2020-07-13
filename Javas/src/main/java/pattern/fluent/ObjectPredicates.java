package pattern.fluent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectPredicates {
    If anIf;

    IsNotNull isNotNull(){
        return new IsNotNull(anIf);
    }
}
