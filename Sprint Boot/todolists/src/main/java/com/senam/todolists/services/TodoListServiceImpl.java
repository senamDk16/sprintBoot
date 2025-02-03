package com.senam.todolists.services;

import com.senam.todolists.Entity.TodoList;
import com.senam.todolists.repository.TodoListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoListServiceImpl implements TodoListService{

    private final TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }
    @Override
    public List<TodoList> getAllTodolists() {
        return todoListRepository.findAll();
    }

    @Override
    public TodoList getTodolist(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Override
    public List<TodoList> getByStatus(String status) {
        return todoListRepository.findByStatus(status);
    }

    @Override
    public TodoList addTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList updateTodoList(Long id, TodoList todoListDetail) {
        return todoListRepository.findById(id).map( todoList -> {
            todoList.setTitle(todoListDetail.getTitle());
            todoList.setDescription(todoListDetail.getDescription());
            todoList.setStatus(todoListDetail.getStatus());
            return todoList;
        }).orElse(null);
    }

    @Override
    public void delteTodoList(Long id) {
        todoListRepository.deleteById(id);
    }
}
