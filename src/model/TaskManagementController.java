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
        TaskManagementController clon = new TaskManagementController(10);
        clon.hashTableChaining = this.hashTableChaining.clone();
        clon.keys = new ArrayList<>(this.keys);

        HashNode<String, Activity>[] array = clon.hashTableChaining.getArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                do {
                    array[i].setValue(array[i].getValue().clone());
                    Activity act = array[i].getValue();
                    if(act instanceof Task){
                        if(((Task) act).getPriorityLevel()==PriorityLevel.NON_PRIORITY){
                            clon.taskQueue.offer(act);
                        } else if(((Task) act).getPriorityLevel()==PriorityLevel.LOW){
                            clon.priorityQueueLow.enqueue(act, calculatePriority(act.getDate()));
                        } else if(((Task) act).getPriorityLevel()==PriorityLevel.MEDIUM){
                            clon.priorityQueueMedium.enqueue(act, calculatePriority(act.getDate()));
                        } else {
                            clon.priorityQueueHigh.enqueue(act, calculatePriority(act.getDate()));
                        }
                    } else {
                        clon.reminderQueue.offer(act);
                    }
                } while(array[i].getNext()!=null);
            }
        }

        return clon;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public void setSomething(String newTitle){
        Activity activity = hashTableChaining.get(keys.get(0));
        activity.setTittle(newTitle);
    }
    public void getSomething(){
        System.out.println(hashTableChaining.get(keys.get(0)).getTittle());
    }
}
