package com.vandenbreemen.corpuscles;

public class CorpusclesData {

    /**
     * Raw byte data
     */
    byte[][] data;

    public CorpusclesData(int height, int width) {
        data = new byte[height][width];
    }

    /**
     * Overwrite the data in this {@link CorpusclesData} with that found in the given data
     * @param data
     */
    void overwriteWith(CorpusclesData data) {
        for (int i = 0; i < this.data.length; i++) {
            System.arraycopy(data.data[i], 0, this.data[i], 0, this.data[0].length);
        }
    }

    public boolean activated(int alongHeight, int alongWidth) {
        return (data[alongHeight][alongWidth] & (1 << 0)) != 0;
    }

    public void activate(int alongHeight, int alongWidth) {
        data[alongHeight][alongWidth] |= (1 << 0);
    }

    public void deactivate(int alongHeight, int alongWidth) {
        data[alongHeight][alongWidth] &= ~(1 << 0);
    }

    /**
     * Turns the bit at the given position on or off
     * @param alongHeight       Where along the height of the grid
     * @param alongWidth
     * @param atPosition
     * @param to                Whether the bit is to be enabled/disabled
     */
    public void setBit(int alongHeight, int alongWidth, int atPosition, boolean to) {
        if(to){
            data[alongHeight][alongWidth] |= (1<<atPosition);
        }  else {
            data[alongHeight][alongWidth] &= ~(1<<atPosition);
        }
    }

    /**
     * Returns whether the bit at the given position is on
     * @param alongHeight
     * @param alongWidth
     * @param at            Position along byte at position alongWidth, alongHeight
     * @return              True if the bit is on
     */
    public boolean bitIsOn(int alongHeight, int alongWidth, int at) {
        return (data[alongHeight][alongWidth] & (1<<at)) != 0;
    }

    public byte value(int alongHeight, int alongWidth, int fromLowest, int toHighest) {
        byte val = 0;
        for(int i=fromLowest; i<=toHighest; i++) {
            if(bitIsOn(alongHeight, alongWidth, i)) {
                val += (1<<i);
            }
        }
        return val;
    }

    public void writeData(int alongHeight, int alongWidth, byte rawByte) {
        data[alongHeight][alongWidth] = rawByte;
    }
}
