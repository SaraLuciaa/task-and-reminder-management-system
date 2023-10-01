package model;

public class Stack<E> implements IStack<E> {
    private Node<E> top;

    public Stack() {
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(E data){
        Node<E> item = new Node<>(data);
        item.setNext(top);
        top = item;
    }

    @Override
    public E peek() throws StackException{
        if (isEmpty()) {
            throw new StackException("Stack is empty. Peek");
        }

        return top.getData();
    }

    @Override
    public E pop() throws StackException{
        if (isEmpty()) {
            throw new StackException("Stack is empty. Pop");
        }

        E data = top.getData();
        top = top.getNext();
        return data;
    }

    public String print(){
        return print(top);
    }

    private String print(Node<E> current){
        String str = "";
        if (this.top == null){
            str = "Empty stack";
        } else if (current.getNext()==null){
            str += current.getData();
        } else {
            str += current.getData() + " " + print(current.getNext());
        }
        return str;
    }
}
