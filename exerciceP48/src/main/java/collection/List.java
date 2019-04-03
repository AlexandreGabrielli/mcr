package collection;

import iterator.Iterator;
import iterator.ListIterator;

public class List <T> implements Collection {
    private Element head;

    public <T>List(){
        head = new Element(null,null);
    }


    public void add(Object object) {
        ListIterator it = new ListIterator(head);

        Element e;

        while (it.hasNext()) {
            e = (Element) it.next();
        }

        e.next()=new Element(object,null);
    }

    public Iterator iterator() {
        return null;
    }
}
