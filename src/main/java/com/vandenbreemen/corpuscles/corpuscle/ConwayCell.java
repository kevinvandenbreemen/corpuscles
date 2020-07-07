package com.vandenbreemen.corpuscles.corpuscle;

import com.vandenbreemen.corpuscles.Corpuscle;
import com.vandenbreemen.corpuscles.Simulation;

public class ConwayCell extends Corpuscle {
    public ConwayCell(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void takeTurn(int alongHeight, int alongWidth) {
        //  Step 1:  Determine neighbours (Moore neighbourhood)
        int[] range = simulation.getMooreNeighbourhoodRange(alongHeight, alongWidth);
        int hMin = range[0];
        int hMax = range[1];
        int wMin = range[2];
        int wMax = range[3];

        //  Step 2:  Determine count of live cells
        int aliveCount = 0;
        for(int h=hMin; h<=hMax; h++) {
            for(int w=wMin; w<=wMax; w++) {
                if(h == alongHeight && w == alongWidth) {
                    continue;
                }
                if(simulation.activated(h, w)) {
                    aliveCount ++;
                }
            }
        }

        //  Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        if(aliveCount == 3 && !simulation.activated(alongHeight, alongWidth)) {
            simulation.activate(alongHeight, alongWidth);
        }

        //  Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        if(aliveCount < 2 && simulation.activated(alongHeight, alongWidth)) {
            simulation.deactivate(alongHeight, alongWidth);
        }

        //  Any live cell with more than three live neighbours dies, as if by overpopulation.
        if(aliveCount > 3 && simulation.activated(alongHeight, alongWidth)) {
            simulation.deactivate(alongHeight, alongWidth);
        }

    }
}
