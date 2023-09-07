package adt;

import java.util.Iterator;

/**
 *
 * @author Lim Jia Qing
 * shared with Sow Yi Chin
 * @param <T>
 */
public interface ListInterface<T extends Comparable<T>> {
    
    public Iterator<T> getIterator();

    public boolean add(T newEntry);

    public boolean remove(int givenPosition);
    
    public T remove(T anEntry);

    public boolean replace(int givenPosition, T newEntry);

    public void clear();
    
    public boolean isEmpty();
    
    public int getNumberOfEntries();
    
    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);
    
    public int indexOf(T anEntry);

}
