package ru.panifidkin.lvlproject.serialize;

public interface Serializer<T> {

    String serialize(T obj);

    T deserialize(String value, Class<T> clazz);
}
