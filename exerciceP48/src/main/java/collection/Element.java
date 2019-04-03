package collection;

public class Element {
    private Object data;
    private Element next;

    Element(Object data, Element next) {
        this.data = data;
        this.next = next;
    }



    public Element next() {
        return next;
    }
}
