package org.mcwhirter.tileotron;

import java.util.List;

import org.junit.Test;

public class SolverTest {

    @Test
    public void solve() {

        Inventory inventory = new Inventory();
        inventory.addInventory(13, 36);
        inventory.addInventory(35, 24);
        inventory.addInventory(29, 21);
        inventory.addInventory(24, 11);
        //inventory.addInventory(8, 9);

        Solver solver = new Solver(inventory, 9, 221, 3, 12);

        List<Room> solutions = solver.solve(9000000 );

        //for (Room solution : solutions) {
            //System.err.println( solution );
        //}

    }
}
