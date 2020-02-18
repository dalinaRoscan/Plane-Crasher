package items_p;

import com.tilegame.Handler;

import java.awt.*;

public abstract class Entity {
    protected float x;
    protected float y;
    protected int width, height;
    public static final int DEFAULT_HEALTH = 40;
    protected int health;
    protected int damage;

    protected Handler handler;
    protected Rectangle bounds;

    protected boolean active = true;


    public Entity(Handler handler, float x, float y, int width, int height, int damage){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.damage = damage;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
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

    public boolean isActive() {
        return active;
    }

    public abstract void update();
    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(int amount){
        health -= amount;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getMap().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x+bounds.x+xOffset), (int)(y+bounds.y+yOffset), bounds.width, bounds.height);
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
