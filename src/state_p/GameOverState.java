package state_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import items_p.Player;
import ui_p.*;

import java.awt.*;

public class GameOverState extends State {

    private UIManager uiManager;
    public GameOverState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        uiManager.addObject(new UIImageBackground(0, 0, 1200, 768, Assets.gameOver));
        uiManager.addObject(new UIImageButton(350, 600, 280, 80, Assets.exit, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("USER EXITED GAME");
                System.exit(0);
            }
        }));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
}
