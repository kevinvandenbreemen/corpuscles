package com.vandenbreemen.corpuscles;

import com.vandenbreemen.corpuscles.corpuscle.ConwayCell;

/**
 * An automaton that possesses one or more types of {@link Corpuscle} that it uses to process data in a {@link Simulation}.
 * By default this automaton performs its logic using a {@link ConwayCell} only.  However you can override the type and
 * use whatever types of cells you'd like
 */
public class CellularAutomaton {

    private Simulation simulation;

    public CellularAutomaton(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Gets the most appropriate {@link Corpuscle} for the cell at the given location in the simulation
     * @param alongWidth
     * @param alongHeight
     * @param simulation    Simulation in which to operate the {@link Corpuscle}
     * @return
     */
    public Corpuscle getCorpuscle(int alongWidth, int alongHeight, Simulation simulation) {
        return new ConwayCell(simulation);
    }

    /**
     * Performs a full epoch (that is, iterates over all cells in the simulation, executing logic)
     */
    public final void performNextEpoch() {
        for(int h=0; h<simulation.height(); h++) {
            for(int w=0; w<simulation.width(); w++) {
                Corpuscle cell = getCorpuscle(h, w, this.simulation);
                cell.takeTurn(h, w);
            }
        }
        simulation.nextEpoch();
    }
}
