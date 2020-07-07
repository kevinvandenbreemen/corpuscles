package com.vandenbreemen.corpuscles;

import com.vandenbreemen.corpuscles.corpuscle.ConwayCell;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CorpuscleTest {

    @Test
    public void testAct() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        Corpuscle corpuscle = new Corpuscle(simulation);
        corpuscle.takeTurn(0,0);
    }

    @Test
    public void testConwayRuleAnyDeadCellWith3LiveNeighboursBecomesLiveCell() {
        CorpusclesData data = new CorpusclesData(10, 10);
        Simulation simulation = new Simulation(data);

        //  Set up 3 live neighbours
        simulation.activate(2, 1);
        simulation.activate(3,3);
        simulation.activate(1,2);
        simulation.nextEpoch();

        ConwayCell cell = new ConwayCell(simulation);
        cell.takeTurn(2,2);

        simulation.nextEpoch();

        assertTrue(simulation.activated(2,2));
    }

    @Test
    public void testConwayRuleCellDiesBecauseOfFewerThan2LiveNeighbours() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(2,2); //  Live cell at 2,2
        Simulation simulation = new Simulation(data);

        //  Set up only 1 live neighbour
        simulation.activate(2, 1);
        simulation.nextEpoch();

        ConwayCell cell = new ConwayCell(simulation);
        cell.takeTurn(2,2);

        simulation.nextEpoch();

        assertFalse(simulation.activated(2,2));
    }

    @Test
    public void testConwayRuleCellLivesOnBecauseOf2Or3LiveNeighbours() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(2,2); //  Live cell at 2,2
        Simulation simulation = new Simulation(data);

        //  Set up 2 live neighbours
        simulation.activate(2, 1);
        simulation.activate(3,3);
        simulation.nextEpoch();

        ConwayCell cell = new ConwayCell(simulation);
        cell.takeTurn(2,2);

        simulation.nextEpoch();

        assertTrue(simulation.activated(2,2));
    }

    @Test
    public void testConwayRuleAnyLiveCellWith4LiveNeighboursDies() {
        CorpusclesData data = new CorpusclesData(10, 10);
        data.activate(2,2); //  Live cell at 2,2
        Simulation simulation = new Simulation(data);

        //  Set up 4 live neighbours
        simulation.activate(2, 1);
        simulation.activate(3,3);
        simulation.activate(1,2);
        simulation.activate(1,3);
        simulation.nextEpoch();

        ConwayCell cell = new ConwayCell(simulation);
        cell.takeTurn(2,2);

        simulation.nextEpoch();

        assertFalse(simulation.activated(2,2));
    }

}