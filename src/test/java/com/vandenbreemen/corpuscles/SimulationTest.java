package com.vandenbreemen.corpuscles;

import org.junit.Test;

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

}