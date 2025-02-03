package com.senam.todolists.services;

import com.senam.todolists.Entity.TodoList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoListService {
    List<TodoList> getAllTodolists();
    TodoList getTodolist(Long id);
    List<TodoList> getByStatus(String status);
    TodoList addTodoList(TodoList todoList);
    TodoList updateTodoList(Long id, TodoList todoList);
    void delteTodoList(Long id);
}
