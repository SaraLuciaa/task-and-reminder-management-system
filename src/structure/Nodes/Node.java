package structure.Nodes;

public class Node<E> implements INode<E>, Cloneable {
    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public Node<E> getNext() {
        return next;
    }

    @Override
    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public Node<E> clone() {
        try {
            Node clone = (Node) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
