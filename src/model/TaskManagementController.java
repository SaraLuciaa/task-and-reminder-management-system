package model;

import structure.HashTable.HashTableChaining;
import structure.Nodes.HashNode;
import structure.Queue.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class TaskManagementController implements Cloneable{
    private HashTableChaining<String,Activity> hashTableChaining;
    private ArrayList<String> keys;
    private PriorityQueue<Activity> priorityQueueLow;
    private PriorityQueue<Activity> priorityQueueMedium;
    private PriorityQueue<Activity> priorityQueueHigh;
    private Queue<Activity> taskQueue;
    private Queue<Activity> reminderQueue;

    private String action;
    
    public TaskManagementController(int size){
        hashTableChaining = new HashTableChaining<>(size);
        priorityQueueLow = new PriorityQueue<>();
        priorityQueueMedium = new PriorityQueue<>();
        priorityQueueHigh = new PriorityQueue<>();
        taskQueue = new Queue<>();
        reminderQueue = new Queue<>();
        keys = new ArrayList<>();
        action = "";
    }

    public String addActivity(Activity newAct){
        String code=keyCreator();
        hashTableChaining.add(code,newAct);
        keys.add(code);
        if(newAct instanceof Task){
            Task task = (Task) newAct;
            if(task.isPriority()){
                priorityQueueAdd(task,task.getPriorityLevel());
            } else {
                taskQueue.offer(task);
            }
        }
        showActivities();
        return "Your activity was added with the key: " + code;
    }

    public String keyCreator(){
        String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder generatedCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            char randomCharacter = validCharacters.charAt(randomIndex);
            generatedCode.append(randomCharacter);
        }
        String code = generatedCode.toString();
        if(!keys.contains(code)){
            return code;
        } else {
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

    public void showActivities(){
        for(HashNode node : hashTableChaining.getArray()){
            if(node!=null){
                System.out.println(node.getValue().toString());
                while(node.getNext()!=null){
                    System.out.println(node.getValue().toString());
                }
            }
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
