package ui;
import model.TaskManagementController;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    private TaskManagementController taskController;
    public Main(){
        taskController=new TaskManagementController(10);
    }

    public static void main(String[] args) {
        Main manager=new Main();
        manager.menu();
    }

    public void menu(){
        /*boolean status=true;
        Scanner l = new Scanner(System.in);
        int op;
        System.out.println("Welcome to the task management and reminders app");
        while(status) {
            System.out.println("What do you want to do? Enter an option:");
            System.out.println("");
            op = l.nextInt();
            switch (op) {
            }
        }*/

        System.out.println(taskController.activityAdd("Holis","Me gusta las papitas :3",Calendar.getInstance(),Calendar.getInstance()));
        System.out.println(taskController.activityAdd("Holis","Me gusta las papitas :3",Calendar.getInstance(),Calendar.getInstance(),true,1));
    }

}