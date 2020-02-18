package com.tilegame;

import display_p.Display;
import graphics_p.Assets;
import input_p.KeyManager;
import input_p.MouseManager;
import state_p.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private Display display;

    public String title;
    public int width, height;

    private  Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g; //deseneaza pe canvas

    int x = 0;

    private MenuState menuState;
    private InstructionsState instructionsState;
    private GameOverState gameOverState;
    private WinState winState;
    private GameState gameState;
    private GameState2 gameState2;
    private GameState3 gameState3;
    private GameState4 gameState4;
    private GameState5  gameState5;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init(){//starts graphics
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);

        menuState = new MenuState(handler);
        instructionsState = new InstructionsState(handler);
        gameOverState = new GameOverState(handler);
        winState = new WinState(handler);

        gameState = new GameState(handler);
        gameState2 = new GameState2(handler);
        gameState3 = new GameState3(handler);
        gameState4 = new GameState4(handler);
        gameState5 = new GameState5(handler);

        State.setState(menuState);

    }

    private void update(){
        keyManager.update();
        if(State.getState() != null)
            State.getState().update();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0, width, height); //clear screen

        if(State.getState() != null)
            State.getState().render(g);

        bs.show();//afiseaza
        g.dispose();//graphics ends properly
    }

    @Override
    public void run() {
        init();

        int fps = 60;//frames/sec; update si render de 60 de ori intr o sec
        double timePerTick = 1000000000/fps; //nanosec(un miliard intr o sec) timpul maxim pt update si render
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;//spune cand sa se apeleze update si render
            lastTime = now;

            if(delta >= 1)
            {
                update();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public synchronized void start(){
        if(running == true)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(running == false)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public InstructionsState getInstructionsState(){
        return instructionsState;
    }

    public MenuState getMenuState(){
        return menuState;
    }

    public GameOverState getGameOverState(){
        return gameOverState;
    }

    public WinState getWinState(){
        return winState;
    }

    public GameState getGameState(){
        return gameState;
    }

    public GameState2 getGameState2() {
        return gameState2;
    }

    public GameState3 getGameState3() {
        return gameState3;
    }

    public GameState4 getGameState4() {
        return gameState4;
    }

    public GameState5 getGameState5() {
        return gameState5;
    }
}
