package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    public static class Position{
        public int x;
        public int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void addHexagon(TETile[][] tiles,Position p,int n,TETile t){
        int row = p.x;
        int column = p.y;
        int increase= 0;
        boolean isrepeat = false;
        for (int j= row;j<row+n*2;j++){
            for (int i= column-increase;i < column-increase+n+2*(increase);i++){
                tiles[i][j] = t;
            }
            if (increase<n-1 && isrepeat == false){
                increase +=1;
            } else if (increase == n-1 && isrepeat==false){
                isrepeat = true;
            } else if (isrepeat == true){
                increase -=1;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] Tiles = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                Tiles[x][y] = Tileset.NOTHING;
            }
        }
        int s =3;
        Position[][] p = new Position[5][9];
        for (int y = 0; y < 9; y++){
            if (y == 0 || y == 8){
                int x = 2;
                p[x][y] = new Position((2*s-1)*x, y * s);
            }else if (y%2==0){
                for (int x = 0; x <5; x = x + 2)
                {
                    p[x][y] = new Position((2*s-1)*x, y * s);
                }
            }else{
                for (int x = 1; x <4; x = x + 2){
                    p[x][y] = new Position((2*s-1)*x, y * s);
                }
            }
            //0,2;1,13;2,024;3,13;4,024;5,13;6,024;7,13;8,2
        }
        TETile t = Tileset.GRASS;
        addHexagon(Tiles,p[0][2],s,t);
        addHexagon(Tiles,p[0][4],s,t);
        addHexagon(Tiles,p[1][7],s,t);
        t = Tileset.FLOWER;
        addHexagon(Tiles,p[3][7],s,t);
        addHexagon(Tiles,p[4][6],s,t);
        addHexagon(Tiles,p[1][1],s,t);
        t = Tileset.TREE;
        addHexagon(Tiles,p[2][8],s,t);
        addHexagon(Tiles,p[3][3],s,t);
        addHexagon(Tiles,p[4][4],s,t);
        t = Tileset.MOUNTAIN;
        addHexagon(Tiles,p[0][6],s,t);
        addHexagon(Tiles,p[1][3],s,t);
        addHexagon(Tiles,p[1][5],s,t);
        addHexagon(Tiles,p[2][0],s,t);
        addHexagon(Tiles,p[2][2],s,t);
        addHexagon(Tiles,p[2][4],s,t);
        addHexagon(Tiles,p[2][6],s,t);
        addHexagon(Tiles,p[3][1],s,t);
        t = Tileset.SAND;
        addHexagon(Tiles,p[3][5],s,t);
        addHexagon(Tiles,p[4][2],s,t);
        ter.renderFrame(Tiles);
    }
    }

