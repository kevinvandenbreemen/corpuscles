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

}