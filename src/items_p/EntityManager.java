package items_p;

import com.tilegame.Handler;
import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    private Handler handler;
    private Player player;
    private Follower follower;
    private ArrayList<Entity> entities;


    public EntityManager(Handler handler, Player player, Follower follower){
        this.handler = handler;
        this.player = player;
        this.follower = follower;
        entities = new ArrayList<Entity>();
        addEntity(player);
        addEntity(follower);

    }

    public void update(){
        for(int i = 0; i < entities.size(); ++i)
        {
            Entity e = entities.get(i);
            e.update();
            if(!e.isActive())
            {
                entities.remove(e);
            }
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < entities.size(); ++i)
        {
            Entity e = entities.get(i);
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public Follower getFollower() {
        return follower;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public boolean isEntityAtPosition(int x, int y){
        for(Entity e : entities)
        {
            if(e.getX() == x && e.getY() == y)
                return true;
        }
        return false;
    }
}
