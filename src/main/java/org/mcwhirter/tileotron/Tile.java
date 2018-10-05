package org.mcwhirter.tileotron;

import java.util.Random;

public class Tile {

    public Tile(int length) {
        this.length = length;
    }

    public Tile(Tile original) {
        this.length = original.length;
    }

    public int getLength() {
        return this.length;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setLength(int cutLength) {
        this.length = cutLength;
    }

    private int length;

    private int offset;
}
