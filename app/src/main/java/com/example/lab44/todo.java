package com.example.lab44;

public class todo {

    String todoText;
    Boolean urgency;

    public String getTodoText() {
        return todoText;
    }
    public void setTodoText(String newTodoText){
        this.todoText = newTodoText;
    }

    public void setUrgency(Boolean newUrgency){
        this.urgency = newUrgency;
    }

    public boolean isUrgent(){
        return true;
    }

    public boolean notUrgent(){
        return false;
    }

}
