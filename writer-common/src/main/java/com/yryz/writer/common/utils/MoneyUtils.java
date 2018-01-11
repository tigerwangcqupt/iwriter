package com.yryz.writer.common.utils;

import java.math.BigDecimal;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2017/8/25 11:39
 * Created by lifan
 */
public class MoneyUtils {

    public static String format(final BigDecimal amount) {
        BigDecimal newAmount = amount.divide(new BigDecimal(10000), 3, BigDecimal.ROUND_DOWN);
        return newAmount.toPlainString();
    }

    public static String balanceFormat(final BigDecimal amount) {
        BigDecimal newAmount = amount.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_DOWN);
        return newAmount.toPlainString();
    }

    public static BigDecimal getMoney(final BigDecimal amount) {
        return amount.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal setBigDecimal(final BigDecimal amount) {
        BigDecimal bigDecimal = amount.movePointRight(4);
        BigDecimal divide = bigDecimal.divide(new BigDecimal(1), 0, BigDecimal.ROUND_DOWN);
        return divide;
    }

    public static void main(String[] args) {
        String s = balanceFormat(new BigDecimal(999999));
        //扩大一万
        BigDecimal bigDecimal = setBigDecimal(new BigDecimal(1.06));
        System.out.println(bigDecimal);
        //缩小一万
        bigDecimal = getMoney(bigDecimal);
        System.out.println(bigDecimal);

        BigDecimal withdrawAmount = new BigDecimal(100);
        BigDecimal settlementAmount = new BigDecimal(50);
        System.out.println(setBigDecimal(withdrawAmount.subtract(settlementAmount)));

    }
}
