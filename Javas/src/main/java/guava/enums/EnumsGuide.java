package guava.enums;

import com.google.common.base.Converter;
import com.google.common.base.Enums;
import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.function.Function;

/**
 * @author payno
 * @date 2019/9/20 10:30
 * @description
 */
public class EnumsGuide {
    public enum Day{
        Monday,
        Friday
    }

    public void enums(){
        Enums.stringConverter(Day.class);
        EnumSet.allOf(Day.class);
    }

    @Test
    public void test1(){
        Function<String, Day> dayConverter=Enums.stringConverter(Day.class);
        Day monday = dayConverter.apply("Monday");
        Day unknown = dayConverter.apply("unknown");
        System.out.println(monday);
        System.out.println(unknown);
    }

    @Test
    public void test2(){
        Day monday = Day.Monday;
        System.out.println(monday.toString().equals("Monday"));
    }

    @Test
    public void test3(){
        EnumSet set = EnumSet.allOf(Day.class);
    }
}
