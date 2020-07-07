package com.vandenbreemen.corpuscles;

public class CorpusclesData {

    /**
     * Raw byte data
     */
    private byte[][] data;

    public CorpusclesData(int height, int width) {
        data = new byte[height][width];
    }
}
