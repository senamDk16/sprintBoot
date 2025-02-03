package com.senam.todolists.controller;

import com.senam.todolists.Entity.TodoList;
import com.senam.todolists.services.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/api/taches")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController (TodoListService todoListService){
        this.todoListService = todoListService;
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTaches(){
        return  new ResponseEntity<>(todoListService.getAllTodolists(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoList> getOneTache(@PathVariable Long id){
        return  new ResponseEntity<>(todoListService.getTodolist(id), HttpStatus.OK);
    }
    @GetMapping("/status/:{status}")
    public ResponseEntity<List<TodoList>> getByStatus(@PathVariable String status){
        return  new ResponseEntity<>(todoListService.getByStatus(status),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoList> createTodolist(@RequestBody TodoList todoList){
        return  new ResponseEntity<>(todoListService.addTodoList(todoList), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoList> updateTodolist(@PathVariable Long id, @RequestBody TodoList todoListDetail){
        return new ResponseEntity<>(todoListService.updateTodoList(id, todoListDetail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTodolist(@PathVariable Long id){
        todoListService.delteTodoList(id);
    }

}
