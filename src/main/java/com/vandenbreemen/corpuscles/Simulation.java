package com.vandenbreemen.corpuscles;

/**
 *
 */
public class Simulation {

    public static class NeighbourHoodRange {
        public static final int HEIGHT_MIN = 0;
        public static final int HEIGHT_MAX = 1;
        public static final int WIDTH_MIN = 2;
        public static final int WIDTH_MAX = 3;
    }

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

    public void setBit(int alongHeight, int alongWidth, int atPosition, boolean to) {
        dataCopy.setBit(alongHeight, alongWidth, atPosition, to);
    }

    public boolean bitIsOn(int alongHeight, int alongWidth, int at) {
        return data.bitIsOn(alongHeight, alongWidth, at);
    }

    public int width() {
        return data.width();
    }

    public int height() {
        return data.height();
    }

    /**
     * Generates min/max height and width values to use when visiting the Moore neighbourhood of a given cell
     * @param alongHeight   Where along the height of the simulation to start
     * @param alongWidth    Where along the width of the simulation to start
     * @return              An array with 4 items:  [hMin, hMax, wMin, wMax]
     */
    public int[] getMooreNeighbourhoodRange(int alongHeight, int alongWidth) {
        int hMin = alongHeight-1;
        int hMax = alongHeight + 1;
        int wMin = alongWidth - 1;
        int wMax = alongWidth + 1;

        if(hMin < 0) {
            hMin = height()-1;
        }
        if(wMin < 0) {
            wMin = width()-1;
        }

        hMax %= height();
        wMax %= width();

        return new int[] {
            hMin,
            hMax,
            wMin,
            wMax
        };
    }

    public byte data(int alongHeight, int alongWidth) {
        return data.data(alongHeight, alongWidth);
    }

    /**
     * Gets the current value at the given position/bit range of the reality
     * @param alongHeight
     * @param alongWidth
     * @param fromLowest
     * @param toHighest
     * @return
     */
    public byte value(int alongHeight, int alongWidth, int fromLowest, int toHighest) {
        return data.value(alongHeight, alongWidth, fromLowest, toHighest);
    }
}
