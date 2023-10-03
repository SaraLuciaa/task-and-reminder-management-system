package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class VersionController{
    private TaskManagementController currentController;
    private TaskManagementController[] controllers;
    private int size;
    private int contro=0;
    private static final int DEFAULT_CAPACITY = 10;

    public VersionController() {
        controllers = new TaskManagementController[DEFAULT_CAPACITY];
        size=0;
        newController();
    }

    public void push(TaskManagementController element) {
        if (size == controllers.length) {
            resize();
        }
        controllers[size] = element;
        size++;
    }

    public void pop() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        currentController=controllers[size-2];
        controllers[size - 1] = null;
        contro--;
        size--;
    }

    public TaskManagementController peek() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        return controllers[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = controllers.length * 2;
        controllers = Arrays.copyOf(controllers, newCapacity);
    }
    private void newController(){
        currentController=new TaskManagementController(10,contro);
        push(currentController);
        contro++;
    }
    public void addActivity(){
        currentController.activityAdd("hola","hola",Calendar.getInstance(),Calendar.getInstance());
        System.out.println("Se agrego objeto. EN controller: "+currentController.getControllerNum());
        currentController.exist();
        newController();
    }
    public void check1(){
        currentController.exist();
        System.out.println("Se verifica. En controller: "+currentController.getControllerNum());
        pop();
        System.out.println("Se hace pop. Se hace pop del controller "+(currentController.getControllerNum()+1));
        currentController.exist();
        addActivity();
        currentController.exist();
        System.out.println("Se verifica. En controller: "+currentController.getControllerNum());
    }
}
