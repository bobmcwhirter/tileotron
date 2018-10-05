package org.mcwhirter.tileotron;

import java.util.Random;

public class Tile {

    public Tile(int length, Tile[]...cuts) {
        this.length = length;
        this.cuts = cuts;
    }

    public Tile(Tile original) {
        this.length = original.length;
        this.cuts = new Tile[ original.cuts.length ][];
        for ( int i = 0 ; i < this.cuts.length ; ++i) {
            this.cuts[i] = new Tile[ original.cuts[i].length ];
            for ( int j = 0 ; j < this.cuts[i].length ; ++j ) {
                this.cuts[i][j] = new Tile(original.cuts[i][j]);
            }
        }
    }

    public Tile[][] getCutChoices() {
        return this.cuts;
    }

    public int getLength() {
        if ( this.cutLength != null ) {
            return this.cutLength;
        }
        return this.length;
    }

    public int getOriginalLength() {
        return this.length;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setLength(int cutLength) {
        this.cutLength = cutLength;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public boolean isStock() {
        return this.stock;
    }

    private Tile[][] cuts;

    private int length;

    private int offset;

    private boolean stock;

    private Integer cutLength;
}
