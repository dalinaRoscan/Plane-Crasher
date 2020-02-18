package items_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import java.awt.*;

public class Cloud extends StaticEntity {

    public Cloud(Handler handler, float x, float y, int damage){
        super(handler, x, y, 12, 24, damage);
        health = 5;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloud, (int)x, (int)y, width, height, null);
    }

    @Override
    public void die() {
    }
}
