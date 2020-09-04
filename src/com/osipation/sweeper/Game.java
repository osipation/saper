package com.osipation.sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;
    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {//конструктор
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {

        bomb.start();
        flag.start();
        state = GameState.PLAYED;//начало с состояния игры
    }

    public Box getBox(Coord coord) {//что изобразить в том или ином месте экрана
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else  return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) return;
        openBox (coord);
        checkWinner();
    }

    private void checkWinner(){//победа если закрытых ячеек столько же сколько бомб
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;

    }

    private void openBox(Coord coord){
        switch (flag.get(coord)){
            case OPENED: setOpenedToCloseBoxesAroundNumber(coord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord)){
                    case ZERO: openBoxesAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default: flag.setOpenedToBox(coord);
                    return;
                }
        }
    }

    private void setOpenedToCloseBoxesAroundNumber(Coord coord) {//открывает закрытые ячейки вокруг числа при нажатии на пустую
        if (bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                for (Coord around : Ranges.getCoordsAround(coord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coord bombed) {//открытие всех бомб при проигрыше
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord: Ranges.getAllCoords()){
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBomb (coord);
            else flag.setNoBombToFlagedSafeBox (coord);


        }
    }

    private void openBoxesAround(Coord coord) {//рекурсивное открытие путых ячеек вокруг
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
        flag.toggleFlagToBox(coord);
    }

    private boolean gameOver() {//при окончании игры при след нажатии начинаем игру заново
        if (state == GameState.PLAYED)
            return false;
        else start();
        return true;
    }
}
