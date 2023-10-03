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
    private void newController(){
        currentController=currentController.clone();
        stack.push(currentController);
    }
    private void undoAction(){
        currentController=stack.pop();
    }
    public void addActivity(){
        newController();
        currentController.activityAdd("hola","hola",Calendar.getInstance(),Calendar.getInstance());
        currentController.exist();
        System.out.println("\n\n\n");
        newController();
        currentController.activityAdd("hola","hola",Calendar.getInstance(),Calendar.getInstance());
        currentController.activityAdd("hola","hola",Calendar.getInstance(),Calendar.getInstance());
        currentController.exist();
        undoAction();
        System.out.println("\n\n\n");
        currentController.exist();
    }

}
