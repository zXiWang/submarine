package cn.tedu.submarine;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class SeaObject implements Serializable {
    private static final int LIVE = 1;
    private static final int DEAD = 0;
    int width;
    int height;
    int speed;
    int life;
    int x;
    int y;
    private int state = LIVE;

    SeaObject(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    SeaObject(int width, int height) {
        Random rand = new Random();
        speed = (int) (Math.random() * (2) + 2);
        this.width = width;
        this.height = height;
        if (rand.nextBoolean()) {
            x = -width;

        } else {
            x = World.WIDTH + width;
            speed = -speed;
        }
        y = (int) (Math.random() * (World.HEIGHT - height - 150) + 150);

    }

    abstract void step();

    public abstract ImageIcon getImage();

    void moveRight() {
    }

    void moveLeft() {
    }

    public boolean isLive() {
        return this.state == LIVE;
    }

    public boolean isDead() {
        return this.state == DEAD;
    }

    public void goDead() {
        this.state = DEAD;
    }

    public SeaObject shootThunder() {
        if (this.isLive()) {
            int x = this.x + this.width; //雷x
            int y = this.y - 5; //雷y

            //instanceof 关键字 : 判断当前对象的真实类型
            if (this instanceof MineSubmarine) { //判断当前对象是否是水雷潜艇对象
                return new Mine(x, y);
            } else if (this instanceof TorpedoSubmarine) { //判断当前对象是否是鱼雷潜艇对象
                return new Torpedo(x, y);
            } else if (this instanceof Battleship) {
                return new Bomb(x - 35, y + 20);
            }
        }
        return null; //如果代码执行到这一步,说明当前对象为侦查潜艇对象
    }

    public boolean isHit(SeaObject other) {
        int x1 = this.x - other.width; //x1:潜艇的x - 炸弹的宽
        int x2 = this.x + this.width; //x2:潜艇的x + 潜艇的宽
        int y1 = this.y - other.height; //y1:潜艇的y - 炸弹的高
        int y2 = this.y + this.height; //yw:潜艇的y + 潜艇的高
        int x = other.x; //x:炸弹的x
        int y = other.y; //y:炸弹的y

        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public boolean isOutOfBounds() {
        return this.x >= World.WIDTH + this.width || this.x <= -this.width || this.y >= World.HEIGHT || this.y <= 0; //潜艇的x>=窗口的宽,即越界了
    }


    public void paintImage(Graphics g) {
        if (this.getImage() != null) { //判断当前对象获取的图片不能为空
            ImageIcon s = this.getImage(); //获取当前对象的图片
            //2.绘制的坐标
            s.paintIcon(null, g, this.x, this.y); //绘制当前对象的图片
        }
    }
}
