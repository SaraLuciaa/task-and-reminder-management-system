package model;

import java.util.Arrays;
import java.util.Calendar;

public class VersionController{
    private TaskManagementController currentController;
    private TaskManagementController[] controllers;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public VersionController(){
        controllers = new TaskManagementController[DEFAULT_CAPACITY];
        size=0;
        currentController=new TaskManagementController(10);
        push(currentController);
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
        currentController=currentController.clone();
        push(currentController);
    }
    public void addActivity(){
        newController();
        currentController.activityAdd("hola","hola",Calendar.getInstance(),Calendar.getInstance());
    }
    public void check1(){
        currentController.exist();
        pop();
        System.out.println("\n\n\n");
        currentController.exist();
        System.out.println("\n\n\n");
        addActivity();
        currentController.exist();
        System.out.println("\n\n\n");
        addActivity();
        currentController.exist();
        System.out.println("\n\n\n");
        pop();
        currentController.exist();

    }
}
