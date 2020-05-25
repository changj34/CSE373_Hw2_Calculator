package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

/**
 * This file should contain any tests that check and make sure your
 * delete method is efficient.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteStress extends TestDeleteFunctionality {
    @Test(timeout=15 * SECOND)
    public void stressTestDelete() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }
        
        for (int i = 0; i < cap; i++) {
            list.delete(findSize(list));
        }
        assertEquals(0, list.size());
    }
    
    private int findSize(IList<Integer> list) {
        if (list.size()-2 > 0) {
            return list.size()-2;
        }else {
            return 0;
        }
    }
}
