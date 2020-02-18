package state_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import ui_p.*;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageBackground(0, 0, 1200, 768, Assets.start));
        uiManager.addObject(new UIImageButton(600, 170, 280, 80, Assets.newGame, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().getGameState());
            }
        }));
        uiManager.addObject(new UIImageButton(350, 520, 280, 80, Assets.instructions, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().getInstructionsState());
                handler.getMouseManager().setUiManager(handler.getGame().getInstructionsState().getUIManager());
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

    public UIManager getUIManager(){
        return uiManager;
    }
}
