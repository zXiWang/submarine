package cn.tedu.submarine;

import javax.swing.*;

/**
 * 图片资源类 : 主要用来加载项目中所需要的图片资源
 */
public class ImageResources {
    //ImageIcon 用来存图片类型
    public static ImageIcon battleship; //用于战舰图片的静态变量
    public static ImageIcon battleshipright; //用于战舰图片的静态变量
    public static ImageIcon bomb; //用于深水炸弹图片的静态变量
    public static ImageIcon gameover; //用于游戏结束图片的静态变量
    public static ImageIcon mine; //用于水雷图片的静态变量
    public static ImageIcon minesubm; //用于水雷潜艇图片的静态变量
    public static ImageIcon minesubmright; //用于水雷潜艇图片的静态变量
    public static ImageIcon obsersubm; //用于侦查潜艇图片的静态变量
    public static ImageIcon obsersubmright; //用于侦查潜艇图片的静态变量
    public static ImageIcon sea; //用于海洋背景图片的静态变量
    public static ImageIcon start; //用于游戏开始图片的静态变量
    public static ImageIcon torpedo; //用于鱼雷图片的静态变量
    public static ImageIcon torpesubm; //用于鱼雷潜艇图片的静态变量
    public static ImageIcon torpesubmright; //用于鱼雷潜艇图片的静态变量

    static { //静态代码块,用于为上面静态变量赋值具体的图片(路径)
        battleship = new ImageIcon("src/项目/img/battleship.png");
        battleshipright = new ImageIcon("src/项目/img/battleshipright.png");
        bomb = new ImageIcon("src/项目/img/bomb.png");
        gameover = new ImageIcon("src/项目/img/gameover.png");
        mine = new ImageIcon("src/项目/img/mine.png");
        minesubm = new ImageIcon("src/项目/img/minesubm.png");
        minesubmright = new ImageIcon("src/项目/img/minesubmright.png");
        obsersubm = new ImageIcon("src/项目/img/obsersubm.png");
        obsersubmright = new ImageIcon("src/项目/img/obsersubmright.png");
        sea = new ImageIcon("src/项目/img/sea.png");
        start = new ImageIcon("src/项目/img/start.png");
        torpedo = new ImageIcon("src/项目/img/torpedo.png");
        torpesubm = new ImageIcon("src/项目/img/torpesubm.png");
        torpesubmright = new ImageIcon("src/项目/img/torpesubmright.png");
    }
}
