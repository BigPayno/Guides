package binaryfind.findrange;

/**
 * @author payno
 * @date 2020/6/24 09:41
 * @description
 */
public abstract class AbstractBinaryRangeFind implements BinaryFind{

    @Override
    public int binaryFind(int[] nums, int target) {
        FindContext context = FindContext.of(nums,target,0,nums.length-1);
        while (context.low<=context.high){
            printFindRange(context);
            context.mid = (context.high+context.low)>>>1;
            context.midVal = context.nums[context.mid];
            if(!(tryLowContract(context)||tryHighContract(context))){
                //  当能够收缩就收缩（区间在变小），如果不能收缩要么跳出循环都没有找到对应的值；要么就是无法收缩即找到值
                return getResult(context);
            }
        }
        return -1;
    }

    abstract boolean tryLowContract(FindContext context);
    abstract boolean tryHighContract(FindContext context);
    abstract int getResult(FindContext context);
    abstract void printFindRange(FindContext context);
}
