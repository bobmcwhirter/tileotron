package org.mcwhirter.tileotron;

public class RowGenerator {

    public RowGenerator(Inventory inventory, int targetLength, int minLength) {
        this.inventory = inventory;
        this.targetLength = targetLength;
        this.minLength = minLength;
    }

    public Row generate() {
        Row row = new Row();

        while (row.getLength() < this.targetLength) {
            int targetLength = this.targetLength - row.getLength();
            Tile tile = this.inventory.take(targetLength);
            if ( tile == null ) {
                return null;
            }
            row.addTile(tile);
        }

        return row;
    }

    private final Inventory inventory;

    private final int targetLength;

    private final int minLength;
}
