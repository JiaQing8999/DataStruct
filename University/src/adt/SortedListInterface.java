package adt;

import java.util.Iterator;

/**
 * @author Lim Jia Qing
 * @param <T>
 */
public interface SortedListInterface<T> {

    public Iterator<T> getIterator();

    public boolean add(T newEntry);

    public T remove(T anEntry);

    public boolean contains(T anEntry);

    public T getEntry(int givenPosition);

    public void clear();

    public int getNumberOfEntries();

    public boolean isEmpty();

}
