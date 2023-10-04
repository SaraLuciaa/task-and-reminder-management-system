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
        Calendar fecha = Calendar.getInstance();
        fecha.set(2023, Calendar.OCTOBER, 15, 12, 0, 0);
        currentController.activityAdd("goa","jos",fecha,true,1);
        fecha.set(2023, Calendar.OCTOBER, 15, 14, 30, 0);
        currentController.activityAdd("goa","jos",fecha,true,1);
        fecha.set(2023, Calendar.OCTOBER, 4, 14, 30, 0);
        currentController.activityAdd("goa","jos",fecha,true,1);
        fecha.set(2023, Calendar.OCTOBER, 30, 14, 30, 0);
        currentController.activityAdd("goa","jos",fecha,true,1);
    }
    public void modifyActivity(){
        newController();
    }
    public void deleteActivity(){
        newController();
    }

}
