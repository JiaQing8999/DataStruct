package adt;

/**
 *
 * @author 
 */
public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public String toString() {
        String str = "";
        Node currentNode = topNode;
        while (currentNode != null) {
            str += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return str;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = topNode;
        topNode = newNode;
        //alternative way
        //topNode = new Node(newEntry, topNode);
    }

    @Override
    public T pop() {
        T topElement = null;
        if (!isEmpty()) {
            topElement = topNode.data;
            topNode = topNode.next;
        }
        return topElement;
    }

    @Override
    public T peek() {
        T topElement = null;
        if (!isEmpty()) {
            topElement = topNode.data;
        }
        return topElement;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    private class Node {

        T data;
        Node next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

    }
}
