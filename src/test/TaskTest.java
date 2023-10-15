package test;

import junit.framework.TestCase;
import model.PriorityLevel;
import model.Task;
import java.util.Calendar;

public class TaskTest extends TestCase{

    private Task task;
    private Calendar date;
    public void setUpStage1(){
        Calendar date= Calendar.getInstance();
        date.set(2023,Calendar.DECEMBER,20);
        this.date=date;
        task=new Task("Task 1","We are adding a new task object",date,true,0);
    }
    public void testCreateTask1() {
        setUpStage1();
        assertEquals("Task 1",task.getTittle());
    }
    public void testCreateTask2(){
        setUpStage1();
        assertEquals("We are adding a new task object",task.getDescription());
    }
    public void testCreateTask3(){
        setUpStage1();
        assertEquals(this.date,task.getDate());
    }
    public void testCreateTask4(){
        setUpStage1();
        assertTrue(task.isPriority());
    }
    public void testCreateTask5(){
        setUpStage1();
        assertEquals(PriorityLevel.HIGH,task.getPriorityLevel());
    }
}