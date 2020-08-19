package style;

/**
 * 翻转状态   酷炫，优雅
 *
 * @author zhaolei22
 * @date 2020/08/19
 */
public enum FlipStatus {
    OPEN{
        @Override
        FlipStatus flip() {
            return CLOSE;
        }
    },CLOSE{
        @Override
        FlipStatus flip() {
            return OPEN;
        }
    };
    abstract FlipStatus flip();

    public static FlipStatus fromBoolean(boolean open){
        return open?OPEN:CLOSE;
    }

    public static void main(String[] args) {
        FlipStatus start = FlipStatus.fromBoolean(true);
        start.flip().flip().flip().flip().flip();
    }
}
