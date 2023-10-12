package test;

import junit.framework.TestCase;
import model.Reminder;

import java.util.Calendar;


public class ReminderTest extends TestCase{

    private Reminder reminder;
    private Calendar date;
    public void setUpStage1(){
        Calendar date= Calendar.getInstance();
        date.set(2023,Calendar.DECEMBER,20,3,20,30);
        this.date=date;
        reminder=new Reminder("Reminder 1","We are adding a new reminder object",date);
    }
    public void testCreateReminder1() {
        setUpStage1();
        assertEquals("Reminder 1",reminder.getTittle());
    }
    public void testCreateReminder2(){
        setUpStage1();
        assertEquals("We are adding a new reminder object",reminder.getDescription());
    }
    public void testCreateReminder3(){
        setUpStage1();
        assertEquals(date,reminder.getDate());
    }
}
