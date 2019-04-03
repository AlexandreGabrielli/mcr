package iterator;

import collection.Element;

public class ListIterator implements Iterator {
   private Element current;

   public ListIterator (Element current) {
       this.current = current;
   }

    public Object next() {
       current =current.next();
       return current ;
    }

    public boolean hasNext() {
        return current.next() != null;
    }
}
