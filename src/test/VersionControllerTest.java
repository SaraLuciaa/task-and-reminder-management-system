package test;

import junit.framework.TestCase;

public class VersionControllerTest extends TestCase{

    /*
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
        // versionController.modifyActivity("New Tittle");
        assertEquals(versionController.getCurrentController(), versionController.getStack().peek());
        assertEquals(3,versionController.getStack().size());
    }
    public void testNewController5(){
        setUpStage2();
        versionController.deleteActivity();
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
        // versionController.modifyActivity("New Tittle");
        versionController.undoAction();
        assertEquals(this.managementController,versionController.getCurrentController());
    }
    public void testUndoAction5(){
        setUpStage3();
        versionController.deleteActivity();
        versionController.undoAction();
        assertEquals(this.managementController,versionController.getCurrentController());
    }
    public void testNotSameObjects1(){
        setUpStage2();
        versionController.addActivity("reminder 2","We are adding a reminder", Calendar.getInstance());
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
    }
    public void testNotSameObjects2() throws QueueException {
        setUpStage2();
        // versionController.modifyActivity("New tittle");
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        // assertNotSame(this.managementController.getReminderQueue().peek(),versionController.getCurrentController().getReminderQueue().peek());
    }
    public void testNotSameObjects3(){
        setUpStage3();
        // versionController.modifyActivity("New tittle");
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        assertNotSame(this.managementController.getPriorityQueueHigh().peek(),versionController.getCurrentController().getPriorityQueueHigh().peek());
    }
    public void testNotSameObjects4(){
        setUpStage3();
        // versionController.modifyActivity("New tittle");
        assertNotSame(this.managementController.getSomething(),versionController.getCurrentController().getSomething());
        assertNotSame(this.managementController.getPriorityQueueHigh().peek().getTittle(),versionController.getCurrentController().getPriorityQueueHigh().peek().getTittle());
    }
    public void testNotSameObjects5() throws QueueException {
        setUpStage2();
        // versionController.modifyActivity("New tittle");
        versionController.undoAction();
        // assertEquals(this.managementController.getReminderQueue().peek(),versionController.getCurrentController().getReminderQueue().peek());
    }*/
}
