package org.mcwhirter.tileotron;

import java.util.List;

import org.junit.Test;

public class SolverTest {

    @Test
    public void solve() {

        Inventory inventory = new Inventory();
        //inventory.addInventory(13, 36);
        for (int i = 0; i < 10; ++i) {
            inventory.addInventory(new Tile(36));
        }

        for (int i = 0; i < 3; ++i) {
            inventory.addInventory(new Tile(36,
                                            new Tile[]{
                                                    new Tile(35)
                                            },
                                            new Tile[]{
                                                    new Tile(29)
                                            }));
        }

        for (int i = 0; i < 76; ++i) {
            inventory.addInventory(new Tile(24,
                                            new Tile[]{
                                                    new Tile(11),
                                                    new Tile(13),
                                            },
                                            new Tile[]{
                                                    new Tile(23)
                                            },
                                            new Tile[]{
                                                    new Tile(21)
                                            }//,
                                            //new Tile[]{
                                            //new Tile(23)
                                            //}
            ));
        }

        //inventory.addInventory(35, 24);
        //inventory.addInventory(29, 21);
        //inventory.addInventory(24, 11);
        //inventory.addInventory(8, 9);

        Solver solver = new Solver(inventory, 9, 221, 3, 12);

        List<Room> solutions = solver.solve(9000000);

        //for (Room solution : solutions) {
        //System.err.println( solution );
        //}

    }
}
