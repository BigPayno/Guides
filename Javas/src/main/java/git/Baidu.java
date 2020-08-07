package git;


import java.util.function.Consumer;
import java.util.function.Function;

public class Baidu {

    enum State{
        Very_Angry;
    }

    static class People{
        public People on(State state){
            return this;
        }

        public People apply(Function<Integer,Integer> stringConsumer){
            return this;
        }

        public People whenComplete(Consumer<Me> stringConsumer){
            return this;
        }

        public void byebye(){}
    }


    static class Me{
        public void byebye(){}
    }
    static class Peoples{

        public static String UNFAIR;

        public static void addListener(String str, Consumer<People> me){

        }
    }


    public static void main(String[] args) {
        Boolean a = null;
        boolean test = a == null;
        System.out.println(test);
        /*Peoples.addListener(
                Peoples.UNFAIR, me-> me.on(State.Very_Angry)
                                       .apply(exp->exp+3)
                                       .whenComplete(Me::byebye));*/


    }
}
