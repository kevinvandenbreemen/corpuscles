package com.vandenbreemen.corpuscles;

/**
 *
 */
public class Simulation {

    private CorpusclesData data;

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

    public void nextEpoch() {
        this.data.overwriteWith(this.dataCopy);
    }
}
