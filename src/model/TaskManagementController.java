package model;

import structure.HashTable.HashTableChaining;
import structure.Nodes.HashNode;
import structure.Nodes.Node;
import structure.Queue.Entry;
import structure.Queue.PriorityQueue;
import structure.Queue.Queue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    // Getter
    public Node<Activity> getTaskQueue() {
        return taskQueue.peekNode();
    }
    public Node<Activity> getReminderQueue() {
        return reminderQueue.peekNode();
    }
    public List<Entry<Activity>> getHighTasks(){
        return priorityQueueHigh.getHeap();
    }
    public List<Entry<Activity>> getMediumTasks(){
        return priorityQueueMedium.getHeap();
    }
    public List<Entry<Activity>> getLowTasks(){
        return priorityQueueLow.getHeap();
    }

    // Add, edit and remove
    private void addActivity(String key, Activity newAct){
        hashTableChaining.add(key,newAct);
        keys.add(key);
        if(newAct instanceof Task){
            Task task = (Task) newAct;
            if(task.isPriority()){
                priorityQueueAdd(task,task.getPriorityLevel());
            } else {
                taskQueue.offer(newAct);
            }
        } else {
            reminderQueue.offer(newAct);
        }
    }

    public String addActivity(Activity newAct){
        String key = keyCreator();
        addActivity(key, newAct);
        return "Your activity was added with the key: " + key;
    }

    public String editActivity(Activity newAct, String key) {
        remove(key);
        hashTableChaining.remove(key);
        addActivity(key, newAct);
        return "Your activity has been successfully modified";
    }

    public String removeActivity(String key){
        remove(key);
        hashTableChaining.remove(key);
        return "Your activity has been successfully removed";
    }

    public void remove(String key) {
        Activity act = hashTableChaining.get(key);
        if(act instanceof Task){
            Task task = (Task) act;
            if(task.getPriorityLevel()==PriorityLevel.NON_PRIORITY){
                taskQueue.remove(act);
            } else if(task.getPriorityLevel()==PriorityLevel.LOW){
                priorityQueueLow.remove(act);
            } else if(((Task) act).getPriorityLevel()==PriorityLevel.MEDIUM){
                priorityQueueMedium.remove(act);
            } else {
                priorityQueueHigh.remove(act);
            }
        } else {
            reminderQueue.remove(act);
        }
    }

    // Auxiliary methods
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

    public String getKey(Activity act){
        HashNode<String, Activity>[] array = hashTableChaining.getArray();
        for (int i = 0; i < array.length; i++) {
            if(array[i]!=null){
                HashNode<String, Activity> current = array[i];
                while (current!=null){
                    if(current.getValue() == act){
                        return current.getKey();
                    }
                    current = current.getNext();
                }
            }
        }
        return null;
    }

    @Override
    public TaskManagementController clone() {
        TaskManagementController clon = new TaskManagementController(10);
        clon.hashTableChaining = this.hashTableChaining.clone();
        clon.keys = new ArrayList<>(this.keys);

        HashNode<String, Activity>[] array = clon.hashTableChaining.getArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                HashNode<String, Activity> current = array[i];
                while (current!=null) {
                    current.setValue(current.getValue().clone());
                    Activity act = current.getValue();
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
                    current = current.getNext();
                }
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

    public Activity getSomething(){
        return hashTableChaining.get(keys.get(0));
    }

    public PriorityQueue<Activity> getPriorityQueueHigh() {
        return priorityQueueHigh;
    }
}