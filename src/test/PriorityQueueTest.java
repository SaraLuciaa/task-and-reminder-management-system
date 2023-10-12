package test;

import junit.framework.TestCase;
import structure.Queue.PriorityQueue;
import model.Task;

import java.util.Calendar;

public class PriorityQueueTest extends TestCase{
    private PriorityQueue<Task> priorityQueue;
    private Task task;
    public long calculatePriority(Calendar date){
        Calendar now = Calendar.getInstance();
        return (date.getTimeInMillis() - now.getTimeInMillis()) / 1000;
    }
    public void setUpStage1(){
        priorityQueue=new PriorityQueue<>();
    }
    public void setUpStage2(){
        priorityQueue=new PriorityQueue<>();
        Calendar date=Calendar.getInstance();
        date.set(2023,Calendar.DECEMBER,20,12,0);
        Task task=new Task("Task 1", "We are adding a task", date,true,0);
        this.task=task;
        priorityQueue.enqueue(task,calculatePriority(date));
    }
    public void setUpStage3(){
        priorityQueue=new PriorityQueue<>();
        Calendar date=Calendar.getInstance();
        date.set(2023,Calendar.DECEMBER,13,12,0);
        Task task=new Task("Task 1", "We are adding a task", date,true,0);
        priorityQueue.enqueue(task,calculatePriority(date));
        priorityQueue.dequeue();
    }
    public void setUpStage4(){
        priorityQueue=new PriorityQueue<>();
        Calendar date=Calendar.getInstance();
        date.set(2023,Calendar.DECEMBER,13,0,0);
        Task task=new Task("Task 1", "We are adding a task", date,true,0);
        priorityQueue.enqueue(task,calculatePriority(date));
        date.set(2024,Calendar.DECEMBER,13,0,0);
        Task task2=new Task("Task 2", "We are adding a task", date,true,0);
        this.task=task;
        priorityQueue.enqueue(task2,calculatePriority(date));
    }
    public void testIsEmpty1(){
        setUpStage1();
        assertTrue(priorityQueue.isEmpty());
    }
    public void testIsEmpty2(){
        setUpStage2();
        assertFalse(priorityQueue.isEmpty());
    }
    public void testIsEmpty3(){
        setUpStage3();
        assertTrue(priorityQueue.isEmpty());
    }
    public void testEnqueue1(){
        setUpStage1();
        Task task=new Task("Task 2", "We are adding a task", Calendar.getInstance(),true,0);
        priorityQueue.enqueue(task,calculatePriority(Calendar.getInstance()));
        assertEquals(1,priorityQueue.size());
    }
    public void testEnqueue2(){
        setUpStage2();
        Task task=new Task("Task 3", "We are adding a task", Calendar.getInstance(),true,0);
        priorityQueue.enqueue(task,calculatePriority(Calendar.getInstance()));
        assertEquals(2,priorityQueue.size());
    }
    public void testEnqueue3(){
        setUpStage3();
        Task task=new Task("Task 4", "We are adding a task", Calendar.getInstance(),true,0);
        priorityQueue.enqueue(task,calculatePriority(Calendar.getInstance()));
        assertEquals(1,priorityQueue.size());
    }
    public void testDequeue1(){
        setUpStage2();
        priorityQueue.dequeue();
        assertEquals(0,priorityQueue.size());
    }
    public void testDequeue2(){
        setUpStage4();
        priorityQueue.dequeue();
        assertEquals(1,priorityQueue.size());
    }
    public void testPeek1(){
        setUpStage2();
        assertEquals(task,priorityQueue.peek());
    }
    public void testPeek2(){
        setUpStage4();
        assertEquals(task,priorityQueue.peek());
    }
    public void testPeek3(){
        setUpStage4();
        Calendar date=Calendar.getInstance();
        date.set(2023,Calendar.NOVEMBER,13,0,0);
        Task task=new Task("Task 6", "We are adding a task", date,true,0);
        priorityQueue.enqueue(task,calculatePriority(date));
        assertEquals(task,priorityQueue.peek());
    }
}
