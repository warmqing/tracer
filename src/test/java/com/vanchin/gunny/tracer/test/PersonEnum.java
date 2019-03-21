package com.vanchin.gunny.tracer.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vanchin
 * @date 2019/3/20 11:52
 */
public enum PersonEnum {
    WEIGHT(1, "WEIGHT"),
    HEIGHT(2, "HEIGHT"),
    WEALTH(3, "WEALTH"),
    AGE(4, "AGE"),
    HANDSOME(5, "HANDSOME");

    private int value;
    private String type;

    PersonEnum(int value, String type) {
        this.value = value;
        this.type = type;
    }


    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public static List<String> types() {
        List<String> types = new ArrayList<String>();
        for (PersonEnum p : PersonEnum.values()) {
            types.add(p.getType());
        }
        return types;
    }
}
