package model;

import structure.HashTable.HashTableChaining;
import structure.Nodes.HashNode;
import structure.Nodes.Node;
import structure.Queue.PriorityQueue;
import structure.Queue.Queue;

import java.util.List;
import java.util.Random;

public class TaskManagementController implements Cloneable{
    private HashTableChaining<String,Activity> hashTableChaining;
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
        action = "";
    }

    // Getter
    public Node<Activity> getTaskQueue() {
        return taskQueue.peekNode();
    }
    public Node<Activity> getReminderQueue() {
        return reminderQueue.peekNode();
    }
    public List<Activity> getHighTasks(){
        return priorityQueueHigh.getHeap();
    }
    public List<Activity> getMediumTasks(){
        return priorityQueueMedium.getHeap();
    }
    public List<Activity> getLowTasks(){
        return priorityQueueLow.getHeap();
    }

    // Add, edit and remove
    private void addActivity(String key, Activity newAct){
        hashTableChaining.add(key,newAct);
        if(newAct instanceof Task){
            Task task = (Task) newAct;
            if(task.isPriority()){
                priorityQueueAdd(task);
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
        Activity existingActivity = hashTableChaining.get(key);

        if (existingActivity == null) {
            return "Activity not found with key: " + key;
        }

        existingActivity.setTittle(newAct.getTittle());
        existingActivity.setDescription(newAct.getDescription());
        existingActivity.setDate(newAct.getDate());

        if (existingActivity instanceof Task) {
            Task existingTask = (Task) existingActivity;
            if (existingTask.isPriority()) {
                if (existingTask.getPriorityLevel() == PriorityLevel.LOW) {
                    priorityQueueLow.remove(existingTask);
                } else if (existingTask.getPriorityLevel() == PriorityLevel.MEDIUM) {
                    priorityQueueMedium.remove(existingTask);
                } else {
                    priorityQueueHigh.remove(existingTask);
                }

                priorityQueueAdd(existingTask);
            }
        }

        return "Your activity has been successfully modified";
    }


    public String removeActivity(String key){
        remove(key);
        hashTableChaining.remove(key);
        return "Your activity has been successfully removed";
    }

    public void remove(String key) {
        Activity act = hashTableChaining.get(key);
        if (act instanceof Task) {
            Task task = (Task) act;
            if (task.getPriorityLevel() == PriorityLevel.NON_PRIORITY) {
                taskQueue.remove(act);
            } else if (task.getPriorityLevel() == PriorityLevel.LOW) {
                priorityQueueLow.remove(act);
            } else if (task.getPriorityLevel() == PriorityLevel.MEDIUM) {
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
        if(!hashTableChaining.containsKey(code)){
            return code;
        } else {
            keyCreator();
        }
        return code;
    }

    public void priorityQueueAdd(Task task){
        PriorityLevel pl = task.getPriorityLevel();
        if(pl ==PriorityLevel.HIGH){
            priorityQueueHigh.enqueue(task);
        }else if(pl == PriorityLevel.MEDIUM){
            priorityQueueMedium.enqueue(task);
        }else{
            priorityQueueLow.enqueue(task);
        }
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

        HashNode<String, Activity>[] array = clon.hashTableChaining.getArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                HashNode<String, Activity> current = array[i];
                while (current!=null) {
                    Activity act = current.getValue();
                    if(act instanceof Task&&((Task) act).isPriority()){
                        current.setValue(current.getValue().clone());
                        act = current.getValue();
                        if(((Task) act).getPriorityLevel()==PriorityLevel.LOW){
                            clon.priorityQueueLow.enqueue(act);
                        } else if(((Task) act).getPriorityLevel()==PriorityLevel.MEDIUM){
                            clon.priorityQueueMedium.enqueue(act);
                        } else {
                            clon.priorityQueueHigh.enqueue(act);
                        }
                    }
                    current = current.getNext();
                }
            }
        }
        cloneReminderQueue(clon);
        cloneNoPriorityTaskQueue(clon);
        return clon;
    }
    private void cloneReminderQueue(TaskManagementController clon){
        clon.reminderQueue.clone();
        Node<Activity> currentNode= reminderQueue.peekNode();
        while(currentNode!=null){
            Activity act = currentNode.getData();
            act.clone();
            clon.hashTableChaining.set(getKey(act),act);
            clon.reminderQueue.offer(act);
            currentNode=currentNode.getNext();
        }
    }
    private void cloneNoPriorityTaskQueue(TaskManagementController clon){
        clon.taskQueue.clone();
        Node<Activity> currentNode= taskQueue.peekNode();
        while(currentNode!=null){
            Activity act = currentNode.getData();
            act.clone();
            clon.hashTableChaining.set(getKey(act),act);
            clon.taskQueue.offer(act);
            currentNode=currentNode.getNext();
        }
    }
    public void setAction(String action) {
        this.action = action;
    }
    public Activity getSomething(){
        return hashTableChaining.get(hashTableChaining.getAllKeys().get(0));
    }
    public PriorityQueue<Activity> getPriorityQueueHigh() {
        return priorityQueueHigh;
    }
}