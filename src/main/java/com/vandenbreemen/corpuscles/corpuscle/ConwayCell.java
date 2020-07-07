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
        int hMin = alongHeight-1;
        int hMax = alongHeight + 1;
        int wMin = alongWidth - 1;
        int wMax = alongWidth + 1;
        if(hMin < 0) {
            hMin = 0;
        }
        if(hMax > (simulation.height()-1)) {
            hMax = simulation.height()-1;
        }
        if(wMin < 0) {
            wMin = 0;
        }
        if(wMax > (simulation.width()-1)) {
            wMax = simulation.width()-1;
        }

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

    }
}
