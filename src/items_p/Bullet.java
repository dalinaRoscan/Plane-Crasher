package items_p;

import com.tilegame.Handler;
import map_p.BulletDeleter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private static int numberBullets = 0;

    private int objectId;

    private boolean active;

    protected float x, y;
    private int width, height;
    public static final float DEFAULT_SPEED = 10.0f;
    public static final int DEFAULT_WIDTH = 12, DEFAULT_HEIGHT = 8;

    private float speed;
    private int id;

    protected Handler handler;
    protected Rectangle bounds;
    protected BufferedImage texture;

    public boolean fired = false;
    public boolean used = false;

    private BulletDeleter bulletDeleter;

    public Bullet(Handler handler, BufferedImage texture, float x, float y, int id, BulletDeleter bulletDeleter){
        this.handler = handler;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.speed = DEFAULT_SPEED;
        this.id = id;

        this.bulletDeleter = bulletDeleter;

        bounds = new Rectangle(0, 0, width, height);

        objectId = ++numberBullets;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public int getObjectId() {
        return this.objectId;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update(){
        if(!used)
        {
            if (fired)
                move();
            checkAttacks();
            if(this.x < 0 || this.x > 2000) {
                bulletDeleter.deleteBullet(this.getObjectId());
            }
        }

    }

    public  void render(Graphics g){
        if(!used) {
            if (fired)
                g.drawImage(texture, (int) x, (int) y, width, height, null);
        }
    }


    public boolean checkEntityCollisions(){
        for(Entity e : handler.getMap().getEntityManager().getEntities()){
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds()))
            {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(){
        return new Rectangle((int)x, (int)y, bounds.width, bounds.height);
    }

    public Rectangle getCollisionBoundsAfter(long delta) {
        return new Rectangle((int)this.getXAfter(delta), (int)this.getYAfter(delta), bounds.width, bounds.height);
    }

    public boolean checkCollisionAfter(long delta, Rectangle otherObject) {
        if(!this.active) return false;
        return otherObject.intersects(this.getCollisionBoundsAfter(delta));
    }

    public void move(){

        if(!checkEntityCollisions() && id == 0)
        {
            x += speed;
        }
        else if(!checkEntityCollisions() && id == 1)
        {
            x -= speed;
        }
        else
        {
            fired = false;
        }
    }

    public int getXAfter(long delta) {
        return (int) (this.x - this.speed * delta);
    }

    public int getYAfter(long delta) {
        return (int) this.y;
    }

    private void checkAttacks(){
        for(Entity e: handler.getMap().getEntityManager().getEntities())
        {
            if(getCollisionBounds().intersects(e.getCollisionBounds(0,0)))
            {
                e.hurt(1);
                used = true;
            }
        }
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }
}
