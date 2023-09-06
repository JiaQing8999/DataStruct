package adt;
/**
 *
 * @author Phua Mei Kee
 */
import java.util.Iterator;

public interface BinarySearchTreeInterface<T extends Comparable<T>> {

    public boolean contains(T entry);

    public T getEntry(T entry);

    public T add(T newEntry);

    public T remove(T entry);

    public boolean isEmpty();

    public void clear();

    public Iterator<T> getInorderIterator();

    public T getSmallestValue();
    
    public T getLargestValue();

}
