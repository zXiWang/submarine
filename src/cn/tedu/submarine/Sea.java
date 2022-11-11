package cn.tedu.submarine;

import javax.swing.*;

public class Sea extends SeaObject {

    Sea(int x) {
        super(x, 0, World.WIDTH, World.HEIGHT, 1);
        this.x = x;
    }

    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        return ImageResources.sea;
    }

}
