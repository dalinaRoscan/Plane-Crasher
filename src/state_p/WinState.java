package state_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import ui_p.ClickListener;
import ui_p.UIImageBackground;
import ui_p.UIImageButton;
import ui_p.UIManager;

import java.awt.*;

public class WinState extends State {

    private UIManager uiManager;
    public WinState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        uiManager.addObject(new UIImageBackground(0, 0, 1200, 768, Assets.win));
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
