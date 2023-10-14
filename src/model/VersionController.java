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
    public void undoAction() {
        if (stack.size() > 1){
            stack.pop();
            currentController = stack.peek();
        }
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

    public String editActivity(Activity act, String title, String description, Calendar date, Boolean isPriority, String priorityLevel){
        String key = currentController.getKey(act);
        System.out.println(key);
        newController("Edit task");
        int priorityL = -1;
        System.out.println(priorityLevel);
        switch (priorityLevel){
            case "high" -> priorityL = 0;
            case "medium" -> priorityL = 1;
            case "low" -> priorityL = 2;
            default -> priorityL = 3;
        }
        Task newAct = new Task(title,description,date,isPriority, priorityL);
        return currentController.editActivity(newAct, key);
    }

    public String editActivity(Activity act, String title, String description, Calendar date){
        String key = currentController.getKey(act);
        System.out.println(key);
        newController("Edit reminder");
        Reminder newAct = new Reminder(title,description,date);
        return currentController.editActivity(newAct, key);
    }

    public void deleteActivity(Activity act){
        String key = currentController.getKey(act);
        System.out.println(key);
        newController("Delete activity");
        currentController.removeActivity(key);
    }
    public void getSomething(){
        currentController.getSomething();
    }

    public TaskManagementController getCurrentController() {
        return currentController;
    }

    public Stack<TaskManagementController> getStack() {
        return stack;
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
}
