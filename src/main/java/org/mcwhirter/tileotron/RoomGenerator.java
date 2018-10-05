package org.mcwhirter.tileotron;

public class RoomGenerator {

    public RoomGenerator(Inventory inventory, int numRows, int targetLength, int minLength) {
        this.inventory = inventory;
        this.numRows = numRows;
        this.targetLength = targetLength;
        this.minLength = minLength;
    }

    public Room generate() {
        Room room = new Room();
        for ( int i = 0 ; i < numRows ; ++i ) {
            RowGenerator rowGenerator = new RowGenerator(this.inventory, this.targetLength, this.minLength);
            Row row = rowGenerator.generate();
            if ( row == null ) {
                return null;
            }
            room.addRow(row);

        }
        return room;
    }

    private final Inventory inventory;

    private final int numRows;

    private final int targetLength;

    private final int minLength;
}
