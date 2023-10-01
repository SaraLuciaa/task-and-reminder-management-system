package model;

import structure.HashTable.HashTableChaining;

import java.util.Calendar;

public class TaskManagementController {
    private HashTableChaining<String,Activity> hashTableChaining;
    public TaskManagementController(){
        hashTableChaining=new HashTableChaining<>(10);
    }
    public String activityAdd(String tittle, String description, Calendar date, Calendar dayTime){
        Reminder reminder=new Reminder(tittle,description,date,dayTime);
        hashTableChaining.add("sd3r33fe",reminder);
        return "Su reminder fue creada y agregada exitosamente, la clave de busquedad es sd3r33fe";
    }
    public String activityAdd(String tittle, String description, Calendar date, Calendar dayTime,boolean isPriority, int pl){
        PriorityLevel priorityLevel=searchPriorityLevel(pl);
        Task task=new Task(tittle,description,date,dayTime,isPriority,priorityLevel);
        hashTableChaining.add("hola293",task);
        return "Su task fue creada y agregada exitosamente, la clave de busquedad es hola293";
    }
    public PriorityLevel searchPriorityLevel(int level){
        return switch (level) {
            case 1 -> PriorityLevel.HIGH;
            case 2 -> PriorityLevel.MEDIUM;
            case 3 -> PriorityLevel.LOW;
            default -> null;
        };
    }
}
