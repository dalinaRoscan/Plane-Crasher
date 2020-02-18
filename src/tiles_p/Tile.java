package tiles_p;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile skyTile = new Sky(0);
    public static Tile cloudTile = new MarginCloud(1);

    public static final int TILEWIDTH = 48, TILEHEIGHT = 48;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void update(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isWalkable(){
        return false;
    }

    public int getId(){
        return id;
    }
}
