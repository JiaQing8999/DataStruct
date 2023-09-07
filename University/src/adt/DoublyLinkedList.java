/*
 * @author Sow Yi Chin
 */
package adt;

import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> implements ListInterface<T> {

    private Node head;  //reference to first node in the list
    private Node tail;  //reference to last node in the list
    private int numberOfEntries;    //number of nodes in the list

    //constructor
    public DoublyLinkedList() {
        clear();
    }

    @Override
    public boolean add(T newEntry) {

        Node newNode = new Node(newEntry);  //create a new node

        if (newEntry != null) {
            if (isEmpty()) {
                head = tail = newNode;  //set head & tail point to new node
                newNode.prev = newNode.next = null;
            } else {    //add the new node to the end of list
                newNode.prev = tail;    //set new node prev point to tail
                tail.next = newNode;    //set tail next point to new node
                tail = newNode;     //update the tail to new node
                tail.next = null;
            }
            numberOfEntries++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(int givenPosition) {

        //case 1: If the list is empty or out of bounds, return false
        if (isEmpty() || givenPosition < 1 || givenPosition > getNumberOfEntries()) {
            return false;
        } else if (givenPosition == 1) {   //case 2: If the index is 1,
            head = head.next;   //update head point to second node
            if (head != null) {     //if has second node
                head.prev = null;
            } else {    //if no second node, the list is now empty, so update head & tail 
                head = tail = null;
            }
            numberOfEntries--;
            return true;
        } else if (givenPosition < getNumberOfEntries()) {    //case 3: the index is in the middle 
            Node currentNode = head;    //set the currentNode has same reference to head
            for (int i = 1; i < givenPosition; i++) {
                currentNode = currentNode.next;     //get the node
            }
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            numberOfEntries--;
            return true;
        } else {
            //case 4: the index is the last 
            tail = tail.prev;   //update tail to last 2nd node 
            tail.next = null;
            numberOfEntries--;
            return true;
        }
    }

    @Override
    public T remove(T anEntry) {
        // Handle the case where the list is empty
        if (isEmpty()) {
            return null; // List is empty, no entry to remove
        }

        // Initialize a currentNode to traverse the list
        Node currentNode = head;

        // Traverse the list to find the first occurrence of the given entry
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                // Found the entry, now remove it
                if (currentNode == head) {
                    // If the entry is at the head, update the head
                    head = currentNode.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        // If there's no more elements, update tail as well
                        tail = null;
                    }
                } else if (currentNode == tail) {
                    // If the entry is at the tail, update the tail
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    // If the entry is in the middle of the list
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                }

                // Decrement the number of entries and return the removed entry
                numberOfEntries--;
                return currentNode.data;
            }
            currentNode = currentNode.next;
        }

        // The entry was not found in the list
        return null;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {

        if (isEmpty() || givenPosition < 1 || givenPosition > numberOfEntries) {
            return false;
        } else {
            Node currentNode = head;    //set the currentNode has same reference to head
            for (int i = 1; i < givenPosition; i++) {
                currentNode = currentNode.next;
            }
            currentNode.data = newEntry;
            return true;
        }
    }

    @Override
    public T getEntry(int givenPosition) {

        Node currentNode = head;    //set the currentNode has same reference to head
        int currentPosition = 1;

        // Traverse the list to find the element at the given position
        while (currentNode != null) {
            if (currentPosition == givenPosition) {
                return currentNode.data;    // return the data
            }
            currentNode = currentNode.next;     //move to next node
            currentPosition++;
        }
        return null;
    }

    @Override
    public boolean contains(T anEntry) {

        Node currentNode = head;   //set the currentNode has same reference to head

        //Traverse the list
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return true;
            }
            currentNode = currentNode.next;     //move to next node
        }
        return false;
    }

    @Override
    public int indexOf(T anEntry) {

        Node currentNode = head;    //set the currentNode has same reference to head
        int index = 1;

        // Traverse the list
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return index;  // Return the index when a match is found
            }
            currentNode = currentNode.next;     //move to next node
            index++;
        }
        return -1;  //return -1 if the element is not found
    }

    @Override
    public void clear() {   //clear the list to become empty
        head = tail = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;    //return true if numberOfEntries is zero(empty),else false 
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public Iterator<T> getIterator() {
        return new DoublyLinkedIterator();
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = head;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    //------------------Node class------------------
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

    //------------------Iterator------------------
    private class DoublyLinkedIterator implements Iterator<T> {

        private Node currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T currentElement = null;
            if (hasNext()) {
                currentElement = currentNode.data;
                currentNode = currentNode.next;
            }
            return currentElement;
        }
    }
}
