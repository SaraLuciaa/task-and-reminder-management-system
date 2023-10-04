package model;


import structure.HashTable.HashTableChaining;
import structure.Queue.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class TaskManagementController implements Cloneable{
    private HashTableChaining<String,Activity> hashTableChaining;
    private ArrayList<String> keys;
    private PriorityQueue priorityQueueLow;
    private PriorityQueue priorityQueueMedium;
    private PriorityQueue priorityQueueHigh;
    private Queue taskQueue;
    private Queue reminderQueue;
    private String action="";
    public TaskManagementController(int size){
        hashTableChaining=new HashTableChaining<>(size);
        priorityQueueLow=new PriorityQueue();
        priorityQueueMedium=new PriorityQueue();
        priorityQueueHigh=new PriorityQueue();
        taskQueue=new Queue();
        reminderQueue=new Queue();
        keys=new ArrayList<>();
    }
    public String activityAdd(String tittle, String description, Calendar date){
        Reminder reminder=new Reminder(tittle,description,date);
        String code=keyCreator();
        hashTableChaining.add(code,reminder);
        keys.add(code);
        reminderQueue.offer(reminder);
        return "Your reminder was added with the key: "+code;
    }
    public String activityAdd(String tittle, String description, Calendar date,boolean isPriority, int pl){
        PriorityLevel priorityLevel=searchPriorityLevel(pl);
        Task task=new Task(tittle,description,date,isPriority,priorityLevel);
        String code=keyCreator();
        hashTableChaining.add(code,task);
        if(isPriority) {
            priorityQueueAdd(task,priorityLevel);
        }else{
            taskQueue.offer(task);
        }
        keys.add(code);
        return "Your priority task was added with the key: "+code;
    }
    public PriorityLevel searchPriorityLevel(int level){
        return switch (level) {
            case 0 -> PriorityLevel.NON_PRIORITY;
            case 1 -> PriorityLevel.HIGH;
            case 2 -> PriorityLevel.MEDIUM;
            case 3 -> PriorityLevel.LOW;
            default -> null;
        };
    }
    public String keyCreator(){
        String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder generatedCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            char randomCharacter= validCharacters.charAt(randomIndex);
            generatedCode.append(randomCharacter);
        }
        String code=generatedCode.toString();
        if(!keys.contains(code)){
            return code;
        }else{
            keyCreator();
        }
        return code;
    }
    public void priorityQueueAdd(Task task, PriorityLevel priorityLevel){
        long priority=calculatePriority(task.getDate());
        if(priorityLevel==PriorityLevel.HIGH){
            priorityQueueHigh.enqueue(task,priority);
        }else if(priorityLevel==PriorityLevel.MEDIUM){
            priorityQueueMedium.enqueue(task,priority);
        }else{
            priorityQueueLow.enqueue(task,priority);
        }
    }
    public long calculatePriority(Calendar date){
        Calendar now = Calendar.getInstance();
        return (date.getTimeInMillis() - now.getTimeInMillis()) / 1000;
    }
    public void exist(){
        System.out.println("\n\n\n");
        for(String k:keys){
            System.out.println(hashTableChaining.get(k));
        }
    }
    @Override
    public TaskManagementController clone() {
        try {
            TaskManagementController cloned = (TaskManagementController) super.clone();
            cloned.hashTableChaining = this.hashTableChaining.clone();
            cloned.keys = new ArrayList<>(this.keys);
            cloned.priorityQueueHigh=this.priorityQueueHigh.clone();
            cloned.priorityQueueMedium=this.priorityQueueMedium.clone();
            cloned.priorityQueueLow=this.priorityQueueLow.clone();
            cloned.taskQueue=this.taskQueue.clone();
            cloned.reminderQueue=this.reminderQueue.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
