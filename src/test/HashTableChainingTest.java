package test;

import junit.framework.TestCase;
import model.Reminder;
import model.Activity;
import model.Task;
import structure.HashTable.HashTableChaining;

import java.util.Calendar;

public class HashTableChainingTest extends TestCase {
    private HashTableChaining<String, Activity> hashTable;
    private Task task;
    private Reminder reminder;
    public void setUpStage1(){
        hashTable=new HashTableChaining<>(10);
    }
    public void setUpStage2(){
        hashTable=new HashTableChaining<>(10);
        Reminder reminder=new Reminder("Reminder 1", "We are adding a reminder", Calendar.getInstance());
        this.reminder=reminder;
        hashTable.add("asdw23",reminder);
        Task task=new Task("Task 1", "We are adding a task", Calendar.getInstance(),true,0);
        this.task=task;
        hashTable.add("sadw24",task);
    }
    public void setUpStage3(){
        hashTable=new HashTableChaining<>(10);
        Reminder reminder=new Reminder("Reminder 2", "We are adding a reminder", Calendar.getInstance());
        this.reminder=reminder;
        hashTable.add("asdw23",reminder);
        Task task=new Task("Task 2", "We are adding a task", Calendar.getInstance(),true,1);
        hashTable.add("asdw23",task);
        Task task2=new Task("Task 3", "We are adding a task", Calendar.getInstance(),true,2);
        this.task=task2;
        hashTable.add("sadw24",task2);
        Reminder reminder2=new Reminder("Reminder 3", "We are adding a reminder", Calendar.getInstance());
        hashTable.add("sadw24",reminder2);
    }
    public void setUpStage4(){
        hashTable=new HashTableChaining<>(10);
        Reminder reminder1=new Reminder("Reminder 4", "We are adding a reminder", Calendar.getInstance());
        this.reminder=reminder1;
        Reminder reminder2=new Reminder("Reminder 5", "We are adding a reminder", Calendar.getInstance());
        Reminder reminder3=new Reminder("Reminder 6", "We are adding a reminder", Calendar.getInstance());
        hashTable.add("jua23",reminder1);
        hashTable.add("jua23",reminder2);
        hashTable.add("jua23",reminder3);
    }
    public void setUpStage5(){
        hashTable=new HashTableChaining<>(10);
        Reminder reminder=new Reminder("Reminder 7", "We are adding a reminder", Calendar.getInstance());
        this.reminder=reminder;
        Task task=new Task("Task 4", "We are adding a task", Calendar.getInstance(),true,2);
        this.task=task;
        hashTable.add("code12",reminder);
        hashTable.add("code123",task);
    }
    public void testHashKey1(){
        setUpStage1();
        assertEquals(Math.abs("asdw23".hashCode() % 10),hashTable.hash("asdw23"));
    }
    public void testHashKey2(){
        setUpStage1();
        assertEquals(Math.abs("sadw24".hashCode() % 10),hashTable.hash("sadw24"));
    }
    public void testAddHashTable1(){
        setUpStage2();
        int key=Math.abs("asdw23".hashCode() % 10);
        assertEquals(reminder,hashTable.getArray()[key].getValue());
    }
    public void testAddHashTable2(){
        setUpStage2();
        int key=Math.abs("sadw24".hashCode() % 10);
        assertEquals(task,hashTable.getArray()[key].getValue());
    }
    public void testAddHashTable3(){
        setUpStage1();
        Reminder reminder1=new Reminder("Reminder 4", "We are adding a reminder", Calendar.getInstance());
        hashTable.add("loi23",reminder1);
        assertTrue(hashTable.containsKey("loi23"));
    }
    public void testAddHashTable4(){
        setUpStage3();
        Reminder reminder1=new Reminder("Reminder 6", "We are adding a reminder", Calendar.getInstance());
        hashTable.add("lde23",reminder1);
        assertTrue(hashTable.containsKey("lde23"));
    }
    public void testHashTableChaining1(){
        setUpStage3();
        int key=Math.abs("asdw23".hashCode() % 10);
        assertEquals(reminder,hashTable.getArray()[key].getNext().getValue());
    }
    public void testHashTableChaining2(){
        setUpStage3();
        int key=Math.abs("sadw24".hashCode() % 10);
        assertEquals(task,hashTable.getArray()[key].getNext().getValue());
    }
    public void testHashTableChaining3(){
        setUpStage4();
        int key=Math.abs("jua23".hashCode() % 10);
        assertEquals(reminder,hashTable.getArray()[key].getNext().getNext().getValue());
    }
    public void testGetHash1(){
        setUpStage5();
        assertEquals(reminder,hashTable.get("code12"));
    }
    public void testGetHash2(){
        setUpStage5();
        assertEquals(task,hashTable.get("code123"));
    }
    public void testGetHash3(){
        setUpStage1();
        assertNull(hashTable.get("hji123"));
    }
    public void testContainsKey1(){
        setUpStage4();
        assertTrue(hashTable.containsKey("jua23"));
    }
    public void testContainsKey2(){
        setUpStage1();
        assertFalse(hashTable.containsKey("hel123"));
    }
    public void testContainsKey3(){
        setUpStage5();
        assertTrue(hashTable.containsKey("code12"));
    }
    public void testContainsKey4(){
        setUpStage5();
        assertFalse(hashTable.containsKey("code34"));
    }
    public void testHashRemove1(){
        setUpStage5();
        hashTable.remove("code12");
        assertFalse(hashTable.containsKey("code12"));
    }
    public void testHashRemove2(){
        setUpStage5();
        hashTable.remove("code123");
        assertTrue(hashTable.containsKey("code12"));
    }
    public void testHashRemove3(){
        setUpStage5();
        hashTable.remove("code123");
        assertFalse(hashTable.containsKey("code123"));
    }
    public void testHashRemove4(){
        setUpStage2();
        hashTable.remove("asdw23");
        assertTrue(hashTable.containsKey("sadw24"));
    }
}
