package cn.tedu.submarine;

import javax.swing.*;

/**
 * 水雷
 */
public class Mine extends SeaObject {
    Mine(int x, int y) { //通过外部获取对象的x坐标和y坐标
        super(x, y, 11, 11, 2);
    }

    @Override
    void step() {
        y -= speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.mine;
        }
        return null;
    }

    public boolean isOutOfBounds() {
        return this.y <= 150 - this.height; //潜艇的x>=窗口的宽,即越界了
    }

}
