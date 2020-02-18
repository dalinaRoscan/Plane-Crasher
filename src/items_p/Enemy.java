package items_p;

import com.tilegame.Handler;
import graphics_p.Assets;

import java.awt.*;

public class Enemy extends StaticEntity {
    public int i=0;
    public Enemy(Handler handler, float x, float y, int damage){
        super(handler, x, y, 96, 96, damage);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, (int)x, (int)y, width, height, null);
    }

    @Override
    public void die() {
        Player.score += 10;
    }
}
