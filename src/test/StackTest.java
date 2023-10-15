package test;

import junit.framework.TestCase;
import structure.Stack.Stack;

public class StackTest extends TestCase {
    private Stack<Integer> stack;

    public void setUpStage1(){
        stack=new Stack<>();
    }
    public void setUpStage2(){
        stack=new Stack<>();
        stack.push(1);
    }
    public void setUpStage3(){
        stack=new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }
    public void testIsEmpty1(){
        setUpStage1();
        assertTrue(stack.isEmpty());
    }
    public void testIsEmpty2(){
        setUpStage2();
        assertFalse(stack.isEmpty());
    }
    public void testIsEmpty3(){
        setUpStage2();
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    public void testSize1(){
        setUpStage3();
        assertEquals(3,stack.size());
    }
    public void testSize2(){
        setUpStage3();
        stack.pop();
        assertEquals(2,stack.size());
    }
    public void testSize3(){
        setUpStage2();
        stack.pop();
        assertEquals(0,stack.size());
    }
    public void testPush1(){
        setUpStage1();
        stack.push(5);
        assertEquals(1,stack.size());
    }
    public void testPush2(){
        setUpStage3();
        assertEquals(3,stack.size());
    }
    public void testPush3(){
        setUpStage2();
        stack.pop();
        stack.push(7);
        assertEquals(1,stack.size());
    }
    public void testPop1(){
        setUpStage2();
        stack.pop();
        assertEquals(0, stack.size());
    }
    public void testPop2(){
        setUpStage3();
        stack.pop();
        assertEquals(2,stack.size());
    }
    public void testPop3(){
        setUpStage1();
        assertNull(stack.pop());
    }
    public void testPeek1(){
        setUpStage2();
        assertEquals(Integer.valueOf(1),stack.peek());
    }
    public void testPeek2(){
        setUpStage3();
        stack.pop();
        assertEquals(Integer.valueOf(3),stack.peek());
    }
    public void testPeek3(){
        setUpStage1();
        assertNull(stack.peek());
    }
}
