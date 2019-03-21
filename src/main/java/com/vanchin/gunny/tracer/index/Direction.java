package com.vanchin.gunny.tracer.index;

/**
 * @author vanchin
 * @date 2019/3/13 14:43
 */
public class Direction<T> {
    private T target;
    private String type;
    private String value;

    public Direction(T target, String name, String value){
        this.target = target;
        this.type = name;
        this.value = value;
    }

    public Direction(String name, String value){
        this.type = name;
        this.value = value;
    }

    public T getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
