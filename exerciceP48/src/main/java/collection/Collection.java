package collection;

import iterator.Iterator;

public interface Collection {
    void add(Object object);
    Iterator iterator();
}
