package map_p;

import com.tilegame.Handler;
import items_p.Bullet;
import items_p.Cloud;

public class MapFactory {
    public static Map mapFactory(Handler handler, int level) {
        Map map;
        switch (level){
            case 1:
                map=new Map(handler,"maps/map1.txt");
                map.getEntityManager().addEntity(new Cloud(handler, 500, 100, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 500, 300, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 700, 350, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 900, 500, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 550, 400, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 660, 600, 0));
                break;
            case 2:
                map=new Map(handler,"maps/map2.txt");
                map.currentLevel=2;
                map.getEntityManager().getPlayer().setHealth(35);
                map.getEntityManager().getFollower().setHealth(35);
                for(Bullet b : map.getBulletsEnemy()){
                    b.setSpeed(11);
                }
                map.getEntityManager().addEntity(new Cloud(handler, 600, 100, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 500, 200, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 800, 300, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 540, 400, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 770, 500, 0));
                break;
            case 3:
                map=new Map(handler,"maps/map3.txt");
                map.currentLevel=3;
                map.getEntityManager().getPlayer().setHealth(30);
                map.getEntityManager().getFollower().setHealth(30);
                for(Bullet b : map.getBulletsEnemy()){
                    b.setSpeed(12);
                }
                map.getEntityManager().addEntity(new Cloud(handler, 730, 150, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 630, 350, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 830, 550, 0));
                break;
            case 4:
                map=new Map(handler,"maps/map4.txt");
                map.currentLevel=4;
                map.getEntityManager().getPlayer().setHealth(20);
                map.getEntityManager().getFollower().setHealth(20);
                for(Bullet b : map.getBulletsEnemy()){
                    b.setSpeed(14);
                }
                map.getEntityManager().addEntity(new Cloud(handler, 500, 70, 0));
                map.getEntityManager().addEntity(new Cloud(handler, 640, 450, 0));
                break;
            case 5:
                map=new Map(handler,"maps/map5.txt");
                map.currentLevel=5;
                map.getEntityManager().getPlayer().setHealth(10);
                map.getEntityManager().getFollower().setHealth(10);
                for(Bullet b : map.getBulletsEnemy()){
                    b.setSpeed(18);
                }
                break;

            default: map=new Map(handler,"maps/map1.txt") ;
        }
        return map;
    }
}
