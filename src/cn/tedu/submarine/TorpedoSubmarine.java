package cn.tedu.submarine;

import javax.swing.*;

/**
 * 鱼雷潜艇
 */
public class TorpedoSubmarine extends SeaObject implements EnemyScore {
    TorpedoSubmarine() {
        super(64, 20);
    }


    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive() && speed > 0) {
            return ImageResources.torpesubm;

        } else if (this.isLive() && speed < 0) {
            return ImageResources.torpesubmright;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 40;
    }


}
