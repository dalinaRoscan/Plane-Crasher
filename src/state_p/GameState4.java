package state_p;

import com.tilegame.Handler;
import map_p.Map;
import map_p.MapFactory;

import java.awt.*;

public class GameState4 extends State {

    private Map map;

    public GameState4(Handler handler){
        super(handler);
        map = MapFactory.mapFactory(handler, 4);
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
    }

    public Map getMap() {
        return map;
    }
}
