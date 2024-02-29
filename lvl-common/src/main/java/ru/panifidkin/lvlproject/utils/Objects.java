package ru.panifidkin.lvlproject.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class Objects {

    public static <T, R> R applyIfNotNull(T obj, Function<T, R> function) {
        return obj == null ? null : function.apply(obj);
    }
}
