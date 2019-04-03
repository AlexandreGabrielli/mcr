package iterator;

import collection.Array;

public class ArrayIterator implements Iterator {
    private int index;
    private Array parent;

    public Object next() {
        if (hasNext()){
            return parent.at(index+1);
        }
       throw new IndexOutOfBoundsException();
    }

    public boolean hasNext() {
        return index < parent.length();
    }
}
