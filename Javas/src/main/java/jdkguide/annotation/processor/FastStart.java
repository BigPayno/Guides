package jdkguide.annotation.processor;

import org.springframework.stereotype.Component;

@Component
public class FastStart {

    interface TestJavaDefinition{
        String packageName();
        String className();
        Class<?> controllerClass();
        class Default implements TestJavaDefinition{
            @Override
            public String packageName() {
                return "hello";
            }

            @Override
            public String className() {
                return "Code";
            }

            @Override
            public Class<?> controllerClass() {
                return FastStart.class;
            }
        }
    }
}
