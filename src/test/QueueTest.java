package test;

import junit.framework.TestCase;
import structure.Queue.Queue;
import structure.Queue.QueueException;

public class QueueTest extends TestCase {
    private Queue<Integer> queue;

    public void setUpStage1(){
        queue=new Queue<>();
    }
    public void setUpStage2(){
        queue=new Queue<>();
        queue.offer(1);
    }
    public void setUpStage3(){
        queue=new Queue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
    }
    public void testIsEmpty1(){
        setUpStage1();
        assertTrue(queue.isEmpty());
    }
    public void testIsEmpty2(){
        setUpStage2();
        assertFalse(queue.isEmpty());
    }
    public void testPeek1() throws QueueException {
        setUpStage2();
        assertEquals(Integer.valueOf(1),queue.peek());
    }
    public void testPeek2() throws QueueException {
        setUpStage3();
        assertEquals(Integer.valueOf(1),queue.peek());
    }
    public void testPeek3() throws QueueException{
        setUpStage3();
        queue.poll();
        assertEquals(Integer.valueOf(2),queue.poll());
    }
    public void testOffer1(){
        setUpStage1();
        queue.offer(4);
        assertEquals(1,queue.size());
    }
    public void testOffer2(){
        setUpStage2();
        queue.offer(5);
        queue.offer(6);
        assertEquals(3,queue.size());
    }
    public void testPoll1() throws QueueException {
        setUpStage2();
        queue.poll();
        assertEquals(0,queue.size());
    }
    public void testPoll2() throws QueueException {
        setUpStage3();
        queue.poll();
        assertEquals(2,queue.size());
    }
    public void testPoll3() throws QueueException{
        setUpStage3();
        queue.poll();
        queue.offer(4);
        assertEquals(3,queue.size());
    }
}
