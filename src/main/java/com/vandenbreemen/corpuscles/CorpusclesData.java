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
    protected void overwriteWith(CorpusclesData data) {
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

    public byte data(int alongHeight, int alongWidth) {
        return data[alongHeight][alongWidth];
    }

    public void writeData(int alongHeight, int alongWidth, byte rawByte) {
        data[alongHeight][alongWidth] = rawByte;
    }

    public int width() {
        return data[0].length;
    }

    public int height() {
        return data.length;
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
        byte value=0;
        for(int i=0; i<=toMaxBit-fromMinBit; i++) {
            if(bitIsOn(alongHeight, alongWidth, (fromMinBit+i))) {
                value |= (1<<i);
            }
        }

        return value;
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
        for(int i=0; i<=toMaxBit-fromMinBit; i++) {
            setBit(alongHeight, alongWidth, fromMinBit + i, (data & (1<<i)) != 0);
        }
    }
}
