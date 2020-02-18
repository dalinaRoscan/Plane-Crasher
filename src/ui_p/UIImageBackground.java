package ui_p;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageBackground extends UIObject {
    private BufferedImage image;

    public UIImageBackground(float x, float y, int width, int height, BufferedImage image){
        super(x, y, width, height);
        this.image = image;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
    }
}
