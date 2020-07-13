package pattern.fluent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StringPredicates {
    If anIf;

    IsNotNullOrBlank isNotNullOrBlank(){
        return new IsNotNullOrBlank(anIf);
    }
}
