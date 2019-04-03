package collection;

import iterator.Iterator;

public class Array<T> implements Collection {
    private Object[] array;

    public <T> Array(int size) {
        array = (T[]) new Object[size];
    }

    public void add(Object object) {

    }

    public T at(int i){
        return (T) array[i];
    }

    public int length() {
        return array.length;
    }

    public Iterator iterator() {
        return null;
    }

}
