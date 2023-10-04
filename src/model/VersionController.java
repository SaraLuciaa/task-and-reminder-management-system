package model;

import structure.Stack.Stack;

import java.time.LocalDate;
import java.util.Calendar;

public class VersionController{
    private TaskManagementController currentController;
    private Stack<TaskManagementController> stack;

    public VersionController(){
        stack=new Stack<>();
        currentController=new TaskManagementController(10);
        stack.push(currentController);
    }
    private void newController(String action){
        currentController=currentController.clone();
        currentController.setAction(action);
        stack.push(currentController);
    }
    private void undoAction(){
        currentController=stack.pop();
    }

    public String addActivity(String title, String description, LocalDate date, Boolean isTask, Boolean isPriority, String priorityLevel){
        System.out.println(currentController);
        newController("Add task");
        System.out.println(currentController);
        System.out.println(priorityLevel);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, date.getYear());
        calendar.set(Calendar.MONTH, date.getMonthValue() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());

        int priorityL = -1;
        switch (priorityLevel){
            case "high" -> priorityL = 1;
            case "medium" -> priorityL = 2;
            case "low" -> priorityL = 3;
            default -> priorityL = 0;
        }

        Activity newAct;
        if(isTask){
            newAct = new Task(title,description,calendar,isPriority, priorityL);
        } else {
            newAct = new Reminder(title,description,calendar);
        }

        return currentController.addActivity(newAct);
    }

    public void modifyActivity(){
        newController("Modify element");
    }
    public void deleteActivity(){
        newController("Delete element");
    }
    public void exist(){
        currentController.exist();
    }
}
