package items_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import map_p.Map;
import state_p.State;
import ui_p.UIText;
import utilities_p.Database;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Base {

    private BufferedImage frame;

    private long lastAttackTimer, attackCoolDown = 400, attackTimer = attackCoolDown;

    public static int score;

    public Player(Handler handler, float x, float y){
        super(handler, x, y, Base.DEFAULT_WIDTH, Base.DEFAULT_HEIGHT, Entity.DEFAULT_HEALTH);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 96;
        bounds.height = 96;

        frame = Assets.player;
        this.score = 0;
    }

    @Override
    public void update() {
        getInput();
        move();
        checkAttacks();
        //System.out.println("Player " + health);
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCoolDown)
            return;
        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().up)
        {
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y - arSize;
        }
        else if(handler.getKeyManager().down)
        {
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y + cb.height;
        }
        else if(handler.getKeyManager().left)
        {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else if(handler.getKeyManager().right)
        {
            ar.x = cb.x + width;
            ar.y = cb.y + cb.height/2 - arSize/2;
        }
        else
        {
            return;
        }

        attackTimer = 0;

        for(Entity e: handler.getMap().getEntityManager().getEntities())
        {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar))
            {
                this.hurt(e.damage);
                e.hurt(e.damage);
                return;
            }
        }
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
        {
            yMove = -speed;
        }
        if(handler.getKeyManager().down)
        {
            yMove = speed;
        }
        if(handler.getKeyManager().left)
        {
            xMove = -speed;
        }
        if(handler.getKeyManager().right)
        {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int)x,(int)y, width, height,null);
    }

    @Override
    public void die() {
        Player.score += (Map.scoreTimeOut - Map.scoreTimeIn)/100;
        State.setState(handler.getGame().getGameOverState());
        Database.setDBScore();
        handler.getGame().getGameOverState().getUiManager().addObject(new UIText(580, 300, Player.score));
        handler.getMouseManager().setUiManager(handler.getGame().getGameOverState().getUiManager());
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }
}
