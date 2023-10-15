package structure.Queue;

import structure.Nodes.Node;

public class Queue<E> implements IQueue<E>, Cloneable{
    private Node<E> front;
    private Node<E> back;
    private int size;

    public Queue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public E peek(){
        if(isEmpty()){
            return null;
        }

        return front.getData();
    }

    public Node<E> peekNode(){
        if(isEmpty()){
            return null;
        }

        return front;
    }

    @Override
    public E poll(){
        if(isEmpty()){
            return null;
        }

        E head = front.getData();
        front = front.getNext();
        size--;
        return head;
    }

    @Override
    public void offer(E item) {
        Node<E> node = new Node<>(item);
        if (isEmpty()){
            front = node;
            back = node;
        } else {
            back.setNext(node);
            back = node;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public String print() {
        StringBuilder result = new StringBuilder();
        Node<E> current = front;
        while (current != null) {
            result.append(current.getData());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }
        return result.toString();
    }

    public void remove(E item) {
        Node<E> current = front;
        Node<E> prev = null;

        while (current != null) {
            if (current.getData().equals(item)) {
                if (prev == null) {
                    front = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                    if (current.getNext() == null) {
                        back = prev;
                    }
                }
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }
    public void set(E element){
        Node<E> current = this.front;
        while (current != null) {
            if(current.getData()==element){
                current.setData(element);
            }
            current=current.getNext();
        }
    }

    @Override
    public Queue<E> clone() {
        try {
            Queue<E> clone = (Queue<E>) super.clone();

            clone.front = clone.back = null;
            Node<E> current = this.front;
            Node<E> prevCloneNode = null;

            while (current != null) {
                Node<E> clonedNode = current.clone();
                if (clone.front == null) {
                    clone.front = clonedNode;
                } else {
                    prevCloneNode.setNext(clonedNode);
                }
                clone.back = clonedNode;

                prevCloneNode = clonedNode;
                current = current.getNext();
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean contains(E existingAct) {
        Node<E> current = front;
        while (current != null) {
            if (current.getData().equals(existingAct)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}