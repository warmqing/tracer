package com.vanchin.gunny.tracer.test;

import com.vanchin.gunny.tracer.index.Target;

/**
 * @author vanchin
 * @date 2019/3/20 11:33
 */
public class Tag implements Target {

    private long id;
    private String name;

    Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getUniqueId() {
        return Long.toString(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
