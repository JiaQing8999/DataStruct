/*
 * @author sowyichin
 */
package adt;

public class DoublyLinkedlList<T> implements ListInterface<T> {

    private Node head;  //reference to first node in the list
    private Node tail;  //reference to last node in the list
    private int numberOfEntries;    //number of nodes in the list
    private int index;

    public DoublyLinkedlList() {
    }

    @Override
    public boolean add(T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T remove(int givenPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {   //clear the doubly linked list
        head = null;
        tail = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T getEntry(int givenPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(T anEntry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfEntries() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;    //true if numberOfEntries=0(empty);false=nonempty
    }

    @Override
    public boolean isFull() {
        return false;   //doubly linked list is impossible become full, so reurn false
    }

    private class Node {

        private Node prev;  //reference to the previous node
        private T data;
        private Node next;  //reference to the next node

        public Node(T data) {
            this.data = data;
        }

        public Node(Node prev, T data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}
