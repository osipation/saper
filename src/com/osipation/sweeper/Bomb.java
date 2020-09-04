package com.osipation.sweeper;

import java.util.function.BiPredicate;

class Bomb {
    private Martix bombMap;
    private int totalBombs;//кол-во бомб

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start() {
        bombMap = new Martix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)//создание всех бомб
            placeBomb();
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount(){//устанавливаем ограничение бомб на половину клеток
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb() {//установка бомб и чисел вокруг вокруг
        while (true) {//ограничение для установки бомб в одну клетку
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }


    private void incNumbersAroundBomb (Coord coord){
        for (Coord around : Ranges.getCoordsAround(coord) )
            if (Box.BOMB!=bombMap.get(around))
            bombMap.set(around,bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
