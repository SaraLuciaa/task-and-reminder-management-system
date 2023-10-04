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
    private Queue queue;
    public TaskManagementController(int size){
        hashTableChaining=new HashTableChaining<>(size);
        priorityQueueLow=new PriorityQueue();
        priorityQueueMedium=new PriorityQueue();
        priorityQueueHigh=new PriorityQueue();
        queue=new Queue<>();
        keys=new ArrayList<>();
    }
    public String activityAdd(String tittle, String description, Calendar date){
        Reminder reminder=new Reminder(tittle,description,date);
        String code=keyCreator();
        hashTableChaining.add(code,reminder);
        keys.add(code);
        return "Your reminder was added with the key: " + code;
    }

    public String activityAdd(String tittle, String description, Calendar date,boolean isPriority, int pl){
        Task task=new Task(tittle,description,date,isPriority,pl);
        String code=keyCreator();
        hashTableChaining.add(code,task);
        if(isPriority) {
            priorityQueueAdd(task,PriorityLevel.values()[pl]);
        }
        keys.add(code);
        return "Your task was added with the key: " + code;
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
                // Add to queue
            }
        }
        return "Your activity was added with the key: " + code;
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
        System.out.println(priorityQueueHigh.peek());
    }
    public long calculatePriority(Calendar date){
        Calendar now = Calendar.getInstance();
        return (date.getTimeInMillis() - now.getTimeInMillis()) / 1000;
    }
    public void exist(){
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
            cloned.queue=this.queue.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
