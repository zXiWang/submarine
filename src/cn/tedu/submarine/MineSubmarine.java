package cn.tedu.submarine;

import javax.swing.*;

/**
 * 水雷潜艇
 */
public class MineSubmarine extends SeaObject implements EnemyLife {
    MineSubmarine() {
        super(63, 19);
    }

    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive() && speed > 0) {
            return ImageResources.minesubm;
        } else if (this.isLive() && speed < 0) {
            return ImageResources.minesubmright;
        }
        return null;
    }

    @Override
    public int getLife() {
        return 1; //重写,打掉水雷潜艇,战舰得一条命
    }

}
