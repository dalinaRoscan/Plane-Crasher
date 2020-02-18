package ui_p;

import java.awt.*;

public class UIText extends UIObject {
    private String scoreString;

    public UIText(float x, float y, int score){
        super(x, y, 0, 0);
        this.scoreString = "Your Score: " + score;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString(scoreString, (int)x, (int)y);
    }

    @Override
    public void onClick() {
    }
}
