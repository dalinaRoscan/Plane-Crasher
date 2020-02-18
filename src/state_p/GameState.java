package state_p;

import com.tilegame.Handler;
import map_p.Map;
import map_p.MapFactory;
import java.awt.*;

public class GameState extends State {

    Map map;

    public GameState(Handler handler){
        super(handler);
        map = MapFactory.mapFactory(handler, 1);
        handler.setMap(map);
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
    }
}
