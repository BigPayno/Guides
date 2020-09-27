package easytest.context;

import easytest.context.LambdaContextProvider.BiFunctionRef;
import easytest.context.LambdaContextProvider.FunctionRef;
import easytest.context.LambdaContextProvider.SupplierRef;

public interface TestContextProvider {
    void init() throws IllegalStateException, NoSuchMethodException;
    Class<?> testController() throws ClassNotFoundException;
    String testMethod();
    static TestContextProvider threadStack(){
        return new ThreadStackContextProvider();
    }
    static <T> TestContextProvider ref(SupplierRef<T> ref){
        return LambdaContextProvider.of(ref);
    }
    static <T,R> TestContextProvider ref(FunctionRef<T, R> ref){
        return LambdaContextProvider.of(ref);
    }
    static <T,U,R> TestContextProvider ref(BiFunctionRef<T, U, R> ref){
        return LambdaContextProvider.of(ref);
    }
}
