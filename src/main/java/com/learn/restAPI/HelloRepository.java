package com.learn.restAPI;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HelloRepository {

    private final Map<Long, Hello> storage = new HashMap<>();
    private long nextId = 1;

    public Hello save(Hello hello) {
        if (hello.getId() == null) {
            hello.setId(nextId++);
        }
        storage.put(hello.getId(), hello);
        return hello;
    }

    public Optional<Hello> findById(Long id) {
        Hello hello = storage.get(id);
        return Optional.ofNullable(hello);
    }

    public List<Hello> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
