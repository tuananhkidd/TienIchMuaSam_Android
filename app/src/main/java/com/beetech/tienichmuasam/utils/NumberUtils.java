package com.beetech.tienichmuasam.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    public static String fomartMoney(BigDecimal price){
        DecimalFormat format = new DecimalFormat("#.###");
        return format.format(price.toBigInteger());
    }
}
