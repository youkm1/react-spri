package com.example.demo.persistence;

import com.example.demo.dto.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//extends implements
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    //AOP 인터페이스 제공하는 기본적 쿼리가 아닌 쿼리
    List<TodoEntity> findByUserId(String userId);


}
