package dataStructure;

import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStructureTest {

    @Test
    public void testInsertArrayList() {
        CustomList<String> animals = new CustomArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("fish");
        animals.add("bat");

        assertEquals("bat", animals.get(3));
        assertEquals("fish", animals.get(2));

    }
    @Test
    public void testPrintArrayList() {
        CustomList<Integer> numbers = new CustomArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        assertEquals("1, 2, 3, 4, ", numbers.print());
    }


    @Test
    public void testGetArrayList() {
        CustomList<String> animals = new CustomArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("fish");

        assertEquals("dog", animals.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        CustomList<String> emptylist = new CustomArrayList<>();
        String o = emptylist.get(0);
    }
}