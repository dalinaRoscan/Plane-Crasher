package map_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import items_p.*;
import state_p.State;
import tiles_p.Tile;
import ui_p.UIText;
import utilities_p.Database;
import utilities_p.Utils;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map implements BulletDeleter {
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    private Handler handler;

    private EntityManager entityManager;
    private List<Bullet> bullets;
    private final List<Bullet> bulletsEnemy;

    private long lastTime, currentTime, lastTime1, lastTime2, lastTime3, lastTime4, lastTime5, lastTime6, lastTimeF;
    public static long scoreTimeIn, scoreTimeOut;
    public int currentLevel = 1;

    public Map(Handler handler, String path){

        bulletsEnemy = new ArrayList<Bullet>();
        bullets = new ArrayList<Bullet>();

        this.handler = handler;
        loadMap(path);
        entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY), new Follower(handler, 48, 48, bullets));
        entityManager.addEntity(new Enemy(handler, 1056,48, Entity.DEFAULT_HEALTH));
        entityManager.addEntity(new Enemy(handler, 900, 160, Entity.DEFAULT_HEALTH));
        entityManager.addEntity(new Enemy(handler, 1056,268, Entity.DEFAULT_HEALTH));
        entityManager.addEntity(new Enemy(handler, 900, 380, Entity.DEFAULT_HEALTH));
        entityManager.addEntity(new Enemy(handler, 1056,488, Entity.DEFAULT_HEALTH));
        entityManager.addEntity(new Enemy(handler, 900, 596, Entity.DEFAULT_HEALTH));

        scoreTimeIn = System.currentTimeMillis();
        scoreTimeOut = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
        lastTime1 = lastTime;
        lastTime2 = lastTime;
        lastTime3 = lastTime;
        lastTime4 = lastTime;
        lastTime5 = lastTime;
        lastTime6 = lastTime;
        lastTimeF = lastTime;
    }

    public void update(){
        currentTime = System.currentTimeMillis();
        if(entityManager.getPlayer().isActive()) {
            entityManager.update();
            if (handler.getKeyManager().space && currentTime - lastTime > 250) {
                bullets.add(new Bullet(handler, Assets.fire, entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth(), entityManager.getPlayer().getY() + 10, 0, this));
                bullets.add(new Bullet(handler, Assets.fire, entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth(), entityManager.getPlayer().getY() + entityManager.getPlayer().getHeight() / 3, 0, this));
                bullets.add(new Bullet(handler, Assets.fire, entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth(), entityManager.getPlayer().getY() + entityManager.getPlayer().getHeight() / 3 * 2, 0, this));
                bullets.add(new Bullet(handler, Assets.fire, entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth(), entityManager.getPlayer().getY() + entityManager.getPlayer().getHeight() - 10, 0, this));
                lastTime = currentTime;
             }
            if (currentTime - lastTime1 > 3000 && entityManager.isEntityAtPosition(1056, 48)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 60, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 85, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 110, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 135, 1, this));
                lastTime1 = currentTime;
            }
            if (currentTime - lastTime2 > 3500 && entityManager.isEntityAtPosition(900, 160)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 172, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 197, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 222, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 247, 1, this));
                lastTime2 = currentTime;
            }
            if (currentTime - lastTime3 > 3500 && entityManager.isEntityAtPosition(1056, 268)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 280, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 305, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 330, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 355, 1, this));
                lastTime3 = currentTime;
            }
            if (currentTime - lastTime4 > 4500 && entityManager.isEntityAtPosition(900, 380)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 392, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 417, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 442, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 467, 1, this));
                lastTime4 = currentTime;
            }
            if (currentTime - lastTime5 > 3300 && entityManager.isEntityAtPosition(1056, 488)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 500, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 525, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 550, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 1040, 575, 1, this));
                lastTime5 = currentTime;
            }
            if (currentTime - lastTime6 > 4000 && entityManager.isEntityAtPosition(900, 596)) {
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 608, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 633, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 658, 1, this));
                bulletsEnemy.add(new Bullet(handler, Assets.fireEnemy, 884, 683, 1, this));
                lastTime6 = currentTime;
            }

            if(entityManager.getFollower().isActive())
            {
                if (currentTime - lastTimeF > 2000) {
                    bullets.add(new Bullet(handler, Assets.fire, entityManager.getFollower().getX() + entityManager.getFollower().getWidth(), entityManager.getFollower().getY() + entityManager.getFollower().getHeight() / 3, 0, this));
                    bullets.add(new Bullet(handler, Assets.fire, entityManager.getFollower().getX() + entityManager.getFollower().getWidth(), entityManager.getFollower().getY() + entityManager.getFollower().getHeight() / 3 * 2, 0, this));
                    lastTimeF = currentTime;
                }
            }

            for (Bullet b : bullets) {
                b.fired = true;
                b.update();
            }
            synchronized (bulletsEnemy) {
                try {
                    for (Bullet b : bulletsEnemy) {
                        b.fired = true;
                        b.update();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        scoreTimeOut = System.currentTimeMillis();
        if((entityManager.getPlayer().isActive() && entityManager.getFollower().isActive() && entityManager.getEntities().size() == 2) || (entityManager.getPlayer().isActive() && entityManager.getEntities().size()==1 ))
        {
            Player.score += (scoreTimeOut - scoreTimeIn)/1000;
            switch (currentLevel){
                case 1:
                    handler.getGame().getGameState2().getMap().setLastTime1(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime2(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime3(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime4(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime5(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime6(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTime(System.currentTimeMillis());
                    handler.getGame().getGameState2().getMap().setLastTimeF(System.currentTimeMillis());

                    State.setState(handler.getGame().getGameState2());
                    handler.setMap(handler.getGame().getGameState2().getMap());
                    handler.getMouseManager().setUiManager(null);
                    break;
                case 2:
                    handler.getGame().getGameState3().getMap().setLastTime1(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime2(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime3(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime4(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime5(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime6(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTime(System.currentTimeMillis());
                    handler.getGame().getGameState3().getMap().setLastTimeF(System.currentTimeMillis());
                    State.setState(handler.getGame().getGameState3());
                    handler.setMap(handler.getGame().getGameState3().getMap());
                    handler.getMouseManager().setUiManager(null);
                    break;
                case 3:
                    handler.getGame().getGameState4().getMap().setLastTime1(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime2(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime3(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime4(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime5(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime6(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTime(System.currentTimeMillis());
                    handler.getGame().getGameState4().getMap().setLastTimeF(System.currentTimeMillis());
                    State.setState(handler.getGame().getGameState4());
                    handler.setMap(handler.getGame().getGameState4().getMap());
                    handler.getMouseManager().setUiManager(null);
                    break;
                case 4:
                    handler.getGame().getGameState5().getMap().setLastTime1(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime2(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime3(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime4(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime5(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime6(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTime(System.currentTimeMillis());
                    handler.getGame().getGameState5().getMap().setLastTimeF(System.currentTimeMillis());
                    State.setState(handler.getGame().getGameState5());
                    handler.setMap(handler.getGame().getGameState5().getMap());
                    handler.getMouseManager().setUiManager(null);
                    break;
                case 5:
                    State.setState(handler.getGame().getWinState());
                    Database.setDBScore();
                    handler.getGame().getWinState().getUiManager().addObject(new UIText(580, 300, Player.score));
                    handler.getMouseManager().setUiManager(handler.getGame().getWinState().getUiManager());
                    break;
            }
        }
    }

    public void render(Graphics g){
        if(entityManager.getPlayer().isActive()) {
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
                }
            }
            entityManager.render(g);
            for (Bullet b : bullets) {
                b.render(g);
            }
            for (Bullet b : bulletsEnemy) {
                b.render(g);
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.skyTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.cloudTile;
        }
        return t;
    }

    private void loadMap(String path){
        String file = Utils.loadFileAsString(path);
        String [] tokens = file.split("\\s+");//whitespaces
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        for(int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]); //primele 4 sunt width height spawn
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Bullet> getBulletsEnemy() {
        if(bulletsEnemy != null)
            return bulletsEnemy;
        return null;
    }

    @Override
    public void deleteBullet(int bulletId) {
        for (int i = 0; i < bulletsEnemy.size(); ++i) {
            if (bulletsEnemy.get(i).getObjectId() == bulletId) {
                bulletsEnemy.get(i).deactivate();
                break;
            }
        }
        return;
    }

    public void setLastTime1(long lastTime1) {
        this.lastTime1 = lastTime1;
    }

    public void setLastTime2(long lastTime2) {
        this.lastTime2 = lastTime2;
    }

    public void setLastTime3(long lastTime3) {
        this.lastTime3 = lastTime3;
    }

    public void setLastTime4(long lastTime4) {
        this.lastTime4 = lastTime4;
    }

    public void setLastTime5(long lastTime5) {
        this.lastTime5 = lastTime5;
    }

    public void setLastTime6(long lastTime6) {
        this.lastTime6 = lastTime6;
    }

    public void setLastTimeF(long lastTimeF) {
        this.lastTimeF = lastTimeF;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}

