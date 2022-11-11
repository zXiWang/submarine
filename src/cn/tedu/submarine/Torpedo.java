package cn.tedu.submarine;

import javax.swing.*;

public class Torpedo extends SeaObject {
    Torpedo(int x, int y) {
        super(x, y, 5, 18, 2);
    }

    @Override
    void step() {
        y -= speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.torpedo;

        }
        return null;
    }
}
