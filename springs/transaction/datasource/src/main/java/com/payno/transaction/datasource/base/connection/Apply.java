package com.payno.transaction.datasource.base.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2020/6/8 09:12
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    private Integer applyId;
    private String cusName;
}
