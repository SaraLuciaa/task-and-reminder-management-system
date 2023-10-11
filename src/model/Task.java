package model;

import java.util.Calendar;

public class Task extends Activity implements Cloneable{
    private PriorityLevel priorityLevel;
    private boolean isPriority;

    public Task(String title, String description, Calendar date, boolean isPriority, int priorityLevel) {
        super(title, description, date);
        this.isPriority = isPriority;
        this.priorityLevel = PriorityLevel.values()[priorityLevel];
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}
