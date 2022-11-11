package cn.tedu.submarine;

import javax.swing.*;

/**
 * 炸弹
 */
public class Bomb extends SeaObject {

    Bomb(int x, int y) {
        super(x, y, 9, 12, 3);
        if (y >= 300) speed = -speed;
    }

    @Override
    void step() {
        y += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.bomb;
        }
        return null;
    }

}
