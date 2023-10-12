package model;

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

    public void modifyActivity(String title){
        newController("Modify element");

        currentController.setSomething(title);
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
