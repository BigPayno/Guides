package binaryfind.findrange;

/**
 * @author payno
 * @date 2020/6/24 11:03
 * @description
 */
public class MainTest {
    public static void main(String[] args) {
        BinaryFind leftRangeFind = new LeftRangeBinaryRangeFind();
        System.out.println(leftRangeFind.binaryFind(new int[]{1},1));
        System.out.println(leftRangeFind.binaryFind(new int[]{1},0));
        System.out.println(leftRangeFind.binaryFind(new int[]{1,2,3,3,4,5,7,7,7,10},7));
        System.out.println(leftRangeFind.binaryFind(new int[]{1,2,3,3,4,5,7,7,7,10},1));
        System.out.println(leftRangeFind.binaryFind(new int[]{1,2,3,3,4,5,7,7,7,10},10));
        System.out.println(leftRangeFind.binaryFind(new int[]{1,2,3,3,4,5,7,7,7,10},11));
        System.out.println(leftRangeFind.binaryFind(new int[]{1,2,3,3,4,5,7,7,7,10},0));
        System.err.println();
        BinaryFind baseRangeFind = new BaseBinaryRangeFind();
        System.out.println(baseRangeFind.binaryFind(new int[]{1},1));
        System.out.println(baseRangeFind.binaryFind(new int[]{1},0));
        System.out.println(baseRangeFind.binaryFind(new int[]{1,2,3,4,5,7,10},7));
        System.out.println(baseRangeFind.binaryFind(new int[]{1,2,3,4,5,7,10},1));
        System.out.println(baseRangeFind.binaryFind(new int[]{1,2,3,4,5,7,10},10));
        System.out.println(baseRangeFind.binaryFind(new int[]{1,2,3,4,5,7,10},11));
        System.out.println(baseRangeFind.binaryFind(new int[]{1,2,3,4,5,7,10},0));
    }
}
