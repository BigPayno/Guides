package com.payno.transaction.faststart;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author payno
 * @date 2020/6/2 10:43
 * @description
 */
public interface Menento {
    @Data
    @AllArgsConstructor
    class Default implements Menento{
        String name;
    }
}
