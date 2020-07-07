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
}
