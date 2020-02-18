package tiles_p;

import graphics_p.Assets;

public class Sky extends Tile {

    public Sky(int id){
        super(Assets.sky, id);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
