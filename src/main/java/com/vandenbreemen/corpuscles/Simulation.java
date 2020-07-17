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
        dataCopy = buildDataCopy(height, width);
        dataCopy.overwriteWith(this.data);
    }

    /**
     * Generate a copy of the data for use as scratchpad state written to by corpuscles
     * @param height
     * @param width
     * @return
     */
    protected CorpusclesData buildDataCopy(int height, int width) {
        return new CorpusclesData(height, width);
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

    /**
     * Gets a byte whose bits (from min to max position) are set to the corresponding bits along the given range.  For example,
     * if you call this method specifing 3 to 5 then the first three bits will be set on/off on the resulting byte based on whether
     * the corresponding bits at positions 3 through 5 are on.
     * @param alongHeight
     * @param alongWidth
     * @param fromMinBit
     * @param toMaxBit
     * @return
     */
    public byte data(int alongHeight, int alongWidth, int fromMinBit, int toMaxBit) {
        return data.data(alongHeight, alongWidth, fromMinBit, toMaxBit);
    }

    /**
     * Writes bits from the given byte to bits along the specified range at the specified location.  For example, if you pass in
     * 00001010, 1, 4 to this method and the byte at (alongHeight, alongWidth) is 00001111 then that byte will become 00010101
     * @param alongHeight
     * @param alongWidth
     * @param data              Raw byte you want to write
     * @param fromMinBit        Minimum bit position of the byte at (alongHeight,alongWidth) to write to
     * @param toMaxBit          Maximum bit position of the byte at (alongHeight, alongWidth) to write to
     */
    public void writeData(int alongHeight, int alongWidth, byte data, int fromMinBit, int toMaxBit) {
        dataCopy.writeData(alongHeight, alongWidth, data, fromMinBit, toMaxBit);
    }

    /**
     * Calculates the absolute distance between two points(cells) in the grid
     * @param p1AlongHeight
     * @param p1AlongWidth
     * @param p2AlongHeight
     * @param p2AlongWidth
     * @return
     */
    public int distanceBetween(int p1AlongHeight, int p1AlongWidth, int p2AlongHeight, int p2AlongWidth) {
        return data.distanceBetween(p1AlongHeight, p1AlongWidth, p2AlongHeight, p2AlongWidth);
    }
}
