package model;

import structure.Nodes.Node;
import structure.Queue.Entry;
import structure.Stack.Stack;

import java.util.Calendar;
import java.util.List;

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
        int priorityL = -1;
        System.out.println(priorityLevel);
        switch (priorityLevel){
            case "high" -> priorityL = 0;
            case "medium" -> priorityL = 1;
            case "low" -> priorityL = 2;
            default -> priorityL = 3;
        }
        Task newAct = new Task(title,description,date,isPriority, priorityL);
        return currentController.addActivity(newAct);
    }

    public String addActivity(String title, String description, Calendar date){
        newController("Add reminder");
        Reminder newAct = new Reminder(title,description,date);
        return currentController.addActivity(newAct);
    }

    public void modifyActivity(String title){
        newController("Modify element");

        currentController.setSomething(title);
    }

    public Node<Activity> getTaskQueue() {
        return currentController.getTaskQueue();
    }

    public Node<Activity> getReminderQueue() {
        return currentController.getReminderQueue();
    }

    public List<Entry<Activity>> getHighTasks() { return currentController.getHighTasks(); }

    public List<Entry<Activity>> getMediumTasks() { return currentController.getMediumTasks(); }

    public List<Entry<Activity>> getLowTasks() { return currentController.getLowTasks(); }

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
