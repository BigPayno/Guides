package com.payno.guides.utils.mockito;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FastStart {

    @Test
    public void mockMethodCall(){
        List<String> mock = mock(List.class);
        //  mock method calls
        when(mock.get(anyInt())).thenReturn("mock data!");
        when(mock.add(anyString())).thenReturn(Boolean.FALSE);
        doNothing().when(mock).add(anyInt(),anyString());
        doThrow(new RuntimeException("mock throw exception")).when(mock).forEach(any());
        System.out.println(mock.get(1));
        System.out.println(mock.add("Hello"));
        mock.add(1,"doNothing!");
        try{
            mock.forEach(System.err::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void mockCallTimes(){
        ExecutorService mock = mock(ExecutorService.class);
        mock.shutdown();
        mock.shutdown();
        try{
            verify(mock,atLeast(2)).shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void mockDiffReturn(){
        List<String> mock = mock(List.class);
        when(mock.get(1)).thenReturn("hello");
        when(mock.get(2)).thenReturn("world");
        when(mock.get(3)).thenReturn("A","B","C");
        when(mock.add(anyString())).thenAnswer(context->{
           Object args = context.getArgument(0);
           if(args instanceof String){
               return ((String) args).startsWith("hello");
           }else{
               return false;
           }
        });
        when(mock.size()).thenReturn(1).thenReturn(2);
        System.out.println(mock.get(1));
        System.out.println(mock.get(2));
        System.out.println(mock.get(3));
        System.out.println(mock.get(3));
        System.out.println(mock.get(3));
        System.out.println(mock.size());
        System.out.println(mock.size());
        System.out.println(mock.add("hello"));
        System.out.println(mock.add("hel"));
    }

    @Test
    public void mockWithSpy(){
        List<String> real = Lists.newArrayList("a","b");
        List<String> spy = Mockito.spy(real);
        doReturn(3).when(spy).size();
        doReturn("C").when(spy).get(2);
        System.out.println(spy.get(1));
        System.out.println(spy.get(2));
        System.out.println(spy.size());
    }
}
