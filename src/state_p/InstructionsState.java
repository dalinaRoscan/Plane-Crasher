package state_p;

import com.tilegame.Handler;
import graphics_p.Assets;
import ui_p.ClickListener;
import ui_p.UIImageBackground;
import ui_p.UIImageButton;
import ui_p.UIManager;

import java.awt.*;

public class InstructionsState extends State {

    private UIManager uiManager;
    public InstructionsState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        uiManager.addObject(new UIImageBackground(0, 0, 1200, 768, Assets.instructionsPage));
        uiManager.addObject(new UIImageButton(50, 630, 280, 80, Assets.back, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().getMenuState());
                handler.getMouseManager().setUiManager(handler.getGame().getMenuState().getUIManager());
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
