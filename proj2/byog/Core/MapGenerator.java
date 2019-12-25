package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/*
Generate a map
 */
public class MapGenerator {
    private static final TETile ENTRY_TILE = Tileset.LOCKED_DOOR;
    private static final TETile EXIT_TILE = Tileset.FLOOR;
    TERenderer ter = new TERenderer();
    Random r = new Random();
    int seed = r.nextInt();

}
