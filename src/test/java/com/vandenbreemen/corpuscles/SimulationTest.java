package com.vandenbreemen.corpuscles;

import org.junit.Test;

import static com.vandenbreemen.corpuscles.Simulation.NeighbourHoodRange.*;
import static org.junit.Assert.*;

public class SimulationTest {

    @Test
    public void testQueueMove() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(1,1);
        simulation.nextEpoch();

        assertTrue(data.activated(1,1));

    }

    @Test
    public void testActivateHasNoEffectBeforeNextEpoch() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(1,1);
        assertFalse(data.activated(1,1));
    }

    @Test
    public void testDeactivateHasNoEffectBeforeNextEpoch() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(1,1);
        Simulation simulation = new Simulation(data);

        simulation.deactivate(1,1);
        assertTrue(data.activated(1,1));

        simulation.nextEpoch();

        assertFalse(data.activated(1,1));
    }

    @Test
    public void testGetsActivationStatus() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(1,1);
        Simulation simulation = new Simulation(data);

        //  Simulation deactivate means queue up deactivated state
        simulation.deactivate(1,1);

        assertTrue(simulation.activated(1,1));
    }

    @Test
    public void testGetLengthWidth() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        assertEquals(10, simulation.width());
        assertEquals(10, simulation.height());
    }

    @Test
    public void testGetNeighbourhoodBoundsOverTorusSpace() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);
        int[] ranges = simulation.getMooreNeighbourhoodRange(0,0);
        assertEquals(9, ranges[HEIGHT_MIN]);
        assertEquals(1, ranges[HEIGHT_MAX]);
        assertEquals(9, ranges[WIDTH_MIN]);
        assertEquals(1, ranges[WIDTH_MAX]);
    }

    @Test
    public void testGetNeighbourhoodBoundsOverTorusSpaceMaxEdges() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);
        int[] ranges = simulation.getMooreNeighbourhoodRange(9,9);
        assertEquals(8, ranges[HEIGHT_MIN]);
        assertEquals(0, ranges[HEIGHT_MAX]);
        assertEquals(8, ranges[WIDTH_MIN]);
        assertEquals(0, ranges[WIDTH_MAX]);
    }

    /**
     * Get the raw data at a given cell
     */
    @Test
    public void testGetDataAtCell() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(5,5);
        simulation.nextEpoch();

        byte dataByte = simulation.data(5,5);

        assertEquals(1, dataByte);
    }

    @Test
    public void testTurnOnBit() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(5,5);
        simulation.setBit(5, 5, 1, true);
        simulation.nextEpoch();

        //  Bits 1 and 0 are on (2+1)
        assertEquals(3, simulation.data(5,5));
    }

    @Test
    public void testTurnOffBit() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(5,5);
        simulation.setBit(5, 5, 0, false);  //  Same as deactivating!
        simulation.nextEpoch();

        assertEquals(0, simulation.data(5,5));
    }

    @Test
    public void testGetBitAtPosition() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(5,5);
        simulation.setBit(5, 5, 4, true);
        simulation.nextEpoch();

        assertTrue(simulation.bitIsOn(5,5,4));
    }

    @Test
    public void testGetBitAtPositionOffByDefault() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        simulation.activate(5,5);
        simulation.nextEpoch();

        assertFalse(simulation.bitIsOn(5,5,4));
    }
}