package com.skd.motorcade.util;

@FunctionalInterface
public interface FloatFunc<V> {
    float apply(V val);
}
