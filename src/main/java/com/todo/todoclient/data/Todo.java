package com.todo.todoclient.data;

public class Todo {

    private int todoId;

    private String todoDescription;

    private boolean todoStatus;

    private int todoAssignment;

    public Todo() {
    }

    public Todo(String todoDescription, boolean todoStatus, int todoAssignment) {
        this.todoDescription = todoDescription;
        this.todoStatus = todoStatus;
        this.todoAssignment = todoAssignment;
    }

    public Todo(int todoId, String todoDescription, boolean todoStatus, int todoAssignment) {
        this.todoId = todoId;
        this.todoDescription = todoDescription;
        this.todoStatus = todoStatus;
        this.todoAssignment = todoAssignment;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public boolean isTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(boolean todoStatus) {
        this.todoStatus = todoStatus;
    }

    public int getTodoAssignment() {
        return todoAssignment;
    }

    public void setTodoAssignment(int todoAssignment) {
        this.todoAssignment = todoAssignment;
    }
}
