package cn.tedu.submarine;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel {
    public static final int WIDTH = 641; //背景宽
    public static final int HEIGHT = 479; //背景高
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    int state = START;

    SeaObject[] submarines = {};
    SeaObject[] thunders = {};
    Bomb[] bombs = {};
    Battleship battleship = new Battleship();

    Sea seaFirst = new Sea(0);
    Sea seaDouble = new Sea(-WIDTH);
    int score = 0;

    private int submarineEnterIndex = 0;
    private int thundersEnterIndex = 0;

    public static void main(String[] args) {
        World gw = new World();
        gw.paintWorld();
        gw.action();
    }

    public SeaObject createSubmarine() {
        int type = (int) (Math.random() * 20);
        if (type < 10) {
            return new ObserveSubmarine();
        } else if (type < 15) {
            return new TorpedoSubmarine();
        } else {
            return new MineSubmarine();
        }
    }

    public void submarineEnterAction() {
        submarineEnterIndex++;
        if (submarineEnterIndex % 20 == 0) {
            SeaObject submarine = createSubmarine();
            submarines = Arrays.copyOf(submarines, submarines.length + 1);
            submarines[submarines.length - 1] = submarine;
        }
    }

    public void outOfBoundsAction() {
        for (int i = 0; i < submarines.length; i++) { //遍历所有的潜艇
            if (submarines[i].isOutOfBounds()) { //越界了
                submarines[i] = submarines[submarines.length - 1];
                submarines = Arrays.copyOf(submarines, submarines.length - 1); //缩容
            }
        }
        for (int i = 0; i < thunders.length; i++) { //遍历所有的潜艇
            if (thunders[i].isOutOfBounds()) { //越界了
                thunders[i] = thunders[thunders.length - 1];
                thunders = Arrays.copyOf(thunders, thunders.length - 1); //缩容
            }
        }
        for (int i = 0; i < bombs.length; i++) { //遍历所有的潜艇
            if (bombs[i].isOutOfBounds()) { //越界了
                bombs[i] = bombs[bombs.length - 1];
                bombs = Arrays.copyOf(bombs, bombs.length - 1); //缩容
            }
        }
    }

    public void thunderEnterAction() {
        thundersEnterIndex++;
        if (thundersEnterIndex % 50 == 0) {
            for (SeaObject submarine : submarines) {
                SeaObject thunder = submarine.shootThunder();
                if (thunder != null) {
                    thunders = Arrays.copyOf(thunders, thunders.length + 1);
                    thunders[thunders.length - 1] = thunder;
                }
            }
        }
    }

    public void bombEnterAction() {
        if (bombs.length <= 5) {
            bombs = Arrays.copyOf(bombs, bombs.length + 1);
            bombs[bombs.length - 1] = (Bomb) battleship.shootThunder();
        }
    }

    public void bombBangAction() {
        for (Bomb bomb : bombs) {
            for (SeaObject submarine : submarines) {
                if (bomb.isLive() && submarine.isLive() && submarine.isHit(bomb)) {
                    bomb.goDead();
                    submarine.goDead();
                    if (submarine instanceof EnemyScore) {
                        EnemyScore es = (EnemyScore) submarine;
                        score += es.getScore();
                    }
                    if (submarine instanceof EnemyLife) {
                        EnemyLife el = (EnemyLife) submarine;
                        int num = el.getLife();
                        battleship.addLift(num);
                    }
                }
            }
        }
        for (SeaObject thunder : thunders) {
            if (thunder.isLive() && battleship.isLive() && battleship.isHit(thunder)) {
                thunder.goDead();
                battleship.lossLife(1);
                if (battleship.getLife() <= 0) {
                    state = GAME_OVER;
                }
            }
        }

    }

    private void stepAction() {
        seaFirst.step();
        if (seaFirst.x >= WIDTH) seaFirst.x = -WIDTH;
        seaDouble.step();
        if (seaDouble.x >= WIDTH) seaDouble.x = -WIDTH;
        for (SeaObject submarine : submarines) {
            if (submarine.isLive())
                submarine.step();
        }
        for (SeaObject thunders : thunders) {
            if (thunders.isLive())
                thunders.step();
        }
        battleship.step();
        for (SeaObject bomb : bombs) {
            if (bomb != null)
                bomb.step();
        }
    }

    void action() {

        File file = new File("submarine.xw");
        if (file.exists()) {
            int r = JOptionPane.showConfirmDialog(
                    World.this,
                    "是否载入上次游戏"
            );

            if (r == JOptionPane.YES_OPTION) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    GameInfo gameInfo = (GameInfo) ois.readObject();
                    submarines = gameInfo.getSubmarines();
                    thunders = gameInfo.getThunders();
                    bombs = gameInfo.getBombs();
                    battleship = gameInfo.getBattleship();
                    thundersEnterIndex = gameInfo.getThundersEnterIndex();
                    submarineEnterIndex = gameInfo.getSubmarineEnterIndex();
                    score = gameInfo.getScore();
                    ois.close();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(
                        World.this,
                        "载入成功!"
                );
                state = RUNNING;
            }

        }

        KeyAdapter adapter = new KeyAdapter() { //创建键盘侦听器匿名子类
            @Override
            public void keyReleased(KeyEvent e) { //重写实现按下后要处理的事件逻辑
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> battleship.moveLeft();
                    case KeyEvent.VK_D -> battleship.moveRight();
                    case KeyEvent.VK_W -> battleship.moveUp();
                    case KeyEvent.VK_S -> battleship.moveDown();
                    case KeyEvent.VK_V -> {
                        for (SeaObject submarine : submarines
                        ) {
                            if (submarine instanceof EnemyScore) {
                                EnemyScore es = (EnemyScore) submarine;
                                score += es.getScore();
                            }
                            if (submarine instanceof EnemyLife) {
                                EnemyLife el = (EnemyLife) submarine;
                                int num = el.getLife();
                                battleship.addLift(num);
                            }
                        }
                        submarines = new SeaObject[0];
                        thunders = new SeaObject[0];
                    }
                    case KeyEvent.VK_P -> {
                        if (state == RUNNING) {
                            state = PAUSE;
                            int r = JOptionPane.showConfirmDialog(
                                    World.this,
                                    "是否保存游戏"
                            );//弹框
                            if (r == JOptionPane.YES_OPTION) {
                                GameInfo gameInfo = new GameInfo(submarines, thunders, bombs, battleship,
                                        submarineEnterIndex, thundersEnterIndex, score);
                                try {
                                    FileOutputStream fis = new FileOutputStream("submarine.xw");
                                    ObjectOutputStream oos = new ObjectOutputStream(fis);
                                    oos.writeObject(gameInfo);
                                    oos.close();
                                } catch (IOException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(
                                        World.this,
                                        "存档成功!"
                                );
                            }
                            state = RUNNING;
                        }
                    }
                    case KeyEvent.VK_SPACE -> {
                        switch (state) {
                            case START -> state = RUNNING;
                            case GAME_OVER -> { // 游戏结束，清理现场
                                submarines = new SeaObject[0];
                                bombs = new Bomb[0];
                                battleship = new Battleship();
                                thunders = new SeaObject[0];
                                score = 0;
                                state = START;
                            }
                        }
                        bombEnterAction();
                    }
                }
            }
        };
        this.addKeyListener(adapter); //将键盘的侦听的具体子类,添加到侦测组件中

        java.util.Timer timer = new Timer(); //创建具体的定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (state != GAME_OVER && state != START && state != PAUSE) {
                    submarineEnterAction(); //调用了潜艇入场的方法
                    thunderEnterAction(); //调用了雷入场的方法
                    stepAction(); //调用自动移动的方法
                    outOfBoundsAction(); //调用删除越界海洋对象的方法
                    bombBangAction();
                    repaint(); //系统提供的刷新绘制的方法
                }
            }
        };
        timer.schedule(task, 1000, 10);
    }

    /**
     * 绘制窗口的代码
     */
    void paintWorld() {
        JFrame frame = new JFrame(); //创建窗口
        setFocusable(true); //可聚焦的
        frame.add(this); //把面板添加到窗口中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口结束程序
        frame.setSize(WIDTH, HEIGHT); //窗口尺寸
        frame.setLocationRelativeTo(null); //窗口居中呈现
        frame.setVisible(true); //可视化

    }

    private void paintState(Graphics g) {
        ImageIcon s;
        switch (state) {
            case START -> {
                s = ImageResources.start; //获取当前对象的图片
                s.paintIcon(null, g, 0, 0); //绘制当前对象的图片
            }
            case GAME_OVER -> {
                s = ImageResources.gameover;
                s.paintIcon(null, g, 0, 0);
            }
        }
    }

    //重写父类绘制的方法,实现我们自己项目绘制的逻辑
    @Override
    public void paint(Graphics g) { //g是画笔
        seaFirst.paintImage(g);
        seaDouble.paintImage(g);

//        ImageResources.sea.paintIcon(null,g,0,0); //背景图片

        battleship.paintImage(g);

        for (Bomb bomb : bombs) {
            bomb.paintImage(g);
        }

        for (SeaObject submarine : submarines) {
            submarine.paintImage(g);
        }

        for (SeaObject thunder : thunders) {
            thunder.paintImage(g);
        }
        paintState(g); // 画游戏状态

        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("分数:" + score, 200, 50);
        g.drawString("生命:" + battleship.getLife(), 400, 50);
    }
}