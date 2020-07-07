package com.vandenbreemen.corpuscles;

public class CorpusclesData {

    /**
     * Raw byte data
     */
    private byte[][] data;

    public CorpusclesData(int height, int width) {
        data = new byte[height][width];
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
