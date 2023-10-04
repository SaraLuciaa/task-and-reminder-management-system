package ui;
import model.VersionController;

import java.time.Month;
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
        addActivity();
    }
    public void addActivity(){
        Calendar fecha=Calendar.getInstance();
        fecha.set(2023, Calendar.NOVEMBER,30,20,0,0);;
        versionController.addActivity("Pendiente1","El pendiente 1",fecha);
        versionController.exist();
        fecha.set(2023, Calendar.NOVEMBER,10,20,0,0);
        versionController.addActivity("tarea1","La tare 1",fecha,true,1);
        versionController.exist();
        fecha.set(2023, Calendar.NOVEMBER,1,20,0,0);
        versionController.addActivity("tarea2","La tare 2",fecha,true,1);
        versionController.exist();
        fecha.set(2023, Calendar.NOVEMBER,10,20,0,0);
        versionController.addActivity("tarea3","La tare 1",fecha,false,0);
        versionController.exist();
        fecha.set(2023, Calendar.NOVEMBER,24,20,0,0);
        versionController.addActivity("tarea4","La tare 1",fecha,false,0);
        versionController.exist();
        fecha.set(2023, Calendar.DECEMBER,30,20,0,0);;
        versionController.addActivity("Pendiente2","El pendiente 2",fecha);
        versionController.exist();
    }
}