package datastructures;

import static org.junit.Assert.fail;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.interfaces.IList;

/**
 * This class should contain all the tests you implement to verify that
 * your 'delete' method behaves as specified.
 *
 * This test _extends_ your TestDoubleLinkedList class. This means that when
 * you run this test, not only will your tests run, all of the ones in
 * TestDoubleLinkedList will also run.
 *
 * This also means that you can use any helper methods defined within
 * TestDoubleLinkedList here. In particular, you may find using the
 * 'assertListMatches' and 'makeBasicList' helper methods to be useful.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteFunctionality extends TestDoubleLinkedList {
    @Test(timeout=SECOND)
    public void basicTestDelete() {
        IList<String> list = makeBasicList();
        assertEquals("b", list.delete(1));
        this.assertListMatches(new String[] {"a", "c"}, list);
        assertEquals("c", list.delete(1));
        this.assertListMatches(new String[] {"a"}, list);
        assertEquals("a", list.delete(0));
        this.assertListMatches(new String[] {}, list);
        try {
            list.delete(0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // This is ok: do nothing
        }
    }
    
    @Test(timeout=SECOND)
    public void testOutOfBounds() {
        IList<String> list = makeBasicList();
        try {
            list.delete(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // This is ok: do nothing
        }
        
        try {
            list.delete(5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // This is ok: do nothing
        }
    }
    
    @Test(timeout=SECOND)
    public void testFrontRepair() {
        IList<String> list = makeBasicList();
        list.delete(0);
        assertEquals("b", list.get(0));
    }
    
    @Test(timeout=SECOND)
    public void testBackRepair() {
        IList<String> list = makeBasicList();
        list.delete(2);
        assertEquals("b", list.get(list.size()-1));
    }
    
    
}