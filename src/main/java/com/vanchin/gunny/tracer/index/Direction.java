package com.vanchin.gunny.tracer.index;

/**
 * @author wangqing21
 * @date 2019/3/13 14:43
 */
public class Direction<T> {
    private T target;
    private String name;
    private String value;

    public Direction(T target, String name, String value){
        this.target = target;
        this.name = name;
        this.value = value;
    }

    public Direction(String name, String value){
        this.name = name;
        this.value = value;
    }

    public T getTarget() {
        return target;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
