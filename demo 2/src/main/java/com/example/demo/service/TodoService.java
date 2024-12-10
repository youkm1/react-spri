package com.example.demo.service;

import com.example.demo.dto.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My First TODO ITEM").build();
        repository.save(entity);
        TodoEntity savedEntity = repository.findById(entity.getId().toString()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity Id: {} is saved!", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);

    }

    public List<TodoEntity> update(final TodoEntity entity){
        validate(entity);

        final Optional<TodoEntity> original = repository.findById(entity.getId().toString());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity){
        validate(entity);

        try {
            repository.delete(entity);

        } catch (Exception e) {
            log.error("err while deleting entity ",entity.getId(), e);
            throw new RuntimeException("err while deleting entity "
                    + entity.getId());
        }
        return retrieve(entity.getUserId());
    }
    private void validate(final TodoEntity entity){
        if(entity == null){
            log.warn("entity cannot be null");
            throw new RuntimeException("entity cannot be null");
        }

        if(entity.getUserId()==null){
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }


}
