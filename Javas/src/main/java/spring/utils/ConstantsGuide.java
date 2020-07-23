package spring.utils;

import org.springframework.core.Constants;

/**
 * 常量指南
 *
 * @author zhaolei22
 * @date 2020/07/23
 */
public class ConstantsGuide {

    public static class Definition{
        public static final int PAYNO = 1;
        public static final String HELLO = "HELLO";
    }

    public static void main(String[] args) {
        Constants constants = new Constants(Definition.class);
        int val = constants.asNumber("PAYNO").intValue();
        System.out.println(val);
    }
}
