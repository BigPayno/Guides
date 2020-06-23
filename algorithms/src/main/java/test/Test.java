package test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.Collections;
import java.util.concurrent.Executors;

/**
 * @author payno
 * @date 2020/6/23 15:05
 * @description
 */
public class Test {

    private static int queryStart(int []nums,int start,int end,int target){
        int index = (start+end)/2;
        if(nums[index]<target){

            return queryStart(nums,index,end,target);
        }else if(nums[index]==target){
            for(int i=index;i>=start;i--){
                if(nums[i]<target){
                    return i+1;
                }
                if(i==start){
                    if(nums[start]==target){
                        return start;
                    }
                }
            }
            return -1;
        }else {
            return queryStart(nums, start, index, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,4,5,5,5,6};
        int target = 7;
        int start = queryStart(nums,0,nums.length,target);
        if(start!=-1){
            System.out.println(start);
            for (int i=start;i<nums.length;i++){
                if(nums[i]!=target){
                    System.out.println(i-1);
                    break;
                }
            }
        }else{
            System.out.println("no target num!");
        }
    }
}
