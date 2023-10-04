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

    private void newController(){
        currentController = currentController.clone();
        stack.push(currentController);
    }
    private void undoAction(){
        currentController=stack.pop();
    }

    public String addActivity(String title, String description, LocalDate date, Boolean isTask, Boolean isPriority, String priorityLevel){
        System.out.println(currentController);
        newController();
        System.out.println(currentController);
        System.out.println(priorityLevel);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, date.getYear());
        calendar.set(Calendar.MONTH, date.getMonthValue() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());

        int priorityL = -1;
        switch (priorityLevel){
            case "high" -> priorityL = 0;
            case "medium" -> priorityL = 1;
            case "low" -> priorityL = 2;
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
        newController();
    }
    public void deleteActivity(){
        newController();
    }

}
