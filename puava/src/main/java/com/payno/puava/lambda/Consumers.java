package com.payno.puava.lambda;

import java.util.function.Consumer;

public final class Consumers{
    private Consumers(){}

    interface ConsumerThrowsException<T> extends Consumer<T>{

        void acceptThrowsException() throws Exception;

        default void acceptException(Exception e){
            e.printStackTrace();
        }

        @Override
        default void accept(T t){
            try{
                acceptThrowsException();
            }catch (Exception e){
                acceptException(e);
            }
        }
    }

}
