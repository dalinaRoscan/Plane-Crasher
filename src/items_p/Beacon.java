package items_p;

import com.tilegame.Handler;

import java.awt.*;
import java.util.List;

public class Beacon extends Entity {

    private List<Bullet> bullets;

    private static int LEFT_OFFSET = 500;

    public Beacon(Handler handler, float x, float y, int width, int height, List<Bullet> bullets) {
        super(handler, x, y, width, height, 0);
        this.bullets = bullets;
        this.x -= LEFT_OFFSET;
        this.width += LEFT_OFFSET;
        this.bounds.width = this.width;
    }

    public long timeToBeacon(int playerX, int playerY, int playerSpeed) {
        return (long) (Math.sqrt((playerX-x) * (playerX - x) + (playerY - y) * (playerY - y)) / playerSpeed);
    }

    public boolean willBeFree(long delta) {
        boolean willBe = true;
        for (Bullet bullet : bullets) {
            if(bullet.checkCollisionAfter(delta, this.getCollisionBounds(0, 0))) {
                willBe = false;
                break;
            }
        }
        return willBe;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {

    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }
}
