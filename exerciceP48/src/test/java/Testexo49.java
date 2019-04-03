import collection.Array;
import collection.Collection;
import iterator.Iterator;
import collection.List;
import org.junit.Test;

public class Testexo49 {

    @Test
    public void testConstructorArray() {
        Array<String> a = new Array<String>(3);
    }

    @Test
    public void testaddarray(){
        Array<String> a = new Array<String>(3);
        a.add("one"); a.add("two"); a.add("three");
    }

//    @Test
//    public void test1 () {
//        List<Integer> l = new List<Integer>();
//        l.add(1); l.add(2); l.add(3);
//        Array<String> a = new Array<String>(3);
//        a.add("one"); a.add("two"); a.add("three");
//        for (Collection<?> collection : new Collection<?>[] { l, a })
//        {
//            Iterator<?> it = collection.iterator(); // fabrication
//            String s = "< ";
//            while (it.hasNext())
//                s += it.next() + " ";
//            System.out.println(s + ">");
//        }
//    }


}
