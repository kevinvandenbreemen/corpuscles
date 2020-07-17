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

    @Test
    public void testWriteByte() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte) 3);
        assertTrue(data.bitIsOn(2,2,0));
        assertTrue(data.bitIsOn(2,2,1));
        assertFalse(data.bitIsOn(2,2,3));
    }

    @Test
    public void testReadValueFromRangeOfByte() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte) 3);

        byte value = data.data(2,2, 1,2);
        assertEquals(1,value);
    }

    @Test
    public void testReadValueFromRangeOfByteLargerRange() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, Byte.MAX_VALUE);

        byte value = data.data(2,2, 2,5);
        assertEquals(15,value);
    }

    @Test
    public void testReadFullRangeOfByte() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, Byte.MAX_VALUE);

        byte value = data.data(2,2, 0,7);
        assertEquals(Byte.MAX_VALUE,value);
    }

    @Test
    public void testReadRangeWhenSomeBitsOff() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)4);

        byte value = data.data(2,2, 2,6);
        assertEquals(1,value);
    }

    @Test
    public void testWriteByteToRange() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)5, 1, 4);

        assertEquals(10, data.data(2,2));

    }

    @Test
    public void testWriteByteToRangeTurnsOffBits() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)12);
        data.writeData(2,2, (byte)5, 1, 4);

        assertEquals(10, data.data(2,2));

    }

    @Test
    public void testWriteAndReadByteAtRange() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)3, 2, 7);
        assertEquals(3, data.data(2,2,2,7));
    }

    @Test
    public void testWriteAndReadByteAtRange1() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)12, 2, 7);
        assertEquals(12, data.data(2,2,2,7));
    }

    @Test
    public void testWriteAndReadByteAtRange2() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)57, 2, 7);
        assertEquals(57, data.data(2,2,2,7));
    }

    @Test
    public void testWriteAndReadByteAtRange3() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.writeData(2,2, (byte)27, 2, 6);
        assertEquals(27, data.data(2,2,2,6));
    }

    @Test
    public void testWriteAndReadByteAtRange4() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.setBit(2,2, 1, true);
        data.writeData(2,2, (byte)27, 2, 6);
        assertEquals(27, data.data(2,2,2,6));
    }

    @Test
    public void testWriteAndReadByteAtRange5() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.setBit(2,2, 1, true);
        data.setBit(2,7, 1, true);
        data.writeData(2,2, (byte)27, 2, 6);
        assertEquals(27, data.data(2,2,2,6));
    }

    @Test
    public void testCalculateDistanceBetweenTwoPoints() {
        CorpusclesData data = new CorpusclesData(10, 10);
        assertEquals(1, data.distanceBetween(2,2, 2,3));
    }

    @Test
    public void testCalculateDistanceBetweenTwoPointsDiagonal() {
        CorpusclesData data = new CorpusclesData(10, 10);
        assertEquals(1, data.distanceBetween(2,2, 3,3));
    }

    @Test
    public void testCalculateDistanceBetweenTwoPointsWrapAroundDiag() {
        CorpusclesData data = new CorpusclesData(10, 10);
        assertEquals(4, data.distanceBetween(2,2, 9,9));
    }

    @Test
    public void testCalculateDistanceBetweenTwoPointsWrapAroundStraight() {
        CorpusclesData data = new CorpusclesData(10, 10);
        assertEquals(2, data.distanceBetween(8,8, 0,8));
    }


}