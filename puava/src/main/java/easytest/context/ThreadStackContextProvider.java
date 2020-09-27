package easytest.context;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import easytest.runnable.EasyTest;

public class ThreadStackContextProvider implements TestContextProvider {

    StackTraceElement stackTraceElement;

    private String getTestControllerName(String testClassName){
        return testClassName.substring(0,testClassName.length()-4);
    }

    private String getTestMethodName(String testMethodName){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,testMethodName.substring(4));
    }

    private StackTraceElement getControllerTestStackTraceElement(){
        PeekingIterator<StackTraceElement> peekingIterator = Iterators.peekingIterator(
                Iterators.forArray(Thread.currentThread().getStackTrace()));
        while(peekingIterator.hasNext()){
            StackTraceElement cur = peekingIterator.next();
            if(peekingIterator.hasNext()){
                StackTraceElement next = peekingIterator.peek();
                if(
                   cur.getClassName().equals(EasyTest.class.getName())&&
                   !next.getClassName().equals(cur.getClassName())
                ){
                    return getMethodTestJava(peekingIterator);
                }
            }
        }
        return null;
    }

    private StackTraceElement getMethodTestJava(PeekingIterator<StackTraceElement> peekingIterator){
        while(peekingIterator.hasNext()){
            StackTraceElement cur = peekingIterator.next();
            if(peekingIterator.hasNext()){
                StackTraceElement next = peekingIterator.peek();
                if(
                   !next.getClassName().equals(cur.getClassName())
                ){
                    return cur;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized void init() throws IllegalStateException {
        if(stackTraceElement==null){
            this.stackTraceElement = getControllerTestStackTraceElement();
        }
    }

    @Override
    public Class<?> testController() throws ClassNotFoundException {
        init();
        String testClassName = stackTraceElement.getClassName();
        return Class.forName(getTestControllerName(testClassName));
    }

    @Override
    public String testMethod() {
        init();
        return getTestMethodName(stackTraceElement.getMethodName());
    }
}
