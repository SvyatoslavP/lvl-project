package ru.panifidkin.lvlproject.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

@Component
public class ObjectSerializer<T> implements Serializer<T> {

    private Gson gson;

    @PostConstruct
    public void init() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    @Override
    public String serialize(@Nonnull T obj) {
        return gson.toJson(obj);
    }

    @Override
    public T deserialize(String value, Class<T> clazz) {
        return gson.fromJson(value, clazz);
    }
}
