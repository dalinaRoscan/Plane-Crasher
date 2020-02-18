package com.tilegame;

import input_p.KeyManager;
import input_p.MouseManager;
import map_p.Map;

public class Handler {

    private Game game;
    private Map map;

    public Handler(Game game){
        this.game = game;
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
