package model;

import java.util.Arrays;
import java.util.Calendar;

import structure.Stack.Stack;
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
    public String addActivity(String tittle, String description, Calendar date){
        newController("Add reminder");
        return currentController.activityAdd(tittle,description,date);
    }
    public String addActivity(String tittle, String description, Calendar date,boolean isPriority, int priorityLevel){
        newController("Add task");
        return currentController.activityAdd(tittle,description,date,isPriority,priorityLevel);
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
