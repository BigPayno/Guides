package easytest.context;

import easytest.context.LambdaContextProvider.Ref;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class LambdaContextProvider<F extends Ref> implements TestContextProvider{

    F function;
    SerializedLambda serializedLambda;

    interface Ref{

    }
    @FunctionalInterface
    public interface FunctionRef<T, R> extends java.util.function.Function<T, R>, Serializable, Ref{
    }
    @FunctionalInterface
    public interface BiFunctionRef<T, U, R> extends java.util.function.BiFunction<T, U, R>, Serializable, Ref{
    }
    @FunctionalInterface
    public interface SupplierRef<T> extends java.util.function.Supplier<T>, Serializable, Ref{
    }

    private LambdaContextProvider() {
        super();
    }

    public static <F extends Ref> LambdaContextProvider<F> of(F f){
        LambdaContextProvider<F> lambdaContextProvider = new LambdaContextProvider<F>();
        lambdaContextProvider.function = f;
        return lambdaContextProvider;
    }

    @Override
    public synchronized void init() throws IllegalStateException{
        if(serializedLambda==null){
            try{
                // 直接调用writeReplace
                Method writeReplace = function.getClass().getDeclaredMethod("writeReplace");
                writeReplace.setAccessible(true);
                Object sl = writeReplace.invoke(function);
                serializedLambda = (SerializedLambda) sl;
            }catch (Exception e){
                e.printStackTrace();
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

    @Override
    public Class<?> testController() throws ClassNotFoundException {
        init();
        return Class.forName(serializedLambda.getImplClass().replace('/','.'));
    }

    @Override
    public String testMethod() {
        init();
        return serializedLambda.getImplMethodName();
    }
}
