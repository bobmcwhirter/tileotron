package org.mcwhirter.tileotron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Inventory {

    private static final Random RANDOM = new Random();

    public Inventory() {

    }

    public Inventory(Inventory original) {
        original.inventory.forEach(e -> {
            addInventory(new Tile(e));
        });
    }

    public void addInventory(int amount, int length) {
        //for (int i = 0; i < amount; ++i) {
        //this.inventory.add(new Tile(length));
        //}
        addInventory(new Tile(length));
    }

    public void addInventory(Tile tile) {
        this.inventory.add(tile);
        tile.setStock(true);
    }

    public Tile take(int targetLength) {
        if (this.inventory.isEmpty()) {
            //System.err.println("out of tiles");
            return null;
        }
        int index = 0;
        if (this.inventory.size() > 1) {
            index = RANDOM.nextInt(this.inventory.size() - 1);
        }

        Tile tile = this.inventory.remove(index);

        if (tile.isStock()) {
            this.stockUsed.add(tile);
        }

        if (RANDOM.nextInt(2) > 0) {
            //System.err.println("cut");
            Tile[][] choices = tile.getCutChoices();
            Tile[] choice = null;
            if (choices.length > 1) {
                choice = choices[RANDOM.nextInt(choices.length)];
                tile = choice[0];
                if (choice.length > 1) {
                    for (int i = 1; i < choice.length; ++i) {
                        this.inventory.add(choice[i]);
                    }
                }
            }
        } else {
            //System.err.println("don't cut");
        }

        this.used.add(tile);
        return tile;
    }

    public void reset() {
        this.inventory.addAll(this.used);
        this.used.clear();
    }

    public List<Tile> getStockUsed() {
        return this.stockUsed;
    }

    private List<Tile> inventory = new ArrayList<>();

    private List<Tile> used = new ArrayList<>();

    private List<Tile> stockUsed = new ArrayList<>();

}
