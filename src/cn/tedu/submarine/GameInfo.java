package cn.tedu.submarine;

import java.io.Serializable;

/**
 * 保存游戏信息
 */
public class GameInfo implements Serializable {
    public static final long serialVersionUID = 1L;
    private SeaObject[] submarines;
    private SeaObject[] thunders;
    private Bomb[] bombs;
    private Battleship battleship;

    private int submarineEnterIndex;
    private int thundersEnterIndex;
    private int score;

    public GameInfo(SeaObject[] submarines, SeaObject[] thunders, Bomb[] bombs, Battleship battleship,
                    int submarineEnterIndex, int thundersEnterIndex, int score) {
        this.submarines = submarines;
        this.thunders = thunders;
        this.bombs = bombs;
        this.battleship = battleship;
        this.submarineEnterIndex = submarineEnterIndex;
        this.thundersEnterIndex = thundersEnterIndex;
        this.score = score;
    }

    public SeaObject[] getSubmarines() {
        return submarines;
    }

    public void setSubmarines(SeaObject[] submarines) {
        this.submarines = submarines;
    }

    public SeaObject[] getThunders() {
        return thunders;
    }

    public void setThunders(SeaObject[] thunders) {
        this.thunders = thunders;
    }

    public Bomb[] getBombs() {
        return bombs;
    }

    public void setBombs(Bomb[] bombs) {
        this.bombs = bombs;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    public int getSubmarineEnterIndex() {
        return submarineEnterIndex;
    }

    public void setSubmarineEnterIndex(int submarineEnterIndex) {
        this.submarineEnterIndex = submarineEnterIndex;
    }

    public int getThundersEnterIndex() {
        return thundersEnterIndex;
    }

    public void setThundersEnterIndex(int thundersEnterIndex) {
        this.thundersEnterIndex = thundersEnterIndex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
