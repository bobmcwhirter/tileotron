package org.mcwhirter.tileotron;

import java.util.ArrayList;
import java.util.List;

public class Row {

    public Row() {

    }

    public void addTile(Tile tile) {
        this.tiles.add( tile );
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public int getLength() {
        return this.tiles.stream().mapToInt(e->e.getLength()).sum();
    }

    public void finish(int targetLength) {
        int curOffset = 0;
        for (Tile tile : this.tiles) {
            tile.setOffset(curOffset);
            curOffset+= tile.getLength();
        }
        int fullLength = getLength();
        int cutLength = fullLength - targetLength;
        Tile lastTile = this.tiles.get(this.tiles.size() - 1);
        lastTile.setLength( lastTile.getLength() - cutLength );
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        for ( int i = 0 ; i < this.tiles.size() ; ++i ) {
            //buffer.append( "|");
            String line = dots(this.tiles.get(i).getLength());
            buffer.append( line );
            //buffer.append( this.tiles.get(i).getLength());
            //if ( i == this.tiles.size() - 1 ) {
                //buffer.append( " (" );
                //buffer.append( getLength() );
                //buffer.append( ")" );
            //}
        }

        StringBuffer wide = new StringBuffer();

        for ( int i = 0 ; i < 1 ; ++i ) {
            wide.append(buffer).append("\n");
        }


        return wide.toString();
    }

    private static String dots(int num) {
        String str = "";
        for ( int i = 0 ; i < num ; ++i ) {
            if ( i == 0 ) {
                str = str + "[";
            } else if ( ( i + 1 ) == num ) {
                str = str + "]";
            } else {
                str = str + "=";
            }
        }
        return str;

    }

    private List<Tile> tiles = new ArrayList<>();

}
