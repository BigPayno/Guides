package common.functions;

public enum Consumers {
    Fluent(new LambdaFluentConsumer());

    LambdaFluentConsumer instance;
    Consumers(LambdaFluentConsumer instance){
        this.instance = instance;
    }

    public static LambdaFluentConsumer fluent(){
        return Fluent.instance;
    }
}
