package com.vandenbreemen.corpuscles;

import org.junit.Test;

import static org.junit.Assert.*;

public class CorpusclesDataTest {

    @Test
    public void testCreateCorpusclesData() {
        CorpusclesData data = new CorpusclesData(10, 10);
    }

    @Test
    public void testGetActivatedIsFalseByDefault() {
        CorpusclesData data = new CorpusclesData(10, 10);
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                assertFalse(data.activated(i,j));
            }
        }
    }

    @Test
    public void testActivate() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(2,1);
        assertTrue(data.activated(2, 1));
    }

    @Test
    public void testDeactivate() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(2,1);
        data.deactivate(2, 1);
        assertFalse(data.activated(2, 1));
    }

    @Test
    public void testExtractValue() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.setBit(2,2,1,true);
        data.setBit(2,2,0,true);
        data.setBit(2,2,3,true);

        assertEquals(11, data.value(2,2,0, 3));
    }

}