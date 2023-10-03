package ui;
import model.TaskManagementController;
import model.VersionController;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    private VersionController versionController;
    public Main(){
        versionController=new VersionController();
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
        addActivity();
    }
    public void addActivity(){
        versionController.addActivity();
    }
}