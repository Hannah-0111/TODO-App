package com.example.todo.service;

import com.example.todo.model.TodoEntity;
import com.example.todo.model.TodoRequest;
import com.example.todo.repository.TodoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
  private final TodoRepo todoRepo;

  public TodoEntity add(TodoRequest req) {
    TodoEntity todoEntity = new TodoEntity();
    todoEntity.setTitle(req.getTitle());
    todoEntity.setOrder(req.getOrder());
    todoEntity.setCompleted(req.getCompleted());
    return this.todoRepo.save(todoEntity);
  }

  public TodoEntity searchById(Long id) {
    Optional<?> result = this.todoRepo.findById(id);
    return (TodoEntity) result.orElseThrow(() -> new RuntimeException("value not present"));
  }

  public List<TodoEntity> searchAll() {
    return this.todoRepo.findAll();
  }

  public TodoEntity updateById(Long id, TodoRequest req) {
    TodoEntity todoEntity = this.searchById(id);

    if(req.getTitle() != null) {
      todoEntity.setTitle(req.getTitle());
    }
    if(req.getOrder() != null) {
      todoEntity.setOrder(req.getOrder());
    }
    if(req.getCompleted() != null) {
      todoEntity.setCompleted(req.getCompleted());
    }
    return this.todoRepo.save(todoEntity);
  }

  public void deleteById(Long id) {
    this.todoRepo.deleteById(id);
  }

  public void deleteAll() {
    this.todoRepo.findAll();
  }
}
