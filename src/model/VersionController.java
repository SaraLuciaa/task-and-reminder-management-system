package model;

import structure.Nodes.Node;
import structure.Stack.Stack;

import java.util.Calendar;

public class VersionController{
    private TaskManagementController currentController;
    private Stack<TaskManagementController> stack;

    public VersionController(){
        stack = new Stack<>();
        currentController = new TaskManagementController(10);
        stack.push(currentController);
    }

    private void newController(String action){
        currentController = currentController.clone();
        currentController.setAction(action);
        stack.push(currentController);
    }
    public void undoAction(){
        currentController=stack.pop();
    }

    public String addActivity(String title, String description, Calendar date, Boolean isPriority, String priorityLevel){
        newController("Add task");
        System.out.println(priorityLevel);

        int priorityL = -1;
        switch (priorityLevel){
            case "high" -> priorityL = 1;
            case "medium" -> priorityL = 2;
            case "low" -> priorityL = 3;
            default -> priorityL = 0;
        }

        Task newAct = new Task(title,description,date,isPriority, priorityL);

        return currentController.addActivity(newAct);
    }

    public String addActivity(String title, String description, Calendar date){
        newController("Add reminder");

        Reminder newAct = new Reminder(title,description,date);

        return currentController.addActivity(newAct);
    }

    public void proofActivities(){
        Calendar date = Calendar.getInstance();
        Task act1 = new Task("p1", "lele1", date, false, 0);
        Task act2 = new Task("p2", "lele1", date, false, 0);
        Task act3 = new Task("p3", "lele1", date, false, 0);
        Task act4 = new Task("p4", "lele1", date, false, 0);
        currentController.addActivity(act1);
        currentController.addActivity(act2);
        currentController.addActivity(act3);
        currentController.addActivity(act4);

        Reminder act5 = new Reminder("p5", "lele1", date);
        Reminder act6 = new Reminder("p6", "lele1", date);
        Reminder act7 = new Reminder("p7", "lele1", date);
        Reminder act8 = new Reminder("p8", "lele1", date);
        currentController.addActivity(act5);
        currentController.addActivity(act6);
        currentController.addActivity(act7);
        currentController.addActivity(act8);
    }

    public void modifyActivity(String title){
        newController("Modify element");

        currentController.setSomething(title);
    }

    /*public HashNode<String, Activity>[] getArray(){
        return currentController.getArray();
    }

    public PriorityQueue<Activity> getPriorityQueueLow() {
        return currentController.getPriorityQueueLow();
    }

    public PriorityQueue<Activity> getPriorityQueueMedium() {
        return currentController.getPriorityQueueMedium();
    }

    public PriorityQueue<Activity> getPriorityQueueHigh() {
        return currentController.getPriorityQueueHigh();
    }

    public Queue<Activity> getTaskQueue() {
        return currentController.getTaskQueue();
    }*/

    public Node<Activity> getTaskQueue() {
        return currentController.getTaskQueue();
    }

    public Node<Activity> getReminderQueue() {
        return currentController.getReminderQueue();
    }

    public void getSomething(){
        currentController.getSomething();
    }
    public void deleteActivity(){
        newController("Delete element");
    }
    public void exist(){
        currentController.exist();
    }
}
