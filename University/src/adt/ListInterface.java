package adt;

import java.util.Iterator;

/**
 *
 * @author Lim Jia Qing
 */
public interface ListInterface<T> {
    
    public Iterator<T> getIterator();

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public boolean remove(int givenPosition);

    public void clear();

    public boolean replace(int givenPosition, T newEntry);

    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);

    public int getNumberOfEntries();

    public boolean isEmpty();

    public boolean isFull();
}
