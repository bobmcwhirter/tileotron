package org.mcwhirter.tileotron;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Room {

    public Room() {

    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public List<Row> getRows() {
        return this.rows;
    }

    public void finish(int targetLength) {
        this.rows.forEach(e -> {
            e.finish(targetLength);
        });
    }

    public int score() {
        if (this.score == null) {
            calculateScore();
        }
        return this.score;
    }

    public void toHTML(Inventory inventory, PrintWriter out) {
        out.println("<html>");
        out.println("<body>");

        for (Row row : this.rows) {
            out.println("<div style='clear: both'>");
            for (Tile tile : row.getTiles()) {
                int color = (int) ((tile.getLength() / 36.0) * 255.0);
                String hex = Integer.toHexString(color);
                out.println("<div style='background-color: #" + hex + hex + hex + "; width: " + (2 * tile.getLength()) + "pt; height: 36pt; border: 1px solid #ccc; display: inline; float: left'>");
                out.println(tile.getLength());
                out.println("</div>");
            }
            out.println("</div>");
        }

        out.println("<pre style='clear: both'>");
        out.println("Cut List");
        Map<Integer, List<Tile>> tiles = this.rows.stream().flatMap(e -> e.getTiles().stream()).collect(Collectors.groupingBy((Tile e) -> e.getLength()));

        List<Integer> lengths = new ArrayList<>();
        lengths.addAll(tiles.keySet());
        lengths.sort(Comparator.comparingInt(l -> l));

        for (Integer integer : lengths) {
            out.println(tiles.get(integer).size() + " @ " + integer + "\"\n");
        }
        out.println("</pre>");

        out.println("<pre>");
        out.println("Stock Used");

        List<Tile> stockUsed = inventory.getStockUsed();
        Map<Integer, List<Tile>> stock = stockUsed.stream().collect(Collectors.groupingBy(e -> e.getOriginalLength()));
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.addAll(stock.keySet());
        sorted.sort(Comparator.comparingInt(l -> l));

        for (Integer integer : sorted) {
            out.println(integer + " @ " + stock.get(integer).size());

        }

        out.println("</pre>");


        out.println("</body>");
        out.println("</html>");
    }

    private void calculateScore() {
        Set<Integer> prevRowOffsets = new HashSet<>();
        for (Row row : this.rows) {
            Set<Integer> curRowOffsets = row.getTiles().stream().map(e -> e.getOffset()).collect(Collectors.toSet());
            curRowOffsets.remove(0);
            int offsets = curRowOffsets.size();
            Set<Integer> aligned = new HashSet<>();
            aligned.addAll(curRowOffsets);
            aligned.removeAll(prevRowOffsets);
            if (aligned.size() != offsets) {
                //System.err.println( "first order alignment:\n" + curRowOffsets + "\n" + prevRowOffsets + "\n" + aligned);
                this.score = -100;
                return;
            }

            /*
            aligned.clear();
            aligned.addAll(curRowOffsets);
            if ( aligned.size() != offsets ) {
                //System.err.println( "second order alignment");
                return -101;
            }
            */

            for (int curRowOffset : curRowOffsets) {
                for (int prevRowOffset : prevRowOffsets) {
                    if (Math.abs(prevRowOffset - curRowOffset) < 2) {
                        //System.err.println( "setback: " + Math.abs(prevRowOffset - curRowOffset));
                        this.score = -50;
                        return;
                    }
                }
            }

            prevRowOffsets.clear();
            prevRowOffsets.addAll(curRowOffsets);
        }

        if (this.rows.stream().flatMap(e -> e.getTiles().stream()).mapToInt(e -> e.getLength()).filter(e -> e < 6).findAny().isPresent()) {
            this.score = -100;
            return;
        }
        this.score = 100;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        //buffer.append("Room: " + score());
        //buffer.append("\n");
        for (Row row : this.rows) {
            buffer.append(row);
            //buffer.append("\n");
        }
        Map<Integer, List<Tile>> tiles = this.rows.stream().flatMap(e -> e.getTiles().stream()).collect(Collectors.groupingBy((Tile e) -> e.getLength()));

        List<Integer> lengths = new ArrayList<>();
        lengths.addAll(tiles.keySet());
        lengths.sort((l, r) -> Integer.compare(l, r));

        for (Integer integer : lengths) {
            buffer.append(tiles.get(integer).size() + " @ " + integer + "\"\n");
        }

        return buffer.toString();
    }


    private List<Row> rows = new ArrayList<>();

    private Integer score;
}
