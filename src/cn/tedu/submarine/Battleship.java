package cn.tedu.submarine;

import javax.swing.*;

/**
 * 战舰类
 */
public class Battleship extends SeaObject {
    int life;
    boolean speedIsRight = true;

    Battleship() {
        super(270, 124, 66, 26, 20);
        life = 5;
    }

    @Override
    void step() {

    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive() && speedIsRight) {
            return ImageResources.battleship;
        } else if (this.isLive() && !speedIsRight) {
            return ImageResources.battleshipright;
        }
        return null;
    }

    void moveRight() {
        speedIsRight = true;
        x += Math.abs(speed);
    }

    void moveLeft() {
        speedIsRight = false;
        x -= Math.abs(speed);

    }

    public void moveUp() {
        y -= Math.abs(speed);
    }

    public void moveDown() {
        y += Math.abs(speed);
    }

    public int getLife() {
        return life;
    }

    public void lossLife(int num) {
        life -= num;
    }

    public void addLift(int num) {
        life += num;
    }


}
