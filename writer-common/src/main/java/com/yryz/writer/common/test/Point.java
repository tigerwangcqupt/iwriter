package com.yryz.writer.common.test;

public class Point<T> {
    private T var;

    public Point(T var) {
        this.var = var;
    }

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }
}