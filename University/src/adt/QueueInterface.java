package adt;

import java.util.Iterator;

/**
 *
 * @author Phua Mei Kee
 */
public interface QueueInterface<T> {

   
    public void enqueue(T newEntry);

   
    public T dequeue();

    
    public T getFront();

    
    public boolean isEmpty();

  
    public void clear();
} 
