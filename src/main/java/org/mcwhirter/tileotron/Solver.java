package org.mcwhirter.tileotron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solver {
    
    public Solver(Inventory inventory, int numRows, int targetLength, int setBack, int minLength) {
        this.inventory = inventory;
        this.numRows = numRows;
        this.targetLength = targetLength;
        this.setBack = setBack;
        this.minLength = minLength;
    }
    
    public List<Room> solve(int iterations) {
        String now = new Date().toString();
        List<Room> solutions = new ArrayList<>();
        for ( int i = 0 ; i < iterations ; ++i ) {
            if ( i % 10000 == 0 ) {
                System.err.println( i );
            }
            Inventory myInventory = new Inventory(this.inventory);
            RoomGenerator roomGenerator = new RoomGenerator(myInventory, numRows, targetLength, minLength);
            Room room = roomGenerator.generate();
            if ( room != null ) {
                room.finish(this.targetLength);
                //System.err.println( "Iteration: " + i );
                //System.err.println( room );

                if ( room.score() > 0 ) {
                    solutions.add(room);
                    try (PrintWriter out = new PrintWriter(new FileOutputStream(new File("solutions", now + "-" + i + ".html")))) {
                        room.toHTML(myInventory, out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.err.println( room );
                }
            }
        }

        return solutions;
    }

    private final Inventory inventory;

    private final int numRows;

    private final int targetLength;

    private final int setBack;

    private final int minLength;
}
