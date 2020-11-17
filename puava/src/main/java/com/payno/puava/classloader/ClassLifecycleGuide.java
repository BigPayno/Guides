package com.payno.puava.classloader;

import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.charset.Charset;

public class ClassLifecycleGuide {

    public interface TestPrint{
        void print();
    }

    /**
     * complie:Java->Class
     *     Java Complier API
     *     run:     java编译器提供参数,得到Java编译器的输出信息,接收编译器的错误信息，一个或多个Java源程式文件
     */
    @Test
    public void complie() throws IOException {
        String classPath = "com.payno.puava.classloader.test.ComplierGuide";
        System.out.println(Classs.isLoaded(classPath));
        String interfaceClass = "com.payno.puava.classloader.ClassLifecycleGuide$TestPrint";
        System.out.println(Classs.isPresent(interfaceClass));
        ClassPathResource resource = new ClassPathResource("ComplierGuide.java");
        CharSource charSource = Files.asCharSource(resource.getFile(), Charset.defaultCharset());
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null, charSource.read());
        System.out.println(result == 0 ? "编译成功" : "编译失败");
    }


    
}
