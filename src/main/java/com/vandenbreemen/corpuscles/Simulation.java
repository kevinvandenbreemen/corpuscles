package com.vandenbreemen.corpuscles;

/**
 *
 */
public class Simulation {

    /**
     * The underlying actual "reality" of the world of the simulation
     */
    private CorpusclesData data;

    /**
     * Copy.  This is where changes get made during processing of each epoch.  When a new epoch begins
     * the contents of this are written to the {@link #data}.
     */
    private CorpusclesData dataCopy;

    public Simulation(CorpusclesData data) {
        this.data = data;

        int height = this.data.data.length;
        int width = this.data.data[0].length;
        dataCopy = new CorpusclesData(height, width);
        dataCopy.overwriteWith(this.data);
    }

    public void activate(int alongHeight, int alongWidth) {
        this.dataCopy.activate(alongHeight, alongWidth);
    }

    /**
     * Transitions the simulation to the next epoch
     */
    public void nextEpoch() {
        this.data.overwriteWith(this.dataCopy);
    }

    public boolean activated(int alongHeight, int alongWidth) {
        return data.activated(alongHeight, alongWidth);
    }

    public void deactivate(int alongHeight, int alongWidth) {
        dataCopy.deactivate(alongHeight, alongWidth);
    }
}
