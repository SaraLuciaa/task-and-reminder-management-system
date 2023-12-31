package test;

import junit.framework.TestCase;
import model.TaskManagementController;
import model.VersionController;

import java.util.Calendar;

public class VersionControllerTest extends TestCase{

    private VersionController versionController;
    private TaskManagementController managementController;
    public void setUpStage1(){
        versionController=new VersionController();
        this.managementController=versionController.getCurrentController();
    }
    public void setUpStage2(){
        versionController=new VersionController();
        versionController.addActivity("reminder 1","We are adding a reminder", Calendar.getInstance());
        this.managementController=versionController.getCurrentController();
    }
    public void setUpStage3(){
        versionController=new VersionController();
        versionController.addActivity("Task 1","We are adding a task", Calendar.getInstance(),true,"high");
        this.managementController=versionController.getCurrentController();
    }
    public void testNewController1(){
        setUpStage1();
        assertNotNull(versionController.getCurrentController());
        assertEquals(1,versionController.getStack().size());
    }
    public void testNewController2(){
        setUpStage2();
        assertEquals(versionController.getCurrentController(), versionController.getStack().peek());
        assertEquals(2,versionController.getStack().size());
    }
    public void testNewController3(){
        setUpStage3();
        assertEquals(versionController.getCurrentController(), versionController.getStack().peek());
        assertEquals(2,versionController.getStack().size());
    }
    public void testNewController4(){
        setUpStage2();
        versionController.editActivity(managementController.getReminderQueue().getData(),"reminder 1","We are adding a reminder", Calendar.getInstance());
        assertEquals(versionController.getCurrentController(), versionController.getStack().peek());
        assertEquals(3,versionController.getStack().size());
    }
    public void testNewController5(){
        setUpStage2();
        versionController.deleteActivity(managementController.getReminderQueue().getData());
        assertEquals(versionController.getCurrentController(), versionController.getStack().peek());
        assertEquals(3,versionController.getStack().size());
    }
    public void testUndoAction1(){
        setUpStage1();
        versionController.undoAction();
        assertEquals(this.managementController,versionController.getCurrentController());
    }
    public void testUndoAction2(){
        setUpStage2();
        versionController.undoAction();
        assertNotSame(this.managementController,versionController.getCurrentController());
    }
    public void testUndoAction3(){
        setUpStage3();
        versionController.undoAction();
        assertNotSame(this.managementController,versionController.getCurrentController());
    }
    public void testUndoAction4(){
        setUpStage3();
        versionController.editActivity(managementController.getPriorityQueueHigh().peek(),"Task 1","We are adding a task", Calendar.getInstance(),true,"high");
        versionController.undoAction();
        assertEquals(this.managementController,versionController.getCurrentController());
    }
    public void testUndoAction5(){
        setUpStage3();
        versionController.deleteActivity(managementController.getPriorityQueueHigh().peek());
        versionController.undoAction();
        assertEquals(this.managementController,versionController.getCurrentController());
    }
    public void testNotSameObjects1(){
        setUpStage2();
        versionController.addActivity("reminder 2","We are adding a reminder", Calendar.getInstance());
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
    }
    public void testNotSameObjects2(){
        setUpStage2();
        versionController.editActivity(managementController.getReminderQueue().getData(),"reminder 1","We are adding a reminder", Calendar.getInstance());
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        assertNotSame(this.managementController.getReminderQueue(),versionController.getCurrentController().getReminderQueue());
    }
    public void testNotSameObjects3(){
        setUpStage3();
        versionController.editActivity(managementController.getPriorityQueueHigh().peek(),"Task 1","We are adding a task", Calendar.getInstance(),true,"high");
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        assertNotSame(this.managementController.getPriorityQueueHigh().peek(),versionController.getCurrentController().getPriorityQueueHigh().peek());
    }
    public void testNotSameObjects4(){
        setUpStage3();
        versionController.editActivity(managementController.getPriorityQueueHigh().peek(),"New tittle","We are adding a task", Calendar.getInstance(),true,"high");
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        assertNotSame(this.managementController.getPriorityQueueHigh().peek().getTittle(),versionController.getCurrentController().getPriorityQueueHigh().peek().getTittle());
    }
    public void testNotSameObjects5(){
        setUpStage2();
        versionController.editActivity(managementController.getReminderQueue().getData(),"reminder 1","We are adding a reminder", Calendar.getInstance());
        versionController.undoAction();
        assertEquals(this.managementController.getReminderQueue(),versionController.getCurrentController().getReminderQueue());
    }
    public void testEditActivity1(){
        setUpStage2();
        assertEquals(this.managementController.getReminderQueue().getData().getTittle(),versionController.getReminderQueue().getData().getTittle());
        versionController.editActivity(managementController.getReminderQueue().getData(),"New reminder tittle","We are adding a reminder", Calendar.getInstance());
        assertNotSame(this.managementController.getReminderQueue().getData().getTittle(),versionController.getReminderQueue().getData().getTittle());
    }
    public void testEditActivity2(){
        setUpStage3();
        assertEquals(this.managementController.getPriorityQueueHigh().peek().getTittle(),versionController.getCurrentController().getPriorityQueueHigh().peek().getTittle());
        versionController.editActivity(managementController.getPriorityQueueHigh().peek(),"New tittle","We are adding a task", Calendar.getInstance(),true,"high");
        assertNotSame(this.managementController.getPriorityQueueHigh().peek().getTittle(),versionController.getCurrentController().getPriorityQueueHigh().peek().getTittle());
    }
    public void testDeleteActivity1(){
        setUpStage2();
        versionController.deleteActivity(managementController.getReminderQueue().getData());
        assertNull(versionController.getCurrentController().getReminderQueue());
    }
    public void testDeleteActivity2(){
        setUpStage3();
        versionController.deleteActivity(managementController.getPriorityQueueHigh().peek());
        assertNull(versionController.getCurrentController().getPriorityQueueHigh().peek());
    }
}
