package cn.tedu.submarine;

import javax.swing.*;

/**
 * 侦查潜艇
 */
public class ObserveSubmarine extends SeaObject implements EnemyScore {
    ObserveSubmarine() {
        super(63, 19);
    }

    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive() && speed > 0) {
            return ImageResources.obsersubm;

        } else if (this.isLive() && speed < 0) {
            return ImageResources.obsersubmright;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 10;
    }

}
