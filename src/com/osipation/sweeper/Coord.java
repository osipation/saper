package com.osipation.sweeper;

public class Coord {//создаем координаты
    public int x;
    public int y;

    public Coord (int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {//сравнение на совпадение
        if (o instanceof Coord){
            Coord to = (Coord) o;
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}
