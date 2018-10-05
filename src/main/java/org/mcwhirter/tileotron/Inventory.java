package org.mcwhirter.tileotron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Inventory {

    public Inventory() {

    }

    public Inventory(Inventory original) {
        this.inventory.addAll(
                original.inventory
                        .stream()
                        .map(e -> new Tile(e))
                        .collect(Collectors.toList())
        );
    }

    public void addInventory(int amount, int length) {
        for (int i = 0; i < amount; ++i) {
            this.inventory.add(new Tile(length));
        }
    }

    public Tile take(int targetLength) {
        if ( this.inventory.isEmpty() ) {
            //System.err.println( "out of tiles");
            return null;
        }
        int index = 0;
        if ( this.inventory.size() > 1 ) {
            index = new Random().nextInt(this.inventory.size() - 1);
        }
        Tile tile = this.inventory.remove(index);
        this.used.add(tile);
        return tile;
    }

    public void reset() {
        this.inventory.addAll(this.used);
        this.used.clear();
    }

    private List<Tile> inventory = new ArrayList<>();

    private List<Tile> used = new ArrayList<>();

}
