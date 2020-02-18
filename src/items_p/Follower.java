package items_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Follower extends Base {
    private BufferedImage frame;

    private static int BEACON_OFFSET_TIME = 2;
    private static float DODGE_SPEED = 8f;

    private long lastAttackTimer, attackCoolDown = 400, attackTimer = attackCoolDown;

    private Beacon[] beacons;

    private float finalX, finalY;
    private int currentBeacon;

    private List<Bullet> bullets;


    public Follower(Handler handler, float x, float y, List<Bullet> bullets){
        super(handler, x, y, 48, 48, Entity.DEFAULT_HEALTH);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;

        this.bullets = bullets;

        frame = Assets.follower;

        initialiseBeacons();

        currentBeacon = 0;
        finalX = beacons[currentBeacon].x;
        finalY = beacons[currentBeacon].y;
    }

    private void initialiseBeacons() {
        beacons = new Beacon[14];
        beacons[0] = new Beacon(handler, 48, 48, 48, 48, bullets);
        beacons[1] = new Beacon(handler, 48, 96, 48, 48, bullets);
        beacons[2] = new Beacon(handler, 48, 144, 48, 48, bullets);
        beacons[3] = new Beacon(handler, 48, 192, 48, 48, bullets);
        beacons[4] = new Beacon(handler, 48, 240, 48, 48, bullets);
        beacons[5] = new Beacon(handler, 48, 288, 48, 48, bullets);
        beacons[6] = new Beacon(handler, 48, 336, 48, 48, bullets);
        beacons[7] = new Beacon(handler, 48, 384, 48, 48, bullets);
        beacons[8] = new Beacon(handler, 48, 432, 48, 48, bullets);
        beacons[9] = new Beacon(handler, 48, 480, 48, 48, bullets);
        beacons[10] = new Beacon(handler, 48, 528, 48, 48, bullets);
        beacons[11] = new Beacon(handler, 48, 576, 48, 48, bullets);
        beacons[12] = new Beacon(handler, 48, 624, 48, 48, bullets);
        beacons[13] = new Beacon(handler, 48, 672, 48, 48, bullets);
    }

    private void checkNextBeacon() {
        if(!beacons[currentBeacon].willBeFree(beacons[currentBeacon].timeToBeacon((int)x, (int)y, (int)speed) + BEACON_OFFSET_TIME)) {
            int up = currentBeacon - 1;
            int down = currentBeacon + 1;
            int nextBeacon = -1;

            while(up >= 0 && down < 14) {
                if(beacons[up].willBeFree(beacons[up].timeToBeacon((int)x, (int)y, (int)speed) + BEACON_OFFSET_TIME + Math.abs(up-currentBeacon))) {
                    nextBeacon = up;
                    break;
                }

                if(beacons[down].willBeFree(beacons[down].timeToBeacon((int)x, (int)y, (int)speed) + BEACON_OFFSET_TIME + Math.abs(down-currentBeacon))) {
                    nextBeacon = down;
                    break;
                }
                up-=1;
                down+=1;
            }
            if(nextBeacon == -1) {
                while (up >= 0) {
                    if (beacons[up].willBeFree(beacons[up].timeToBeacon((int) x, (int) y, (int) speed) + BEACON_OFFSET_TIME + Math.abs(up-currentBeacon))) {
                        nextBeacon = up;
                        break;
                    }
                    up -= 1;
                }

                while (down < 14) {
                    if (beacons[down].willBeFree(beacons[down].timeToBeacon((int) x, (int) y, (int) speed) + BEACON_OFFSET_TIME + Math.abs(down-currentBeacon))) {
                        nextBeacon = down;
                        break;
                    }
                    down += 1;
                }
            }

            if(nextBeacon != -1) {
                finalX = beacons[nextBeacon].x;
                finalY = beacons[nextBeacon].y;
                currentBeacon = nextBeacon;
            } else {
                System.out.println("No escape!");
            }
        }
    }

    @Override
    public void update() {
        for(Beacon b : beacons)
        {
            b.setBullets(handler.getMap().getBulletsEnemy());
        }
        checkNextBeacon();
        move();
        checkAttacks();
        //System.out.println("Follower " + health);
    }

    @Override
    public void move() {
        if(!checkEntityCollisions(0, finalY-y))
            y+=(finalY-y)/DODGE_SPEED;
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


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int)x,(int)y, width, height,null);

    }

    @Override
    public void die() {
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }
}
