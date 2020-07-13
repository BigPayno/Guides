package pattern.fluent;

public class Test {
    public static void main(String[] args) {
        Fluents.cases()
                .string().isNotNullOrBlank()
                    .thenConsume("a",System.out::println)
                .object().isNotNull()
                    .thenConsume(null,System.err::println);
    }
}
