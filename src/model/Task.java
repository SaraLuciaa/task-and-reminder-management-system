package model;

import java.util.Calendar;

public class Task extends Activity{
    private boolean isPriority;
    private PriorityLevel priorityLevel;
    public Task(String tittle, String description, Calendar date,boolean isPriority,PriorityLevel priorityLevel){
        super(tittle,description,date);
        this.isPriority=isPriority;
        this.priorityLevel=priorityLevel;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}