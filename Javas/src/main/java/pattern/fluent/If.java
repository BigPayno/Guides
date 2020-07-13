package pattern.fluent;

public class If {
    public static final If anIf;
    private static final StringPredicates stringPredicates;
    private static final ObjectPredicates objectPredicates;
    static {
        anIf = new If();
        stringPredicates = new StringPredicates(anIf);
        objectPredicates = new ObjectPredicates(anIf);
    }
    public StringPredicates string(){
        return stringPredicates;
    }
    public ObjectPredicates object(){
        return objectPredicates;
    }
}
